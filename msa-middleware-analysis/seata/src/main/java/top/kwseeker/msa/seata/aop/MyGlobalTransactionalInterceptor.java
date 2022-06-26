package top.kwseeker.msa.seata.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 往sql方法上添加的事务增强逻辑
 */
public class MyGlobalTransactionalInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Class<?> targetClass = methodInvocation.getThis() != null ? AopUtils.getTargetClass(methodInvocation.getThis()) : null;
        Method specificMethod = ClassUtils.getMostSpecificMethod(methodInvocation.getMethod(), targetClass);
        if (!specificMethod.getDeclaringClass().equals(Object.class)) {
            final Method method = BridgeMethodResolver.findBridgedMethod(specificMethod);
            final MyGlobalTransactional globalTransactionalAnnotation =
                    getAnnotation(method, targetClass, MyGlobalTransactional.class);

            if (globalTransactionalAnnotation != null) {
                System.out.println("获取事务、事务信息、传播类型、开启事务...");

                Object rs;
                try {
                    rs = methodInvocation.proceed();
                } catch (Throwable ex) {
                    System.out.println("执行回滚...");
                    throw ex;
                }

                System.out.println("提交事务...");
                return rs;
            }
        }
        return methodInvocation.proceed();
    }

    public <T extends Annotation> T getAnnotation(Method method, Class<?> targetClass, Class<T> annotationClass) {
        return Optional.ofNullable(method).map(m -> m.getAnnotation(annotationClass))
                .orElse(Optional.ofNullable(targetClass).map(t -> t.getAnnotation(annotationClass)).orElse(null));
    }
}
