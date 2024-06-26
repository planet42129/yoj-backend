package com.yhh.yoj.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yhh.yoj.common.ErrorCode;
import com.yhh.yoj.exception.BusinessException;
import com.yhh.yoj.judge.codesandbox.CodeSandbox;
import com.yhh.yoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yhh.yoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
@Slf4j
//@Component
public class RemoteCodeSandbox implements CodeSandbox {

    // 定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";

 /*   @Value("${codesandbox.url}")
    private String url;


    @PostConstruct
    public void init() {
        log.info("CodeSandbox URL: {}", url);
    }*/


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("远程代码沙箱");
//        String url = "http://localhost:8105/executeCode";
        String url = "http://47.100.246.109:8105/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        log.info("URL: {}", url);
        log.info("Request JSON: {}", json);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "executeCode remoteSandbox error, message = " + responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }

}
