import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CSVUtil {

    public static void main(String[] args) throws Exception {
    	 List<Developer> developers = new ArrayList<Developer>();
    	 String csvFile = "/Users/ConnieZalo/Desktop/TestData/test.csv";
         FileWriter writer = new FileWriter(csvFile);
         
         
    	File inputFile = new File("/Users/ConnieZalo/Desktop/TestData/test.txt");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("event");
         System.out.println("----------------------------");
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("id : " 
                  + eElement.getAttribute("id"));
              
               System.out.println("filename : " 
                       + eElement.getAttribute("filename"));
               System.out.println("type : " 
                       + eElement.getAttribute("type"));
               System.out.println("parent : " 
                       + eElement.getAttribute("parent"));
               
               System.out.println("start : " 
                       + eElement.getAttribute("tsStart"));

               System.out.println("end : " 
                       + eElement.getAttribute("tsEnd"));
               System.out.println("sid : " 
                       + eElement.getAttribute("sid"));
               System.out.println("category : " 
                       + eElement.getAttribute("category"));
               System.out.println("name : " 
                       + eElement.getAttribute("name"));
               System.out.println("duration : " 
                       + eElement.getAttribute("duration"));
               developers.add(new Developer( eElement.getAttribute("id"), eElement.getAttribute("tsStart"),
            		   eElement.getAttribute("tsEnd"), eElement.getAttribute("sid"), eElement.getAttribute("filename"),eElement.getAttribute("type"), 
            		   eElement.getAttribute("parent"),eElement.getAttribute("name"),eElement.getAttribute("duration")
            		   ));
               
            }
         }
         
       

        //for header
        CSVUtils.writeLine(writer, Arrays.asList("id","sid","filename","parent", "type", "category","name","tsStart", "tsEnd", "duration"));

        for (Developer d : developers) {

            List<String> list = new ArrayList<>();
            list.add(d.getId());
            list.add(d.getSid());
            list.add(d.getFilename());

            list.add(d.getParent());

            list.add(d.getType());
            list.add(d.getCategory());
            list.add(d.getName());
            list.add(d.getTsStart());
            list.add(d.getTsEnd());
            list.add(d.getDuration());

            CSVUtils.writeLine(writer, list);

	        }

        writer.flush();
        writer.close();

    }
}
