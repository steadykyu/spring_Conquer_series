package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    /**
     * Servlet MVC 코드
     *
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("OK");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){

        log.info("username ={}, age={}", memberName, memberAge);

        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){

        log.info("username ={}, age={}", username, age);

        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
             String username,
             int age){

        log.info("username ={}, age={}", username, age);

        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){

//        Integer = null;
//        int = null 이므로, age에 Integer를 넣어야한다. 안그럼 500 Error 나온다.

//        username = "" 인 경우 -> Null 이 아닌 빈문자 이므로 통과된다.

        log.info("username ={}, age={}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age){

//        Integer = null;
//        int = null 이므로, age에 Integer를 넣어야한다. 안그럼 500 Error 나온다.

//        username = "" 인 경우 -> Null 이 아닌 빈문자 이므로 통과된다.

        log.info("username ={}, age={}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap){
        log.info("username ={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "OK";
    }
    // multivalueMap -> ? userIds = 1&userIds = 2 일떄 userIds 를 조회하면 List<Integer> -> [1,2] 출력

//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@RequestParam String username,
//                                   @RequestParam int age){
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//        log.info("username ={}, age={}", helloData.getUsername(), helloData.getAge());
//        log.info("helloData={}", helloData); // 객체를 ToSTring으로 알아서 바꾸어줌
//        return "Ok";
//    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username ={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); // 객체를 ToSTring으로 알아서 바꾸어줌
        return "Ok";
    }
    // 프로퍼티 -> 필드

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username ={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); // 객체를 ToSTring으로 알아서 바꾸어줌
        return "Ok";
    }
}
