//package com.ks.fitpass.web.handle;
//
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, WebRequest request) {
//        return handleException(HttpStatus.BAD_REQUEST, "Missing Param", ex);
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
//        return handleException(HttpStatus.BAD_REQUEST, "Mismatch Type", ex);
//    }
//
//    @ExceptionHandler(EmptyResultDataAccessException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
//        return handleException(HttpStatus.NOT_FOUND, "Not Found", ex);
//    }
//
//    @ExceptionHandler(NullPointerException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request, Model model) {
//        return handleException(HttpStatus.BAD_REQUEST, "Null Pointer Exception", ex);
//    }
//
//    private ResponseEntity<Object> handleException(HttpStatus status, String message, Exception ex) {
//        List<String> details = new ArrayList<>();
//        details.add(ex.getMessage());
//
//        ResponseError err = ResponseError.builder()
//                .timestamp(LocalDateTime.now())
//                .status(status)
//                .message(message)
//                .errors(details)
//                .build();
//
//        return ResponseEntityBuilder.build(err);
//    }
//
//    // For handling exceptions not explicitly handled above
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleException(Exception ex) {
//        ModelAndView modelAndView = new ModelAndView("error/error");
//        modelAndView.addObject("timestamp", LocalDateTime.now());
//        modelAndView.addObject("status", HttpStatus.BAD_REQUEST.value());
//        modelAndView.addObject("error", "Bad Request");
//        modelAndView.addObject("message", ex.getMessage());
//        return modelAndView;
//    }
//}
