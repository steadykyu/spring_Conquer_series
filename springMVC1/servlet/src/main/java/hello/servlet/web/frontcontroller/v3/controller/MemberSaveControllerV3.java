package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);
        // 이전에는 컨트롤러에서 서블릿 기능(HttpRequest)을 이용해 요청 메시지의 정보를 담아 보냈었다.
        // 그러나 이제는 Servlet에 종속되지 않고, 요청메시지의 정보를 Model를 통해 보낼 수 있다.
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);

        return mv;

    }
}
