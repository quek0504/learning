package com.cwquek.ecommerce.product.feign;

import com.cwquek.ecommerce.common.to.SkuReductionTo;
import com.cwquek.ecommerce.common.to.SpuBonusTo;
import com.cwquek.ecommerce.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("mall-coupon")
public interface CouponFeignService {

    /***
     * CouponFeignService.saveSpuBonus(spuBonusTo);
     * 1. convert RequestBody spuBonusTo to json object
     * 2. locate mall-coupon microservice, send request with json object to /coupon/spubonus/save
     * 3, when mall-coupon microservice receive the request,
     * (@RequestBody SpuBonusEntity spuBonus)；it will convert json to SpuBonusEntity；
     * 只要json數據模型是兼容的，雙方服務無需使用同一個to (transfer object)
     * @param spuBonusTo
     * @return
     */
    @PostMapping("/coupon/spubonus/save")
    R saveSpuBonus(@RequestBody SpuBonusTo spuBonusTo);

    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);

    // example of mall-coupon's SpuBonusController
//    R save(@RequestBody SpuBonusEntity spuBonus){
//        spuBonusService.save(spuBonus);
//
//        return R.ok();
//    }

}
