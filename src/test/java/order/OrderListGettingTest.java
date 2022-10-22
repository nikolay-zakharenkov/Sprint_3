package order;

import generators.GeneratorOrder;
import steps.TestsSteps;
import io.restassured.response.Response;
import models.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class OrderListGettingTest {
    TestsSteps step = new TestsSteps();

    Integer orderId;

    @Before
    public void createOrder() {
        Order order = GeneratorOrder.createBlackOrder();
        Response response = step.createOrder(order);
        orderId = step.orderCreationResponseCheck(response);
    }

    @Test
    public void getOrderListTest() {
        Response responseList = step.getOrderList();
        step.getOrderListResponseCheck(responseList);
    }

    @After
    public void deleteOrder() {
        step.cancelOrder(orderId);
    }
}