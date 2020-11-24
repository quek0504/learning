package com.cwquek.ecommerce.member.entity;

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
 * @date 2020-11-25 04:54:12
 */
@Data
@TableName("ums_member_level")
public class MemberLevelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * level naming
	 */
	private String name;
	/**
	 * to level up
	 */
	private Integer growthPoint;
	/**
	 * [0->no；1->yes]
	 */
	private Integer defaultStatus;
	/**
	 * 
	 */
	private BigDecimal freeFreightPoint;
	/**
	 * 
	 */
	private Integer commentGrowthPoint;
	/**
	 * 
	 */
	private Integer priviledgeFreeFreight;
	/**
	 * 
	 */
	private Integer priviledgeMemberPrice;
	/**
	 * 
	 */
	private Integer priviledgeBirthday;
	/**
	 * 
	 */
	private String note;

}
