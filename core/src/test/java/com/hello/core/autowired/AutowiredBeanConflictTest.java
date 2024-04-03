package com.hello.core.autowired;

import com.hello.core.AutoAppConfig;
import com.hello.core.discount.DiscountPolicy;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemberService;
import com.hello.core.order.Order;
import com.hello.core.order.OrderService;
import com.hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class AutowiredBeanConflictTest {

    @Test
    void BeanConflictScan(){
        // given : 필드명으로 rateDiscountPolicy를 입력했다.
        ApplicationContext ac
                = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        OrderService orderService = ac.getBean(OrderService.class);

        // VIP 회원일 경우, 할인 받는다.
        Member member = new Member(1L, "member1", Grade.VIP);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        memberRepository.save(member);

        // then: VIP 회원이므로 할인을 받는다.
        Order order = orderService.createOrder(member.getId(), "상품", 20000);
        System.out.println(order.getDiscountPrice()); // 2000
        // -> rateDiscountPolicy로 10퍼 할인 받았다.
    }
}
