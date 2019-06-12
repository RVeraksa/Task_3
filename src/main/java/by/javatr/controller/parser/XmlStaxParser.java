package by.javatr.controller.parser;

import by.javatr.bean.Gem;
import org.apache.log4j.Logger;

import java.util.Set;

public class XmlStaxParser implements XmlParser {

    private static final Logger logger = Logger.getLogger(XmlStaxParser.class);

    @Override
    public Set<Gem> parse(String filename) throws ParseException {
        GemStAXBuilder stAXBuilder = new GemStAXBuilder();
        stAXBuilder.buildSetGems(filename);
        return stAXBuilder.getGems();
    }
}
