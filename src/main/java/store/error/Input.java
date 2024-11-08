package store.error;

public enum Input implements Error{

    ;


    private final String message;
    Input(String message){
        this.message = message;
    }

    public String message() {
        return message;
    }
}
