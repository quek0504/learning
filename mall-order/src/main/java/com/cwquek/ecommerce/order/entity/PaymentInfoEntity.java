package com.cwquek.ecommerce.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
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
@TableName("oms_payment_info")
public class PaymentInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String orderSn;
	/**
	 * 
	 */
	private Long orderId;
	/**
	 * 
	 */
	private BigDecimal totalAmount;
	/**
	 * payment info
	 */
	private String subject;
	/**
	 * 
	 */
	private String paymentStatus;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date confirmTime;
	/**
	 * 
	 */
	private String callbackContent;
	/**
	 * 
	 */
	private Date callbackTime;

}
