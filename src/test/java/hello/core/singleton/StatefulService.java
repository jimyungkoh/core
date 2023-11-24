package hello.core.singleton;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class StatefulService {

    private int price; /*상태를 유지하는 필드*/

    public void order(String name, int price) {
        log.info(String.format("name =  %s, price = %s", name, price));
        this.price = price;
        /*
        위에 선언한 "private int price;"를 제거하고
        "return price"를 order method 안에 넣으면 무상태 설계 가능
        * */
    }

}
