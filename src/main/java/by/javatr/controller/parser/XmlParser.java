package by.javatr.controller.parser;

import by.javatr.bean.Gem;

import java.util.Set;

public interface XmlParser {
    public Set<Gem> parse(String filename) throws ParseException;

}
