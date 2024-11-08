package store.convenience;

import store.config.FilePath;
import store.domain.Item;
import store.util.FileLoad;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    public ConvenienceController(){
        FileLoad.LoadFile(FilePath.PRODUCT.path());
        FileLoad.LoadFile(FilePath.PROMOTIONS.path());
    }

    public void run(){
        outputView.printItem(new Item());
    }
}
