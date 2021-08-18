package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//주문 서비스 구현체
@Component
//@RequiredArgsConstructor생성자 자동 생성
public class OrderServiceImpl implements OrderService{

    /*필드주입 @Autowired private MemberRepository memberRepository;*/
    /*필드주입 @Autowired private DiscountPolicy discountPolicy;*/
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*setter 주입(생성자 주입)*/
    /*@Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
    System.out.println("memberRepository = "+memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    System.out.println("discountPolicy = "+discountPolicy);
        this.discountPolicy = discountPolicy;
    }
    */
    /*생성자 주입*/
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy){
        //System.out.println("1. OrderServiceImpl.orderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId);
        
        /*'int discountPrice = discountPolicy.discount(member, itemPrice)'는
            단일 책임 원칙을 잘 지킨 케이스*/
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    /*테스트 용도*/
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
