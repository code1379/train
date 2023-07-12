package com.coder.train.member.service;

import com.coder.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    // 注入 mapper（也可以使用 @Autowired）
    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return memberMapper.count();
    }
}
