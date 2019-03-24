package io.renren.modules.mall.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.mall.entity.MallProductEntity;
import io.renren.modules.mall.service.MallProductService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 商品管理
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-03-24 14:13:50
 */
@RestController
@RequestMapping("mall/mallproduct")
public class MallProductController extends AbstractController{
    @Autowired
    private MallProductService mallProductService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("mall:mallproduct:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = mallProductService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{productId}")
    @RequiresPermissions("mall:mallproduct:info")
    public R info(@PathVariable("productId") Long productId){
        MallProductEntity mallProduct = mallProductService.getById(productId);

        return R.ok().put("mallProduct", mallProduct);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("mall:mallproduct:save")
    public R save(@RequestBody MallProductEntity mallProduct){
    	SysUserEntity sysUserEntity = super.getUser();
    	mallProduct.setCreateUser(sysUserEntity.getUsername());
    	mallProduct.setUpdateUser(sysUserEntity.getUsername());
    	mallProduct.setStatus(Constant.STATUS_VALID);
    	mallProduct.setOrderNum(Constant.OREDER_NUM_DEFAULT);
        mallProductService.save(mallProduct);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("mall:mallproduct:update")
    public R update(@RequestBody MallProductEntity mallProduct){
        ValidatorUtils.validateEntity(mallProduct);
        mallProductService.updateById(mallProduct);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("mall:mallproduct:delete")
    public R delete(@RequestBody Long[] productIds){
        mallProductService.removeByIds(Arrays.asList(productIds));

        return R.ok();
    }

}
