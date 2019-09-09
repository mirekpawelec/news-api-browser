package pl.pawelec.newsbrowser.service.exception.errorhandler;

import pl.pawelec.newsbrowser.service.assembler.ErrorMessageAssembler;
import pl.pawelec.newsbrowser.service.exception.errorhandler.errortype.ConnectionException;
import pl.pawelec.newsbrowser.service.exception.errorhandler.errortype.NewsBrowserException;
import pl.pawelec.newsbrowser.service.exception.errorhandler.errortype.NotFoundException;
import pl.pawelec.newsbrowser.service.exception.errorhandler.errortype.ValidationException;
import pl.pawelec.newsbrowser.service.exception.model.ErrorMessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private ErrorMessageAssembler errorMessageAssembler;

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler({ConnectionException.class})
    @ResponseBody
    public ErrorMessageModel connectionException(ConnectionException error) {
        addErrorIdAndCode(error, "SERVICE_UNAVAILABLE_EXCEPTION");
        return errorMessageAssembler.assembleFrom(error, error.getParameters());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    @ResponseBody
    public ErrorMessageModel notFoundException(NotFoundException error) {
        addErrorIdAndCode(error, "NOT_FOUND_EXCEPTION");
        return errorMessageAssembler.assembleFrom(error, error.getParameters());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    public ErrorMessageModel validationException(ValidationException error) {
        addErrorIdAndCode(error, "VALIDATION_EXCEPTION");
        return errorMessageAssembler.assembleFrom(error, error.getParameters());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NewsBrowserException.class})
    @ResponseBody
    public ErrorMessageModel defaultErrorHandling(NewsBrowserException error) {
        addErrorIdAndCode(error, "INTERNAL_SERVER_ERROR");
        return errorMessageAssembler.assembleFrom(error);
    }

    private static void addErrorIdAndCode(NewsBrowserException e, String defaultErrorCode) {
        e.setErrorId(UUID.randomUUID().toString());
        e.setErrorCode(Optional.ofNullable(e.getErrorCode()).orElse(defaultErrorCode));
        logDetailsOfError(e);
    }

    private static void logDetailsOfError(NewsBrowserException e) {
        LOGGER.error("ERROR_ID: {}", e.getErrorId());
        LOGGER.error(e.getErrorMessage(), e);
    }
}