import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.naming.Name;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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


        HashMap<String, String> instrumentMap = new HashMap<String, String>(){
            {
                put("Snare (hit)","<НОГАРУКА>");
                put("Snare (side stick)","<НОГАРУКА>");
                put("Snare (rim shot)","<НОГАРУКА>");
                put("Hi-Hat (closed)","НОГА");
                put("Hi-Hat (half)","<НОГАРУКА>");
                put("Hi-Hat (open)","<НОГАРУКА>");
                put("Pedal Hi-Hat (hit)","<НОГАРУКА>");
                put("Kick (hit)","<НОГАРУКА>");
                put("Kick (hit)","<НОГАРУКА>");
                put("High Floor Tom (hit)","<НОГАРУКА>");
                put("High Tom (hit)","<НОГАРУКА>");
                put("Mid Tom (hit)","<НОГАРУКА>");
                put("Low Tom (hit)","<НОГАРУКА>");
                put("Very Low Tom (hit)","<НОГАРУКА>");
                put("Ride (edge)","<НОГАРУКА>");
                put("Ride (middle)","<НОГАРУКА>");
                put("Ride (bell)","<НОГАРУКА>");
                put("Ride (choke)","<НОГАРУКА>");
                put("Splash (hit)","<НОГАРУКА>");
                put("Splash (choke)","<НОГАРУКА>");
                put("China (hit)","<НОГАРУКА>");
                put("China (choke)","<НОГАРУКА>");
                put("Crash high (hit)","<НОГАРУКА>");
                put("Crash high (choke)","<НОГАРУКА>");
                put("Crash medium (hit)","<НОГАРУКА>");
                put("Crash medium (choke)","<НОГАРУКА>");
                put("Cowbell low (hit)","<НОГАРУКА>");
                put("Cowbell low (tip)","<НОГАРУКА>");
                put("Cowbell medium (hit)","<НОГАРУКА>");
                put("Cowbell medium (tip)","<НОГАРУКА>");
                put("Cowbell high (hit)","<НОГАРУКА>");
                put("Cowbell high (tip)","<НОГАРУКА>");
                put("Woodblock low (hit)","<НОГАРУКА>");
                put("Woodblock high (hit)","<НОГАРУКА>");
                put("Hand (hit)","<НОГАРУКА>");
                put("Hand (mute)","<НОГАРУКА>");
                put("Hand (slap)","<НОГАРУКА>");
                put("Hand (hit)","<НОГАРУКА>");
                put("Hand (mute)","<НОГАРУКА>");
                put("Hand (slap)","<НОГАРУКА>");
                put("Timbale low (hit)","<НОГАРУКА>");
                put("Timbale high (hit)","<НОГАРУКА>");
                put("Agogo low (hit)","<НОГАРУКА>");
                put("Agogo high (hit)","<НОГАРУКА>");
                put("Conga low (hit)","<НОГАРУКА>");
                put("Conga low (slap)","<НОГАРУКА>");
                put("Conga low (mute)","<НОГАРУКА>");
                put("Conga high (hit)","<НОГАРУКА>");
                put("Conga high (slap)","<НОГАРУКА>");
                put("Conga high (mute)","<НОГАРУКА>");
                put("Whistle low (hit)","<НОГАРУКА>");
                put("Whistle high (hit)","<НОГАРУКА>");
                put("Guiro (hit)","<НОГАРУКА>");
                put("Guiro (scrap-return)","<НОГАРУКА>");
                put("Surdo (hit)","<НОГАРУКА>");
                put("Surdo (mute)","<НОГАРУКА>");
                put("Tambourine (hit)","<НОГАРУКА>");
                put("Tambourine (return)","<НОГАРУКА>");
                put("Tambourine (roll)","<НОГАРУКА>");
                put("Tambourine (hand)","<НОГАРУКА>");
                put("Cuica (open)","<НОГАРУКА>");
                put("Cuica (mute)","<НОГАРУКА>");
                put("Vibraslap (hit)","<НОГАРУКА>");
                put("Triangle (hit)","<НОГАРУКА>");
                put("Triangle (mute)","<НОГАРУКА>");
                put("Grancassa (hit)","<НОГАРУКА>");
                put("Piatti (hit)","<НОГАРУКА>");
                put("Piatti (hand)","<НОГАРУКА>");
                put("Cabasa (hit)","<НОГАРУКА>");
                put("Cabasa (return)","<НОГАРУКА>");
                put("Castanets (hit)","<НОГАРУКА>");
                put("Claves (hit)","<НОГАРУКА>");
                put("Left Maraca (hit)","<НОГАРУКА>");
                put("Left Maraca (return)","<НОГАРУКА>");
                put("Right Maraca (hit)","<НОГАРУКА>");
                put("Right Maraca (return)","<НОГАРУКА>");
                put("Shaker (hit)","<НОГАРУКА>");
                put("Shaker (return)","<НОГАРУКА>");
                put("Bell Tree (hit)","<НОГАРУКА>");
                put("Bell Tree (return)","<НОГАРУКА>");
                put("Jingle Bell (hit)","<НОГАРУКА>");
                put("Tinkle Bell (hit)","<НОГАРУКА>");
                put("Golpe (thumb)","<НОГАРУКА>");
                put("Golpe (finger)","<НОГАРУКА>");
                put("Hand Clap (hit)","<НОГАРУКА>");
                put("Electric Snare (hit)","<НОГАРУКА>");
                put("Snare (side stick)","<НОГАРУКА>");
                put("Low Floor Tom (hit)","<НОГАРУКА>");
                put("Ride (edge)","<НОГАРУКА>");
                put("Ride (middle)","<НОГАРУКА>");
                put("Ride (bell)","<НОГАРУКА>");
                put("Ride (choke)","<НОГАРУКА>");
                put("Reverse Cymbal (hit)","<НОГАРУКА>");
                put("Metronome (hit)","<НОГАРУКА>");
                put("Metronome (bell)","<НОГАРУКА>");

            }
        };
        String drumPart ="";
        for (Map.Entry<String, String> entry : instrumentMap.entrySet()) {

            XPathExpression xPathExpression = xPath.compile("//*[contains(text(),'"+entry.getKey()+"')]/../..");
            NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            if (nodeList.getLength()>0){
                drumPart = nodeList.item(0).getAttributes().getNamedItem("id").getNodeValue();
                break;
            }
        }
        XPathExpression p3 = xPath.compile("//part[@id='"+drumPart+"']/measure/note/instrument");
        NodeList part = (NodeList) p3.evaluate(doc, XPathConstants.NODESET);

        Map<String, String> instrumentIdMap = new HashMap<>();

        XPathExpression instruments = xPath.compile("//score-part[@id='"+drumPart+"']/score-instrument");
        NodeList InstrumentNodes = (NodeList) instruments.evaluate(doc,XPathConstants.NODESET);

        for (int i = 0; i<InstrumentNodes.getLength(); i++){
            instrumentIdMap.put(InstrumentNodes.item(i).getAttributes().getNamedItem("id").getNodeValue(), getChild((Element)InstrumentNodes.item(i),"instrument-name").getTextContent());
        }

        ArrayList<String> drums = new ArrayList<String>();

        for (int i = 0; i<part.getLength();i++){
            if(instrumentMap.get(instrumentIdMap.get(part.item(i).getAttributes().getNamedItem("id").getNodeValue()))!="НОГА"){
                drums.add(part.item(i).getAttributes().getNamedItem("id").getNodeValue());
            }
        }

        Map<String, Long> counts = drums.stream().collect(Collectors.groupingBy(e -> e, Collectors.<String>counting()));

        String maxval = Collections.max(counts.entrySet(), (entry1, entry2) -> (int) (entry1.getValue() -
                entry2.getValue())).getKey();

        System.out.println(counts.get(maxval));


        XPathExpression currentNoteChordIndicate = xPath.compile("//part[@id='"+drumPart+"']/measure/note/instrument[@id='"+maxval+"']/../chord");
        XPathExpression nextNoteChordIndicate = xPath.compile("//part[@id='"+drumPart+"']/measure/note/instrument[@id='"+maxval+"']/../following-sibling::chord");
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

public static Element getChild(Element parent, String name){
        for (Node child = parent.getFirstChild(); child!=null; child = child.getNextSibling()){
            if (child instanceof Element && name.equals(child.getNodeName())){
                return (Element) child;
            }
        }
        return null;
}

}


