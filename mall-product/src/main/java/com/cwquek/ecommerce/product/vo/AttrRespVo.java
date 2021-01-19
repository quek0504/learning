package com.cwquek.ecommerce.product.vo;

import lombok.Data;

@Data
public class AttrRespVo extends AttrVo{

    private String categoryName;

    private String groupName;

    private Long[] categoryPath;

}
