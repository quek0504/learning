package com.cwquek.ecommerce.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.cwquek.ecommerce.product.entity.CommentReplyEntity;
import com.cwquek.ecommerce.product.service.CommentReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.common.utils.R;



/**
 * 
 *
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 03:59:19
 */
@RestController
@RequestMapping("product/commentreply")
public class CommentReplyController {
    @Autowired
    private CommentReplyService commentReplyService;

    /**
     * list
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:commentreply:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentReplyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * info
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:commentreply:info")
    public R info(@PathVariable("id") Long id){
		CommentReplyEntity commentReply = commentReplyService.getById(id);

        return R.ok().put("commentReply", commentReply);
    }

    /**
     * save
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:commentreply:save")
    public R save(@RequestBody CommentReplyEntity commentReply){
		commentReplyService.save(commentReply);

        return R.ok();
    }

    /**
     * update
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:commentreply:update")
    public R update(@RequestBody CommentReplyEntity commentReply){
		commentReplyService.updateById(commentReply);

        return R.ok();
    }

    /**
     * delete
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:commentreply:delete")
    public R delete(@RequestBody Long[] ids){
		commentReplyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
