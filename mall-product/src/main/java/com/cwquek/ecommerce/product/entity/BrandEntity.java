package com.cwquek.ecommerce.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.cwquek.ecommerce.common.valid.AddGroup;
import com.cwquek.ecommerce.common.valid.ListValue;
import com.cwquek.ecommerce.common.valid.UpdateGroup;
import com.cwquek.ecommerce.common.valid.UpdateStatusGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * @author cwquek
 * @email quek0504@gmail.com
 * @date 2020-11-25 03:42:00
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @NotNull(message = "must specify brand id in update request", groups = {UpdateGroup.class})
    @Null(message = "cannot specify brand id in create request", groups = {AddGroup.class})
    @TableId
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "must submit brand name", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @NotEmpty(groups = {AddGroup.class})
    @URL(message = "logo must be a valid url", groups = {AddGroup.class, UpdateGroup.class})
    private String logo;
    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
	@NotNull(groups = {AddGroup.class, UpdateStatusGroup.class})
    @ListValue(vals = {0, 1}, groups = {AddGroup.class, UpdateStatusGroup.class}) // self-define validation annotation
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @NotEmpty(groups = {AddGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$", message = "first letter must be a letter", groups = {AddGroup.class, UpdateGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull(groups = {AddGroup.class})
    @Min(value = 0, message = "sort must be positive integer", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

}
