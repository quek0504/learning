package com.cwquek.ecommerce.warehouse.entity;

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
 * @date 2020-11-25 04:55:14
 */
@Data
@TableName("wms_ware_order_task")
public class WareOrderTaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long orderId;
	/**
	 * 
	 */
	private String orderSn;
	/**
	 * 
	 */
	private String consignee;
	/**
	 * 
	 */
	private String consigneeTel;
	/**
	 * 
	 */
	private String deliveryAddress;
	/**
	 * 
	 */
	private String orderComment;
	/**
	 * 
	 */
	private Integer paymentWay;
	/**
	 * 
	 */
	private Integer taskStatus;
	/**
	 * 
	 */
	private String orderBody;
	/**
	 * 
	 */
	private String trackingNo;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Long wareId;
	/**
	 * 
	 */
	private String taskComment;

}
