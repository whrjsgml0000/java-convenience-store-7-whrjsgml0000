package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.common.Response;
import store.error.Input;

public class InputView {
    public String requestNameAndQuantity() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        return Console.readLine();
    }

    public Response requestContinueShopping() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        while (true) {
            Response response = parseResponse(Console.readLine());
            if (response != Response.ERROR) {
                return response;
            }
            ErrorPrinter.printError(Input.RESPONSE_FORM);
        }
    }

    private Response parseResponse(String response) {
        switch (response) {
            case "Y" -> {
                return Response.YES;
            }
            case "N" -> {
                return Response.NO;
            }
            case null, default -> ErrorPrinter.printError(Input.RESPONSE_FORM);
        }
        return Response.ERROR;
    }

    public Response requestAddFreeItem(String name, int count) {
        System.out.printf("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)%n", name, count);
        while (true) {
            Response response = parseResponse(Console.readLine());
            if (response != Response.ERROR) {
                return response;
            }
            ErrorPrinter.printError(Input.RESPONSE_FORM);
        }
    }

    public Response requestCantReceivePromotion(String name, int quantity) {
        System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)%n", name, quantity);
        while(true){
            Response response = parseResponse(Console.readLine());
            if(response != Response.ERROR){
                return response;
            }
            ErrorPrinter.printError(Input.RESPONSE_FORM);
        }
    }
}
