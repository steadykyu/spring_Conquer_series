package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class responseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("/response/hello") // 뷰 템플릿 경로
                .addObject("data", "hello!"); // 속성 매핑 변수명 , 넣을 객체

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        // 뷰 템플릿에 넘길 model 데이터
        model.addAttribute("data", "hello!"); // 속성 매핑 변수명 , 넣을 객체

        return "/response/hello"; // 뷰 논리명
    }
    // response body를 쓴다면? -> 바로 Body 데이터를 응답하므로, 뷰템플릿의 html이 나오지 않는다.


    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        // 뷰 템플릿에 넘길 model 데이터
        model.addAttribute("data", "hello!"); // 속성 매핑 변수명 , 넣을 객체
    }
    // 뷰 논리명과 요청 URL 이 같으면 Spring에서 알아서 해준다.
    // 명시성이 너무 떨어져서 비추 ;

    // 뷰 논리명에 알아서 추가해주는 suffix
}
