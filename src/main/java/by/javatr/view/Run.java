package by.javatr.view;

import by.javatr.bean.Gem;
import by.javatr.controller.ValidatorFactory;
import by.javatr.controller.parser.ParseException;
import by.javatr.controller.parser.ParserFactory;
import org.apache.log4j.Logger;

import java.util.Set;

public class Run {
    private static final Logger logger = Logger.getLogger(Run.class);
    public static void main(String[] args) {
        System.out.println(ValidatorFactory.getInstance().getValidator().isValide("germs","germs"));
        try {
            Set<Gem> gems = ParserFactory.getInstance().getXmlParser(ParserFactory.ParserType.SAX).parse("germs");
            for (Gem gem : gems) {
                System.out.println(gem);
            }
        } catch (ParseException e) {
            logger.info("Opps!",e);
        }
    }
}
