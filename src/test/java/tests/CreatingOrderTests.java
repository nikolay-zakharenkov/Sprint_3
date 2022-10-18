package tests;

import java.io.File;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import steps.TestsSteps;

public class CreatingOrderTests {
    TestsSteps step = new TestsSteps();


    @After
    public void tearDown() {
        step.cancelOrderStep();
        step.clearCash();
    }

    @Test
    public void createNewOrderWithBlackColorTest() {
        File newOrder = new File("src/test/resources/order/newOrderWithBlackColor.json");
        Response response = step.createOrderStep(newOrder);
        step.compareBodyAndStatusCodeForCreatedOrderRequest(response);
    }

    @Test
    public void createNewOrderWithGreyColorTest() {
        File newOrder = new File("src/test/resources/order/newOrderWithGreyColor.json");
        Response response = step.createOrderStep(newOrder);
        step.compareBodyAndStatusCodeForCreatedOrderRequest(response);
    }

    @Test
    public void createNewOrderWithBlackAndGreyColorsTest() {
        File newOrder = new File("src/test/resources/order/newOrderWithAllColors.json");
        Response response = step.createOrderStep(newOrder);
        step.compareBodyAndStatusCodeForCreatedOrderRequest(response);
    }

    @Test
    public void createNewOrderWithoutColorTest() {
        File newOrder = new File("src/test/resources/order/newOrderWithoutColor.json");
        Response response = step.createOrderStep(newOrder);
        step.compareBodyAndStatusCodeForCreatedOrderRequest(response);
    }
}
