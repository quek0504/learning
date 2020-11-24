package com.cwquek.ecommerce.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

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
	private Integer showStatus;
	/**
	 * sort
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

}
