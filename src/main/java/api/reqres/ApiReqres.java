package api.reqres;

import api.base.ApiBase;
import api.enums.EndPoints;
import api.enums.Method;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;
import models.reqres.login.Login;

public class ApiReqres extends ApiBase {

    private static final String BASE_URL = "https://reqres.in/api";

    static {
        String reqresBaseUrl = System.getProperty("reqresBaseUrl");
        RestAssured.baseURI = reqresBaseUrl == null ? BASE_URL : reqresBaseUrl;
    }

    public static Response getLoginResponse(final Login loginData) {
        return getResponse(Method.POST, EndPoints.LOGIN.toString(), getRequestSpecification(ContentType.JSON, loginData));
    }

    public static Response getRegisterResponse(final Login loginData) {
        return getResponse(Method.POST, EndPoints.REGISTER.getEndpoint(), getRequestSpecification(ContentType.JSON, loginData));
    }

    public static Response getUserResponse(final Method methodType, final Map<String, ?> requestBody) {
        return getResponse(methodType, EndPoints.USER.getEndpoint(), getRequestSpecification(ContentType.JSON, requestBody));
    }

    public static Response getUserResponse() {
        return getResponse(Method.GET, EndPoints.USER.getEndpoint(), getRequestSpecification(ContentType.JSON));
    }

    public static Response getUserResponse(final Map<String, ?> pathParam) {
        return getResponse(Method.GET, EndPoints.USER_BY_ID.getEndpoint(),
                getRequestSpecification(ContentType.JSON).pathParams(pathParam));
    }

    public static Response getUpdateUserResponse(Method methodType, final Map<String, ?> pathParam,
            final Map<String, ?> requestBody) {
        return getResponse(methodType, EndPoints.USER_BY_ID.getEndpoint(),
                getRequestSpecification(ContentType.JSON,requestBody).pathParams(pathParam));
    }

    public static Response getDeleteUserResponse(final Map<String, ?> pathParams) {
        return getResponse(Method.DELETE, EndPoints.USER_BY_ID.getEndpoint(),
                getRequestSpecification(ContentType.JSON).pathParams(pathParams));
    }

}
