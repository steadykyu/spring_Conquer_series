package com.hello.core.singleton;

public class StatefulService {
    private int price; // 상태를 유지하는 클라이언트의 필드

    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제!
        //: 특정 클라이언트가 order() 호출하면서, 상태를 유지하는 필드값을 변경할 수 있게 되버렸다.
    }

    public int getPrice(){
        return price;
    }
}
