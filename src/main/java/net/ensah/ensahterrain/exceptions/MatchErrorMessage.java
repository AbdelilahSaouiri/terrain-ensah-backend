package net.ensah.ensahterrain.exceptions;

public enum MatchErrorMessage {
    RECORD_ALREADY_EXISTS("match deja reserve"),
    INTERNAL_SERVER_ERROR("Internal terrain-Ensah server error"),
    NO_RECORD_FOUND("match n'existe pas");

    private String message;

    MatchErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
