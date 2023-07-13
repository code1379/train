package com.coder.train.member.controller;

import com.coder.train.common.resp.CommonResp;
import com.coder.train.member.req.MemberRegisterReq;
import com.coder.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count(){
        // Ctrl + Alt + V
        int count = memberService.count();
        CommonResp<Integer> resp = new CommonResp<>();
        resp.setContent(count);
        return resp;
    }

    @PostMapping("/register")
    public CommonResp<Long> register(MemberRegisterReq req){
        long id = memberService.register(req);
        // CommonResp<Long> resp = new CommonResp<>();
        // resp.setContent(id);
        // return resp;
        // 因为多了一个构造函数，所以可以直接使用下面的语句
        return new CommonResp<>(id);
    }
}
