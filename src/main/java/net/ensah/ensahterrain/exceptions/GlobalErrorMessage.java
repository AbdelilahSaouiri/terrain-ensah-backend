package net.ensah.ensahterrain.exceptions;

public enum GlobalErrorMessage {
    MISSING_REQUIRED_FIELD("missing required field"),
    RECORD_ALREADY_EXISTS("record already exists"),
    INTERNAL_SERVER_ERROR("Internal terrain-Ensah server error"),
    NO_RECORD_FOUND("no record found");

    private String message;

    GlobalErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
