package com.cwquek.ecommerce.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.common.utils.Query;
import com.cwquek.ecommerce.coupon.dao.SpuBonusDao;
import com.cwquek.ecommerce.coupon.entity.SpuBonusEntity;
import com.cwquek.ecommerce.coupon.service.SpuBonusService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("spuBonusService")
public class SpuBonusServiceImpl extends ServiceImpl<SpuBonusDao, SpuBonusEntity> implements SpuBonusService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuBonusEntity> page = this.page(
                new Query<SpuBonusEntity>().getPage(params),
                new QueryWrapper<SpuBonusEntity>()
        );

        return new PageUtils(page);
    }

}