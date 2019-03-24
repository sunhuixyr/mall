package io.renren.modules.mall.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.mall.dao.MallProductDao;
import io.renren.modules.mall.entity.MallProductEntity;
import io.renren.modules.mall.service.MallProductService;


@Service("mallProductService")
public class MallProductServiceImpl extends ServiceImpl<MallProductDao, MallProductEntity> implements MallProductService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MallProductEntity> page = this.page(
                new Query<MallProductEntity>().getPage(params),
                new QueryWrapper<MallProductEntity>()
        );

        return new PageUtils(page);
    }
    
    

}
