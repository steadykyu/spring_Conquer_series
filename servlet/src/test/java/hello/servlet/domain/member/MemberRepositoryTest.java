package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    // 싱글톤이므로, 메서드로 인스턴스를 얻어온다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    //각 테스트가 끝날 때, 다음 테스트에 영향을 주지 않도록 각 테스트의 저장소를 clearStore() 를 호출해서 초기화
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();;
    }

    @Test
    public void save() throws Exception{
        //given
        Member member = new Member("hello", 20);
        //when
        Member savedMember = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    public void findAll() throws Exception{
        //given
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",30);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(member1,member2);
    }
}