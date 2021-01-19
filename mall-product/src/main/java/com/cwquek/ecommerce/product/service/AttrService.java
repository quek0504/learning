package com.cwquek.ecommerce.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.product.entity.AttrEntity;
import com.cwquek.ecommerce.product.vo.AttrGroupRelationVo;
import com.cwquek.ecommerce.product.vo.AttrRespVo;
import com.cwquek.ecommerce.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 03:42:00
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long categoryId, String type);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getAttrRelation(Long attrGroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);

    PageUtils getAttrNoRelation(Map<String, Object> params, Long attrGroupId);
}

