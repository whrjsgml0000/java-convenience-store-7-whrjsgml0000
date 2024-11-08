package store.error;

public enum Input implements Error{
    PURCHASE_FORM("구매 형식 입력이 잘못됐습니다."),
    RESPONSE_FORM("응답 형식이 잘못됐습니다."),
    EXIST_ALREADY("중복된 상품이 존재합니다."),
    NULL_ITEM_NAME("존재하지 않는 상품은 구매할 수 없습니다."),
    ;


    private final String message;
    Input(String message){
        this.message = message;
    }

    public String error() {
        return message;
    }
}
