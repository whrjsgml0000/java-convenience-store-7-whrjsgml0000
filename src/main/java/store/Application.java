package store;

import store.config.Configuration;
import store.convenience.ConvenienceController;

public class Application {
    private static final Configuration CONFIGURATION = Configuration.getInstance();

    public static void main(String[] args) {
        ConvenienceController convenienceController = CONFIGURATION.getConvenienceController();
        convenienceController.run();
    }
}
