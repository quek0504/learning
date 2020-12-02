package com.cwquek.ecommerce.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 3 levels classification of products
 * 
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 03:42:00
 */
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * category id
	 */
	@TableId
	private Long catId;
	/**
	 * category name
	 */
	private String name;
	/**
	 * parent cid
	 */
	private Long parentCid;
	/**
	 * level
	 */
	private Integer catLevel;
	/**
	 * [0-hide, 1-show]
	 */
	// mybatis plus annotation for logical delete
	@TableLogic(value = "1", delval = "0")
	private Integer showStatus;
	/**
	 * sort (assign Integer value to entity, 0 appears first, 1 appears next and so on)
	 */
	private Integer sort;
	/**
	 * icon uri
	 */
	private String icon;
	/**
	 * unit of measurement
	 */
	private String productUnit;
	/**
	 * count
	 */
	private Integer productCount;

	// mybatis plus annotation - exist false meaning this field is not from database
	@TableField(exist=false)
	private List<CategoryEntity> children;
}
