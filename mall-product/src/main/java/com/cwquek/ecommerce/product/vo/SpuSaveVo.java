package com.cwquek.ecommerce.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SpuSaveVo {

    private String spuName;
    private String spuDescription;
    private Long categoryId;
    private Long brandId;
    private BigDecimal weight;
    private int publishStatus;
    private List<String> descript;
    private List<String> images;
    private Bonus bonus;
    private List<BaseAttrs> baseAttrs;
    private List<Skus> skus;

}
