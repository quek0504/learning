package com.cwquek.ecommerce.member.entity;

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
 * @date 2020-11-25 04:54:12
 */
@Data
@TableName("ums_member_receive_address")
public class MemberReceiveAddressEntity implements Serializable {
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
	 * receiver name
	 */
	private String name;
	/**
	 * receiver phone
	 */
	private String phone;
	/**
	 * 
	 */
	private String postCode;
	/**
	 * 
	 */
	private String state;
	/**
	 * 
	 */
	private String city;
	/**
	 * 
	 */
	private String detailAddress;
	/**
	 * 
	 */
	private String areacode;
	/**
	 * 
	 */
	private Integer defaultStatus;

}
