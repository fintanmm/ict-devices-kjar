
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
// import io.restassured.RestAssured.*;
// import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContainers {


    // public void setUp(String baseURI, String method, List headers) {
    //     RestAssured.baseURI = baseURI;
    //     Response response = RestAssured.given().header(headers).get();
    // }

    @Test
    public void testIfContaierIsRunning() {
        RestAssured.baseURI = "http://localhost:8090/rest/server/containers";
        Response response = RestAssured.given().accept("application/json").when().get();
      
        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("SUCCESS"));    
    }

    @Test
    public void testContainerIsStarted() {

        RestAssured.baseURI = "http://localhost:8090/rest/server/containers";
        RestAssured.given().accept("application/json").when().get().
            then().assertThat().body("result.kie-containers.kie-container[0].status", equalTo("STARTED"));
    }

    @Test
    public void testContainerName() {

        RestAssured.baseURI = "http://localhost:8090/rest/server/containers/ict-devices-kjar-1_0-SNAPSHOT";
        RestAssured.given().accept("application/json").when().get().
            then().assertThat().body("type", equalTo("SUCCESS"));
    }
}