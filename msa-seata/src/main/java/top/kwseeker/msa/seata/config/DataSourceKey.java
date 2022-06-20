package top.kwseeker.msa.seata.config;

import lombok.Getter;

@Getter
public enum DataSourceKey {
    /**
     * Order data source key.
     */
    ORDER,
    /**
     * Storage data source key.
     */
    STORAGE,
    /**
     * Account data source key.
     */
    ACCOUNT,
}