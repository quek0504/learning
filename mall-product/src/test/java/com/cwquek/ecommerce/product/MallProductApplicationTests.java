package com.cwquek.ecommerce.product;

import com.cwquek.ecommerce.product.service.BrandService;
import com.cwquek.ecommerce.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@Slf4j
@SpringBootTest
class MallProductApplicationTests {

	@Autowired
	BrandService brandService;

	@Autowired
	CategoryService categoryService;

	@Test
	void testFindCategoryPath() {
		Long[] categoryPath = categoryService.findCategoryPath(100L);
		// invalid categoryId test
		// Long[] categoryPath = categoryService.findCategoryPath(1000L);
		log.info("Complete path: {}", Arrays.asList(categoryPath));
	}

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
