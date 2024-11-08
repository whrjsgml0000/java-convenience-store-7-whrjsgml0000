package store.convenience;

import store.common.Response;
import store.config.FilePath;
import store.util.FileLoad;
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
        outputView.introduction(convenienceService.getCurrentState());
        inputView.requestNameAndQuantity();


        if(inputView.requestContinueShopping() == Response.NO)
            continueShopping = false;
    }

    private void saveLoadData(){
        loadDataService.saveProductData(FileLoad.LoadFile(FilePath.PRODUCT.path()));
        loadDataService.savePromotionData(FileLoad.LoadFile(FilePath.PROMOTIONS.path()));
    }
}
