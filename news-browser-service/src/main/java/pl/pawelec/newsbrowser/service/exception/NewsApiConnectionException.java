package pl.pawelec.newsbrowser.service.exception;

import pl.pawelec.newsbrowser.service.exception.errorhandler.errortype.ConnectionException;

import static pl.pawelec.newsbrowser.service.exception.errorcode.NewsBrowserErrorCode.NB_0003;

public class NewsApiConnectionException extends ConnectionException {

    public NewsApiConnectionException(String url) {
        super(NB_0003, url);
    }
}

