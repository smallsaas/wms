package com.jfeat.am.module.warehouse.api.crud;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.warehouse.services.persistence.model.Suppliers;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.am.module.warehouse.services.domain.dao.QuerySuppliersDao;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.warehouse.services.domain.service.SuppliersService;
import com.jfeat.am.module.warehouse.services.domain.model.SuppliersRecord;
import com.jfeat.am.module.warehouse.services.domain.model.SuppliersModel;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-22
 */
@RestController
@Api("WMS-供应商")
@RequestMapping("/api/wms/suppliers")
public class SuppliersEndpoint   {


    @Resource
    SuppliersService suppliersService;

    @Resource
    QuerySuppliersDao querySuppliersDao;

    //@Resource
    //UserService userService;

    @BusinessLog(name = "Suppliers", value = "create Suppliers")
    @PostMapping
    @ApiOperation(value = "新建供应商",response = SuppliersModel.class)
    public Tip createSuppliers(@RequestBody SuppliersModel entity) {

        Integer affected = 0;
        try {
            affected = suppliersService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(5000,"供应商编号重复");
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查看供应商",response = SuppliersModel.class)
    public Tip getSuppliers(@PathVariable Long id) {
        return SuccessTip.create(suppliersService.retrieveMaster(id));
    }

    @BusinessLog(name = "Suppliers", value = "update Suppliers")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改供应商信息",response = SuppliersModel.class)
    public Tip updateSuppliers(@PathVariable Long id, @RequestBody SuppliersModel entity) {
        entity.setId(id);
        return SuccessTip.create(suppliersService.updateMaster(entity));
    }

    @BusinessLog(name = "Suppliers", value = "update Suppliers")
    @PutMapping("/{id}/forbidden")
    @ApiOperation(value = "设为禁用/正常",response = Suppliers.class)
    public Tip forbiddenSuppliers(@PathVariable Long id) {
        //User user = userService.getById(JWTKit.getUserId(getHttpServletRequest()));
        //return SuccessTip.create(suppliersService.changeSupplierStatus(id,user));
        return SuccessTip.create(suppliersService.changeSupplierStatus(id));
    }
    @BusinessLog(name = "Suppliers", value = "delete Suppliers")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除供应商",response = SuppliersModel.class)

    public Tip deleteSuppliers(@PathVariable Long id) {
        return SuccessTip.create(suppliersService.deleteMaster(id));
    }

    @GetMapping("/{id}/products")
    @ApiOperation(value = "供应商提供的货物",response = SuppliersModel.class)
    public Tip allProductsSuppliersProvided(@PathVariable Long id) {
        return SuccessTip.create(suppliersService.allProductsSuppliersProvide(id));
    }

    @GetMapping
    @ApiOperation(value = "供应商列表",response = SuppliersModel.class)
    public Tip querySupplierses(Page<SuppliersRecord> page,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "id", required = false) Long id,
                                @RequestParam(name = "supplierName", required = false) String supplierName,
                                @RequestParam(name = "supplierCode", required = false) String supplierCode,
                                @RequestParam(name = "supplierPCD", required = false) String supplierPCD,
                                @RequestParam(name = "supplierAddress", required = false) String supplierAddress,
                                @RequestParam(name = "supplierPostcode", required = false) String supplierPostcode,
                                @RequestParam(name = "supplierContactName", required = false) String supplierContactName,
                                @RequestParam(name = "supplierContactPhone", required = false) String supplierContactPhone,
                                @RequestParam(name = "supplierContactFax", required = false) String supplierContactFax,
                                @RequestParam(name = "supplierContactEmail", required = false) String supplierContactEmail,
                                @RequestParam(name = "supplierContactPosition", required = false) String supplierContactPosition,
                                @RequestParam(name = "supplier_contactCellPhone", required = false) String supplier_contactCellPhone,
                                @RequestParam(name = "supplierAccountName", required = false) String supplierAccountName,
                                @RequestParam(name = "supplierAccountBank", required = false) String supplierAccountBank,
                                @RequestParam(name = "supplierAccountBankNo", required = false) Long supplier_accountBankNo,
                                @RequestParam(name = "supplierInvoiceTitle", required = false) String supplierInvoiceTitle,
                                @RequestParam(name = "supplierStatus", required = false) String supplierStatus,
                                @RequestParam(name = "supplierNote", required = false) String supplierNote,
                                @RequestParam(name = "supplierRegisterTime", required = false) Date supplierRegisterTime,
                                @RequestParam(name = "field1", required = false) String field1,
                                @RequestParam(name = "field2", required = false) String field2,
                                @RequestParam(name = "orderBy", required = false) String orderBy,
                                @RequestParam(name = "sort", required = false) String sort) {
        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String pattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(pattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        SuppliersRecord record = new SuppliersRecord();
        record.setId(id);
        record.setSupplierName(supplierName);
        record.setSupplierCode(supplierCode);
        record.setSupplierPCD(supplierPCD);
        record.setSupplierAddress(supplierAddress);
        record.setSupplierPostcode(supplierPostcode);
        record.setSupplierContactName(supplierContactName);
        record.setSupplierContactPhone(supplierContactPhone);
        record.setSupplierContactFax(supplierContactFax);
        record.setSupplierContactEmail(supplierContactEmail);
        record.setSupplierContactPosition(supplierContactPosition);
        record.setSupplierContactCellPhone(supplier_contactCellPhone);
        record.setSupplierAccountName(supplierAccountName);
        record.setSupplierAccountBank(supplierAccountBank);
        record.setSupplierAccountBankNo(supplier_accountBankNo);
        record.setSupplierInvoiceTitle(supplierInvoiceTitle);
        record.setSupplierStatus(supplierStatus);
        record.setSupplierNote(supplierNote);
        record.setSupplierRegisterTime(supplierRegisterTime);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(querySuppliersDao.findSuppliersPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}
