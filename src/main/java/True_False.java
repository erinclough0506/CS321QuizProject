import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class True_False {

    public static ArrayList<True_FalseTest> TF_List = new ArrayList<True_FalseTest>();

    public static void main(String[] args) {
        True_FalseTest item = new True_FalseTest(0,"this is a question","true");

        TF_List.add(item);
        System.out.println("This is the prompt");


        TF_file();
        getQuestionsTF(TF_List);
    }

//---------------------------------------------------------------------


    public static void getQuestionsTF(ArrayList<True_FalseTest> List) {
        try {
            File T_F = new File("True_False.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(T_F);
            System.out.println("rootElement:" + doc.getDocumentElement().getNodeName());
            NodeList QuestionList = doc.getElementsByTagName("QuestionNum");
            System.out.println("Length = " + QuestionList.getLength());
            for (int i = 0; i < QuestionList.getLength(); i++) {
                Node QuestionNode = QuestionList.item(i);
                if (QuestionNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element QuestionElement = (Element) QuestionNode;
                    String QuestionId = QuestionElement.getAttribute("Number");
                    System.out.println("Question Number = " + QuestionId);
                    String QuestionName = QuestionElement.getElementsByTagName("Question").item(0).getTextContent();
                    System.out.println("Question = " + QuestionName);
                    String Flag = QuestionElement.getElementsByTagName("Flag_1").item(0).getTextContent();
                    System.out.println("Answer1 = " + Flag);

                    True_FalseTest Build = new True_FalseTest(i, QuestionName,Flag);
                    TF_List.add(Build);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to save");
        }
    }


    public static void TF_file () {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("Questions");
            doc.appendChild(rootElement);
            for (int i = 0; i < TF_List.size(); i++) {

                //Set Question number in the id
                Element NumberElement = doc.createElement("QuestionNum");
                NumberElement.setAttribute("Number", "" + TF_List.get(i).getNum());
                rootElement.appendChild(NumberElement);
                //---------------------------------------------------------------
                //Set the Question
                Element QuestionElement = doc.createElement("Question");
                QuestionElement.setTextContent(TF_List.get(i).getQuestion());
                NumberElement.appendChild(QuestionElement);
                //----------------------------------------------------------------
                //Set Flag1
                Element Flag_Element1 = doc.createElement("Flag_1");
                Flag_Element1.setTextContent(TF_List.get(i).getFlag());
                NumberElement.appendChild(Flag_Element1);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                StreamResult result = new StreamResult(new File("True_False.xml"));
                DOMSource dom = new DOMSource(doc);
                transformer.transform(dom, result);


            }


        } catch (Exception e) {

            e.printStackTrace();
        }


    }


}

