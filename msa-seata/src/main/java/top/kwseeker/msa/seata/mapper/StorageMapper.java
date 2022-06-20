package top.kwseeker.msa.seata.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.kwseeker.msa.seata.entity.Storage;

@Repository
public interface StorageMapper {
    
    /**
     * 获取库存
     * @param commodityCode 商品编号
     */
    @Select("SELECT id,commodity_code,count FROM storage_tbl WHERE commodity_code = #{commodityCode}")
    Storage findByCommodityCode(@Param("commodityCode") String commodityCode);
    
    /**
     * 扣减库存
     * @param commodityCode 商品编号
     * @param count  要扣减的库存
     */
    @Update("UPDATE storage_tbl SET count = count - #{count} WHERE commodity_code = #{commodityCode}")
    int reduceStorage(@Param("commodityCode") String commodityCode, @Param("count") Integer count);
}
