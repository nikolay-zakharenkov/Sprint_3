package steps;

import builder.CourierRequestBuilder;
import builder.OrderRequestBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Courier;
import models.Order;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestsSteps {
    CourierRequestBuilder courierRequestBuilder = new CourierRequestBuilder();
    OrderRequestBuilder orderRequestBuilder = new OrderRequestBuilder();

    @Step("Creating new courier")
    public Response createCourier(Courier courier) {
        return courierRequestBuilder.createCourier(courier);
    }

    @Step("Removing test data")
    public void clearCourierTestData(Courier courier) {
        courierRequestBuilder.deleteCourierTestData(courier);
    }


    @Step("Login by new courier")
    public Response courierLogin(Courier courier) {
        return courierRequestBuilder.loginCourier(courier);
    }

    @Step("Creating new order")
    public Response createOrder(Order order) {
        return orderRequestBuilder.createOrder(order);
    }

    @Step("Canceling order")
    public void cancelOrder(Integer orderId) {
        orderRequestBuilder.cancelOrder(orderId);
    }

    @Step("Getting list of orders")
    public Response getOrderList() {
        return orderRequestBuilder.getOrderList();
    }

    @Step("Bad request message and status check")
    public void badRequestMessageAndStatusCheck(Response response) {
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Courier creation status and body check")
    public void courierCreationResponseCheck(Response response) {
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(SC_CREATED);
    }

    @Step("Conflict login request response check")
    public void conflictLoginRequestResponseCheck(Response response) {
        response.then().assertThat().body("message",
                        equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(SC_CONFLICT);
    }

    @Step("Success login response check")
    public void successLoginResponseCheck(Response response) {
        response.then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(SC_OK);
    }

    @Step("Login not found response check")
    public void loginNotFoundResponseCheck(Response response) {
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(SC_NOT_FOUND);
    }

    @Step("Not enough data for login response check")
    public void notEnoughDataForLoginResponseCheck(Response response) {
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Order creation response check")
    public Integer orderCreationResponseCheck(Response response) {
        response.then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(SC_CREATED);
        return response.then().extract().body().path("track");
    }

    @Step("Get order list response check")
    public void getOrderListResponseCheck(Response response) {
        response.then().assertThat().body("orders[0]", notNullValue())
                .and()
                .statusCode(SC_OK);
    }
}