package hello.core.singleton;

public class StatefulService {
    private int price; /*상태를 유지하는 필드*/

    public void order(String name, int price){
        System.out.println("name =  " + name + " price = " + price);
        this.price = price;
        /*
        위에 선언한 "private int price;"를 제거하고
        "return price"를 order method 안에 넣으면 무상태 설계 가능
        * */
    }

    public int getPrice(){
        return price;
    }
}
