package builder;

import com.google.gson.Gson;
import io.restassured.response.Response;
import models.Order;

import static io.restassured.RestAssured.given;

public class OrderRequestBuilder extends MainClass {
    private final static String API_ORDERS = "/api/v1/orders";
    private final static Gson gson = new Gson();

    public Response createOrder(Order order) {
        Response response =
                given()
                        .spec(getRequestSpecification())
                        .header("Content-type", "application/json")
                        .and()
                        .body(gson.toJson(order))
                        .when()
                        .post(API_ORDERS);
        return response;
    }

    public void cancelOrder(Integer orderId) {
        if (!(orderId == null)) {
            given()
                    .spec(getRequestSpecification())
                    .header("Content-type", "application/json")
                    .when()
                    .put(API_ORDERS + "/cancel?track={track}", orderId);
        }
    }

    public Response getOrderList() {
        return
                given()
                        .spec(getRequestSpecification())
                        .get(API_ORDERS);
    }
}