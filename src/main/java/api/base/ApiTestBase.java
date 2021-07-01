package api.base;

import extensions.LogExtension;
import extensions.LogFileAttachToAllureReport;
import java.util.HashMap;
import api.enums.EndPoints;
import api.enums.Method;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(LogExtension.class)
@ExtendWith(LogFileAttachToAllureReport.class)
public class ApiTestBase {

    protected static final Logger logger = LoggerFactory.getLogger(ApiTestBase.class);

    private static final String BASE_URL = "https://reqres.in/api";
    private static String baseApiValue;

    static {
        baseApiValue = System.getenv("BASE_API");
    }

    public RequestSpecification getRequestSpecification() {
        RestAssured.baseURI = baseApiValue == null ? BASE_URL : baseApiValue;
        LogConfig logconfig = new LogConfig().enableLoggingOfRequestAndResponseIfValidationFails()
                .enablePrettyPrinting(true);
        RestAssured.config().logConfig(logconfig);
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
        logger.info(request.log().body().toString());
        return request;
    }

    public RequestSpecification getRequestSpecification(Object requestBody) {
        return getRequestSpecification().body(requestBody);
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

        logger.info("Sending {} request to `{}{}` endpoint", methodType, RestAssured.baseURI, endPoint.getEndpoint());

        return response;
    }


}
