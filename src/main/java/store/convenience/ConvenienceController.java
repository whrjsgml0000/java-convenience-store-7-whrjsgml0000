package store.convenience;

import java.util.List;
import store.config.FilePath;
import store.util.FileLoad;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LoadDataService loadDataService;
    public ConvenienceController(LoadDataService loadDataService){
        this.loadDataService = loadDataService;
    }

    public void run(){
        saveLoadData();
    }

    private void saveLoadData(){
        loadDataService.saveProductData(FileLoad.LoadFile(FilePath.PRODUCT.path()));
        loadDataService.savePromotionData(FileLoad.LoadFile(FilePath.PROMOTIONS.path()));
    }
}
