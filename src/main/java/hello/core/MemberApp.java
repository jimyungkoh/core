package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    
    public static void main(String[] args) {

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        
        ApplicationContext ac
            = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService 
            = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        //그냥 1이라고 하면 안됨. Long Type이기 때문에 1L로 표기하는 것

        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }

}
