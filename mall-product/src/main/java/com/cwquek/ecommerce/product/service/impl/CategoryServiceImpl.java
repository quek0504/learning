package com.cwquek.ecommerce.product.service.impl;

import com.cwquek.ecommerce.product.dao.CategoryDao;
import com.cwquek.ecommerce.product.entity.CategoryEntity;
import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cwquek.ecommerce.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {

        List<CategoryEntity> allEntities = baseMapper.selectList(null); // no condition, select all category entities

        // going to add children field to each category entity
        List<CategoryEntity> levelMenu = allEntities.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid()==0; // get level 1 menu
        }).map(menu ->{
            // set children field to each category entity in nested loop
            menu.setChildren(getChildrens(menu, allEntities));
            return menu;
        }).sorted((menu1,menu2) ->{
            // sort two category entities based on sort field
            // based on this condition (ascending effect) : menu1.getSort() - menu2.getSort()
            // assign 0 if menu1.getSort() == null or menu2.getSort() == null , DB could set null value
            return (menu1.getSort() == null ? 0:menu1.getSort()) - (menu2.getSort() ==null ? 0:menu2.getSort());
        }).collect(Collectors.toList());

        return levelMenu;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        // TODO - check whether other services is using Menu

        // soft delete or logical delete
        baseMapper.deleteBatchIds(asList);
    }

    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> allEntities){

        List<CategoryEntity> children = allEntities.stream().filter(categoryEntity -> {
            // get root's children
            return categoryEntity.getParentCid() == root.getCatId();
        }).map(childrenEntity ->{
            childrenEntity.setChildren(getChildrens(childrenEntity, allEntities));
            return childrenEntity;
        }).sorted((menu1,menu2) ->{
            return (menu1.getSort() == null ? 0:menu1.getSort()) - (menu2.getSort() == null ? 0:menu2.getSort());
        }).collect(Collectors.toList());

        return children;
    }

}