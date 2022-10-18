package tests;

import java.io.File;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import steps.TestsSteps;

public class LoginCourierTests {
    File newCourier = new File("src/test/resources/courier/newSprint3CourierWithAllFields.json");
    File loginNewCourier = new File("src/test/resources/login/loginSprint3NewCourier.json");
    TestsSteps step = new TestsSteps();

    @After
    public void tearDown() {
        step.deleteTestDataStep(loginNewCourier);
        step.createCourierStep(newCourier);
    }

    @Test
    public void successLoginCourierTest() {
        Response response = step.loginCourierStep(loginNewCourier);
        step.compareBodyAndStatusCodeForSuccessLoginRequest(response);
    }

    @Test
    public void loginCourierWithMistakeInLoginFieldTest() {File loginCourier = new File("src/test/resources/login/loginNewSprint3CourierWithMistakeInLogin.json");
        Response response = step.loginCourierStep(loginCourier);
        step.compareBodyAndStatusCodeForNotFoundRequest(response);
    }

    @Test
    public void loginCourierWithMistakeInPasswordFieldTest() {
        File loginCourier = new File("src/test/resources/login/loginNewSprint3CourierWithMistakeInPassword.json");
        Response response = step.loginCourierStep(loginCourier);
        step.compareBodyAndStatusCodeForNotFoundRequest(response);
    }

    @Test
    public void loginCourierWithoutLoginFieldTest() {
        File loginCourier = new File("src/test/resources/login/loginNewSprint3CourierWithoutLogin.json");
        Response response = step.loginCourierStep(loginCourier);
        step.compareBodyAndStatusCodeForBadRequest(response);
    }

}
