package api.enums;

public enum EndPoints {

    USER("/users/2"),
    USER_BY_ID("/users/{userId}"),
    USERS("/users"),

    LOGIN("/login"),
    REGISTER("/register");

    private final String endPoint;

    EndPoints(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndpoint() {
        return endPoint;
    }

}
