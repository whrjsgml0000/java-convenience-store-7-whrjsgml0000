package store.view;

import store.error.Error;

public class ErrorPrinter {
    public static void printError(Error error){
        try{
            throw new IllegalArgumentException();
        }catch (IllegalArgumentException e){
            System.out.println(error.error());
        }
    }
}
