import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.util.Arrays;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.NodeList;


public class True_False {
    public static void main(String[] args){


        //----------------------------------------
        //Temporary driver
        //----------------------------------------
    True_False Item = new True_False();
    Scanner myObj = new Scanner(System.in);
        System.out.println("Write your question");

    String Question = myObj.nextLine();


        System.out.println("Set Whether is is true or false");
    Scanner myObj2 = new Scanner(System.in);
    String Flag = myObj2.nextLine();

        System.out.println("The question is " + Question);
        System.out.println("The Flag is set to  " + Flag);

//--------------------------------------------------------------------



    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;
    try{
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        // add elements to Document
        Element rootElement = doc.createElement("User");
        //append root element to document
        doc.appendChild(rootElement);


        //This shows that input variables can be used to fill out the xml  document.
        //append first child element to root element
        rootElement.appendChild(createUserElement(doc, "1", Question, Flag));

        //append first child element to root element
        rootElement.appendChild(createUserElement(doc, "2", Question, Flag));

        //for output to file, console
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //for pp
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        //For formatting
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(new File("create_user.xml"));

        //Write Data
        transformer.transform(source, console);
        transformer.transform(source, file);

    } catch(Exception e)

    {

        e.printStackTrace();
    }

}

    //-----------------------------------
//Creates the elements tags.
//-----------------------------------
    private static Node createUserElement(Document doc,String Question_num,String Question,String Flag )
    {
        Element user = doc.createElement("User");
        //set id attribute
        user.setAttribute("Question_num",Question_num);

        //create Question
        user.appendChild(createUserElements(doc,user,"Question",Question));
        //Create Flag
        user.appendChild(createUserElements(doc,user,"Flag",Flag));

        return user;
    }
//will need to write a utility method to create the text node


    // utility method to create text node
    private static Node createUserElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }



    public void ReadQuestions() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("Items");
            NodeList personList = doc.getElementsByTagName("Items");

            personList.getLength();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}//---------------------------------------------


