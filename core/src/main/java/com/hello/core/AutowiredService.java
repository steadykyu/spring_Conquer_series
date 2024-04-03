package com.hello.core;

import com.hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutowiredService {
//    @Autowired(required = false)
//    public void setNoBean1(Member member) {
//        System.out.println("setNoBean1 = " + member);
//    }
//
//    @Autowired
//    public void setNoBean2(@Nullable Member member) {
//        System.out.println("setNoBean2 = " + member);
//    }
//
//    @Autowired(required = false)
//    public void setNoBean3(Optional<Member> member) {
//        System.out.println("setNoBean3 = " + member);
//    }
}
