package by.javatr.controller.parser;

public class ParserFactory {
    private static ParserFactory ourInstance = new ParserFactory();

    public static ParserFactory getInstance() {
        return ourInstance;
    }

    private ParserFactory() {
    }

    public enum ParserType {
        DOM,
        SAX,
        STAX
    }

    private final XmlParser xmlDomParser = new XmlDomParser();
    private final XmlParser xmlSaxParser = new XmlSaxParser();
    private final XmlParser xmlStaxParser = new XmlStaxParser();

    public XmlParser getXmlParser(ParserType type){
        switch (type){
            case DOM:
                return xmlDomParser;
            case SAX:
                return xmlSaxParser;
            case STAX:
                return xmlStaxParser;
            default:
                return xmlDomParser;
        }
    }
}
