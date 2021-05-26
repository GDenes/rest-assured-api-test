package api.tests.register;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.base.RestAssuredSetup;
import api.enums.EndPoints;
import api.enums.Method;
import models.login.Login;
import org.junit.jupiter.api.Test;

public class RegisterTests extends RestAssuredSetup {
    private static final String EMAIL = "eve.holt@reqres.in";
    private static final String PASSWORD = "pistol";
    private static final String TOKEN = "QpwL5tke4Pnpja7X4";
    private static final String MISSING_PASSWORD = "Missing password";
    private static final String MISSING_EMAIL_OR_USERNAME = "Missing email or username";


    @Test
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
