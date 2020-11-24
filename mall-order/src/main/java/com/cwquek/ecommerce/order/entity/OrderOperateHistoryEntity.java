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
@TableName("oms_order_operate_history")
public class OrderOperateHistoryEntity implements Serializable {
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
	 * by [member；system；admin]
	 */
	private String operateMan;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 【0->to pay；1->to ship；2->delivered；3->completed；4->closed；5->invalid order】
	 */
	private Integer orderStatus;
	/**
	 * 
	 */
	private String note;

}
