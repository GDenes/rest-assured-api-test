package models.reqres.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.qameta.allure.Step;
import lombok.ToString;
import models.base.BaseModel;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"url", "text"})
public class Support extends BaseModel {

    @JsonProperty("url")
    public String url;
    @JsonProperty("text")
    public String text;

    @Step("Get url")
    public String getUrl() {
        logger.info("Get url");
        return url;
    }

    @Step("Set url with `{url}` value")
    public void setUrl(String url) {
        logger.info("Set url `{}`", url);
        this.url = url;
    }

    @Step("Get text")
    public String getText() {
        logger.info("Get text");
        return text;
    }

    @Step("Set text with `{text}` value")
    public void setText(String text) {
        logger.info("Set text with `{}` value", text);
        this.text = text;
    }

}
