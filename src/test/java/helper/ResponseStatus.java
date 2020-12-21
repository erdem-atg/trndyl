package helper;

public class ResponseStatus {
    public ResponseStatus(Integer statusCode, String link) {
        this.statusCode = statusCode;
        this.link = link;
    }
    public Integer getStatusCode() {
        return statusCode;
    }
    public String getLink() {
        return link;
    }

    private final Integer statusCode;
    private final String link;
}
