package by.javatr.controller.parser;

public enum GemEnum {
    GEMS("Gems"),
    PRECIOUSNESS("preciousness"),
    GEM("Gem"),
    NAME("Name"),
    COLOUR("Colour"),
    CLARITY("Clarity"),
    BORDER("Border"),
    VISUALPARAMETS("VisualParamets"),
    VALUE("Value"),
    COUNTRY("Country");

    private String value;

    GemEnum(String value){
        this.value = value;
    }

    String getValue(){
        return value;
    }

}
