package store;

import store.config.Configuration;
import store.convenience.ConvenienceController;

public class Application {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        ConvenienceController convenienceController = configuration.getConvenienceController();
        convenienceController.run();
    }
}
