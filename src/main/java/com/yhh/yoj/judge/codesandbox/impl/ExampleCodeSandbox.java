package com.yhh.yoj.judge.codesandbox.impl;

import com.yhh.yoj.judge.codesandbox.CodeSandbox;
import com.yhh.yoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yhh.yoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yhh.yoj.judge.codesandbox.model.JudgeInfo;
import com.yhh.yoj.model.enums.JudgeInfoMessageEnum;
import com.yhh.yoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * @author hyh
 * @date 2024/6/5
 */
public class ExampleCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
