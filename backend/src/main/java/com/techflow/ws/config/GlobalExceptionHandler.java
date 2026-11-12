package com.techflow.ws.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Technical failures are returned as RFC 7807 problem details (application/problem+json),
 * without exposing internals. Framework errors (400/404/405...) use the same format via
 * ResponseEntityExceptionHandler. Business outcomes keep the existing ServiceResponse contract.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpected(Exception e) {
        log.error("Unexpected error", e);
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro inesperado. Informe o código de correlação ao suporte.");
        problem.setTitle("Erro interno");
        problem.setProperty("correlationId", org.apache.logging.log4j.ThreadContext.get(CorrelationIdFilter.MDC_KEY));
        return problem;
    }
}
