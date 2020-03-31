import javax.swing.*;

public class QuestionForm {
    //text fields for MC
    String a = " ";
    String b = " ";
    String c = " ";
    String d = " ";
    String prompt = " ";
    String answer = " ";
    // answer flags for MC
    boolean aTrue = false;
    boolean bTrue = false;
    boolean cTrue = false;
    boolean dTrue = false;
    boolean noAnswer = false;
    //answer flags & text field for TF
    String TFprompt = " ";
    boolean isTrue = false;
    boolean isFalse = false;

    public void createQuestion(){
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setVisible(true);
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
            aTrue = true;
        }
        else if(bButton.isSelected())
        {
            answer = "b";
            bTrue = true;
        }
        else if(cButton.isSelected())
        {
            answer = "c";
            cTrue = true;
        }
        else if(dButton.isSelected())
        {
            answer = "d";
            dTrue = true;
        }
        else
        {
            answer = "not selected";
            noAnswer = true;
        }
        if(newQuestion.isSelected())
        {
            //will need to write all data that is stored then recall this function to open the form again
            createQuestion();
        }
        if(Submit.isSelected())
        {
            //waiting until .json files work, This will be how they are written to Question.java (storage bank for MC Questions)
        }

    }
    public void CreateTrueFalse()
    {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        JLabel label = new JLabel("Enter your question and select the button that corresponds with the correct answer.");
        frame.add(label);
        JRadioButton T = new JRadioButton("True");
        frame.add(T);
        JRadioButton F = new JRadioButton("False");
        frame.add(F);
        JButton newQuestion = new JButton("Next Question");
        frame.add(newQuestion);
        JButton Submit = new JButton("Submit");
        frame.add(Submit);
        final int WIDTH = 20;
        JTextField text = new JTextField(WIDTH);
        text.setText(TFprompt);
        text.setToolTipText("Enter your Question here.");
        text.setBounds(50,50,300,30);
        frame.add(text);
        if(T.isSelected())
        {
            isTrue = true;
            answer = "T";
        }
        else if(F.isSelected())
        {
            isFalse = true;
            answer = "F";
        }

        if(Submit.isSelected())
        {
            //waiting until .json files work, This will be how they are written to True_False.java (storage bank for TF Questions)
        }
        if(newQuestion.isSelected())
        {
            //will need to write all data that is stored then recall this function to open the form again
            createQuestion();
        }


    }

    public void editQuestion(){



    }



}
