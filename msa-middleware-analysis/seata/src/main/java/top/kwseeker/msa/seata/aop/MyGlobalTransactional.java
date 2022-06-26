package top.kwseeker.msa.seata.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited
public @interface MyGlobalTransactional {

    /**
     * Global transaction timeoutMills in MILLISECONDS.
     * If client.tm.default-global-transaction-timeout is configured, It will replace the DefaultValues.DEFAULT_GLOBAL_TRANSACTION_TIMEOUT.
     *
     * @return timeoutMills in MILLISECONDS.
     */
    int timeoutMills() default 60000;

    /**
     * Given name of the global transaction instance.
     *
     * @return Given name.
     */
    String name() default "";

    /**
     * roll back for the Class
     */
    Class<? extends Throwable>[] rollbackFor() default {};

    /**
     * roll back for the class name
     */
    String[] rollbackForClassName() default {};

    /**
     * not roll back for the Class
     */
    Class<? extends Throwable>[] noRollbackFor() default {};

    /**
     * not roll back for the class name
     */
    String[] noRollbackForClassName() default {};

    ///**
    // * the propagation of the global transaction
    // * @return
    // */
    //Propagation propagation() default Propagation.REQUIRED;

    /**
     * customized global lock retry internal(unit: ms)
     * you may use this to override global config of "client.rm.lock.retryInterval"
     * note: 0 or negative number will take no effect(which mean fall back to global config)
     */
    int lockRetryInternal() default 0;

    /**
     * customized global lock retry times
     * you may use this to override global config of "client.rm.lock.retryTimes"
     * note: negative number will take no effect(which mean fall back to global config)
     */
    int lockRetryTimes() default -1;
}