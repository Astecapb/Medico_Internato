package br.com.internato.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Erros de negócio customizados
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse("BUSINESS_ERROR", ex.getMessage()));
    }

    // Erros de validação (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("VALIDATION_ERROR", details));
    }

    // Qualquer outro erro não tratado

@ExceptionHandler(Exception.class)
public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
    ex.printStackTrace(); // <--- mostra o erro real no console
    Map<String, Object> body = new HashMap<>();
    body.put("code", "INTERNAL_ERROR");
    body.put("message", ex.getMessage()); // mostra a mensagem verdadeira
    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
}
    

    public record ErrorResponse(String code, String message) {}
}