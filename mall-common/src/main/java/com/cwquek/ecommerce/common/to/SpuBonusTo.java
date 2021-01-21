package com.cwquek.ecommerce.common.to;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpuBonusTo {

    private Long spuId;
    private BigDecimal buyBonus;
    private BigDecimal growBonus;

}
