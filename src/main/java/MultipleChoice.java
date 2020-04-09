import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import java.util.*;
import java.util.Arrays;
import java.io.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.NodeList;


public class MultipleChoice {

    public static void main(String[] args) {
        // write your code here

        int number = 1;
        String QPrompt = "Static ports are used for what information";
        String Response1 = "altitude,airspeed";
        String Response2 = "wind direction";
        String Response3 = "Radio Connection";
        String Resp1_flag = "True";
        String Resp2_flag = "True";
        String Resp3_flag = "False";


        Question question1 = new Question(1, QPrompt, Response1, Response2, Response3, Resp1_flag, Resp2_flag, Resp3_flag);

        Question question2 = new Question(1, QPrompt, Response1, Response2, Response3, Resp1_flag, Resp2_flag, Resp3_flag);
        Question question3 = new Question(1, QPrompt, Response1, Response2, Response3, Resp1_flag, Resp2_flag, Resp3_flag);
        // Question1 question4 = new Question1(1,QPrompt,Response1,Response2,Response3,Resp1_flag,Resp2_flag,Resp3_flag);
        // Question1 question5 = new Question1(1,QPrompt,Response1,Response2,Response3,Resp1_flag,Resp2_flag,Resp3_flag);

        ArrayList<Question> List = new ArrayList<Question>();
        List.add(question1);
        List.add(question2);
        List.add(question3);

        // Question1[] List = new Question1[] {question1,question2,question3,question4,question5};


        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("Questions");
            doc.appendChild(rootElement);
            for (int i = 0; i < List.size(); i++) {

                //Set question number in the id section
                Element NumberElement = doc.createElement("QuestionNum");
                NumberElement.setAttribute("Number", "" + List.get(i).Getnum());
                rootElement.appendChild(NumberElement);
                //---------------------------------------------------------------
                //Set the Question
                Element QuestionElement = doc.createElement("Question");
                QuestionElement.setTextContent(List.get(i).Qprompt());
                NumberElement.appendChild(QuestionElement);
                //----------------------------------------------------------------
                //Set Response 1
                Element ResponseElement1 = doc.createElement("Response1");
                ResponseElement1.setTextContent(List.get(i).Response1());
                NumberElement.appendChild(ResponseElement1);
                //----------------------------------------------------------------
                //Set Flag1
                Element Flag_Element1 = doc.createElement("Flag_1");
                Flag_Element1.setTextContent(List.get(i).Response_Flag1());
                NumberElement.appendChild(Flag_Element1);
                //-----------------------------------------------------------------
                //Set Response 2
                Element Response_Element2 = doc.createElement("Response_2");
                Response_Element2.setTextContent(List.get(i).Response2());
                NumberElement.appendChild(Response_Element2);
                //-----------------------------------------------------------------
                //Set Flag2
                Element Flag_Element2 = doc.createElement("Flag_2");
                Flag_Element2.setTextContent(List.get(i).Response_Flag2());
                NumberElement.appendChild(Flag_Element2);
                //-----------------------------------------------------------------
                //Set Response 3
                Element Response_Element3 = doc.createElement("Response_3");
                Response_Element3.setTextContent(List.get(i).Response3());
                NumberElement.appendChild(Response_Element3);
                //-----------------------------------------------------------------
                //Set Flag3
                Element Flag_Element3 = doc.createElement("Flag_3");
                Flag_Element3.setTextContent(List.get(i).Response_Flag3());
                NumberElement.appendChild(Flag_Element3);
                //------------------------------------------------------------------
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource dom = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Multiple_Choice.xml"));
            transformer.transform(dom, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

 /*   private static void getQuestion(Document doc)
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
  */
}