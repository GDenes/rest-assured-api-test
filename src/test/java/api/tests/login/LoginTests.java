package api.tests.login;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.base.ApiTestBase;
import api.enums.EndPoints;
import api.enums.Method;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.login.Login;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Reqres Api tests")
@Feature("Login tests")
public class LoginTests extends ApiTestBase {

    private static final String EMAIL = "eve.holt@reqres.in";
    private static final String PASSWORD = "cityslicka";
    private static final String TOKEN = "QpwL5tke4Pnpja7X4";
    private static final String MISSING_PASSWORD = "Missing password";
    private static final String MISSING_EMAIL_OR_USERNAME = "Missing email or username";

    @Test
    @Story("Testing success login functionality")
    @DisplayName("Successfully login test")
    @Description("In this case, test login with valid credentials")
    public void successfulLoginTest() {
        final Login loginData = new Login();
        loginData.setEmail(EMAIL);
        loginData.setPassword(PASSWORD);

        final String res =
                getResponse(Method.POST, getRequestSpecification(loginData), EndPoints.LOGIN).asString();
        logger.info(res);
        assertTrue(res.contains(TOKEN));
    }

    @Test
    @Story("Testing login functionality - negative scenario")
    @DisplayName("Missing password login test")
    @Description("In this case, test login without password")
    public void missingPasswordLoginTest() {
        final Login loginData = new Login();
        loginData.setEmail(EMAIL);

        final String res =
                getResponse(Method.POST, getRequestSpecification(loginData), EndPoints.LOGIN)
                        .asString();
        logger.info(res);
        assertTrue(res.contains(MISSING_PASSWORD));
    }

    @Test
    @Story("Testing login functionality - negative scenario")
    @DisplayName("Missing email login test")
    @Description("In this case, test login without email")
    public void missingEmailLoginTest() {
        final Login loginData = new Login();
        loginData.setPassword(PASSWORD);

        final String res =
                getResponse(Method.POST, getRequestSpecification(loginData), EndPoints.LOGIN)
                        .asString();
        logger.info(res);
        assertTrue(res.contains(MISSING_EMAIL_OR_USERNAME));
    }
}
