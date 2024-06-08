package com.yhh.yoj.judge;

import com.yhh.yoj.model.entity.QuestionSubmit;

/**
 * @author hyh
 * @date 2024/6/5
 */
public interface JudgeService {

   QuestionSubmit doJudge(long questionSubmitId);
}
