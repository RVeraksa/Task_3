package by.javatr.controller.parser;

import by.javatr.bean.Gem;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class GemsSAXBuilder {
    private static final Logger logger = Logger.getLogger(GemsSAXBuilder.class);
    private Set<Gem> gems;
    private GemHandler gemHandler;
    private XMLReader reader;

    public GemsSAXBuilder(){
        gemHandler = new GemHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(gemHandler);
        } catch (SAXException e) {
            logger.info("SAX parser error",e);
        }
    }

    public Set<Gem> getGems(){
        return gems;
    }

    public void buildSetGems(String fileName){
        try {
            reader.parse(fileName);
        } catch (IOException e) {
           logger.info("SAX parser error",e);
        } catch (SAXException e) {
            logger.info("IO stream error",e);
        }
        gems = gemHandler.getGems();
    }
}
