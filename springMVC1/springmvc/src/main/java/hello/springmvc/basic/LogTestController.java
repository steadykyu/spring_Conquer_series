package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name); // 콘솔에 출력
        // 운영서버에 모든 로그가 남긴다고 생각해보면, 개발로그, 테스트용 로그들이 운영서버에 보이게 되는 문제가 발생한다.

        // 로그를 찍기전에 + 연산이 일어난다. -> 리소스낭비
        // 그러므로 파라미터를 넘겨주도록 해주자.
        log.trace("trace log=" + name);
        // 기본적으로 컨트롤러 정보와 쓰레드 정보를 알려준다.
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);
        // 위 처럼 개발자들끼리 어떤 코드의 로그인지 정보를 전달할 수 있음.
        // default가 info이다. (trace와 debug는 따로 로그가 안나옴 -> 개발서버에서만 필요해서)
        // applicati 설정정보 추가해서 원하는 단계의 로그를 찍어줄 수 있다.


        // 스프링 부트의 설정만으로 로그 기능을 조절할 수 있는 것이 장점

        return "ok";
    }
}
