package store.convenience;

import java.util.Map;
import store.common.Response;
import store.config.FilePath;
import store.error.Input;
import store.util.Extractor;
import store.util.FileLoad;
import store.view.ErrorPrinter;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LoadDataService loadDataService;
    private final ConvenienceService convenienceService;
    private boolean continueShopping = true;
    public ConvenienceController(LoadDataService loadDataService, ConvenienceService convenienceService){
        this.loadDataService = loadDataService;
        this.convenienceService = convenienceService;
    }

    public void run(){
        saveLoadData();
        while(continueShopping){
            shopping();
        }
    }

    private void shopping() {
        String validNameAndQuantity = getValidRequestNameAndQuantity();
        purchase(Extractor.getNameAndQuantityMap(validNameAndQuantity));

        //todo membership();
        //todo receipt();

        if(inputView.requestContinueShopping() == Response.NO)
            continueShopping = false;
    }

    private void purchase(Map<String,Integer> nameAndQuantity) {
        for(String name:nameAndQuantity.keySet()){
            promotion(name, nameAndQuantity.get(name));

        }
    }

    private void promotion(String name, int quantity) {
        if(convenienceService.hasPromotion(name)){
            int compareResult = convenienceService.compareQuantity(name, quantity);
            if(compareResult > 0){
                inputView.requestAddFreeItem(name, compareResult);
            }
        }
    }

    private String getValidRequestNameAndQuantity() {
        outputView.introduction(convenienceService.getCurrentState());
        while(true) {
            String nameAndQuantity = inputView.requestNameAndQuantity();
            if(convenienceService.validNameAndQuantity(nameAndQuantity))
                return nameAndQuantity;
        }
    }

    private void saveLoadData(){
        loadDataService.saveProductData(FileLoad.LoadFile(FilePath.PRODUCT.path()));
        loadDataService.savePromotionData(FileLoad.LoadFile(FilePath.PROMOTIONS.path()));
    }
}
