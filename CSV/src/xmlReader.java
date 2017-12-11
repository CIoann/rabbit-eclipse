import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class xmlReader {





   public static void main(String[] args) {

      try {
         File inputFile = new File("/Users/connie/csv/mytest.txt");
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
               System.out.println("sid : " 
                       + eElement.getAttribute("sid"));
               System.out.println("category : " 
                       + eElement.getAttribute("category"));
               System.out.println("name : " 
                       + eElement.getAttribute("name"));
               
               System.out.println("start : " 
                       + eElement.getAttribute("tsStart"));

               System.out.println("end : " 
                       + eElement.getAttribute("tsEnd"));

               System.out.println("duration : " 
                       + eElement.getAttribute("duration"));
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}