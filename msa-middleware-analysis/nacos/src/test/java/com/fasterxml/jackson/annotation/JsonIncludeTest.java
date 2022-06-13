package com.fasterxml.jackson.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * jackson @JsonInclude 测试
 *
 * @JsonInclude 用法 (可以用在 {ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER} )
 *  ALWAYS          默认策略，任何情况都执行序列化
 *  NON_NULL        值为null的字段不序列化
 *  NON_ABSENT      当实例化的对象有Optional或AtomicReference类型的成员变量时，如果Optional引用的实例为空，用NON_ABSENT能使该字段不做序列化
 *  NON_EMPTY       null、空字符串、空集合、空数组、Optional类型其引用为空、AtomicReference类型的其引用为空　字段不序列化
 *  NON_DEFAULT     值为默认值的字段不序列化
 *  CUSTOM          自定义过滤条件排除属性，此时要指定valueFilter属性，该属性对应一个类，用来自定义判断被JsonInclude修饰的字段是否序列化
 *  USE_DEFAULTS    当JsonInclude在类和字段上都有时，优先使用字段上的注解，此时如果在序列化的get方法上使用了JsonInclude，并设置为USE_DEFAULTS，就会使用类注解的设置
 */
public class JsonIncludeTest {

    @Test
    public void testJsonInclude() throws JsonProcessingException {
        //lastBeat = 1654644820891
        //mockValid = false
        //marked = false
        //tenant = null
        //app = "DEFAULT"
        //instanceId = "192.168.2.169#9002#nj#DEFAULT_GROUP@@stock-service"
        //ip = "192.168.2.169"
        //port = 9002
        //weight = 1.0
        //healthy = true
        //enabled = true
        //ephemeral = true
        //clusterName = "nj"
        //serviceName = "DEFAULT_GROUP@@stock-service"
        //metadata = {LinkedHashMap@12246}  size = 2
        Instance instance = new Instance();
        instance.setLastBeat(1654644820891L);
        instance.setApp("DEFAULT");
        instance.setInstanceId("192.168.2.169#9002#nj#DEFAULT_GROUP@@stock-service");   //IP#端口＃集群＃分组＃服务ID
        instance.setIp("192.168.2.169");
        instance.setPort(9002);
        instance.setEphemeral(true);
        instance.setClusterName("nj");
        instance.setServiceName("DEFAULT_GROUP@@stock-service");    //分组@@服务名
        Map<String, String> metadataMap = new LinkedHashMap<>();
        metadataMap.put("name", "Arvin");
        instance.setMetadata(metadataMap);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);  //美化输出
        System.out.println(mapper.writeValueAsString(instance));
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class Instance extends PojoInstance {
        private volatile long lastBeat = System.currentTimeMillis();
        @JsonIgnore
        private volatile boolean mockValid = false;
        private volatile boolean marked = false;
        private String tenant;
        private String app;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class PojoInstance {
        /**
         * 实例的唯一ID
         */
        private String instanceId;

        /**
         * instance ip.
         */
        private String ip;

        /**
         * instance port.
         */
        private int port;

        /**
         * instance weight.
         */
        private double weight = 1.0D;

        /**
         * instance health status.
         */
        private boolean healthy = true;

        /**
         * If instance is enabled to accept request.
         */
        private boolean enabled = true;

        /**
         * If instance is ephemeral.
         *
         * @since 1.0.0
         */
        private boolean ephemeral = true;

        /**
         * cluster information of instance.
         */
        private String clusterName;

        /**
         * Service information of instance.
         */
        private String serviceName;

        /**
         * user extended attributes.
         */
        private Map<String, String> metadata = new HashMap<String, String>();
    }
}
