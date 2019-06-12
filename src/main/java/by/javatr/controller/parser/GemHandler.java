package by.javatr.controller.parser;

import by.javatr.bean.Gem;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class GemHandler extends DefaultHandler {
    private Set<Gem> gems = new HashSet<>();
    private Gem current = null;
    private GemEnum currentEnum = null;
    private EnumSet<GemEnum> withText = EnumSet.range(GemEnum.NAME,GemEnum.COUNTRY);

    public Set<Gem> getGems(){
        return gems;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs){
        if("Gem".equals(localName)){
            current = new Gem.Builder()
                    .setPreciousness(attrs.getValue(0))
                    .build();
        }
        else{
            GemEnum temp = GemEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)){
                currentEnum = temp;
            }
        }
    }


    public void endElement(String uri, String localName, String qName){
        if("Gem".equals(localName)){
            gems.add(current);
        }
    }

    public void characters(char[] ch, int start, int length){
        String s = new String(ch, start, length).trim();
        if(currentEnum != null){
            switch (currentEnum){
                case NAME:
                    current.setName(s);
                    break;
                case COLOUR:
                    current.setColour(s);
                    break;
                case CLARITY:
                    current.setClarity(Integer.parseInt(s));
                    break;
                case BORDER:
                    current.setBorder(Integer.parseInt(s));
                    break;
                case VALUE:
                    current.setValue(Integer.parseInt(s));
                    break;
                case COUNTRY:
                    current.setCountry(s);
                    break;
                default:
                    throw  new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }

}
