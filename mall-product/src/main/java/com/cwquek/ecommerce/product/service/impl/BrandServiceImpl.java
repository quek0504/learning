package com.cwquek.ecommerce.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.common.utils.Query;
import com.cwquek.ecommerce.product.dao.BrandDao;
import com.cwquek.ecommerce.product.entity.BrandEntity;
import com.cwquek.ecommerce.product.service.BrandService;
import com.cwquek.ecommerce.product.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get("key");
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<BrandEntity>();
        if(!StringUtils.isEmpty(key)){
            queryWrapper.eq("brand_id",key).or().like("name",key);
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    /**
     * Cascade Update
     * @param brand
     */
    @Transactional
    @Override
    public void updateCascade(BrandEntity brand) {

        // normal update pms_brand table
        this.updateById(brand);

        // make sure consistency in multiple redundant tables
        if(!StringUtils.isEmpty(brand.getName())) {
            // update pms_category_brand_relation table
            categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());

            // TODO update other table

        }

    }

}