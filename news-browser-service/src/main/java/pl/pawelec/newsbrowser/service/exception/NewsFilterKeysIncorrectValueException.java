package pl.pawelec.newsbrowser.service.exception;

import pl.pawelec.newsbrowser.service.exception.errorhandler.errortype.ValidationException;
import pl.pawelec.newsbrowser.service.exception.errorcode.NewsBrowserErrorCode;

public class NewsFilterKeysIncorrectValueException extends ValidationException {

    public NewsFilterKeysIncorrectValueException() {
        super(NewsBrowserErrorCode.NB_0002);
    }
}

