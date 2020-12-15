package sk.kosickaacademic.simon.xml;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

    public static void createXml(){
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element root = doc.createElement("shop");
            doc.appendChild(root);
            Element item = doc.createElement("item");
            root.appendChild(item);

            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            item.setAttributeNode(attr);
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode("Milk"));
            item.appendChild(name);
            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode("3"));
            item.appendChild(price);
            Element expiration_date = doc.createElement("expiration_date");
            expiration_date.appendChild(doc.createTextNode("2020-12-17"));
            item.appendChild(expiration_date);

            item = doc.createElement("item");
            root.appendChild(item);

            attr = doc.createAttribute("id");
            attr.setValue("2");
            item.setAttributeNode(attr);
            name = doc.createElement("name");
            name.appendChild(doc.createTextNode("Bread"));
            item.appendChild(name);
            price = doc.createElement("price");
            price.appendChild(doc.createTextNode("2.30"));
            item.appendChild(price);
            expiration_date = doc.createElement("expiration_date");
            expiration_date.appendChild(doc.createTextNode("2020-12-19"));
            item.appendChild(expiration_date);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult sr = new StreamResult(new File("output/shop.xml"));
            transformer.transform(domSource, sr);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void readXml(){
        try{
            File file = new File("output/shop.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("item");
            for(int temp=0; temp<nodeList.getLength(); temp++){
                Node node = nodeList.item(temp);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element)node;
                    System.out.println(element.getElementsByTagName("name").item(0).getTextContent() +" "
                            +element.getElementsByTagName("price").item(0).getTextContent() +" "
                            +element.getElementsByTagName("expiration_date").item(0).getTextContent());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //readFile();
        createXml();
        readXml();
    }
}
