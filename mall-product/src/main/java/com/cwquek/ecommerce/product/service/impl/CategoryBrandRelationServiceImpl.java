package com.cwquek.ecommerce.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.common.utils.Query;
import com.cwquek.ecommerce.product.dao.BrandDao;
import com.cwquek.ecommerce.product.dao.CategoryBrandRelationDao;
import com.cwquek.ecommerce.product.dao.CategoryDao;
import com.cwquek.ecommerce.product.entity.BrandEntity;
import com.cwquek.ecommerce.product.entity.CategoryBrandRelationEntity;
import com.cwquek.ecommerce.product.entity.CategoryEntity;
import com.cwquek.ecommerce.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    BrandDao brandDao;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        // expect client sending brandId and categoryId only
        Long brandId = categoryBrandRelation.getBrandId();
        Long categoryId = categoryBrandRelation.getCategoryId();

        // check whether brand category relation exists
        QueryWrapper<CategoryBrandRelationEntity> query = new QueryWrapper<>();
        query.and(i -> i.eq("brand_id", brandId)).eq("category_id", categoryId);
        List<CategoryBrandRelationEntity> entity = baseMapper.selectList(query);
        if(entity.size() >= 1) {
            return false;
        }

        // get entity from dao
        BrandEntity brandEntity = brandDao.selectById(brandId);
        CategoryEntity categoryEntity = categoryDao.selectById(categoryId);

        // get entity name
        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCategoryName(categoryEntity.getName());

        // save complete information (brandId, categoryId, brandName, categoryName)
        // in pms_category_brand_relation table
        this.save(categoryBrandRelation);

        return true;

    }

    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelationEntity relationEntity = new CategoryBrandRelationEntity();
        relationEntity.setBrandId(brandId);
        relationEntity.setBrandName(name);
        this.update(relationEntity, new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));
    }

    @Override
    public void updateCategory(Long catId, String name) {
        // self define mapper
        this.baseMapper.updateCategory(catId, name);
    }

}