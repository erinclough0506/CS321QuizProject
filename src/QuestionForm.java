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
        if (answer.equals("a"))
        {
            aTrue = true;
        }
        else if (answer.equals("b"))
        {
            bTrue = true;
        }
         else if (answer.equals("c"))
        {
            cTrue = true;
        }
         else if (answer.equals("d"))
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
        frame.setLayout(null);
        JRadioButton aButton = new JRadioButton(a);
        aButton.setBounds(100,120,100,30);
        frame.add(aButton);
        JRadioButton bButton = new JRadioButton(b);
        bButton.setBounds(350,120,100,30);
        frame.add(bButton);
        JRadioButton cButton = new JRadioButton(c);
        cButton.setBounds(100,200,300,30);
        frame.add(cButton);
        JRadioButton dButton = new JRadioButton(d);
        cButton.setBounds(350,200,300,30);
        frame.add(cButton);
        JButton newQuestion = new JButton("Next Question");
        newQuestion.setBounds(205,400,100,30);
        frame.add(newQuestion);
        JButton Submit = new JButton("Submit");
        Submit.setBounds(100,400,100,30);
        frame.add(Submit);
        JLabel label = new JLabel("Enter your question and select the button that corresponds with the correct answer.");
        frame.add(label);
        final int WIDTH = 20;
        JTextField text = new JTextField(WIDTH);
        text.setText(prompt);
        text.setToolTipText("Enter your Question here.");
        text.setBounds(50,50,300,30);
        frame.add(text);
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
