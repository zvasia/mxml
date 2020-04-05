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

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
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

        String maxval = Collections.max(counts.entrySet(), (entry1, entry2) -> (int) (entry1.getValue() -
                entry2.getValue())).getKey();





        XPathExpression currentNoteChordIndicate = xPath.compile("//part[@id='"+drumPart+"']/measure/note/instrument[@id='"+maxval+"']/../chord");
        XPathExpression nextNoteChordIndicate = xPath.compile("//part[@id='"+drumPart+"']/measure/note/instrument[@id='"+maxval+"']/../following-sibling::note/chord");
        NodeList currentNoteChordNodes = (NodeList) currentNoteChordIndicate.evaluate(doc, XPathConstants.NODESET);
        NodeList nextNoteChordNodes = (NodeList) nextNoteChordIndicate.evaluate(doc, XPathConstants.NODESET);



        System.out.println(currentNoteChordNodes.getLength());
        System.out.println(nextNoteChordNodes.getLength());

        Map<String, Double> noteTypes = new HashMap<String, Double>(){
            {
                put("whole", 1.);
                put("half", 2.);
                put("quarter", 4.);
                put("eighth", 8.);
                put("16th", 16.);
                put("32nd", 32.);
                put("64th", 64.);
            }
        };


        XPathExpression perMinute = xPath.compile("//per-minute");
        XPathExpression beatUnit = xPath.compile("//beat-unit");
        XPathExpression NoteTypesPath = xPath.compile("//part[@id='"+drumPart+"']/measure/note/instrument[@id='"+maxval+"']/../type");
        NodeList perMinuteNodes = (NodeList) perMinute.evaluate(doc, XPathConstants.NODESET);
        NodeList beatUnitNodes = (NodeList) beatUnit.evaluate(doc, XPathConstants.NODESET);
        NodeList NoteTypesNodes = (NodeList) NoteTypesPath.evaluate(doc, XPathConstants.NODESET);
        Double PerMinute = Double.parseDouble(perMinuteNodes.item(0).getTextContent()+".");
        Double BeatUnit = noteTypes.get(beatUnitNodes.item(0).getTextContent());

        Double minNote = 1.0;
        Double wholeType = 0.;
        for(int i = 0; i < NoteTypesNodes.getLength();i++){
            wholeType = wholeType + noteTypes.get(NoteTypesNodes.item(i).getTextContent());
            if (noteTypes.get(NoteTypesNodes.item(i).getTextContent()) > minNote){
                minNote = noteTypes.get(NoteTypesNodes.item(i).getTextContent());
            }
        }

        Double maxBpm;
        Double averageBpm;
         Double wholeTypes = wholeType;
        if (currentNoteChordNodes.getLength()>0 || nextNoteChordNodes.getLength()>0){
            averageBpm = (((wholeType/NoteTypesNodes.getLength())/BeatUnit)*PerMinute);
            maxBpm = (minNote/BeatUnit)*PerMinute;
        }
        else {
            averageBpm = ((((wholeType/NoteTypesNodes.getLength())/BeatUnit)*PerMinute)/2.0);
            maxBpm = ((minNote/BeatUnit)*PerMinute)/2.0;
        }

        System.out.println(averageBpm);
        System.out.println(maxBpm);


}

}


