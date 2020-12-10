package sk.kosickaacademic.simon.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {
    public static void readFile(){
        try{
            File file = new File("resources/football.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("match");
            for(int temp=0; temp<nodeList.getLength(); temp++){
                Node node = nodeList.item(temp);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element)node;
                    System.out.println(element.getElementsByTagName("date").item(0).getTextContent());
                    System.out.println(element.getElementsByTagName("team").item(0).getTextContent() +" - " +element.getElementsByTagName("team").item(1).getTextContent()
                            +" " +element.getElementsByTagName("score").item(0).getTextContent());
                    System.out.println("Goals: " +element.getElementsByTagName("time").item(0).getTextContent() +" " +element.getElementsByTagName("player").item(0).getTextContent()
                            +" - " +element.getElementsByTagName("time").item(1).getTextContent() +" " +element.getElementsByTagName("player").item(1).getTextContent()
                            +", " +element.getElementsByTagName("time").item(2).getTextContent() +" " +element.getElementsByTagName("player").item(2).getTextContent()
                            +", " +element.getElementsByTagName("time").item(3).getTextContent() +" " +element.getElementsByTagName("player").item(3).getTextContent());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFile();
    }
}
