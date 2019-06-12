package by.javatr.controller;

public class ValidatorFactory {
    private static ValidatorFactory ourInstance = new ValidatorFactory();

    public static ValidatorFactory getInstance() {
        return ourInstance;
    }

    private ValidatorFactory() {
    }

    private static Valid validator = new Validator();

    public Valid getValidator(){
        return  validator;
    }
}
