package com.cwquek.ecommerce.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.member.entity.GrowthChangeHistoryEntity;

import java.util.Map;

/**
 * 
 *
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 04:54:12
 */
public interface GrowthChangeHistoryService extends IService<GrowthChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

