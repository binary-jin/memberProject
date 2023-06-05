package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
//    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /*orderServiceImpl은 OrderService에 관련된 로직만 해야하는데
    discountPolicy를 자기가 직접 FixDiscountpolicy를 할지
    RateDiscountPolicy를 할지 OrderServiceImpl이 선택, 객체를 만드는 코드임*/
    /*위의 상황을 방지하기 위해 구현 객체를 생성하고 연결하는 별도의 객체를 만들자!*/

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
    //OrderServiceImpl은 FixDiscountPolicy를 의존하지 않고 DiscountPolicy 인터페이스만 의존함
    //OrderServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지는 알 수 없음
    //OrderServiceImpl은 생성자를 통해 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서만 결정함
    //OrderServiceImpl은 실행에만 집중하면 됨
}
