package com.cwquek.ecommerce.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.product.entity.BrandEntity;

import java.util.Map;

/**
 * 
 *
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 03:42:00
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateCascade(BrandEntity brand);
}

