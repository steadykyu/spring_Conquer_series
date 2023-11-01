package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath){
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        modelToRequestAttribute(model, request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request, response);
    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        // Jsp 기술이 HttpServletRequest에 setAtrribute를 해주어 하는 구조이다.
        // 그러므로 서블릿 요청 객체(HttpServletRequest) 에 객체 정보를 넣어준다. Ex) "member" : Member , "members", List<Member>)
        // 유의 getParameter : 요청 메시지 파라미터정보를 꺼내오는 것, setAttribute : 객체 정보값을 넣어주는 것
        // 이전에는 Controller에서 했지만, 현재 View 계층에서  처리해주는 점에 유의하자.
        model.forEach((key, value) -> request.setAttribute(key, value));
    }


}
