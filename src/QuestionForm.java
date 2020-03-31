import javax.swing.*;

public class QuestionForm {
    String a = " ";
    String b = " ";
    String c = " ";
    String d = " ";
    String prompt = " ";
    String answer = " ";
    boolean aTrue = false;
    boolean bTrue = false;
    boolean cTrue = false;
    boolean dTrue = false;

    public void AnswerKey(String prompt, String answer)
    {
        this.prompt = prompt;
        if (answer == "a")
        {
            aTrue = true;
        }
        else if (answer =="b")
        {
            bTrue = true;
        }
         else if (answer == "c")
        {
            cTrue = true;
        }
         else if (answer =="d")
        {
            dTrue = true;
        }
         else
        {
            cTrue = true;
        }
    }








    public void createQuestion(){
        JFrame frame = new JFrame();
        JRadioButton aButton = new JRadioButton(a);
        JRadioButton bButton = new JRadioButton(b);
        JRadioButton cButton = new JRadioButton(c);
        JRadioButton dButton = new JRadioButton(d);
        JButton newQuestion = new JButton("Next Question");
        JButton Submit = new JButton("Submit");
        JLabel label = new JLabel("Enter your question and select the button that corresponds with the correct answer.");
        final int WIDTH = 20;
        JTextField text = new JTextField(WIDTH);
        text.setText(prompt);
        text.setToolTipText("Enter your Question here.");
        if(aButton.isSelected())
        {
            answer = "a";

        }
        else if(bButton.isSelected())
        {
            answer = "b";
        }
        else if(cButton.isSelected())
        {
            answer = "c";
        }
        else if(dButton.isSelected())
        {
            answer = "d";
        }
        else
        {
            answer = "not selected";
        }


    }



    public void editQuestion(){



    }



}
