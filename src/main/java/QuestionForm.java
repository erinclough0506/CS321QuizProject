import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ************************************************************************************************************
// 1. Add Code for if both RADIO BUTTONS were pressed go to an icon error or find a way so that only one or the other is pressed
public class QuestionForm {
    static String a = " ";
    static String b = " ";
    static String c = " ";
    static String d = " ";
    static String prompt = " ";
    static String answer = " ";
    // answer flags for MC
    static boolean aTrue = false;
    static boolean bTrue = false;
    static boolean cTrue = false;
    static boolean dTrue = false;
    static boolean noAnswer = false;
    //answer flags & text field for TF
    //static String TFprompt = " ";
    static boolean isTrue = false;
    static boolean isFalse = false;

    public static void QFormMenu() {
        JFrame selector = new JFrame();
        selector.setLayout(new BorderLayout());

        JLabel label1 = new JLabel("Please select which type of question set you would like to build.");
        selector.add(label1, BorderLayout.NORTH);
        JButton Multi = new JButton("Multiple Choice");
        JButton TF = new JButton("True/False");
        selector.add(Multi, BorderLayout.WEST);
        selector.add(TF,BorderLayout.EAST);
        /*if(Multi.isSelected())
        {
            createQuestion();
            System.out.println("Multiple Choice Selected");
        } else if (TF.isSelected())
        {
            CreateTrueFalse();
        }*/
        selector.pack();
        selector.setVisible(true);
        Multi.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Multiple Choice Selected");
                createQuestion();

            }
        });
        TF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("TrueFalse Selected");
                CreateTrueFalse();

            }
        });
    }
    public static void createQuestion(){
        // 1. Add Incorrect Answers
        // 2. vertical Box Layout
        // Create Frame
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        // Create Correct Answer Panel
        JPanel CorrectPanel=new JPanel();
        CorrectPanel.setLayout(new FlowLayout());

        JRadioButton aButton = new JRadioButton("a");
        aButton.setBounds(100,120,100,30);

        JRadioButton bButton = new JRadioButton("b");
        bButton.setBounds(350,120,100,30);

        JRadioButton cButton = new JRadioButton("c");
        cButton.setBounds(100,200,300,30);

        JRadioButton dButton = new JRadioButton("d");
        dButton.setBounds(350,200,300,30);

        JButton newQuestion = new JButton("Next Question");
        newQuestion.setBounds(205,400,100,30);


        frame.add(aButton);
        frame.add(bButton);
        frame.add(cButton);
        frame.add(dButton);
        JButton Submit = new JButton("Submit");
        Submit.setBounds(100,400,100,30);

        JLabel label = new JLabel("Enter your question and select the button that corresponds with the correct answer.");
        final int WIDTH = 20;
        JTextField correct = new JTextField(WIDTH);
        correct.setText(prompt);
        correct.setToolTipText("Enter your Question here.");
        correct.setBounds(50,50,300,30);

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
        /*if(newQuestion.isSelected())
        {
            //will need to write all data that is stored then recall this function to open the form again
            createQuestion();
        }
        if(Submit.isSelected())
        {
            //waiting until .json files work, This will be how they are written to Question.java (storage bank for MC Questions)
        }*/
        frame.add(correct);
        frame.add(label);
        frame.add(CorrectPanel,BorderLayout.NORTH);
        frame.add(newQuestion);
        frame.add(Submit);

        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void CreateTrueFalse()
    {
        // Create Frame
        JFrame frame = new JFrame();
        //frame.setLayout(new BorderLayout());

        // Create Panel
        JPanel Top= new JPanel();              // Create Label Panel
        Top.setLayout(new BorderLayout());     // Define Layout

        // Create Label
        JLabel label = new JLabel("Enter your question and select the button that corresponds with the correct answer."); // Set Title
        Top.add(label,BorderLayout.NORTH);     // Define Title Layout
        Font TitleF=new Font("Courier",Font.BOLD,20);
        label.setFont(TitleF);

        // Create Text Area
        final int WIDTH = 40;
        JTextArea text = new JTextArea(3,6);
        //text.setToolTipText("Enter your Question here.");
        text.setText("Enter your Question here!");
        //text.setBounds(50,50,300,30);


        // Add Scroll
        JScrollPane QScroll=new JScrollPane(text);
        QScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        QScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Top.add(QScroll,BorderLayout.CENTER);
        // Create Button Panel
        JPanel Buttons=new JPanel();               // Create Button Panel
        Buttons.setLayout(new FlowLayout());       // Define Button Layout
        JRadioButton T = new JRadioButton("True");
        JRadioButton F = new JRadioButton("False");
        Buttons.add(T);
        Buttons.add(F);

        JPanel NextButtons=new JPanel();               // Create Button Panel
        NextButtons.setLayout(new FlowLayout());       // Define Button Layout
        JButton newQuestion = new JButton("Next Question");
        JButton Submit = new JButton("Submit");
        NextButtons.add(Submit);
        NextButtons.add(newQuestion);




        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;
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


        /*if(Submit.isSelected())
        {
            //waiting until .json files work, This will be how they are written to True_False.java (storage bank for TF Questions)
        }
        if(newQuestion.isSelected())
        {
            //will need to write all data that is stored then recall this function to open the form again
            createQuestion();
        }*/

        frame.add(Top,BorderLayout.NORTH);
        frame.add(Buttons,BorderLayout.CENTER);
        frame.add(NextButtons,BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(width/2,height/2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void editQuestion(){



    }
}

