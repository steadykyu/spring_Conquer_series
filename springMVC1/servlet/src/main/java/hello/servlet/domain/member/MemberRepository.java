package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    /**
     * 동시성 문제가 고려되어 있지 않음, 실무 에서는 ConcurrentHashMap, AtomicLong 사용 고려
     *
     * DB가 아닌 메모리에 객체를 저장한다고 가정
     */
    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // id
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    // 외부에서 생성하지 못하도록 생성자에 private를 걸어주자. => 싱글톤
    private MemberRepository(){

    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        // 원본 유지를 위해 다음과 같이 새로 생성해준다.
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
