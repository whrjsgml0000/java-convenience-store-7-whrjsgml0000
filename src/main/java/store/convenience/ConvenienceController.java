package store.convenience;

import java.util.List;
import store.config.FilePath;
import store.util.FileLoad;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    public ConvenienceController(){

    }

    public void run(){
        saveLoadData();
    }

    private void saveLoadData(){
        List<String> productData = FileLoad.LoadFile(FilePath.PRODUCT.path());
        List<String> promotionData = FileLoad.LoadFile(FilePath.PROMOTIONS.path());
    }
}
