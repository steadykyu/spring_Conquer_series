package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    // 요청에 알맞은 하위 컨트롤러 호출을 위해 Map 자료구조를 이용
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3(){
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI(); // 요청 URI를 가져오는 서블릿의 메서드
        ControllerV3 controller = controllerMap.get(requestURI); // 다형성 구조

        // 예외처리
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        // ModelView URL의 논리이름만 가져오므로, 물리적 이름(경로)를 가져오도록 만들어 View 객체를 생성해준다.
        // 논리 이름 : new-form, save-result , members
        MyView view = viewResolver(viewName);

        // 컨트롤러가 서블릿에 종속되지 않도록 Model를 사용했기 떄문에, Model의 요청 메시지 정보를 꺼내올 수 있도록 render 메서드를 오버로딩 해주자.
        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        // 만약 물리적 경로가 변경되면 컨트롤러가 아닌 해당 메서드의 경로만 변경하면 된다.
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        //paramMap 을 만들어서 넘겨주자
        Map<String, String> paramMap = new HashMap<>();

        // ParamerName(username, age)과 해당 Parmeter에 해당하는 값들을 가져와 paramMap을 생성한다.
        request.getParameterNames().asIterator() // username ~ age
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}