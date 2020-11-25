package com.cwquek.ecommerce.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cwquek.ecommerce.product.entity.BrandEntity;
import com.cwquek.ecommerce.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MallProductApplicationTests {

	@Autowired
	BrandService brandService;

	@Test
	void contextLoads() {

//		BrandEntity brandEntity = new BrandEntity();
//		brandEntity.setBrandId(1L);
//		brandEntity.setDescript("Huawei");

//		brandEntity.setName("Huawei");
//		brandService.save(brandEntity);
//		System.out.println("Saving completed...");

//		brandService.updateById(brandEntity);


//		List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
//		list.forEach((item) -> {
//			System.out.println(item);
//		});
//
//		brandService.removeById(1L);

	}

}
