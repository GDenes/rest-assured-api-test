package models.reqres.user;

import io.qameta.allure.Step;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;
import models.base.BaseModel;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "email", "first_name", "last_name", "avatar"})
public class Data extends BaseModel {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("email")
    public String email;
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("last_name")
    public String lastName;
    @JsonProperty("avatar")
    public String avatar;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Step("Get `Data` id")
    public Integer getId() {
        logger.info("Get `Data` with {} id", id);
        return id;
    }

    @Step("Set `Data` id: {id}")
    public void setId(Integer id) {
        logger.info("Set `Data` with {} id", id);
        this.id = id;
    }

    @Step("Get `Data` email")
    public String getEmail() {
        logger.info("Get email");
        return email;
    }

    @Step("Set email: {email}")
    public void setEmail(String email) {
        this.email = email;
    }

    @Step("Get fist name")
    public String getFirstName() {
        logger.info("Get first name");
        return firstName;
    }

    @Step("Set first name with `{firstName}`")
    public void setFirstName(String firstName) {
        logger.info("Set first name with `{}`", firstName);
        this.firstName = firstName;
    }

    @Step("Get last name")
    public String getLastName() {
        logger.info("Get last name");
        return lastName;
    }

    @Step("Set last name with `{lastName}`")
    public void setLastName(String lastName) {
        logger.info("Set last name with `{}`", lastName);
        this.lastName = lastName;
    }

    @Step("Get avatar")
    public String getAvatar() {
        logger.info("Get avatar");
        return avatar;
    }

    @Step("Set avatar with `{avatar}`")
    public void setAvatar(String avatar) {
        logger.info("Set avatar with `{}`", avatar);
        this.avatar = avatar;
    }

    @Step("Get additional properties")
    public Map<String, Object> getAdditionalProperties() {
        logger.info("Get additional properties");
        return additionalProperties;
    }

    @Step("Set additional properties")
    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        logger.info("Set additional properties");
        this.additionalProperties = additionalProperties;
    }

}
