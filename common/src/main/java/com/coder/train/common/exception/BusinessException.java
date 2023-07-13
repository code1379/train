package com.coder.train.common.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }

    private BusinessExceptionEnum e;

    public BusinessExceptionEnum getE() {
        return e;
    }

    public void setE(BusinessExceptionEnum e) {
        this.e = e;
    }
}
