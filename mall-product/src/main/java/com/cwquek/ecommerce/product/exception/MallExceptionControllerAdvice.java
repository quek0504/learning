package com.cwquek.ecommerce.product.exception;

import com.cwquek.ecommerce.common.exception.BizCodeEnums;
import com.cwquek.ecommerce.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/*
    All Exception Handling Go Here
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.cwquek.ecommerce.product.controller") // ResponseBody + ControllerAdvice
public class MallExceptionControllerAdvice {


    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("Validation Error: {}, Error Type: {}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError ->{
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return R.error(BizCodeEnums.VALID_EXCEPTION.getCode(), BizCodeEnums.VALID_EXCEPTION.getMsg()).put("data", errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        return R.error(BizCodeEnums.UKNOWN_EXCEPTION.getCode(), BizCodeEnums.UKNOWN_EXCEPTION.getMsg());
    }

}
