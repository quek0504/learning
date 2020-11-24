package com.cwquek.ecommerce.product.dao;

import com.cwquek.ecommerce.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 3 levels classification of products
 * 
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 03:42:00
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
