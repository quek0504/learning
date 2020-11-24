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
@TableName("oms_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long memberId;
	/**
	 * order id
	 */
	private String orderSn;
	/**
	 * 
	 */
	private Long couponId;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private String memberUsername;
	/**
	 * 
	 */
	private BigDecimal totalAmount;
	/**
	 * 
	 */
	private BigDecimal payAmount;
	/**
	 * 
	 */
	private BigDecimal freightAmount;
	/**
	 * 
	 */
	private BigDecimal promotionAmount;
	/**
	 * amount reduced by member points
	 */
	private BigDecimal integrationAmount;
	/**
	 * 
	 */
	private BigDecimal couponAmount;
	/**
	 * 
	 */
	private BigDecimal discountAmount;
	/**
	 * 
	 */
	private Integer payType;
	/**
	 * [0->PC；1->app]
	 */
	private Integer sourceType;
	/**
	 * [0->to pay；1->to ship；2->delivered；3->completed；4->closed；5->invalid order】
	 */
	private Integer status;
	/**
	 * 
	 */
	private String deliveryCompany;
	/**
	 * logistic id
	 */
	private String deliverySn;
	/**
	 * 
	 */
	private Integer autoConfirmDay;
	/**
	 * member points receivable
	 */
	private Integer integration;
	/**
	 * 
	 */
	private Integer growth;
	/**
	 * 
	 */
	private Integer billType;
	/**
	 * 
	 */
	private String billHeader;
	/**
	 * 
	 */
	private String billContent;
	/**
	 * 
	 */
	private String billReceiverPhone;
	/**
	 * 
	 */
	private String billReceiverEmail;
	/**
	 * 
	 */
	private String receiverName;
	/**
	 * 
	 */
	private String receiverPhone;
	/**
	 * 
	 */
	private String receiverPostCode;
	/**
	 * 
	 */
	private String receiverState;
	/**
	 * 
	 */
	private String receiverCity;
	/**
	 * 
	 */
	private String receiverDetailAddress;
	/**
	 * 
	 */
	private String orderNote;
	/**
	 * delivered[0->no；1->yes]
	 */
	private Integer confirmStatus;
	/**
	 * [0->no；1->yes】
	 */
	private Integer deleteStatus;
	/**
	 * member points used
	 */
	private Integer usedIntegration;
	/**
	 * 
	 */
	private Date paymentTime;
	/**
	 * 
	 */
	private Date deliveryTime;
	/**
	 * 
	 */
	private Date receiveTime;
	/**
	 * 
	 */
	private Date commentTime;
	/**
	 * 
	 */
	private Date modifyTime;

}
