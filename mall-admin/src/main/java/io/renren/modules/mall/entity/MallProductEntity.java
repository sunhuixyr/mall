package io.renren.modules.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品管理
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-03-24 14:13:50
 */
@Data
@TableName("mall_product")
public class MallProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long productId;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品类型
	 */
	private String productType;
	/**
	 * 摘要
	 */
	private String productTitle;
	/**
	 * 图片
	 */
	private String image;
	/**
	 * 详细描述
	 */
	private String productDesc;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 状态，0-无效,1-有效
	 */
	private String status;
	/**
	 * 起始日期，yyyyMMdd
	 */
	private String startDate;
	/**
	 * 结束日期，yyyyMMdd
	 */
	private String endDate;
	/**
	 * 排序,默认9999
	 */
	private Integer orderNum;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新人
	 */
	private String updateUser;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
