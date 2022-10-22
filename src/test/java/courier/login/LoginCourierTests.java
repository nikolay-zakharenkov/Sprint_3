package courier.login;

import generators.GeneratorCourier;
import steps.TestsSteps;
import io.restassured.response.Response;
import models.Courier;
import org.junit.*;

public class LoginCourierTests {
    private final TestsSteps step = new TestsSteps();
    private Courier courier;

    @Before
    public void setUp() {
        courier = GeneratorCourier.createValidCourier();
        step.createCourier(courier);
    }

    @Test
    public void successLoginCourierTest() {
        Response response = step.courierLogin(courier);
        step.successLoginResponseCheck(response);
    }

    @Test
    public void courierInvalidLoginTest() {
        courier.setLogin(courier.getLogin() + "invalid");
        Response response = step.courierLogin(courier);
        step.loginNotFoundResponseCheck(response);
    }

    @Test
    public void courierInvalidPasswordTest() {
        courier.setPassword(courier.getPassword() + "1");
        Response response = step.courierLogin(courier);
        step.loginNotFoundResponseCheck(response);
    }

    @Test
    public void loginCourierWithoutLoginFieldTest() {
        courier.setLogin(null);
        Response response = step.courierLogin(courier);
        step.notEnoughDataForLoginResponseCheck(response);
    }

    @After
    public void clearData() {
        step.clearCourierTestData(courier);
    }
}