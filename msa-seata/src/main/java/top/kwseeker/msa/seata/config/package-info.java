package top.kwseeker.msa.seata.config;

/*
* 这里动态数据源配置依赖 Spring JdbcTemplate 的 AbstractRoutingDataSource.
*
* 数据源查找流程：
* 1) 获取线程ThreadLocal中的lookupKey,
* 2) 根据lookupKey查Map<Object, Object> targetDataSources容器中此key对应的数据源
* 具体源码流程：TODO
* */