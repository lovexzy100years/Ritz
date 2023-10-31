package com.ritz.health.web.controller;

import com.ritz.health.dto.MemberDataDTO;
import com.ritz.health.entity.Result;
import com.ritz.health.message.MessageConstant;
import com.ritz.health.service.IMemberService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@CrossOrigin("*")
public class ReportMemberController {

    @Resource
    private IMemberService memberService;

    @GetMapping("/getMemberTotal")
    public Result getMemberTotal(){
        MemberDataDTO memberDataDTO = memberService.getMemberTotal();
        return new Result(true, MessageConstant.QUERY_MEMBER_SUCCESS,memberDataDTO);
    }
}
