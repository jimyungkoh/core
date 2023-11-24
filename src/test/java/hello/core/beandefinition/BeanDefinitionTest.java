package hello.core.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

import java.util.Arrays;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac
            = new AnnotationConfigApplicationContext(AppConfig.class);

//     GenericXmlApplicationContext ac
//      = new GenericXmlApplicationContext("appConfig.xml");

    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        Arrays.stream(beanDefinitionNames)
                .filter(name -> ac.getBeanDefinition(name)
                        .getRole() == BeanDefinition.ROLE_APPLICATION)
                .forEach(name -> logger.info(
                        String.format("beanDefinitionName: %s; beanDefinition: %s",
                                name, ac.getBeanDefinition(name))));
    }
}
