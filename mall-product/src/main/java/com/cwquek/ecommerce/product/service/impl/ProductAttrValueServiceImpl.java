package com.cwquek.ecommerce.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.common.utils.Query;
import com.cwquek.ecommerce.product.dao.ProductAttrValueDao;
import com.cwquek.ecommerce.product.entity.ProductAttrValueEntity;
import com.cwquek.ecommerce.product.service.ProductAttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<ProductAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveProductAttr(List<ProductAttrValueEntity> collect) {
        this.saveBatch(collect);
    }

}