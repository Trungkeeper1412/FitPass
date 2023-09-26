package com.ks.fitpass.web.handle;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handleMethodArgumentTypeMismatch: triggers when a parameter's type does not match
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
//        List<String> details = new ArrayList<>();
//        details.add(ex.getMessage());
//
//        ResponseError err = ResponseError.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.BAD_REQUEST)
//                .message("Mismatch Type")
//                .errors(details)
//                .build();
//        return ResponseEntityBuilder.build(err);
//    }

    // handleEmptyResultDataAccessException: triggers when a parameter's type does not match
//    @ExceptionHandler(EmptyResultDataAccessException.class)
//    protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
//        List<String> details = new ArrayList<>();
//        details.add(ex.getMessage());
//
//        ResponseError err = ResponseError.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.NOT_FOUND)
//                .message("Not Found")
//                .errors(details)
//                .build();
//        return ResponseEntityBuilder.build(err);
//    }

    // handleNullPointerException
//    @ExceptionHandler(NullPointerException.class)
//    protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request, Model model) {
//        List<String> details = new ArrayList<>();
//        details.add(ex.getMessage());
//
//        ResponseError err = ResponseError.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.BAD_REQUEST)
//                .message("Null Pointer Exception")
//                .errors(details)
//                .build();
//        return ResponseEntityBuilder.build(err);
//    }
}
