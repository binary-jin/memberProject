package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired //의존관계 자동 주입 (== ac.getBean(MemberRepository.class))
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository () {
        return memberRepository;
    }
}
//MemberServiceImpl은 MemoryMemberRepository를 의존하지 않고 MemberRepository 인터페이스만 의존함
//MemberServiceImpl의 입장에서 생성자를 통해 어떤 구현 객체가 들어올 지는 알 수 없음
//MemberServiceImpl의 생성자를 통해 어떤 구현 객체가 들어올지는 오직 외부(AppConfig)에서만 결정 됨
//MemberServiceImpl은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 됨
