package pl.pawelec.newsbrowser.service.exception;

import pl.pawelec.newsbrowser.service.exception.errorhandler.errortype.NotFoundException;

import static pl.pawelec.newsbrowser.service.exception.errorcode.NewsBrowserErrorCode.NB_0001;

public class AnyNewsNotFoundException extends NotFoundException {

    public AnyNewsNotFoundException(String country, String category) {
        super(NB_0001, country, category);
    }
}
