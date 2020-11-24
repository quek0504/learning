package com.cwquek.ecommerce.coupon.dao;

import com.cwquek.ecommerce.coupon.entity.SkuLadderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品阶梯价格
 * 
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 04:52:26
 */
@Mapper
public interface SkuLadderDao extends BaseMapper<SkuLadderEntity> {
	
}
