package pl.pawelec.newsbrowser.service.exception.errorhandler.errortype;

import pl.pawelec.newsbrowser.service.exception.errorcode.ErrorCode;

public class NotFoundException extends NewsBrowserException {

    public NotFoundException(ErrorCode errorCode, String... parameters) {
        super(errorCode, parameters);
    }
}
