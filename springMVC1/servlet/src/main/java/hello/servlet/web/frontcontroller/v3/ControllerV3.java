package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    // username과 age 값을 가져오는 paramMap 매개변수
    // ModelView 반환
    ModelView process(Map<String, String> paramMap);
}
