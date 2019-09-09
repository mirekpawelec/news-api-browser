package pl.pawelec.newsbrowser.service.assembler;

import pl.pawelec.newsbrowser.service.exception.errorhandler.errortype.NewsBrowserException;
import pl.pawelec.newsbrowser.service.exception.model.ErrorMessageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ErrorMessageAssembler {

    @Value("${spring.application.name:UNSET_APPLICATION_ERROR_SOURCE}")
    private String errorSource;

    public ErrorMessageModel assembleFrom(NewsBrowserException exception) {
        return new ErrorMessageModel.Builder(exception.getErrorId(), errorSource)
                .errorCode(exception.getErrorCode())
                .errorMessage(exception.getErrorMessage())
                .build();
    }

    public ErrorMessageModel assembleFrom(NewsBrowserException exception, List<String> parameters) {
        return new ErrorMessageModel.Builder(exception.getErrorId(), errorSource)
                .errorCode(exception.getErrorCode())
                .errorMessage(exception.getErrorMessage())
                .parameters(parameters)
                .build();
    }
}
