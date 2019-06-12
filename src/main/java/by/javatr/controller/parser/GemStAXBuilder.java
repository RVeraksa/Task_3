package by.javatr.controller.parser;

import by.javatr.bean.Gem;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GemStAXBuilder {
    private static final Logger logger = Logger.getLogger(GemStAXBuilder.class);
    private Set<Gem> gems = new HashSet<>();
    private XMLInputFactory inputFactory = XMLInputFactory.newInstance();

    public Set<Gem> getGems(){
        return gems;
    }

    public void buildSetGems(String fileName){
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()){
                int type = reader.next();
                if(type == XMLStreamConstants.START_ELEMENT){
                    name = reader.getLocalName();
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.GEM){
                        Gem gem = buildGem(reader);
                        gems.add(gem);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.info("file not found");
        } catch (XMLStreamException e) {
            logger.info("StAX parser exception");
        } finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.info("File doesn't close");
            }
        }

    }

    private Gem buildGem(XMLStreamReader reader) throws XMLStreamException {
        Gem gem = new Gem.Builder()
                .setPreciousness(reader.getAttributeValue(null,GemEnum.PRECIOUSNESS.getValue()))
                .build();
        String name;

        while (reader.hasNext()){
            int type = reader.next();
            switch (type){
                case XMLStreamConstants
                        .START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.valueOf(name.toUpperCase())){
                        case NAME:
                            gem.setName(getXMLText(reader));
                            break;
                        case VISUALPARAMETS:
                            getVisualPar(gem,reader);
                            break;
                        case VALUE:
                            gem.setValue(Integer.parseInt(getXMLText(reader)));
                            break;
                        case COUNTRY:
                            gem.setCountry(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(GemEnum.valueOf(name.toUpperCase()) == GemEnum.GEM){
                        return gem;
                    }
                    break;
            }
        }
        throw new XMLStreamException("File is not valid");
    }


    private void getVisualPar(Gem gem,XMLStreamReader reader) throws XMLStreamException {
        int type;
        String name;
        while (reader.hasNext()){
            type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.valueOf(name.toUpperCase())){
                        case COLOUR:
                            gem.setColour(getXMLText(reader));
                            break;
                        case CLARITY:
                            gem.setClarity(Integer.parseInt(getXMLText(reader)));
                            break;
                        case BORDER:
                            gem.setBorder(Integer.parseInt(getXMLText(reader)));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(GemEnum.valueOf(name.toUpperCase()) == GemEnum.VALUE)
                        break;
            }
        }
        throw new XMLStreamException("Tag visual parametrs is not valid");
    }


    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if(reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }

}
