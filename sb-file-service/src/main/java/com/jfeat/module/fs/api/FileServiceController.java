package com.jfeat.module.fs.api;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.config.properties.AmProperties;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.core.support.StrKit;
import com.jfeat.am.core.util.ImageUtil;
import com.jfeat.module.fs.service.LoadFileCodeService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by jackyhuang on 2017/7/4.
 */
@RestController
public class FileServiceController extends BaseController {

    @Autowired
    private AmProperties amProperties;

    @Autowired
    LoadFileCodeService loadFileCodeService;

    @GetMapping("/api/fs/dlcode")
    public Tip getCode(@RequestParam String name) {
        String code = loadFileCodeService.genAndGetCode(name);
        return SuccessTip.create(code);
    }

    /**
     * form-data 方式上传图片
     * @param picture
     * @return
     */
    @ApiOperation("multipart方式上传图片")
    @PostMapping("/api/fs/uploadx")
    @ResponseBody
    public Tip formUpload(@RequestHeader("authorization") String token,
                          @RequestParam(name = "blur", defaultValue = "false") Boolean blur,
                          @RequestPart("file") MultipartFile picture) {
        String originalFileName = picture.getOriginalFilename();
        String extensionName= getExtensionName(originalFileName);
        String pictureName = UUID.randomUUID().toString() + "." + extensionName;
        String blurryName = "";
        try {
            String fileSavePath = getFileUploadPath();
            File target = new File(fileSavePath + pictureName);
            target.setReadable(true);
            FileUtils.copyInputStreamToFile(picture.getInputStream(), target);
            logger.info("file uploaded to: {}", target.getAbsolutePath());
            if (blur) {
                File blurryFile = ImageUtil.gaos(target);
                blurryFile.setReadable(true);
                blurryName = blurryFile.getName();
            }
        } catch (Exception e) {
            throw new BusinessException(BusinessCode.UploadFileError);
        }
        return SuccessTip.create(FileInfo.create(getFileHost(), pictureName, blurryName));
    }

    /**
     * 数据格式
     * data:image/jpeg;base64,/9j/4AAQSkZJRgABAQ
     * @return
     * @throws IOException
     */
    @ApiOperation("Base64格式上传图片")
    @PostMapping("/api/fs/upload64")
    @ResponseBody
    public Tip base64Upload(@RequestHeader("authorization") String token,
                            @RequestParam(name = "blur", defaultValue = "false") Boolean blur) throws IOException {
        String base64Data = IOUtils.toString(getHttpServletRequest().getInputStream(), "UTF-8");
        if (StrKit.isBlank(base64Data)) {
            throw new BusinessException(BusinessCode.UploadFileError);
        }

        String dataPrix = "";
        String data = "";
        String[] d = base64Data.split("base64,");
        if (d != null && d.length == 2) {
            dataPrix = d[0];
            data = d[1];
        } else {
            throw new BusinessException(BusinessCode.UploadFileError);
        }

        String suffix = "";
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
            suffix = ".png";
        } else {
            throw new BusinessException(BusinessCode.UploadFileError);
        }

        byte[] dataBytes = Base64Utils.decodeFromString(data);

        String pictureName = UUID.randomUUID().toString() + suffix;
        String blurryName = "";
        try{
            String fileSavePath = getFileUploadPath();
            File target = new File(fileSavePath, pictureName);
            target.setReadable(true);
            FileUtils.writeByteArrayToFile(target, dataBytes);
            logger.info("file uploaded to: {}", target.getAbsolutePath());
            if (blur) {
                File blurryFile = ImageUtil.gaos(target);
                blurryFile.setReadable(true);
                blurryName = blurryFile.getName();
            }
        }catch(Exception ee){
            throw new BusinessException(BusinessCode.UploadFileError);
        }

        return SuccessTip.create(FileInfo.create(getFileHost(), pictureName, blurryName));
    }

    @ApiOperation("上传文件")
    @PostMapping("/api/fs/uploadfile")
    @ResponseBody
    public Tip FileUpload(@RequestHeader("authorization") String token,
                          @RequestPart("file") MultipartFile file) throws IOException{
        if (file.isEmpty()) {
            throw new RuntimeException("file is empty");
        }
        String originalFileName = file.getOriginalFilename();
        String extensionName= getExtensionName(originalFileName);
        Long fileSize = file.getSize();
        String fileName = UUID.randomUUID().toString() + "." + extensionName;
        String path = null;
        try {
            String fileSavePath = getFileUploadPath();
            File target = new File(fileSavePath + fileName);
            path = target.getCanonicalPath();
            target.setReadable(true);
            FileUtils.copyInputStreamToFile(file.getInputStream(), target);
            logger.info("file uploaded to: {}", target.getAbsolutePath());
        } catch (Exception e) {
            throw new BusinessException(BusinessCode.UploadFileError);
        }
        return SuccessTip.create(FileInfo.create(getFileHost(), fileName, extensionName, originalFileName, fileSize, path));
    }

    @ApiOperation("下载文件")
    @GetMapping("/api/pub/fs/loadfile")
    @ResponseBody
    public void loadFile(@RequestParam(required = true) String name,
                         @RequestParam(required = true) String code) throws IOException {
        if (loadFileCodeService.checkCode(code) == false) {
            throw new BusinessException(BusinessCode.BadRequest);
        }

        //获得rootPath
        String rootPath = getFileUploadPath();
        //拼接绝对路径(目录)
        String absolutePath = rootPath + name;
        //获得资源对象
        FileSystemResource fsr = new FileSystemResource(absolutePath);

        //输出文件
        final int buffInt = 1024;
        byte[] buff = new byte[buffInt];
        OutputStream os = null;
        BufferedInputStream bis = null;
        try { //NOSONAR
            HttpServletResponse response = getHttpServletResponse();
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; " + name);
            os = response.getOutputStream();
            bis = new BufferedInputStream(fsr.getInputStream());
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            if (bis != null) {
                bis.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    private String getFileHost() {
        String fileHost = amProperties.getFileHost();
        String host = fileHost;
        String tenant = JWTKit.getTenantId(getHttpServletRequest()) + "";
        if (!fileHost.endsWith("/")) {
            host = host + "/" + tenant;
        }
        else {
            host = host + tenant;
        }
        return host;
    }

    private String getFileUploadPath() {
        String fileSavePath = amProperties.getFileUploadPath();
        String uploadPath = fileSavePath;
        String tenant = JWTKit.getTenantId(getHttpServletRequest()) + "";
        if (!fileSavePath.endsWith(File.separator)) {
            uploadPath = uploadPath + File.separator + tenant + File.separator;
        }
        else {
            uploadPath = uploadPath + tenant + File.separator;
        }

        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return uploadPath;
    }

    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
}
