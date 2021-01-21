package com.cwquek.ecommerce.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Skus {

    private List<Attr> attr;
    private String skuName;
    private BigDecimal price;
    private String skuTitle;
    private String skuSubTitle;
    private List<Images> images;
    private List<String> descar; // description array
    private int fullCount;
    private BigDecimal discount;
    private int stackStatus; // stack with other discount
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;


}
