import java.util.Scanner;
import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class FlashCardForm {
    // Similar to QuestionForm, this class acts as a
    // template for creating flashcards. It
    // is responsible for getting user input
    // and sending the user created flashcards to be stored.
    private static JTextArea question;
    private static JTextArea answer;
    private static JTextArea QuestD=new JTextArea(10,20);;
    private static JTextArea AnswerD=new JTextArea(10,20);;
    static JFrame flashCardF=new JFrame("Study FlashCard");
    static JPanel mainFC=new JPanel();
    static JButton CAnswer=new JButton("Show Answer");
    static JButton Main=new JButton("Exit to Main");
    static JPanel Buttons=new JPanel (new FlowLayout());
    static JScrollPane QScroll=new JScrollPane(QuestD);

    private static int numb=0;
    private static int numb2=0;
    private static boolean pressed=false;
    private static ArrayList<FlashCard> cardList=new ArrayList<FlashCard>();;
    private static ArrayList<FlashCard> OFCList = new ArrayList<FlashCard>();


    public static void CreateFlashcard()
    {
        final JFrame frame;
        frame =new JFrame("Flash Card");
        JPanel mainPanel=new JPanel();
        Font TextF=new Font("Courier",Font.BOLD,20);
        // Text Area Question
        question=new JTextArea(6,20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(TextF);
        // Text Area Answer
        answer=new JTextArea(6,20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(TextF);
        // Jscroll Pane
        JScrollPane QScroll=new JScrollPane(question);
        QScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        QScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane AScroll=new JScrollPane(answer);
        AScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        AScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Labels
        JLabel QLabel=new JLabel("Question");
        JLabel ALabel=new JLabel("Answer");

        // Buttons
        JPanel Buttons=new JPanel();
        Buttons.setLayout(new FlowLayout());
        JButton nextButton=new JButton("Next Card");
        //JButton previousButton=new JButton("Previous Card");
        JButton Submit=new JButton("Submit");
        //Buttons.add(previousButton);
        Buttons.add(Submit);
        Buttons.add(nextButton);



        System.out.println("Size of LIst "+cardList.size());

        // Add action to Buttons
        nextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Next Card Pressed");
                //FlashCard card = new FlashCard(question.getText(), answer.getText());
                //cardList.add(card);
                System.out.println("Size of LIst "+cardList.size());
                clearCard();


            }
        });
        Submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Submit Pressed");
                //FlashCard card = new FlashCard(question.getText(), answer.getText());
               // cardList.add(card);
                System.out.println("Size of LIst "+cardList.size());
                //File();
                MainGUI.showMainMenu();
                frame.dispose();


            }
        });
       /* previousButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Previous Card Pressed");
                editCard();

            }
        });*/
        //***********************************************************************************************
        // MenuBar- Code I was playing around with a possibility
        JMenuBar menuBar =new JMenuBar();
        JMenu fileMenu=new JMenu("File");
        JMenuItem newMenuItem=new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);

        menuBar.add(fileMenu);
        // Event Listeners for MenuBar
        newMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("New Menu Pressed");



            }
        });
        saveMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Save Menu Pressed");

            }
        });
        //**********************************************************************************************************
        // Create Frame


        // Add components to mainPanel
        mainPanel.add(QLabel);
        mainPanel.add(QScroll);
        mainPanel.add(ALabel);
        mainPanel.add(AScroll);
        mainPanel.add(Buttons);

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;

        // Add to frame
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.add(menuBar,BorderLayout.NORTH);
        frame.setPreferredSize(new Dimension(width/2,height/2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static void getOFCQuestions()
    {
        // Class that sends selected Multiple choice form to an array
        // numb=0; // Reinitialize
        // numb2=1;
        // getQuestions(OMCList);
        FCPlayer();
    }
    private static void FCPlayer()
    {
        // Create Frame

        Font TextF=new Font("Courier",Font.BOLD,20);
        // Create TextArea to Print Question and Answer

        QuestD.setText("Question Here "+numb);
        QuestD.setFont(TextF);


        // Create Scroll

        QScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        QScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        // Create Buttons
        Buttons.add(CAnswer);
        Buttons.add(Main);
        // Create Action For buttons
        CAnswer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Show Answer Pressed");
                if (!pressed)
                {

                    CAnswer.setText("Next Card");
                    QuestD.setText("Answer Here "+numb);
                    pressed=true;
                    numb=numb+1;
                    numb2=numb2+1;
                }
                else
                {
                    CAnswer.setText("ShowAnswer");
                    QuestD.setText("Question Here "+numb);
                    pressed=false;
                }

            }
        });
        Main.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Exit to Main Pressed");
                MainGUI.showMainMenu(); // Exit to main menu
                flashCardF.dispose(); // Dispose of previous Frame

            }
        });




        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;

        // Add to frame
        mainFC.add(QScroll);
        //frame.add(QuestD);
        mainFC.add(Buttons);
        flashCardF.getContentPane().add(BorderLayout.CENTER,mainFC);
        flashCardF.setPreferredSize(new Dimension(width/2,height/2));
        flashCardF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        flashCardF.pack();
        flashCardF.setVisible(true);

    }
    private static void clearCard()
    {
        question.setText("");
        answer.setText("");
        question.requestFocus();
        answer.requestFocus();
    }
    private static void editCard()
    {

    }



}
