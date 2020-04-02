import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.util.Arrays;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.NodeList;


public class True_False {


    String prompt;
    String answer;
    String correct_answer;
    String incorrect_answer;
    int correct = 0;
    int incorrect = 0;
    boolean answerB = false;


    True_False[] QuestionB = new True_False[10];


    public String SetQuestion(String obj) {

        String Question = " ";

        return Question;

    }

    public Boolean SetFlag(String obj) {
        Boolean Flag = true;

        if (obj == "true") {

            Flag = true;
        }
        if (obj == " false") {
            Flag = false;
        }

        return Flag;

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


