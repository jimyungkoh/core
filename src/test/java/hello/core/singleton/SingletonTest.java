package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonTest {
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인

        logger.info(String.format("memberService1 = %s", memberService1));
        logger.info(String.format("memberService2 = %s", memberService2));

        //memberService1 != memberService2
        assertNotSame(memberService1, memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        //private으로 생성자를 막아두었다. 컴파일 오류가 발생한다.
        //new SingletonService();

        //1.조회: 호출할 때마다 같은 객체를 반환
        SingletonService singletonService1 = SingletonService.getInstance();

        //2. 조회: 호출할 때마다 같은 객체를 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        //참조값이 같은 것을 확인

        logger.info(String.format("singletonService1 = %s", singletonService1));
        logger.info(String.format("singletonService2 = %s", singletonService2));

        //singletonService1  == singletonService2
        assertSame(singletonService1, singletonService2);

        singletonService1.logic();
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        //AppConfig appConfig = new AppConfig();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 다른 것을 확인

        logger.info(String.format("memberService1 = %s", memberService1));
        logger.info(String.format("memberService2 = %s", memberService2));

        //memberService1 != memberService2
        assertSame(memberService1, memberService2);
    }
}
