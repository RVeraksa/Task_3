package by.javatr.controller.parser;

import by.javatr.bean.Gem;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class XmlSaxParser implements XmlParser {
    private static final Logger logger = Logger.getLogger(XmlSaxParser.class);
    private Set<Gem> gems = new HashSet<>();

    @Override
    public Set<Gem> parse(String filename) throws ParseException {
        GemsSAXBuilder gemsSAXBuilder = new GemsSAXBuilder();
        gemsSAXBuilder.buildSetGems(filename);
        return gemsSAXBuilder.getGems();
    }
}
