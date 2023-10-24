package apicela.duplicata.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CabecalhoInvalido.class)
    public ResponseEntity<Object> handleCabecalhoInvalida(CabecalhoInvalido ex) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "error.");
        response.put("message", "O arquivo CNAB possui formato inválido.");
        response.put("errors: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "error.");
        response.put("message", "O arquivo CNAB possui formato inválido.");
        response.put("errors: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<Object> handleIllegalAcess(IllegalAccessException ex) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "error.");
        response.put("message: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(DuplicataInvalida.class)
    public ResponseEntity<Object> handleDuplicataInvalida(DuplicataInvalida ex) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "error.");
        response.put("message", "O arquivo CNAB possui formato inválido.");
        response.put("errors: ", ex.getMessage().split("\n"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(TransacaoInvalida.class)
    public ResponseEntity<Object> handleTransacaoInvalida(TransacaoInvalida ex) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "error.");
        response.put("message", "O arquivo CNAB possui formato inválido.");
        response.put("errors: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}

