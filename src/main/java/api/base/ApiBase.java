package api.base;

import api.enums.Method;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiBase {

    protected static final Logger logger = LoggerFactory.getLogger(ApiBase.class);

    public static Response getResponse(Method methodType,
            String endPoint, RequestSpecification requestSpecification) {

        Response response = null;

        switch (methodType) {
            case GET:
                response = requestSpecification.get(endPoint);
                break;
            case POST:
                response = requestSpecification.post(endPoint);
                break;
            case DELETE:
                response = requestSpecification.delete(endPoint);
                break;
            case PUT:
                response = requestSpecification.put(endPoint);
                break;
            default:
                break;
        }

        logger.info("Sending {} request to `{}{}` endpoint", methodType, RestAssured.baseURI, endPoint);

        return response;
    }

    public static RequestSpecification getRequestSpecification(ContentType contentType) {
        LogConfig logconfig = new LogConfig().enableLoggingOfRequestAndResponseIfValidationFails()
                .enablePrettyPrinting(true);
        RestAssured.config().logConfig(logconfig);
        RequestSpecification request = RestAssured.given().contentType(contentType);
        logger.info(request.log().body().toString());
        return request;
    }

    public static RequestSpecification getRequestSpecification(ContentType contentType, Object requestBody) {
        return getRequestSpecification(contentType).body(requestBody);
    }

    public static RequestSpecification getRequestSpecification(ContentType contentType, HashMap<String, ?> requestParams) {
        return  getRequestSpecification(contentType).queryParams(requestParams);
    }

}
