package api.tests.reqres.register;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.enums.EndPoints;
import api.enums.Method;
import api.reqres.ApiReqres;
import api.tests.base.ApiTestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.reqres.login.Login;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Reqres Api tests")
@Feature("Register tests")
public class RegisterTests extends ApiTestBase {

    private static final String EMAIL = "eve.holt@reqres.in";
    private static final String PASSWORD = "pistol";
    private static final String TOKEN = "QpwL5tke4Pnpja7X4";
    private static final String MISSING_PASSWORD = "Missing password";
    private static final String MISSING_EMAIL_OR_USERNAME = "Missing email or username";

    @Test
    @Story("Positive register scenarios")
    @DisplayName("Success register test")
    @Description("In this case, test registering user")
    public void successfulRegisterTest() {
        final Login loginData = new Login();
        loginData.setEmail(EMAIL);
        loginData.setPassword(PASSWORD);

        final String res = ApiReqres.getRegisterResponse(loginData).asString();
        logger.info(res);
        assertTrue(res.contains(TOKEN));
    }

    @Test
    @Story("Negative register scenarios")
    @DisplayName("Missing password register test")
    @Description("In this case, test registering user without password")
    public void missingPasswordRegisterTest() {
        final Login loginData = new Login();
        loginData.setEmail(EMAIL);

        final String res = ApiReqres.getRegisterResponse(loginData).asString();
        logger.info(res);
        assertTrue(res.contains(MISSING_PASSWORD));
    }

    @Test
    @Story("Negative register scenarios")
    @DisplayName("Missing username register test")
    @Description("In this case, test registering user without username")
    public void missingIUsernameRegisterTest() {
        final Login loginData = new Login();
        loginData.setPassword(PASSWORD);

        final String res = ApiReqres.getRegisterResponse(loginData).asString();
        logger.info(res);
        assertTrue(res.contains(MISSING_EMAIL_OR_USERNAME));
    }
}
