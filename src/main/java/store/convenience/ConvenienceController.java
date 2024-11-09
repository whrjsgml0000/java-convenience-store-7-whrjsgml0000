package store.convenience;

import java.util.Map;
import store.common.Response;
import store.config.FilePath;
import store.domain.Receipt;
import store.dto.PurchaseDTO;
import store.util.Extractor;
import store.util.FileLoad;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LoadDataService loadDataService;
    private final ConvenienceService convenienceService;
    private boolean continueShopping = true;

    public ConvenienceController(LoadDataService loadDataService, ConvenienceService convenienceService) {
        this.loadDataService = loadDataService;
        this.convenienceService = convenienceService;
    }

    public void run() {
        saveLoadData();
        while (continueShopping) {
            shopping();
        }
    }

    private void shopping() {
        String validNameAndQuantity = getValidRequestNameAndQuantity();
        Receipt receipt = purchase(Extractor.getNameAndQuantityMap(validNameAndQuantity));
        receipt(receipt);

        if (inputView.requestContinueShopping() == Response.NO) {
            continueShopping = false;
        }
    }

    private Receipt purchase(Map<String, Integer> nameAndQuantity) {
        Receipt receipt = new Receipt();
        for (String name : nameAndQuantity.keySet()) {
            int quantity = promotion(name, nameAndQuantity.get(name));
            PurchaseDTO purchaseDTO = convenienceService.purchase(name, quantity);
            receipt.addPurchase(purchaseDTO);
        }
        return receipt;
    }

    private void receipt(Receipt receipt) {
        receipt.setMemberShip(false);
        if (inputView.requestMemberShip() == Response.YES) {
            receipt.setMemberShip(true);
        }
        outputView.printReceipt(receipt);
    }

    // todo 리팩토링 필수.
    private int promotion(String name, int quantity) {
        if (convenienceService.hasPromotion(name)) {
            int compareResult = convenienceService.compareQuantity(name, quantity);
            if (compareResult > 0) {
                Response response = inputView.requestAddFreeItem(name, compareResult);
                if (response == Response.YES) {
                    return quantity + compareResult;
                }
                return quantity;
            }
            if (compareResult < 0) {
                Response response = inputView.requestCantReceivePromotion(name, -compareResult);
                if (response == Response.YES) {
                    return quantity;
                }
                return quantity + compareResult;
            }
        }
        return quantity;
    }

    private String getValidRequestNameAndQuantity() {
        outputView.introduction(convenienceService.getCurrentState());
        while (true) {
            String nameAndQuantity = inputView.requestNameAndQuantity();
            if (convenienceService.validNameAndQuantity(nameAndQuantity)) {
                return nameAndQuantity;
            }
        }
    }

    private void saveLoadData() {
        loadDataService.saveProductData(FileLoad.LoadFile(FilePath.PRODUCT.path()));
        loadDataService.savePromotionData(FileLoad.LoadFile(FilePath.PROMOTIONS.path()));
    }
}
