package order;

import generators.GeneratorOrder;
import steps.TestsSteps;
import io.restassured.response.Response;
import models.Order;
import org.junit.After;
import org.junit.Test;

public class OrderCreationTest {
    TestsSteps step = new TestsSteps();
    Integer orderId;

    @Test
    public void orderCreationBlackColourTest() {
        Order order = GeneratorOrder.createBlackOrder();
        Response response = step.createOrder(order);
        orderId = step.orderCreationResponseCheck(response);
    }

    @Test
    public void orderCreationGreyColourTest() {
        Order order = GeneratorOrder.createGreyOrder();
        Response response = step.createOrder(order);
        orderId = step.orderCreationResponseCheck(response);
    }

    @Test
    public void orderCreationBothColoursTest() {
        Order order = GeneratorOrder.createBothColorOrder();
        Response response = step.createOrder(order);
        orderId = step.orderCreationResponseCheck(response);
    }

    @Test
    public void orderCreationNoColour() {
        Order order = GeneratorOrder.createOrderWithoutColors();
        Response response = step.createOrder(order);
        orderId = step.orderCreationResponseCheck(response);
    }

    @After
    public void clearData() {
        step.cancelOrder(orderId);
    }
}