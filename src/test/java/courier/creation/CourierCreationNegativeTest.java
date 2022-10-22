package courier.creation;

import generators.GeneratorCourier;
import steps.TestsSteps;
import io.restassured.response.Response;
import models.Courier;
import org.junit.Test;

public class CourierCreationNegativeTest {
    TestsSteps step = new TestsSteps();

    @Test
    public void courierCreationPasswordOnlyTest() {
        Courier courier = GeneratorCourier.createUserPasswordOnly();
        Response response = step.createCourier(courier);
        step.badRequestMessageAndStatusCheck(response);
    }

    @Test
    public void courierCreationLoginOnlyTest() {
        Courier courier = GeneratorCourier.createUserLoginOnly();
        Response response = step.createCourier(courier);
        step.badRequestMessageAndStatusCheck(response);
    }

    @Test
    public void courierCreationFirstNameOnlyTest() {
        Courier courier = GeneratorCourier.createUserFirstNameOnly();
        Response response = step.createCourier(courier);
        step.badRequestMessageAndStatusCheck(response);
    }
}