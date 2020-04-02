import java.util.Scanner;
import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class FlashCardForm {
    //Similar to QuestionForm, this class acts as a
    // //template for creating flashcards. It
    // is responsible for getting user input
    // and sending the user created flashcards to be stored.
    private static JTextArea question;
    private static JTextArea answer;
    //private static ArrayList<FlashCard> cardList;


    public static void CreateFlashcard()
    {
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
        JButton previousButton=new JButton("Previous Card");
        Buttons.add(previousButton);
        Buttons.add(nextButton);

        //cardList=new ArrayList<FlashCard>();
        //System.out.println("Size of LIst "+cardList.size());

        // Add action to Buttons
        nextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Next Card Pressed");
                //FlashCard card = new FlashCard(question.getText(), answer.getText());
                //cardList.add(card);
                //System.out.println("Size of LIst "+cardList.size());
                clearCard();


            }
        });
        previousButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Previous Card Pressed");
                editCard();

            }
        });
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
        JFrame frame;
        frame =new JFrame("Flash Card");

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

