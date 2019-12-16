package ru.javawebinar.topjava.util.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String detail;

    @JsonIgnore
    private final Throwable rootCause;

    public ErrorInfo(CharSequence url, ErrorType type, String detail, Throwable rootCause) {
        this.url = url.toString();
        this.type = type;
        this.detail = detail;
        this.rootCause = rootCause;
    }

    public String getDetail(){
        return this.detail;
    }

    public Throwable getRootCause() {
        return rootCause;
    }
}