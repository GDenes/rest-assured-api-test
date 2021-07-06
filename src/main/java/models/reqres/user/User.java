package models.reqres.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.qameta.allure.Step;
import lombok.ToString;
import models.base.BaseModel;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"data", "support"})
public class User extends BaseModel {

    @JsonProperty("data")
    public Data data;
    @JsonProperty("support")
    public Support support;

    @Step("Get `Data` from user object")
    public Data getData() {
        logger.info("Get `Data` from user object");
        return data;
    }

    @Step("Set `Data` with `{data}`")
    public void setData(Data data) {
        logger.info("Set data with `{}`", data);
        this.data = data;
    }

    @Step("Get support")
    public Support getSupport() {
        logger.info("Get support");
        return support;
    }

    @Step("Set support with `{support}` value")
    public void setSupport(Support support) {
        logger.info("Set support with `{}` value", support);
        this.support = support;
    }

}
