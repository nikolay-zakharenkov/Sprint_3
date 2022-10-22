package generators;

import models.Order;

import java.util.ArrayList;

public class GeneratorOrder {
    public static Order createOrderWithoutColors() {
        return new Order(
                "qatest",
                "last name",
                "Adress test",
                1,
                "8 800 555 3535",
                20,
                "2022-02-22",
                "Comment for test",
                new ArrayList<>()
        );
    }
    public static Order createBlackOrder() {
        Order order = createOrderWithoutColors();
        order.addColor("BLACK");
        return order;
    }

    public static Order createGreyOrder() {
        Order order = createOrderWithoutColors();
        order.addColor("GREY");
        return order;
    }

    public static Order createBothColorOrder() {
        Order order = createOrderWithoutColors();
        order.addColor("GREY");
        order.addColor("Black");
        return order;
    }
}
