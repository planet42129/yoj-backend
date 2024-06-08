package com.yhh.yoj.judge.codesandbox;

import com.yhh.yoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.yhh.yoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.yhh.yoj.judge.codesandbox.impl.ThirdPartyCodeSandbox;
import org.springframework.beans.factory.annotation.Value;

/**
 * 代码沙箱工厂
 * @author hyh
 * @date 2024/6/5
 */

public class CodeSandboxFactory {
//todo 参数配置化

//    private static String type;


    public static CodeSandbox newInstance(String type) {
        switch(type) {
            case "example" :
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
