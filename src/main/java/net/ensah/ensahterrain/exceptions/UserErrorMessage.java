package net.ensah.ensahterrain.exceptions;

public enum UserErrorMessage {
    RECORD_ALREADY_EXISTS("Cet utilisateur deja Existe"),
    INTERNAL_SERVER_ERROR("Internal terrain-Ensah server error"),
    NO_RECORD_FOUND("Cet utilisateur n'existe pas ");

    private String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
