package backend;

import edu.miu.cs545.store.dto.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StoreRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testCRUDProductWithValidData() {
        // create product
        ProductDTO productDTO = new ProductDTO("T0001", "Test Prod1", 123.12, "Desc", 10);
        given()
                .contentType("application/json")
                .body(productDTO)
                .when().post("/products")
                .then()
                .statusCode(201);
        // get product
        given()
                .when()
                .get("products/" + productDTO.getProductNumber())
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("productNumber", equalTo("T0001"))
                .body("name", equalTo("Test Prod1"))
                .body("price", equalTo(123.12f))
                .body("description", equalTo("Desc"))
                .body("numberInStock", equalTo(10));

        // update product
        ProductDTO updatedProductDTO = new ProductDTO("T0001", "Test Prod1(updated)", 1223.12, "Desc(updated)", 100);

        given()
                .when()
                .contentType("application/json")
                .body(updatedProductDTO)
                .when()
                .put("products/" + productDTO.getProductNumber())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .and()
                .body("productNumber", equalTo("T0001"))
                .body("name", equalTo("Test Prod1(updated)"))
                .body("price", equalTo(1223.12f))
                .body("description", equalTo("Desc(updated)"))
                .body("numberInStock", equalTo(100));

        //cleanup
        given()
                .when()
                .delete("products/T0001");
    }

    @Test
    public void testPlaceOrderWithValidData() {

        // create temp product
        ProductDTO productDTO = new ProductDTO("T0001", "Test Prod1", 123.12, "Desc", 10);
        given()
                .contentType("application/json")
                .body(productDTO)
                .when().post("/products")
                .then()
                .statusCode(201);

        // create order
        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO(
                "John Doe", "jdoe@mail.com", "1123456789", "Street", "Fairfield", "IA", "52566");

        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO("Master", "1234123413241234", LocalDate.of(2022, 8, 1), "123");

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setPersonalInfo(personalInfoDTO);
        orderDTO.setPaymentInfo(paymentInfoDTO);

        OrderItemDTO orderItemDTO = new OrderItemDTO(productDTO, 5);

        orderDTO.setItems(List.of(orderItemDTO));

        given()
                .contentType("application/json")
                .body(orderDTO)
                .when().post("/orders")
                .then()
                .statusCode(201);

        // check numberInStock of the product
        given()
                .when()
                .get("products/" + productDTO.getProductNumber())
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("numberInStock", equalTo(5));

        //cleanup
        given()
                .when()
                .delete("products/T0001");
    }

    @Test
    public void testPlaceOrderWithNonExistingProduct() {
        // create order
        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO(
                "John Doe", "jdoe@mail.com", "1123456789", "Street", "Fairfield", "IA", "52566");

        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO("Master", "1234123413241234", LocalDate.of(2022, 8, 1), "123");

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setPersonalInfo(personalInfoDTO);
        orderDTO.setPaymentInfo(paymentInfoDTO);

        ProductDTO productDTO = new ProductDTO("T0002", "Not existing product", 123.12, "Desc", 10);

        OrderItemDTO orderItemDTO = new OrderItemDTO(productDTO, 10);

        orderDTO.setItems(List.of(orderItemDTO));

        given()
                .contentType("application/json")
                .body(orderDTO)
                .when().post("/orders")
                .then()
                .statusCode(404);
    }

    @Test
    public void testPlaceOrderInsufficientStock() {

        // create temp product
        ProductDTO productDTO = new ProductDTO("T0001", "Test Prod1", 123.12, "Desc", 2);
        given()
                .contentType("application/json")
                .body(productDTO)
                .when().post("/products")
                .then()
                .statusCode(201);

        // create order
        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO(
                "John Doe", "jdoe@mail.com", "1123456789", "Street", "Fairfield", "IA", "52566");

        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO("Master", "1234123413241234", LocalDate.of(2022, 8, 1), "123");

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setPersonalInfo(personalInfoDTO);
        orderDTO.setPaymentInfo(paymentInfoDTO);

        OrderItemDTO orderItemDTO = new OrderItemDTO(productDTO, 5);

        orderDTO.setItems(List.of(orderItemDTO));

        given()
                .contentType("application/json")
                .body(orderDTO)
                .when().post("/orders")
                .then()
                .statusCode(400)
                .and()
                .body(equalTo("There are not enough items of the product T0001 in stock"));

        //cleanup
        given()
                .when()
                .delete("products/T0001");
    }

    @Test
    public void testChangeOrderStatus() {

        // create temp product
        Long orderId = 1626306711418L;

        given()
                .contentType("application/json")
                .when()
                .put("/orders/" + orderId + "?status=DELIVERED")
                .then()
                .statusCode(200);

        // check changed status
        given()
                .when()
                .get("orders/" + orderId)
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("status", equalTo("DELIVERED"));

        //cleanup set back to shipped status
        given()
                .contentType("application/json")
                .when().put("/orders/" + orderId + "?status=SHIPPED");
    }

}
