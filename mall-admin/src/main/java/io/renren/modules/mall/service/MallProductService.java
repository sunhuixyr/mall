package io.renren.modules.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.mall.entity.MallProductEntity;

import java.util.Map;

/**
 * 商品管理
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-03-24 14:13:50
 */
public interface MallProductService extends IService<MallProductEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

