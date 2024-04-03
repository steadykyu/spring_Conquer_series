package com.hello.core.autowired;

import com.hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@SpringBootTest // 프로젝트의 구성영역으로 생성한 스프링 컨테이너를 가져옵니다.
public class autowiredOptionTest {
    // test Bean은 필드 주입 DI
    @Autowired
    TestBean testBean;

    // 임시 구성 영역에서 빈 등록
    @Configuration
    static class TestConfig {
        @Bean
        public TestBean testBean() {
            return new TestBean();
        }
    }
    // given: 수동으로 등록한 TestBean 객체는 Bean으로 존재하지 않는 Member를 자동 주입 받는다.
    static class TestBean {
//        @Autowired(required = true)
//        public void setNoBean0(Member member) {
//            System.out.println("setNoBean1 = " + member);
//        }
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("setNoBean1 = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("setNoBean2 = " + member);
        }

        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member) {
            System.out.println("setNoBean3 = " + member);
        }
    }

    @Test
    void testAutowired() {
        // Spring이 TestBean을 초기화할 때 @Autowired 옵션에 따라 자동 주입을 시도합니다.
        // Member 타입의 빈이 없으므로, setNoBean 메소드들의 동작을 비교해보자.
    }
}
