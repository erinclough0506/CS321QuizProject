import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.*;


public class True_FalseForm {
    private static JTextArea question;
    public static void CreateTrueFalse() { // Make a TRUE_false form class
        // Create Frame
        JFrame frame = new JFrame();
        //frame.setLayout(new BorderLayout());

        // Create Panel
        JPanel Top = new JPanel();              // Create Label Panel
        Top.setLayout(new BorderLayout());     // Define Layout

        // Create Label
        JLabel label = new JLabel("Enter your question and Select the button that corresponds with the correct answer."); // Set Title
        Top.add(label, BorderLayout.NORTH);     // Define Title Layout
        Font TitleF = new Font("Courier", Font.BOLD, 20);
        label.setFont(TitleF);

        // Text Area Question
        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(TitleF);


        // Add Scroll
        JScrollPane QScroll = new JScrollPane(question);
        QScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        QScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Top.add(QScroll, BorderLayout.CENTER);
        // Create Button Panel
        JPanel Buttons = new JPanel();               // Create Button Panel
        Buttons.setLayout(new FlowLayout());       // Define Button Layout
        JRadioButton T = new JRadioButton("True");
        JRadioButton F = new JRadioButton("False");
        Buttons.add(T);
        Buttons.add(F);

        JPanel NextButtons = new JPanel();               // Create Button Panel
        NextButtons.setLayout(new FlowLayout());       // Define Button Layout
        JButton newQuestion = new JButton("Next Question");
        JButton Submit = new JButton("Submit");
        NextButtons.add(Submit);
        NextButtons.add(newQuestion);

        newQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Next Question Pressed");
                //FlashCard card = new FlashCard(question.getText());
                clearPage();


            }
        });
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Submit Pressed");
                //FlashCard card = new FlashCard(question.getText());
                //clearPage();


            }
        });


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height = screenSize.height;
        int width = screenSize.width;

        frame.add(Top, BorderLayout.NORTH);
        frame.add(Buttons, BorderLayout.CENTER);
        frame.add(NextButtons, BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(width / 2, height / 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private static void clearPage() {
        question.setText("");
        question.requestFocus();
    }

//--------------------------------------------------------------------------------------------------------------
// Create True False test method and display
//--------------------------------------------------------------------------------------------------------------

    private JFormattedTextField trueFalseFormattedTextField;

    public static void  createTest() {

        JFrame frame = new JFrame();

        //Temporary item, XML information will need to be inserted into the Text File.
        String item = "THIS IS AN ITEM";

        //Create panel
        JPanel Top = new JPanel();
        Top.setLayout(new BorderLayout());
        JFormattedTextField Output = new JFormattedTextField(item);
        Top.add(Output,BorderLayout.NORTH);

        //-------------------------------------------------------
        //creates the Buttons
        JPanel Buttons = new JPanel();
        Buttons.setLayout( new FlowLayout());
        //Set True Button
        JButton TRUE = new JButton("TRUE");
        TRUE.setBackground(Color.GREEN);

        //Set False Button
        JButton FALSE = new JButton("FALSE");
        FALSE.setBackground(Color.RED);
        Buttons.add(TRUE);
        Buttons.add(FALSE);

        //Sets next
        JRadioButton next = new JRadioButton("next");
        Buttons.add(next);



        TRUE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("true  pressed");
            }
        });
        //-----------------------------------------------------------
        FALSE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("false  pressed");
            }
        });


        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("next  pressed");
            }
        });




        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height = screenSize.height;
        int width = screenSize.width;
        frame.setPreferredSize(new Dimension(width / 2, height / 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(Top,BorderLayout.NORTH);
        frame.add(Buttons,BorderLayout.CENTER);
        frame.pack();
        Top.setVisible(true);
        frame.setVisible(true);

    }





}