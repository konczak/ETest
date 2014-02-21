package pl.konczak.etest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import pl.konczak.etest.error.SystemException;

@ControllerAdvice
public class SystemExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemExceptionHandler.class);
    private static final String SYSTEM_EXCEPTION_VIEW = "systemException";

    @ExceptionHandler(SystemException.class)
    public ModelAndView handle(SystemException systemException) {

        ModelAndView modelAndView = new ModelAndView(SYSTEM_EXCEPTION_VIEW);
        modelAndView.addObject("systemException", systemException);

        LOGGER.error("Handling SystemException error code <{}> <{}> number <{}> properties [{}]",
                systemException.getErrorCode().getClass().getSimpleName(),
                systemException.getErrorCode(),
                systemException.getErrorCode().getNumber(),
                systemException.getProperties(),
                systemException);

        return modelAndView;
    }
}
