package com.yhh.yoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhh.yoj.annotation.AuthCheck;
import com.yhh.yoj.common.BaseResponse;
import com.yhh.yoj.common.ErrorCode;
import com.yhh.yoj.common.ResultUtils;
import com.yhh.yoj.constant.UserConstant;
import com.yhh.yoj.exception.BusinessException;
import com.yhh.yoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yhh.yoj.model.entity.QuestionSubmit;
import com.yhh.yoj.model.entity.User;
import com.yhh.yoj.model.vo.QuestionSubmitVO;
import com.yhh.yoj.service.QuestionSubmitService;
import com.yhh.yoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 *
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;


    /**
     * 分页获取列表（仅管理员）
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                   HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    }



}
