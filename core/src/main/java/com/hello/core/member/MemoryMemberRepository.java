package com.hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{
    // 회원을 저장할 내부 메모리
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member); // 이거햇는데 안대네
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
