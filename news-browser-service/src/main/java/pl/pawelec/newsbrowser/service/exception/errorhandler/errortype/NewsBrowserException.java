package pl.pawelec.newsbrowser.service.exception.errorhandler.errortype;

import pl.pawelec.newsbrowser.service.exception.errorcode.ErrorCode;
import pl.pawelec.newsbrowser.service.utils.TimeUtils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsBrowserException extends RuntimeException {

    private String errorId;
    private String errorCode;
    private String errorMessage;
    private ZonedDateTime occurrenceDate = TimeUtils.now();
    private List<String> parameters = new ArrayList<>();

    public NewsBrowserException() {
    }

    public NewsBrowserException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getValue();
        this.errorMessage = errorCode.getMessage();
    }

    public NewsBrowserException(ErrorCode errorCode, String... parameters) {
        super(getMessageWithParameters(errorCode.getMessage(), parameters));
        this.errorCode = errorCode.getValue();
        this.errorMessage = getMessageWithParameters(errorCode.getMessage(), parameters);
        this.parameters = Arrays.asList(parameters);
    }

    private static String getMessageWithParameters(String message, String[] parameters) {
        return String.format(message, parameters);
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ZonedDateTime getOccurrenceDate() {
        return occurrenceDate;
    }

    public void setOccurrenceDate(ZonedDateTime occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}