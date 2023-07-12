package com.coder.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.coder.train.member.domain.Member;
import com.coder.train.member.domain.MemberExample;
import com.coder.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    // 注入 mapper（也可以使用 @Autowired）
    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return (int) memberMapper.countByExample(null);
    }

    public long register(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        // 数据中已经存在该手机号，直接返回 id。或者抛出一个用户已经注册了的异常
        if(CollUtil.isNotEmpty(list)){
            // return list.get(0).getId();
            throw new RuntimeException("手机号已经注册了");
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
