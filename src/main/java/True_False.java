import java.util.*;
import java.util.Arrays;
import java.io.*;



public class True_False {



    String prompt;
    String answer;
    String correct_answer;
    String incorrect_answer;
    int correct = 0;
    int incorrect= 0;
    boolean answerB = false;



    True_False[] QuestionB = new True_False[10];



    public String SetQuestion(True_False obj)
    {

        String Question = " ";

        return Question;

    }
    public Boolean SetFlag(String obj)
    {
        Boolean Flag = true;

        if(obj == "T"){

            Flag = true;
        }
        if(obj ==" false")
        {
            Flag = false;
        }

        return Flag;

    }



}
