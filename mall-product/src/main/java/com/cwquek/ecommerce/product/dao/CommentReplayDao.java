package com.cwquek.ecommerce.product.dao;

import com.cwquek.ecommerce.product.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 03:18:19
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}
