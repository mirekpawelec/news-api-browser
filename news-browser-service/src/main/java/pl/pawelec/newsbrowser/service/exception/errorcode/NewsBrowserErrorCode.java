package pl.pawelec.newsbrowser.service.exception.errorcode;

public enum NewsBrowserErrorCode implements ErrorCode {

    NB_0001("Any news not found (country= %s, category= %s)."),
    NB_0002("The filter keys (country, category) are required."),
    NB_0003("Connection with server can't be estabilished (url= %s).");

    private String message;

    NewsBrowserErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getValue() {
        return name();
    }
}
