package tests;

import java.io.File;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import steps.TestsSteps;

public class ListOfOrdersTests {
    TestSteps step = new TestSteps();

    @After
    public void tearDown() {
        step.cancelOrderStep();
        step.clearCash();
    }

    @Test
    public void getListOfOrdersTest() {
        File newOrder = new File("src/test/resources/order/newOrderWithGreyColor.json");
        Response responseList = step.getListOfOrdersStep();
        step.compareBodyAndStatusCodeForGetListRequest(responseList);
    }
}
