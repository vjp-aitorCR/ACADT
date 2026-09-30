/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
/**
 *
 * @author alumno
 */

public class Ejercicio01 extends DefaultHandler{
    public Ejercicio01(){
        super();
    }
    @Override
    public void startDocument() throws SAXException{
        super.startDocument();
        System.out.println("Inicio del documento");
    }
    @Override
    public void endDocument() throws SAXException{
        super.endDocument();
        System.out.println("Fin del documento");
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        super.startElement(uri, localName, qName, attributes);
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    SAXParserFactory parserFactory = SAXParserFactory.newInstance();
    SAXParser parser = parserFactory.newSAXParser();
    XMLReader procesadorxml = parser.getXMLReader():
    public static void main(String[] args) {

        try {
            
           
    }
}
}

