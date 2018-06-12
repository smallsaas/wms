package com.jfeat.am.module.log.aop;

import com.alibaba.fastjson.JSON;
import com.jfeat.am.core.shiro.ShiroKit;
import com.jfeat.am.core.shiro.ShiroUser;
import com.jfeat.am.module.log.LogManager;
import com.jfeat.am.module.log.LogTaskFactory;
import com.jfeat.am.module.log.annotation.BusinessLog;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by jackyhuang on 2017/11/16.
 */
@Aspect
@Component
public class LogAop {

    private Logger log = Logger.getLogger(this.getClass());

    @Pointcut(value = "@annotation(com.jfeat.am.module.log.annotation.BusinessLog)")
    public void cutService() {
    }

    @Around("cutService()")
    public Object recordLog(ProceedingJoinPoint point) throws Throwable {

        //先执行业务
        Object result = point.proceed();

        try {
            handle(point,result);
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    private void handle(ProceedingJoinPoint point,Object result) throws Exception {

        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();

        //如果当前用户未登录，不做日志
        ShiroUser user = ShiroKit.getUser();
        if (null == user) {
            return;
        }

        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        //获取操作名称
        BusinessLog annotation = currentMethod.getAnnotation(BusinessLog.class);
        String businessName = annotation.name();
        String businessValue = annotation.value();


        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(JSON.toJSONString(param));
            sb.append(" & ");
        }

        LogManager.me().executeLog(LogTaskFactory.businessLog(user.getId(),
                user.getName(),
                businessName + "-" + businessValue,
                className,
                methodName,
                sb.toString(),
                result.toString())
                );
    }
}
