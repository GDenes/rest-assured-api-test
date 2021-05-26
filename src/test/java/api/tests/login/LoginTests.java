package api.tests.login;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.base.RestAssuredSetup;
import api.enums.EndPoints;
import api.enums.Method;
import java.util.HashMap;
import java.util.Map;
import models.login.Login;
import org.junit.jupiter.api.Test;

public class LoginTests extends RestAssuredSetup {

    private static final String EMAIL = "eve.holt@reqres.in";
    private static final String PASSWORD = "cityslicka";
    private static final String TOKEN = "QpwL5tke4Pnpja7X4";
    private static final String MISSING_PASSWORD = "Missing password";
    private static final String MISSING_EMAIL_OR_USERNAME = "Missing email or username";


    @Test
    public void successfulLoginTest() {
        Login loginData = Login.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        final String res =
                getResponse(Method.POST, getRequestSpecification(loginData), EndPoints.LOGIN).asString();
        logger.info(res);
        assertTrue(res.contains(TOKEN));
    }

    @Test
    public void missingPasswordLoginTest() {
        Login loginData = Login.builder()
                .email(EMAIL)
                .build();

        final String res =
                getResponse(Method.POST, getRequestSpecification(loginData), EndPoints.LOGIN)
                        .asString();
        logger.info(res);
        assertTrue(res.contains(MISSING_PASSWORD));
    }

    @Test
    public void missingEmailLoginTest() {
        Login loginData = Login.builder()
                .password(PASSWORD)
                .build();

        final String res =
                getResponse(Method.POST, getRequestSpecification(loginData), EndPoints.LOGIN)
                        .asString();
        logger.info(res);
        assertTrue(res.contains(MISSING_EMAIL_OR_USERNAME));
    }
}
