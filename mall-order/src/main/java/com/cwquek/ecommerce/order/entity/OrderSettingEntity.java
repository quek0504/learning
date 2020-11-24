package com.cwquek.ecommerce.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 04:26:44
 */
@Data
@TableName("oms_order_setting")
public class OrderSettingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * (min)
	 */
	private Integer flashOrderOvertime;
	/**
	 * (min)
	 */
	private Integer normalOrderOvertime;
	/**
	 * auto confirm received（days）
	 */
	private Integer confirmOvertime;
	/**
	 * auto set completed（days）
	 */
	private Integer completeOvertime;
	/**
	 * auto give good rating（days）
	 */
	private Integer commentOvertime;
	/**
	 * 
	 */
	private Integer memberLevel;

}
