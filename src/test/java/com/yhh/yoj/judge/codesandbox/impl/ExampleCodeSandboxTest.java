package com.yhh.yoj.judge.codesandbox.impl;

import com.yhh.yoj.judge.codesandbox.CodeSandbox;
import com.yhh.yoj.judge.codesandbox.CodeSandboxFactory;
import com.yhh.yoj.judge.codesandbox.CodeSandboxProxy;
import com.yhh.yoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yhh.yoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yhh.yoj.model.enums.QuestionSubmitLanguageEnum;
import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hyh
 * @date 2024/6/5
 */
@SpringBootTest
class ExampleCodeSandboxTest {

    @Value("${codesandbox.type:remote}")
    private String type;


    @Test
    void executeCodeExampleCodeSandbox() {
        CodeSandbox codeSandbox = new ExampleCodeSandbox();
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        String code = "int main() { }";
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .inputList(inputList)
                .language(language)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
    }

    @Test
    void executeCodeByFactoryAndProxy() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        CodeSandboxProxy codeSandboxProxy = new CodeSandboxProxy(codeSandbox);
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"结果:\" + (a + b));\n" +
                "    }\n" +
                "}\n";
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .inputList(inputList)
                .language(language)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandboxProxy.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String type = scanner.next();
            CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
            String code = "public class Main {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        int a = Integer.parseInt(args[0]);\n" +
                    "        int b = Integer.parseInt(args[1]);\n" +
                    "        System.out.println(\"结果:\" + (a + b));\n" +
                    "    }\n" +
                    "}\n";
            String language = QuestionSubmitLanguageEnum.JAVA.getValue();
            List<String> inputList = Arrays.asList("1 2", "3 4");
            ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                    .code(code)
                    .language(language)
                    .inputList(inputList)
                    .build();
            ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        }
    }
}