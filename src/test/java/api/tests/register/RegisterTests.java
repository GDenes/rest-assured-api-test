package api.tests.register;

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
        Login loginData = Login.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        final String res =
                getResponse(Method.POST, getRequestSpecification(loginData), EndPoints.REGISTER).asString();
        logger.info(res);
        assertTrue(res.contains(TOKEN));
    }

    @Test
    @Story("Negative register scenarios")
    @DisplayName("Missing password register test")
    @Description("In this case, test registering user without password")
    public void missingPasswordRegisterTest() {
        Login loginData = Login.builder()
                .email(EMAIL)
                .build();

        final String res =
                getResponse(Method.POST, getRequestSpecification(loginData), EndPoints.REGISTER)
                        .asString();
        logger.info(res);
        assertTrue(res.contains(MISSING_PASSWORD));
    }

    @Test
    @Story("Negative register scenarios")
    @DisplayName("Missing username register test")
    @Description("In this case, test registering user without username")
    public void missingIUsernameRegisterTest() {
        Login loginData = Login.builder()
                .password(PASSWORD)
                .build();

        final String res =
                getResponse(Method.POST, getRequestSpecification(loginData), EndPoints.REGISTER)
                        .asString();
        logger.info(res);
        assertTrue(res.contains(MISSING_EMAIL_OR_USERNAME));
    }
}
