package com.cwquek.ecommerce.thirdparty.controller;

import com.cwquek.ecommerce.common.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class OssController {

    @RequestMapping("/oss/policy")
    public R policy() {

        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = format + "/";
        return R.ok();

    }



}
