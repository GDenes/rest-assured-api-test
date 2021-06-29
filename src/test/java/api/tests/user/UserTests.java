package api.tests.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.enums.EndPoints;
import api.enums.Method;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.base.ApiTestBase;
import models.user.User;

@Epic("Reqres Api tests")
@Feature("User tests")
public class UserTests extends ApiTestBase {

    private static final String EMAIL_TEST_DATA = "janet.weaver@reqres.in";

    @Test
    @Story("Testing user functionality")
    @DisplayName("User retrieval test")
    @Description("In this case, test retrieval user by id")
    public void getUserTest() {
        final User user =
                getResponse(Method.GET, getRequestSpecification(), EndPoints.USER).as(User.class);
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

        final String res =
                getResponse(Method.POST, getRequestSpecification(requestBody), EndPoints.USERS)
                        .asPrettyString();
        logger.info(res);
    }

    @Test
    @Story("Testing user functionality")
    @DisplayName("Get all user test")
    @Description("In this case, test retrieval all user")
    public void getAllUserTest() {
        Map<String, Integer> requestParams = new HashMap<String, Integer>();
        requestParams.put("page", 2);
        final String res =
                getResponse(Method.GET, getRequestSpecification(requestParams), EndPoints.USERS)
                        .asPrettyString();
        logger.info(res);
    }

    @Test
    @Story("Testing user functionality")
    @DisplayName("Get user by id test")
    @Description("In this case, test retrieval user by id")
    public void getUserByIdTest() {
        final User user = getResponse(Method.GET,
                getRequestSpecification().pathParam("userId", 4),
                EndPoints.USER_BY_ID).as(User.class);
        assertEquals("eve.holt@reqres.in", user.getData().getEmail(), "Un correct email address");
        logger.info(user.toString());
    }

    @Test
    @Story("Testing user functionality")
    @DisplayName("Update user by id test")
    @Description("In this case, test retrieval user by id")
    public void updateUserTest() {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "zion resident");

        final String res =
                getResponse(Method.PUT, getRequestSpecification(requestBody)
                        .pathParam("userId", 2), EndPoints.USER_BY_ID)
                        .asPrettyString();
        logger.info(res);
    }

    @Test
    @Story("Testing user functionality")
    @DisplayName("Delete user by id test")
    @Description("In this case, test deleting user by id")
    public void deleteUserTest() {
        final int res = getResponse(Method.DELETE,
                getRequestSpecification().pathParam("userId", 2),
                EndPoints.USER_BY_ID).getStatusCode();
        logger.info(String.valueOf(res));
        assertEquals(res, 204, "User id is not valid, can not be deleted");
    }

}
