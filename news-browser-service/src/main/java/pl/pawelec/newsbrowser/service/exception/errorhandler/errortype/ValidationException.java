package pl.pawelec.newsbrowser.service.exception.errorhandler.errortype;

import pl.pawelec.newsbrowser.service.exception.errorcode.ErrorCode;

public class ValidationException extends NewsBrowserException {

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ValidationException(ErrorCode errorCode, String... parameters) {
        super(errorCode, parameters);
    }
}