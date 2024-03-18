package com.hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    // 테스트용 설정정보
    static class TestConfig {
        // Bean ( 서비스 객체)
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
    @Test
    void statefulServiceSingleton() {
        // given
        ApplicationContext ac
                = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService",
                StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService",
                StatefulService.class);

        // when : 싱글톤으로 공유되는 statefulService
        //ThreadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        //-> 이때 공유되는 statefulService 인스턴스 속 price에 20000원이 들어가버린다.
        statefulService2.order("userB", 20000);

        //then : 사용자 A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); // 20000

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);


    }
}
