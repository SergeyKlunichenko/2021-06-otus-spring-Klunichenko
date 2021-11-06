package ru.otus.spring.autoreport.exception;

public class AutoReportException extends RuntimeException {
    public AutoReportException(String message, Object ... args) {
        super(String.format(message, args));
    }
}
