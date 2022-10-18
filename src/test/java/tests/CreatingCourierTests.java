package tests;

import java.io.File;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import steps.TestsSteps;

public class CreatingCourierTests {
    File newCourier = new File("src/test/resources/courier/newSprint3CourierWithAllFields.json");
    File loginNewCourier = new File("src/test/resources/login/loginSprint3NewCourier.json");
    TestsSteps step = new TestsSteps();

    @After
    public void tearDown() {
        step.deleteTestDataStep(loginNewCourier);
    }

    @Test
    public void createNewCourierTest() {
        Response response = step.createCourierStep(newCourier);
        step.compareBodyAndStatusCodeForCreatedCourierRequest(response);
    }

    @Test
    public void createSameCouriersTest() {
        step.createCourierStep(newCourier);
        Response response = step.createCourierStep(newCourier);
        step.compareMessageAndStatusCodeForConflictRequest(response);
    }

    @Test
    public void createNewCourierWithOnlyRequiredFieldsTest() {
        Response response = step.createCourierStep(newCourier);
        step.compareBodyAndStatusCodeForCreatedCourierRequest(response);
    }

    @Test
    public void createNewCourierWithOutRequiredLoginFieldTest() {
        File newCourier = new File("src/test/resources/courier/newSprint3CourierWithoutLogin.json");
        Response response = step.createCourierStep(newCourier);
        step.compareMessageAndStatusCodeForBadRequest(response);
    }

    @Test
    public void createNewCourierWithOutRequiredPasswordFieldTest() {
        File newCourier = new File("src/test/resources/courier/newSprint3CourierWithoutPassword.json");
        Response response = step.createCourierStep(newCourier);
        step.compareMessageAndStatusCodeForBadRequest(response);

    }
}
