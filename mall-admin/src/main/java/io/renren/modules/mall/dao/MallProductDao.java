package io.renren.modules.mall.dao;

import io.renren.modules.mall.entity.MallProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品管理
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-03-24 14:13:50
 */
@Mapper
public interface MallProductDao extends BaseMapper<MallProductEntity> {
	
}
