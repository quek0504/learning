package com.cwquek.ecommerce.coupon.entity;

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
 * @date 2020-11-25 04:52:26
 */
@Data
@TableName("sms_coupon")
public class CouponEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * [0->in mall；1->member；2->shopping；3->register]
	 */
	private Integer couponType;
	/**
	 * 
	 */
	private String couponImg;
	/**
	 * 
	 */
	private String couponName;
	/**
	 * number
	 */
	private Integer num;
	/**
	 * 
	 */
	private BigDecimal amount;
	/**
	 * 
	 */
	private Integer claimLimit;
	/**
	 * min spend to use coupon
	 */
	private BigDecimal minPoint;
	/**
	 * 
	 */
	private Date startTime;
	/**
	 * 
	 */
	private Date endTime;
	/**
	 * [0->in apps；1->specific category；2->specific product]
	 */
	private Integer useType;
	/**
	 * 
	 */
	private String note;
	/**
	 * 
	 */
	private Integer publishCount;
	/**
	 * 
	 */
	private Integer useCount;
	/**
	 * 
	 */
	private Integer receiveCount;
	/**
	 * start date to claim
	 */
	private Date enableStartTime;
	/**
	 * end date to claim
	 */
	private Date enableEndTime;
	/**
	 * coupon code
	 */
	private String code;
	/**
	 * who can claim[0->anyone，different levels of members]
	 */
	private Integer memberLevel;
	/**
	 * [0-no，1-yes]
	 */
	private Integer publish;

}
