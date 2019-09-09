package pl.pawelec.newsbrowser.service.exception.model;

import pl.pawelec.newsbrowser.service.utils.TimeUtils;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErrorMessageModel implements Serializable {

    private static final long serialVersionUID = 478279370088610917L;

    private static final String DEFAULT_ERROR_CODE_AND_MESSAGE = "INTERNAL_SERVER_ERROR";

    private String errorId;
    private String errorSource;
    private String errorCode = DEFAULT_ERROR_CODE_AND_MESSAGE;
    private String errorMessage = DEFAULT_ERROR_CODE_AND_MESSAGE;
    private ZonedDateTime occurrenceDate;
    private List<String> parameters;

    public ErrorMessageModel() {
    }

    private ErrorMessageModel(Builder builder) {
        this.errorId = builder.errorId;
        this.errorSource = builder.errorSource;
        this.errorCode = builder.errorCode;
        this.errorMessage = builder.errorMessage;
        this.occurrenceDate = builder.occurrenceDate;
        this.parameters = builder.parameters;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getErrorSource() {
        return errorSource;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ZonedDateTime getOccurrenceDate() {
        return occurrenceDate;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public static class Builder {
        private String errorId;
        private String errorSource;
        private String errorCode;
        private String errorMessage;
        private ZonedDateTime occurrenceDate = TimeUtils.now();
        private List<String> parameters = new ArrayList<>();

        public Builder(String errorId, String errorSource) {
            this.errorId = errorId;
            this.errorSource = errorSource;
        }

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder occurrenceDate(ZonedDateTime occurrenceDate) {
            this.occurrenceDate = occurrenceDate;
            return this;
        }

        public Builder parameters(List<String> parameters) {
            this.parameters = parameters;
            return this;
        }

        public ErrorMessageModel build() {
            return new ErrorMessageModel(this);
        }
    }
}