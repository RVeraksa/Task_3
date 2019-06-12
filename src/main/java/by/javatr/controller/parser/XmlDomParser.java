package by.javatr.controller.parser;

import by.javatr.bean.Gem;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class XmlDomParser implements XmlParser {
    private static final Logger logger = Logger.getLogger(XmlDomParser.class);
    private Set<Gem> gems = new HashSet<>();


    public Set<Gem> parse(String filename) throws ParseException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filename);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            NodeList gemList = root.getElementsByTagName("Gem");
            for(int i=0; i< gemList.getLength(); i++){
                Element gemElement = (Element) gemList.item(i);
                Gem gem = buildGem(gemElement);
                gems.add(gem);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.info("Parser exc");
            throw new ParseException(e);
        }
        return gems;
    }

    private Gem buildGem(Element gemElement){
        Element visualElement = (Element) gemElement.getElementsByTagName("VisualParamets").item(0);
        Integer value = Integer.parseInt(getElementTextContent(gemElement,"Value"));
        Integer clarity = Integer.parseInt(getElementTextContent(visualElement,"Clarity"));
        Integer border = Integer.parseInt(getElementTextContent(visualElement,"Border"));
        Gem gem = new Gem.Builder()
                .setPreciousness(gemElement.getAttribute("preciousness"))
                .setName(getElementTextContent(gemElement,"Name"))
                .setColour(getElementTextContent(visualElement,"Colour"))
                .setClarity(clarity)
                .setBorder(border)
                .setValue(value)
                .setCountry(getElementTextContent(gemElement,"Country"))
                .build();
        return gem;
    }

    private static String getElementTextContent(Element element, String elementName){
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
