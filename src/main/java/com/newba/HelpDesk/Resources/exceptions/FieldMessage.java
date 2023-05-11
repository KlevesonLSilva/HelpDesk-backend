package com.newba.HelpDesk.Resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable{
    private static final long serialVersionUID= 1L;

    private String FieldName;
    private String message;
    
    public FieldMessage(String fieldName, String message) {
        FieldName = fieldName;
        this.message = message;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
