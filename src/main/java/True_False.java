import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.transform.dom.DOMSource;

public class True_False {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {



        //----------------------------------------
        //Temporary driver
        //----------------------------------------

       // True_False Item = new True_False();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Write your question");

        String Question = myObj.nextLine();


        System.out.println("Set Whether is is true or false");
        Scanner myObj2 = new Scanner(System.in);
        String Flag = myObj2.nextLine();

        System.out.println("The question is " + Question);
        System.out.println("The Flag is set to  " + Flag);


//---------------------------------------------------------------------



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
            rootElement.appendChild(createUserElement(doc, "1","1", Question, Flag));

            //append first child element to root element
            rootElement.appendChild(createUserElement(doc, "2","2", Question, Flag));

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
            getQuestion(doc);


        } catch(Exception e)

        {

            e.printStackTrace();
        }

    }

    //-----------------------------------
//Creates the elements tags.
//-----------------------------------
    private static Node createUserElement(Document doc,String Question_num,String Num,String Question,String Flag)
    {
        Element user = doc.createElement("QuestionNum");
        //set id attribute
        user.setAttribute("Users",Question_num);

        //set number
        user.appendChild(createUserElements(doc,user,"num",Num));
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


    private static void getQuestion(Document doc)
    {
        NodeList QuestionList = doc.getElementsByTagName("QuestionNum");
        for(int i=0; i<QuestionList.getLength(); i++)
        {
            Node QuestionNode = QuestionList.item(i);
            if(QuestionNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element QuestionElement = (Element) QuestionNode;
                String QuestionId = QuestionElement.getElementsByTagName("num").item(0).getTextContent();
                String QuestionName = QuestionElement.getElementsByTagName("Question").item(0).getTextContent();
                String QuestionFlag = QuestionElement.getElementsByTagName("Flag").item(0).getTextContent();
                System.out.println("Question Number = " + QuestionId);
                System.out.println("Question = " + QuestionName);
                System.out.println("Question Flag = " + QuestionFlag);

            }
        }
    }

}

