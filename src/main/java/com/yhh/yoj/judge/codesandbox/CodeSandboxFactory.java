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

    static class CodeSandboxGenHelper {
        private static final CodeSandbox REMOTECODESANDBOX = new RemoteCodeSandbox();
        private static final CodeSandbox THRIDPARTYCODESANDBOX = new ThirdPartyCodeSandbox();
        private static final CodeSandbox EXAMPLESANDBOX = new ExampleCodeSandbox();
    }


    public static CodeSandbox newInstance(String type) {
        switch(type) {
            case "remote":
                return CodeSandboxGenHelper.REMOTECODESANDBOX;
            case "thirdParty":
                return CodeSandboxGenHelper.THRIDPARTYCODESANDBOX;
            default:
                return CodeSandboxGenHelper.EXAMPLESANDBOX;
        }
    }

    private CodeSandboxFactory(){}



}
