package com.cwquek.ecommerce.coupon.controller;

import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.common.utils.R;
import com.cwquek.ecommerce.coupon.entity.SpuBonusEntity;
import com.cwquek.ecommerce.coupon.service.SpuBonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 04:52:26
 */
@RestController
@RequestMapping("coupon/spubonus")
public class SpuBonusController {
    @Autowired
    private SpuBonusService spuBonusService;

    /**
     * list
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:spubonus:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuBonusService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * info
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:spubonus:info")
    public R info(@PathVariable("id") Long id){
		SpuBonusEntity spuBonus = spuBonusService.getById(id);

        return R.ok().put("spuBonuss", spuBonus);
    }

    /**
     * save
     */
    @PostMapping("/save")
    //@RequiresPermissions("coupon:spubonus:save")
    public R save(@RequestBody SpuBonusEntity spuBonus){
		spuBonusService.save(spuBonus);

        return R.ok();
    }

    /**
     * update
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:spubonus:update")
    public R update(@RequestBody SpuBonusEntity spuBonus){
		spuBonusService.updateById(spuBonus);

        return R.ok();
    }

    /**
     * delete
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:spubonus:delete")
    public R delete(@RequestBody Long[] ids){
		spuBonusService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
