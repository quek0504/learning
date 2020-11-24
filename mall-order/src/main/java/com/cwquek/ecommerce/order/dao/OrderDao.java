package com.cwquek.ecommerce.order.dao;

import com.cwquek.ecommerce.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 04:26:44
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
