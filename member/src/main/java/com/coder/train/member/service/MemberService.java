package com.coder.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.coder.train.common.exception.BusinessException;
import com.coder.train.common.exception.BusinessExceptionEnum;
import com.coder.train.member.domain.Member;
import com.coder.train.member.domain.MemberExample;
import com.coder.train.member.mapper.MemberMapper;
import com.coder.train.member.req.MemberRegisterReq;
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

    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        // 数据中已经存在该手机号，直接返回 id。或者抛出一个用户已经注册了的异常
        if(CollUtil.isNotEmpty(list)){
            // return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
