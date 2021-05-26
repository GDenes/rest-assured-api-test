package api.base;

import java.util.HashMap;
import java.util.logging.Logger;
import api.enums.EndPoints;
import api.enums.Method;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredSetup {

    public static final Logger logger = Logger.getLogger(RestAssuredSetup.class.getName());

    private static final String BASE_URL = "https://reqres.in/api";

    public RequestSpecification getRequestSpecification() {
        RestAssured.baseURI = BASE_URL;
        LogConfig logconfig = new LogConfig().enableLoggingOfRequestAndResponseIfValidationFails()
                .enablePrettyPrinting(true);
        RestAssured.config().logConfig(logconfig);
        return RestAssured.given().contentType(ContentType.JSON);
    }

    public RequestSpecification getRequestSpecification(Object requestBody) {
        return  getRequestSpecification().body(requestBody);
    }

    public RequestSpecification getRequestSpecification(HashMap<String, ?> requestParams) {
        return  getRequestSpecification().queryParams(requestParams);
    }

    public Response getResponse(Method methodType, RequestSpecification requestSpecification,
            EndPoints endPoint) {
        
        Response response = null;

        switch (methodType) {
            case GET:
                response = requestSpecification.get(endPoint.getEndpoint());
                break;
            case POST:
                response = requestSpecification.post(endPoint.getEndpoint());
                break;
            case DELETE:
                response = requestSpecification.delete(endPoint.getEndpoint());
                break;
            case PUT:
                response = requestSpecification.put(endPoint.getEndpoint());
                break;
            default:
                break;
        }

        return response;
    }
    

}
