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

        int number=1;
        String QPrompt = "Static ports are used for what information";
        String Response1 = "altitude,airspeed";
        String Response2 = "wind direction";
        String Response3 = "Radio Connection";
        String Resp1_flag = "True";
        String Resp2_flag = "False";
        String Resp3_flag = "False";




        Question question1 = new Question(1,QPrompt,Response1,Response2,Response3,Resp1_flag,Resp2_flag,Resp3_flag);
        Question question2 = new Question(2,QPrompt,Response1,Response2,Response3,Resp1_flag,Resp2_flag,Resp3_flag);


        Question[] List = new Question[] {question1,question2};




        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("Questions");
            doc.appendChild(rootElement);
            for (int i = 0; i < List.length; i++) {

                //Set question number in the id section
                Element NumberElement = doc.createElement("QuestionNum");
                NumberElement.setAttribute("Number",""+ List[i].Getnum());
                rootElement.appendChild(NumberElement);
                //---------------------------------------------------------------
                //Set the Question
                Element QuestionElement = doc.createElement("Question");
                QuestionElement.setTextContent(List[i].Qprompt());
                NumberElement.appendChild(QuestionElement);
                //----------------------------------------------------------------
                //Set Response 1
                Element ResponseElement1 = doc.createElement("Response1");
                ResponseElement1.setTextContent(List[i].Response1());
                NumberElement.appendChild(ResponseElement1);
                //----------------------------------------------------------------
                //Set Flag1
                Element Flag_Element1 = doc.createElement("Flag_1");
                Flag_Element1.setTextContent(List[i].Response_Flag1());
                NumberElement.appendChild(Flag_Element1);
                //-----------------------------------------------------------------
                //Set Response 2
                Element Response_Element2 = doc.createElement("Response_2");
                Response_Element2.setTextContent(List[i].Response2());
                NumberElement.appendChild(Response_Element2);
                //-----------------------------------------------------------------
                //Set Flag2
                Element Flag_Element2 = doc.createElement("Flag_2");
                Flag_Element2.setTextContent(List[i].Response_Flag2());
                NumberElement.appendChild(Flag_Element2);
                //-----------------------------------------------------------------
                //Set Response 3
                Element Response_Element3 = doc.createElement("Response_3");
                Response_Element3.setTextContent(List[i].Response3());
                NumberElement.appendChild(Response_Element3);
                //-----------------------------------------------------------------
                //Set Flag3
                Element Flag_Element3 = doc.createElement("Flag_3");
                Flag_Element3.setTextContent(List[i].Response_Flag3());
                NumberElement.appendChild(Flag_Element3);
                //------------------------------------------------------------------
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource dom = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Multiple_Choice.xml"));
            transformer.transform(dom,result);

        }

        catch(Exception e){
            e.printStackTrace();
        }

    }


}
