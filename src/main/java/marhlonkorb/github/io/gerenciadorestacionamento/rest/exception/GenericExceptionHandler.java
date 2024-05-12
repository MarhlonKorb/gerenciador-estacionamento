package marhlonkorb.github.io.gerenciadorestacionamento.rest.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import marhlonkorb.github.io.gerenciadorestacionamento.entities.usuario.exceptions.UsuarioException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> tratarNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso não encontrado: " + ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> tratarDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Violação de integridade de dados: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarExcecaoGeral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entidade não encontrada: " + ex.getMessage());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<String> authenticationException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao efetuar login. Usuário não autorizado.");
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<String> jWTVerificationException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao efetuar login. Token inválido.");
    }
    @ExceptionHandler({BadCredentialsException.class, UsuarioException.class })
    public ResponseEntity<ApiErrors> loginException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrors(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage()));
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<String> dataAcessException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError fieldError) {
                errors.add(fieldError.getDefaultMessage());
            } else {
                errors.add(error.getDefaultMessage());
            }
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}

