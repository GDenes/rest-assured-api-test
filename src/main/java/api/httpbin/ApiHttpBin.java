package api.httpbin;

import api.base.ApiBase;
import api.enums.Method;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;

public class ApiHttpBin extends ApiBase {

    private static final String BASE_URL = "http://httpbin.org";

    static {
        String httpBinBaseUrl = System.getProperty("httpBinBaseUrl");
        RestAssured.baseURI = httpBinBaseUrl == null ? BASE_URL : httpBinBaseUrl;
    }

    public static Response getFileUploadResponse(File file) {
        return getResponse(Method.POST, "/post",
                getRequestSpecification(ContentType.MULTIPART).multiPart(file));
    }

    public static Response getImageResponse() {
        return getResponse(Method.GET, "/image/jpeg",
                getRequestSpecification(ContentType.JSON));
    }

}
