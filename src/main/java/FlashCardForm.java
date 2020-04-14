import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Scanner;
import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FlashCardForm {
    //Similar to QuestionForm, this class acts as a
    // //template for creating flashcards. It
    // is responsible for getting user input
    // and sending the user created flashcards to be stored.
    private static JTextArea question;
    private static JTextArea answer;
    //private static ArrayList<FlashCard> cardList;


    public static void CreateFlashcard() {
        JPanel mainPanel = new JPanel();
        Font TextF = new Font("Courier", Font.BOLD, 20);
        // Text Area Question
        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(TextF);
        // Text Area Answer
        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(TextF);
        // Jscroll Pane
        JScrollPane QScroll = new JScrollPane(question);
        QScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        QScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane AScroll = new JScrollPane(answer);
        AScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        AScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Labels
        JLabel QLabel = new JLabel("Question");
        JLabel ALabel = new JLabel("Answer");

        // Buttons
        JPanel Buttons = new JPanel();
        Buttons.setLayout(new FlowLayout());
        JButton nextButton = new JButton("Next Card");
        JButton previousButton = new JButton("Previous Card");
        Buttons.add(previousButton);
        Buttons.add(nextButton);

        //cardList=new ArrayList<FlashCard>();
        //System.out.println("Size of LIst "+cardList.size());

        // Add action to Buttons
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Next Card Pressed");
                //FlashCard card = new FlashCard(question.getText(), answer.getText());
                //cardList.add(card);
                //System.out.println("Size of LIst "+cardList.size());
                clearCard();


            }
        });
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Previous Card Pressed");
                editCard();

            }
        });
        //***********************************************************************************************
        // MenuBar- Code I was playing around with a possibility
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);

        menuBar.add(fileMenu);
        // Event Listeners for MenuBar
        newMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("New Menu Pressed");


            }
        });
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Save Menu Pressed");

            }
        });
        //**********************************************************************************************************
        // Create Frame
        JFrame frame;
        frame = new JFrame("Flash Card");

        // Add components to mainPanel
        mainPanel.add(QLabel);
        mainPanel.add(QScroll);
        mainPanel.add(ALabel);
        mainPanel.add(AScroll);
        mainPanel.add(Buttons);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height = screenSize.height;
        int width = screenSize.width;

        // Add to frame
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.add(menuBar, BorderLayout.NORTH);
        frame.setPreferredSize(new Dimension(width / 2, height / 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
        answer.requestFocus();
    }

    private static void editCard() {

    }


    public static void getFlashCards(Document doc)
    {
        NodeList flashcardList = doc.getElementsByTagName("QuestionNum");
        for(int i = 0; i < flashcardList.getLength(); i++)
        {
            Node FlashCardNode = flashcardList.item(i);
            if (FlashCardNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element QuestionElement = (Element) FlashCardNode;
                String FlashCardId = QuestionElement.getElementsByTagName("num").item(0).getTextContent();
                String FlashCardTerm = QuestionElement.getElementsByTagName("Question").item(0).getTextContent();
                String FlashCardDefinition = QuestionElement.getElementsByTagName("Flag").item(0).getTextContent();
                System.out.println("Question Number = " + FlashCardId);
                System.out.println("Question = " + FlashCardTerm);
                System.out.println("Question Flag = " + FlashCardDefinition);

            }
        }
    }

    public static void File(ArrayList<FlashCard> flashcardList)
    {
        Element rootElement;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.newDocument();

            //add elements to document
            rootElement = doc.createElement("FlashcardSet");

            //append root element to document
            doc.appendChild(rootElement);

            for (int i = 0; i < flashcardList.size(); i++) {
                rootElement.appendChild(createFlashCardElement(doc, Integer.toString(i + 1), flashcardList.get(i).term, flashcardList.get(i).definition));
            }

            //for output to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            //print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("Flashcard.xml;"));

            //write data
            transformer.transform(source, console);
            transformer.transform(source, file);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    //creates new flashcard in xml
    private static Node createFlashCardElement(Document doc, String num, String term, String definition)
    {
        Element SetName = doc.createElement("flashcard");

        //set num attribute
        SetName.setAttribute("num", num);

        //create term element
        SetName.appendChild(createFlashCardElements(doc, SetName, "term", term));

        //create definition element
        SetName.appendChild(createFlashCardElements(doc, SetName, "definition", definition));

        return SetName;
    }

    //method to create text node
    private static Node createFlashCardElements(Document doc, Element element, String name, String value)
    {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;

    }

}

