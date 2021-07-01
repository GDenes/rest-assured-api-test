package models.login;

import io.qameta.allure.Step;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import models.base.BaseModel;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "email",
        "password"
})
public class Login extends BaseModel {

    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;

    public Login(){
        super();
    }

    @Step("Get email address from `Login` model")
    public String getEmail() {
        logger.info("Get email address from `Login` model");
        return email;
    }

    @Step("Set `Login` model email: {email}")
    public void setEmail(String email) {
        logger.info("Set `Login` model email: {}", email);
        this.email = email;
    }

    @Step("Get password from `Login` model")
    public String getPassword() {
        logger.info("Get password from `Login` model");
        return password;
    }

    @Step("Set `Login` model password: {password}")
    public void setPassword(String password) {
        logger.info("Set `Login` model password: {}", password);
        this.password = password;
    }

}
