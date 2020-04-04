import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Element;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class parser {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        File inputFile = new File("src/main/resources/SLOW BLUES.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        System.out.println("Root element :"+doc.getDocumentElement().getTagName());
        //NodeList nList = doc.getElementsByTagName("student");
        System.out.println("----------------------------");


        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        XPathExpression xPathExpression = xPath.compile("//*[contains(text(),'Snare')]/../..");
        NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
        String drumPart = nodeList.item(0).getAttributes().getNamedItem("id").getNodeValue();
        XPathExpression p3 = xPath.compile("//part[@id='"+drumPart+"']/measure/note/instrument");
        NodeList part = (NodeList) p3.evaluate(doc, XPathConstants.NODESET);

        ArrayList<String> drums = new ArrayList<String>();

        for (int i = 0; i<part.getLength();i++){
            drums.add(part.item(i).getAttributes().getNamedItem("id").getNodeValue());
            //System.out.println(drums.get(i));
        }

        Map<String, Long> counts = drums.stream().collect(Collectors.groupingBy(e -> e, Collectors.<String>counting()));

        for (Map.Entry<String, Long> entry: counts.entrySet()) {
            //System.out.println(entry.getValue());
        }
        String maxval = Collections.max(counts.entrySet(), (entry1, entry2) -> (int) (entry1.getValue() -
                entry2.getValue())).getKey();

        /*
        здесь определение самого частого инструмента
         */

        //String instrumentId = counts.values();



        XPathExpression currentNoteChordIndicate = xPath.compile("//part[@id='"+drumPart+"']/measure/note/instrument[@id='"+maxval+"']/../chord");
        XPathExpression nextNoteChordIndicate = xPath.compile("//part[@id='"+drumPart+"']/measure/note/instrument[@id='"+maxval+"']/../following-sibling::note/chord");
        NodeList currentNoteChordNodes = (NodeList) currentNoteChordIndicate.evaluate(doc, XPathConstants.NODESET);
        NodeList nextNoteChordNodes = (NodeList) nextNoteChordIndicate.evaluate(doc, XPathConstants.NODESET);

        System.out.println(currentNoteChordNodes.getLength());
        System.out.println(nextNoteChordNodes.getLength());

        if (currentNoteChordNodes.getLength()>0 || nextNoteChordNodes.getLength()>0){
            //считаем
        }
        else {

        }
        //XPathExpression xPathExpressionDrums = xPath.compile("//part[@id='\"+drumPart+\"']")





        //System.out.println(part.item(0).getAttributes().getNamedItem("id").getNodeValue());


        //System.out.print(nodeList.item(0).getAttributes().getNamedItem("id").getNodeValue());
        //System.out.println(xPathExpression.evaluate(doc, XPathConstants.STRING));


}

}


