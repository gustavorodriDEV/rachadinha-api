package com.gustavo.rachadinha_api.controllers.exceptions;

import com.gustavo.rachadinha_api.services.exceptions.RecursoNaoEncontradoException;
import com.gustavo.rachadinha_api.services.exceptions.RegraDeNegocioException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<StandardError> handleRecursoNaoEncontrado(RecursoNaoEncontradoException e, HttpServletRequest request) {
        String error = "Recurso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND; // Status 404
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError(error);
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<StandardError> handleRegraDeNegocio(RegraDeNegocioException e, HttpServletRequest request) {
        String error = "Violação de regra de negócio";
        HttpStatus status = HttpStatus.BAD_REQUEST; // Status 400
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError(error);
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}