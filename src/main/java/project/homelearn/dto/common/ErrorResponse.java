package project.homelearn.dto.common;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ErrorResponse {
    private final Map<String, String> errors = new ConcurrentHashMap<>();

    public void addError(String field, String message) {
        errors.put(field, message);
    }
}