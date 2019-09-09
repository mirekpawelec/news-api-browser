package pl.pawelec.newsbrowser.service.exception.errorhandler.errortype;

import pl.pawelec.newsbrowser.service.exception.errorcode.ErrorCode;

public class ConnectionException extends NewsBrowserException {

    public ConnectionException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ConnectionException(ErrorCode errorCode, String... parameters) {
        super(errorCode, parameters);
    }
}
