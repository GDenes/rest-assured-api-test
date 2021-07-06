package api.tests.reqres.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.enums.EndPoints;
import api.enums.Method;
import api.reqres.ApiReqres;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import api.tests.base.ApiTestBase;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import models.reqres.user.User;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Reqres Api tests")
@Feature("User tests")
public class UserTests extends ApiTestBase {

    private static final String EMAIL_TEST_DATA = "janet.weaver@reqres.in";

    @Test
    @Story("Testing user functionality")
    @DisplayName("User retrieval test")
    @Description("In this case, test retrieval user by id")
    public void getUserTest() {
        final User user = ApiReqres.getUserResponse().as(User.class);

        assertEquals(EMAIL_TEST_DATA, user.getData().getEmail(), "Un correct email address");
    }

    @Test
    @Story("Testing user functionality")
    @DisplayName("Create user test")
    @Description("In this case, test login without password")
    public void createUserTest() {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");

        final String res = ApiReqres.getUserResponse(Method.POST, requestBody).asString();

        final JSONObject resJsonObject = new JSONObject(res);
        assertEquals(resJsonObject.get("name"), requestBody.get("name"), "Un correct name");
        assertEquals(resJsonObject.get("job"), requestBody.get("job"), "Un correct job");

        logger.info(res);
    }

    @Test
    @Story("Testing user functionality")
    @DisplayName("Get all user test")
    @Description("In this case, test retrieval all user")
    public void getAllUserTest() {
        Map<String, Integer> requestParams = new HashMap<String, Integer>();
        requestParams.put("page", 2);

        final String res = ApiReqres.getUserResponse(Method.POST, requestParams).asString();
        final JSONObject resJsonObject = new JSONObject(res);

        assertEquals(requestParams.get("page"), resJsonObject.get("page"),
                "Invalid response page number");
        logger.info(res);
    }

    @Test
    @Story("Testing user functionality")
    @DisplayName("Get user by id test")
    @Description("In this case, test retrieval user by id")
    public void getUserByIdTest() {
        Map<String, Integer> pathParams = new HashMap<String, Integer>();
        pathParams.put("userId", 4);

        final User user = ApiReqres.getUserResponse(pathParams).as(User.class);

        assertEquals("eve.holt@reqres.in", user.getData().getEmail(), "Un correct email address");
        logger.info(user.toString());
    }

    @Test
    @Story("Testing user functionality")
    @DisplayName("Update user by id test")
    @Description("In this case, test retrieval user by id")
    public void updateUserTest() {
        final Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "zion resident");

        final Map<String, Integer> pathParams = new HashMap<>();
        pathParams.put("userId", 2);

        final String res = ApiReqres.getUpdateUserResponse(Method.PUT, pathParams, requestBody)
                .asString();

        final JSONObject jsonObject = new JSONObject(res);

        assertEquals(requestBody.get("name"), jsonObject.get("name"), "Name didn't updated");
        assertEquals(requestBody.get("job"), jsonObject.get("job"), "Job didn't updated");
        logger.info(res);
    }

    @Test
    @Story("Testing user functionality")
    @DisplayName("Delete user by id test")
    @Description("In this case, test deleting user by id")
    public void deleteUserTest() {
        final Map<String, Integer> pathParams = new HashMap<>();
        pathParams.put("userId", 2);

        final int res = ApiReqres.getDeleteUserResponse(pathParams).getStatusCode();
        logger.info(String.valueOf(res));
        assertEquals(204, res, "User id is not valid, can not be deleted");
    }

}
