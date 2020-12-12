package com.cwquek.ecommerce.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.cwquek.ecommerce.common.valid.AddGroup;
import com.cwquek.ecommerce.common.valid.UpdateGroup;
import com.cwquek.ecommerce.common.valid.UpdateStatusGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cwquek.ecommerce.product.entity.BrandEntity;
import com.cwquek.ecommerce.product.service.BrandService;
import com.cwquek.ecommerce.common.utils.PageUtils;
import com.cwquek.ecommerce.common.utils.R;

import javax.validation.Valid;


/**
 * 
 *
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 03:59:19
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * list
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * info
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * save
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@Validated(AddGroup.class) @RequestBody BrandEntity brand /*, BindingResult result*/){
        /* Handled by exception controller advice
        if(result.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            // get result error
            result.getFieldErrors().forEach(item -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            return R.error(400, "Submit Error").put("data", map);
        } else {
            brandService.save(brand);
        }
        */
        brandService.save(brand);
        return R.ok();
    }

    /**
     * update
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    /**
     * update status
     */
    @RequestMapping("/update/status")
    //@RequiresPermissions("product:brand:update")
    public R updateStatus(@Validated(UpdateStatusGroup.class) @RequestBody BrandEntity brand){
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * delete
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
