package top.kwseeker.msa.seata.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * AbstractAutoProxyCreator 最终实现 BeanPostProcessor, 所以这个Bean会被加到 AbstractBeanFactory的 List<BeanPostProcessor> beanPostProcessors
 * 然后 Bean 初始化后走到 postProcessAfterInitialization 会挨个执行 beanPostProcessors 的 wrapIfNecessary() 方法
 */
@Component
public class MyGlobalTransactionScanner extends AbstractAutoProxyCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyGlobalTransactionScanner.class);

    private MethodInterceptor interceptor;
    private MethodInterceptor globalTransactionalInterceptor;

    /**
     * 如果bean的Class
     */
    @Override
    protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
        try {
            System.out.println("exec wrapIfNecessary ...");
            interceptor = null;

            Class<?> serviceInterface = MySpringProxyUtils.findTargetClass(bean);
            Class<?>[] interfacesIfJdk = MySpringProxyUtils.findInterfaces(bean);
            if (!existsAnnotation(new Class[]{serviceInterface})
                    && !existsAnnotation(interfacesIfJdk)) {
                return bean;
            }

            if (interceptor == null) {
                if (globalTransactionalInterceptor == null) {
                    globalTransactionalInterceptor = new MyGlobalTransactionalInterceptor();
                    //ConfigurationCache.addConfigListener(
                    //        ConfigurationKeys.DISABLE_GLOBAL_TRANSACTION,
                    //        (ConfigurationChangeListener)globalTransactionalInterceptor);
                }
                interceptor = globalTransactionalInterceptor;
            }

            LOGGER.info("Bean[{}] with name [{}] would use interceptor [{}]", bean.getClass().getName(), beanName, interceptor.getClass().getName());
            if (!AopUtils.isAopProxy(bean)) {
                bean = super.wrapIfNecessary(bean, beanName, cacheKey);
            } else {
                AdvisedSupport advised = MySpringProxyUtils.getAdvisedSupport(bean);
                Advisor[] advisor = buildAdvisors(beanName, getAdvicesAndAdvisorsForBean(null, null, null));
                for (Advisor avr : advisor) {
                    advised.addAdvisor(0, avr);
                }
            }

            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     *
     */
    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource targetSource) throws BeansException {
        return new Object[]{interceptor};
    }

    /**
     * 原GlobalTransactionScanner不仅检查了@GlobalTransactional注解还检查了@GlobalLock注解(只要有一种就返回true)，这里简化只判断@MyGlobalTransactional
     */
    private boolean existsAnnotation(Class<?>[] classes) {
        if (classes != null && classes.length > 0) {
            for (Class<?> clazz : classes) {
                if (clazz == null) {
                    continue;
                }
                //先看类上是否有全局事务注解
                MyGlobalTransactional trxAnno = clazz.getAnnotation(MyGlobalTransactional.class);
                if (trxAnno != null) {
                    return true;
                }
                //没有再看类方法上有没有
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    trxAnno = method.getAnnotation(MyGlobalTransactional.class);
                    if (trxAnno != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
