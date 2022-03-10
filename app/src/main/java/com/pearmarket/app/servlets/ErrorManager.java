package com.pearmarket.app.servlets;

public class ErrorManager extends Exception{
    public enum ErrorTypes {
        INVALID_PARAMETER,
        NULL_OBJECT,
        UNKNOW_ERROR,
        ERROR_404
    }

    private ErrorTypes type = ErrorTypes.INVALID_PARAMETER;
    private String title = "";
    private String description = "";
    public ErrorManager(ErrorTypes type) {
        this.type = type;
    }

    /** Getters & Setters **/

    public ErrorTypes getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
