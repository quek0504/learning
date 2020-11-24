package com.cwquek.ecommerce.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * saved spu
 * 
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 04:54:12
 */
@Data
@TableName("ums_member_collect_spu")
public class MemberCollectSpuEntity implements Serializable {
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
	 * 
	 */
	private Long spuId;
	/**
	 * 
	 */
	private String spuName;
	/**
	 * 
	 */
	private String spuImg;
	/**
	 * 
	 */
	private Date createTime;

}
