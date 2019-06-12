package by.javatr.controller;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Validator implements Valid {
    public boolean isValide(String filename, String shemaname){
        boolean result = false;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = "src/main/resources/"+filename+".xml";
        String schemaName = "src/main/resources/"+shemaname+".xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            javax.xml.validation.Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
            result = true;
            return result;
        } catch (org.xml.sax.SAXException e) {
            System.err.print("validation "+ filename + " is not valid because "
                    + e.getMessage());
        } catch (IOException e) {
            System.err.print(filename + " is not valid because "
                    + e.getMessage());
        }
        return result;
    }
}
