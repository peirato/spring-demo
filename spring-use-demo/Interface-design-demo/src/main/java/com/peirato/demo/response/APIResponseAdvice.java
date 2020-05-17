package com.peirato.demo.response;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.AnnotationUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class APIResponseAdvice implements ResponseBodyAdvice<Object> {


    // 自动将APIException 包装为APIResponse
    @ExceptionHandler(APIException.class)
    public APIResponse handleApiException(HttpServletRequest request,APIException ex){
        log.error("process url {} failed", request.getRequestURL().toString(),ex);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setSuccess(false);
        apiResponse.setCode(ex.getErrorCode());
        apiResponse.setMessage(ex.getErrorMessage());
        return apiResponse;
    }

    // 当方法没有标记@NoAPIResponse才自动包装
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getParameterType() != APIResponse.class
                && AnnotationUtils.findAnnotation(methodParameter.getMethod(),NoAPIResponse.class) == null
                && AnnotationUtils.findAnnotation(methodParameter.getDeclaringClass(),NoAPIResponse.class) == null;
    }

    // 自动包装
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
       APIResponse apiResponse = new APIResponse();
       apiResponse.setSuccess(true);
       apiResponse.setMessage("OK");
       apiResponse.setCode(2000);
       apiResponse.setData(o);
        return apiResponse;
    }
}
