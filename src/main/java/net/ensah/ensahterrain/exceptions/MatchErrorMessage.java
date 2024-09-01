package net.ensah.ensahterrain.exceptions;

public enum MatchErrorMessage {
    RECORD_ALREADY_EXISTS("match deja reserve"),
    INTERNAL_SERVER_ERROR("Internal terrain-Ensah server error"),
    NO_RECORD_FOUND("match n'existe pas"),
    ALREADY_RESEREVED_BY_SAME_PLAYER("vous avez le droit de reserver qu'une seule match Par Jour");

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
