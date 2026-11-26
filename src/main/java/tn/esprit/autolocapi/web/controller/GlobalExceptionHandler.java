package tn.esprit.autolocapi.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import tn.esprit.autolocapi.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), List.of());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(IllegalArgumentException ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), List.of());
    }

    // Champs du DTO invalides (@Valid sur le corps de la requête)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleBodyInvalid(MethodArgumentNotValidException ex) {
        List<String> erreurs = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " : " + f.getDefaultMessage()).sorted().toList();
        return build(HttpStatus.BAD_REQUEST, "Données invalides", erreurs);
    }

    // Paramètres d'URL invalides (@Positive sur les ids)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, Object>> handleParamInvalid(HandlerMethodValidationException ex) {
        List<String> erreurs = ex.getParameterValidationResults().stream()
                .flatMap(r -> r.getResolvableErrors().stream()
                        .map(e -> r.getMethodParameter().getParameterName() + " : " + e.getDefaultMessage()))
                .sorted().toList();
        return build(HttpStatus.BAD_REQUEST, "Paramètres invalides", erreurs);
    }

    // JSON mal formé, date non valide, valeur d'énumération inconnue
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleUnreadable(HttpMessageNotReadableException ex) {
        return build(HttpStatus.BAD_REQUEST,
                "Corps de requête illisible : vérifiez le JSON, les dates (aaaa-mm-jj) et les énumérations",
                List.of());
    }

    private ResponseEntity<Map<String, Object>> build(HttpStatus status, String message, List<String> erreurs) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("statut", status.value());
        body.put("message", message);
        if (!erreurs.isEmpty()) {
            body.put("erreurs", erreurs);
        }
        body.put("horodatage", LocalDateTime.now().toString());
        return ResponseEntity.status(status).body(body);
    }
}