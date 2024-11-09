package store.config;

public enum FilePath {
    PRODUCT("src/main/resources/products.md"),
    PROMOTIONS("src/main/resources/promotions.md"),
    ;

    private final String path;
    FilePath(String path){
        this.path = path;
    }
    public String path() {
        return path;
    }
}
