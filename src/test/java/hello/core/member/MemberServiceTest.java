package hello.core.member;

import org.junit.jupiter.api.Assertions.*;

import hello.core.AppConfig;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {
    
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given: member 값이 주여졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when: join method를 실행하고
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then: member(given)가 findMember(결괏값)와 같은지 확인해라. 
        Assertions.assertThat(member).isEqualTo(findMember);
    }
    
}
