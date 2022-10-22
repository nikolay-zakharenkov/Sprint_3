package builder;

import com.google.gson.Gson;
import io.restassured.response.Response;
import models.Courier;

import static io.restassured.RestAssured.given;

public class CourierRequestBuilder extends MainClass {
    private final static String API_COURIER = "/api/v1/courier/";
    private final static Gson gson = new Gson();

    public Response createCourier(Courier courier) {
        return
                given()
                        .spec(getRequestSpecification())
                        .header("Content-type", "application/json")
                        .and()
                        .body(gson.toJson(courier))
                        .when()
                        .post(API_COURIER);
    }

    public void deleteCourierTestData (Courier courier) {
        Response response = loginCourier(courier);
        if (!(response.then().extract().body().path("id") == null)) {
            int courierId = response.then().extract().body().path("id");
            given()
                    .spec(getRequestSpecification())
                    .delete(API_COURIER + courierId);
        }
    }

    public Response loginCourier (Courier courier) {
        return given()
                .spec(getRequestSpecification())
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(courier.getLoginData()))
                .when()
                .post(API_COURIER + "login");
    }
}