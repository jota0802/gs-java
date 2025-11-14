package br.com.fiap.mentalhealthplatform.application.exception;

public class DuplicateResourceException extends RuntimeException {
    
    public DuplicateResourceException(String message) {
        super(message);
    }
    
    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s já existe com %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
