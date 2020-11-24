package com.cwquek.ecommerce.product.service.impl;

import com.cwquek.ecommerce.product.dao.CommentReplyDao;
import com.cwquek.ecommerce.product.entity.CommentReplyEntity;
import com.cwquek.ecommerce.product.service.CommentReplyService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.common.utils.Query;


@Service("commentReplyService")
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyDao, CommentReplyEntity> implements CommentReplyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentReplyEntity> page = this.page(
                new Query<CommentReplyEntity>().getPage(params),
                new QueryWrapper<CommentReplyEntity>()
        );

        return new PageUtils(page);
    }

}