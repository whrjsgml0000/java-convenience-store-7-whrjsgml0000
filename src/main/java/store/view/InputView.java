package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.common.Response;
import store.error.Input;

public class InputView {
    public String requestNameAndQuantity(){
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        return Console.readLine();
    }

    public Response requestContinueShopping(){
        while(true) {
            System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
            Response response = parseResponse(Console.readLine());
            if(response!=Response.ERROR)
                return response;
        }
    }

    private Response parseResponse(String response){
        switch (response){
            case "Y" -> {return Response.YES;}
            case "N" -> {return Response.NO;}
            case null, default -> ErrorPrinter.printError(Input.RESPONSE_FORM);
        }
        return Response.ERROR;
    }
}
