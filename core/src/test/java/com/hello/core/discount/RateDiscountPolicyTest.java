package com.hello.core.discount;

import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야한다")
    void vip_o(){
        //given : VIP 일떄
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when : 10000 원상품이 주어진다.
        int discount = discountPolicy.discount(member, 20000);
        //then : 할인 액은 2000원이여야한다.
        assertThat(discount).isEqualTo(2000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        //given: VIP 일때
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        //when 
        int discount = discountPolicy.discount(member, 10000);
        //then : 할인액이 0원이여야한다.
        assertThat(discount).isEqualTo(0);
    }
}