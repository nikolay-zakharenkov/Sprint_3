package courier.creation;

import generators.GeneratorCourier;
import steps.TestsSteps;
import io.restassured.response.Response;
import models.Courier;
import org.junit.After;
import org.junit.Test;

public class CourierCreationPositiveTest {
    Courier courier;
    TestsSteps step = new TestsSteps();

    @Test
    public void createNewCourierTest() {
        courier = GeneratorCourier.createValidCourier();
        Response response = step.createCourier(courier);
        step.courierCreationResponseCheck(response);
    }

    @Test
    public void createSameCouriersTest() {
        courier = GeneratorCourier.createValidCourier();
        Response response = step.createCourier(courier);
        step.courierCreationResponseCheck(response);
        response = step.createCourier(courier);
        step.conflictLoginRequestResponseCheck(response);
    }

    @Test
    public void createNewCourierWithOnlyRequiredFieldsTest() {
        courier = GeneratorCourier.createValidCourier();
        courier.setFirstName(null);
        Response response = step.createCourier(courier);
        step.courierCreationResponseCheck(response);
    }

    @After
    public void clearData() {
        step.clearCourierTestData(courier);
    }
}