package com.cwquek.ecommerce.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cwquek.ecommerce.common.constant.ProductConstant;
import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.common.utils.Query;
import com.cwquek.ecommerce.product.dao.AttrAttrgroupRelationDao;
import com.cwquek.ecommerce.product.dao.AttrDao;
import com.cwquek.ecommerce.product.dao.AttrGroupDao;
import com.cwquek.ecommerce.product.dao.CategoryDao;
import com.cwquek.ecommerce.product.entity.AttrAttrgroupRelationEntity;
import com.cwquek.ecommerce.product.entity.AttrEntity;
import com.cwquek.ecommerce.product.entity.AttrGroupEntity;
import com.cwquek.ecommerce.product.entity.CategoryEntity;
import com.cwquek.ecommerce.product.service.AttrService;
import com.cwquek.ecommerce.product.service.CategoryService;
import com.cwquek.ecommerce.product.vo.AttrGroupRelationVo;
import com.cwquek.ecommerce.product.vo.AttrRespVo;
import com.cwquek.ecommerce.product.vo.AttrVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao relationDao;

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        // attr value object has additional attrGroupId property
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        // save attribute
        this.save(attrEntity);
        // save attribute relation
        if (attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() && attr.getAttrGroupId() != null) {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attrEntity.getAttrId());
            relationDao.insert(relationEntity);
        }
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long categoryId, String type) {

        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>().eq("attr_type", "base".equalsIgnoreCase(type) ? ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() : ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());

        if (categoryId != 0) {
            queryWrapper.eq("category_id", categoryId);
        }

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            // attr_id attr_name
            queryWrapper.and((wrapper) -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );

        PageUtils pageUtils = new PageUtils(page);

        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> respVos = records.stream().map((attrEntity) -> {
            AttrRespVo attrRespVo = new AttrRespVo();

            BeanUtils.copyProperties(attrEntity, attrRespVo);

            //  sales attributes wont have attribute group, no attr-attrgroup relation
            if ("base".equalsIgnoreCase(type)) {
                // set additional properties in attrRespVo (categoryName, groupName)
                AttrAttrgroupRelationEntity relationEntity = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
                if (relationEntity != null && relationEntity.getAttrGroupId() != null) {
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(relationEntity.getAttrGroupId());
                    attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName()); // groupName
                }
            }
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCategoryId());

            if (categoryEntity != null) {
                attrRespVo.setCategoryName(categoryEntity.getName()); // categoryName
            }

            return attrRespVo;
        }).collect(Collectors.toList());

        pageUtils.setList(respVos);

        return pageUtils;

    }

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        AttrRespVo respVo = new AttrRespVo();

        AttrEntity attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity, respVo);

        // get relation for specification attribute
        // no attr-attrgroup relation for sales attribute
        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            // set attribute group info
            AttrAttrgroupRelationEntity attrgroupRelation = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
            if (attrgroupRelation != null) {
                respVo.setAttrGroupId(attrgroupRelation.getAttrGroupId());
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupRelation.getAttrGroupId());
                if (attrGroupEntity != null) {
                    respVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }

        // set attribute info
        Long categoryId = attrEntity.getCategoryId();
        Long[] categoryPath = categoryService.findCategoryPath(categoryId);
        respVo.setCategoryPath(categoryPath);

        CategoryEntity categoryEntity = categoryDao.selectById(categoryId);
        if (categoryEntity != null) {
            respVo.setCategoryName(categoryEntity.getName());
        }

        return respVo;
    }


    @Transactional
    @Override
    public void updateAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.updateById(attrEntity); // update attr

        // update relation for specification attribute
        // no attr-attrgroup relation for sales attribute
        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());

            Integer count = relationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            if (count > 0) {
                // update attr-attrgroup relation
                relationDao.update(relationEntity, new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            } else {
                // insert attr-attrgroup relation
                relationDao.insert(relationEntity);
            }
        }
    }

    /***
     * get all specifications (base attributes) based on attribute group id
     * @param attrGroupId
     * @return
     */
    @Override
    public List<AttrEntity> getAttrRelation(Long attrGroupId) {
        List<AttrAttrgroupRelationEntity> entities = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupId));

        List<Long> attrIds = entities.stream().map((attr) -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        if (attrIds == null || attrIds.size() == 0) {
            return null;
        }

        Collection<AttrEntity> attrEntities = this.listByIds(attrIds);

        return (List<AttrEntity>) attrEntities;
    }

    @Override
    public void deleteRelation(AttrGroupRelationVo[] vos) {

        List<AttrAttrgroupRelationEntity> entities = Arrays.asList(vos).stream().map((item) -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());

        relationDao.deleteBatchRelation(entities);
    }

    /**
     * get all specifications (base attributes) which haven't been assigned to request attribute group
     *
     * @param params
     * @param attrGroupId
     * @return
     */
    @Override
    public PageUtils getAttrNoRelation(Map<String, Object> params, Long attrGroupId) {
        // 1. get category of request attribute group
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupId);
        Long categoryId = attrGroupEntity.getCategoryId();

        // 2. request attribute group can only add unassigned attributes from same category
        //    eg. attribute with same category id but not in attr-attrgroup relation table
        // 2.1. get all attribute groups that are in same category
        List<AttrGroupEntity> group = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("category_id", categoryId));
        List<Long> collect = group.stream().map(item -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList()); // collect attribute groups ids

        // 2.2. get all assigned attributes using attribute groups ids
        List<AttrAttrgroupRelationEntity> groupId = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", collect));
        List<Long> attrIds = groupId.stream().map(item -> {
            return item.getAttrId();
        }).collect(Collectors.toList()); // collect all assigned attributes ids from attr-attrgroup relation table

        // init query wrapper
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().eq("category_id", categoryId).eq("attr_type", ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());

        // 2.3. add condition to query wrapper to remove assigned attributes
        // result -> unassigned attributes in same category
        if (attrIds != null && attrIds.size() > 0) {
            wrapper.notIn("attr_id", attrIds);
        }

        // if has query key eg.user searching
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((w) -> {
                w.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), wrapper);

        PageUtils pageUtils = new PageUtils(page);

        return pageUtils;
    }

}