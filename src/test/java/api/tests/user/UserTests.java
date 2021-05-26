package api.tests.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.enums.EndPoints;
import api.enums.Method;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import api.base.RestAssuredSetup;
import models.user.User;

public class UserTests extends RestAssuredSetup {

    private static final String EMAIL_TEST_DATA = "janet.weaver@reqres.in";

    @Test public void getUserTest() {
        final User user =
                getResponse(Method.GET, getRequestSpecification(), EndPoints.USER).as(User.class);
        assertEquals(EMAIL_TEST_DATA, user.getData().getEmail(), "Un correct email address");
    }

    @Test public void createUserTest() {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");

        final String res =
                getResponse(Method.POST, getRequestSpecification(requestBody), EndPoints.USERS)
                        .asPrettyString();
        logger.info(res);
    }

    @Test public void getAllUserTest() {
        Map<String, Integer> requestParams = new HashMap<String, Integer>();
        requestParams.put("page", 2);
        final String res =
                getResponse(Method.GET, getRequestSpecification(requestParams), EndPoints.USERS)
                        .asPrettyString();
        logger.info(res);
    }

    @Test public void getUserByIdTest() {
        final User user = getResponse(Method.GET, getRequestSpecification().pathParam("userId", 4),
                EndPoints.USER_BY_ID).as(User.class);
        assertEquals("eve.holt@reqres.in", user.getData().getEmail(), "Un correct email address");
        logger.info(user.toString());
    }

}
