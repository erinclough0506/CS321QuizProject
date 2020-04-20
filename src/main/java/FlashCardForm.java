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

    /**
     * Creates the FlashCard GUI and sets the buttons action listeners.
     */


    // Similar to QuestionForm, this class acts as a
    // template for creating flashcards. It
    // is responsible for getting user input
    // and sending the user created flashcards to be stored.
    private static JTextArea question;
    private static JTextArea answer;
    private static JTextArea QuestD=new JTextArea(10,20);
    private static JTextArea AnswerD=new JTextArea(10,20);
    static JFrame flashCardF=new JFrame("Study FlashCard");
    static JPanel mainFC=new JPanel();
    static JButton CAnswer=new JButton("Show Answer");
    static JButton Main=new JButton("Exit to Main");
    static JPanel Buttons=new JPanel (new FlowLayout());
    static JScrollPane QScroll=new JScrollPane(QuestD);
    private static int counter=0;
    private static int numb=0;
    private static int numb2=1;
    private static boolean pressed=false;
    private static boolean end=false;
    private static ArrayList<FlashCard> cardList=new ArrayList<FlashCard>();
    private static ArrayList<FlashCard> OFCList = new ArrayList<FlashCard>();


    public static void CreateFlashcard()
    {
        /**
         * The GUI for creating the the flashcards and setting the text boxes and attributes
         */
        final JFrame frame =new JFrame("Flash Card");
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
                /**
                 * Action listener for pressing the next card button.
                 */
                System.out.println("Next Card Pressed");
                FlashCard card = new FlashCard(counter,question.getText(), answer.getText());
                cardList.add(card);
                counter = cardList.size();
                System.out.println("Size of LIst "+cardList.size());
                clearCard();


            }
        });
        Submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                /**
                 *  Action listener for submitting the FlashCards that have been built.
                 */
                System.out.println("Submit Pressed");
                FlashCard card = new FlashCard(counter, question.getText(), answer.getText());
                cardList.add(card);
                counter = cardList.size();
                System.out.println("Final Size of LIst "+cardList.size());
                File(cardList); // Add list to XML File
                cardList.clear(); // clears array
                MainGUI.showMainMenu();
                frame.dispose();
            }
        });

        // Add components to mainPanel
        mainPanel.add(QLabel);
        mainPanel.add(QScroll);
        mainPanel.add(ALabel);
        mainPanel.add(AScroll);
        mainPanel.add(Buttons);
        // Create Help Menu Bar
        JMenuBar Help= new JMenuBar();
        JMenu helpMenu= new JMenu("Help");
        JMenuItem helpMenuItem=new JMenuItem("Help");
        helpMenu.add(helpMenuItem);
        Help.add(helpMenu);

        helpMenuItem.addActionListener(new ActionListener()
        {
            // If Help Was Pressed
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("HelpMenu Pressed");
                JOptionPane.showMessageDialog(null, "Type a Question into question Text area. \n Type the Answer in to the answer text area.\n Select either Next to create a new Flashcard or Submit to save Flashcard deck");

            }
        });
        frame.setJMenuBar(Help);

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;

        // Add to frame
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        // frame.add(menuBar,BorderLayout.NORTH);
        frame.setPreferredSize(new Dimension(width/2,height/2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static void getOFCQuestions()
    {

        /**
         * function that sends selected Multiple choice form to an array
          */

        getFlashCards(OFCList);
        FCPlayer();
    }


    private static void FCPlayer()
    {
        /**
         * Main GUI test driver that sets the question, and checks the write and wrong answers based on the action
         * listeners
         */
        // Create Frame

        Font TextF=new Font("Courier",Font.BOLD,20);
        // Create TextArea to Print Question and Answer

        QuestD.setText(OFCList.get(numb).getTerm());
        QuestD.setFont(TextF);
        final int ArrayS= OFCList.size();

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
                if (!end) {
                    if (!pressed) {

                        if (numb2 == ArrayS) {
                            QuestD.setText(OFCList.get(numb).getDefinition());
                            numb = 0;
                            numb2 = 1;
                            end = true;
                            CAnswer.setText("Restart");
                            System.out.println("Random");
                        } else if (numb2>ArrayS)
                        {
                            CAnswer.setText("Show Answer");

                        } else {
                            CAnswer.setText("Next Card");
                            QuestD.setText(OFCList.get(numb).getDefinition());
                            System.out.println("Show Pressed: Array " + ArrayS + " numb2 " + numb2);
                            pressed = true;
                            numb = numb + 1;
                            numb2 = numb2 + 1;
                        }

                    }
                    else {

                        CAnswer.setText("Show Answer");
                        QuestD.setText(OFCList.get(numb).getTerm());
                        pressed = false;
                        System.out.println("Next Pressed Array " + ArrayS + " numb2 " + numb2);


                    }
                } else {

                    numb = 0;
                    numb2 = 1;
                    pressed = false;
                    end=false;
                    CAnswer.setText("Show Answer");
                    QuestD.setText(OFCList.get(numb).getTerm());
                    System.out.println("end==true");


                }
            }
        });
        Main.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                numb = 0;
                numb2 = 1;
                pressed = false;
                end=false;
                OFCList.clear();
                System.out.println("Exit to Main Pressed");
                MainGUI.showMainMenu(); // Exit to main menu
                flashCardF.dispose(); // Dispose of previous Frame

            }
        });

        // Create Help Menu Bar
        JMenuBar Help= new JMenuBar();
        JMenu helpMenu= new JMenu("Help");
        JMenuItem helpMenuItem=new JMenuItem("Help");
        helpMenu.add(helpMenuItem);
        Help.add(helpMenu);

        helpMenuItem.addActionListener(new ActionListener()
        {
            // If Help Was Pressed
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("HelpMenu Pressed");
                JOptionPane.showMessageDialog(null, "Read Question and mentally answer \n Press show answer to see if you guessed the correct answer\n Once deck is finished select restart to restart flashcard sequence");

            }
        });
        flashCardF.setJMenuBar(Help);


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
    public static void getFlashCards(ArrayList<FlashCard> List)
    {
        try {
            File FCXML = new File("Flashcard.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(FCXML);
            NodeList flashcardList = doc.getElementsByTagName("flashcard");
            System.out.println("Length=" + flashcardList.getLength());
            for (int i = 0; i < flashcardList.getLength(); i++) {
                Node FlashCardNode = flashcardList.item(i);
                if (FlashCardNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element QuestionElement = (Element) FlashCardNode;
                    String FlashCardId = QuestionElement.getAttribute("num");
                    String FlashCardTerm = QuestionElement.getElementsByTagName("term").item(0).getTextContent();
                    String FlashCardDefinition = QuestionElement.getElementsByTagName("definition").item(0).getTextContent();
                    FlashCard card = new FlashCard(i, FlashCardTerm, FlashCardDefinition);
                    List.add(card);
                    System.out.println("Question Number = " + FlashCardId);
                    System.out.println("Question = " + FlashCardTerm);
                    System.out.println("Answer = " + FlashCardDefinition);

                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't Save");
        }
    }

    public static void File(ArrayList<FlashCard> flashcardList)
    {
        /**
         * Creates the XML file and appends the contents while also keeping up with
         * how many objects are needed by passing the function the flashcard list
         * array
         */
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
            NodeList QuestionList = doc.getElementsByTagName("FlashcardSet");
            System.out.println("Length=" + QuestionList.getLength());
            //for output to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            //print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            //StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("Flashcard.xml"));

            //write data
            //transformer.transform(source, console);
            transformer.transform(source, file);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    //creates new flashcard in xml
    private static Node createFlashCardElement(Document doc, String num, String term, String definition)
    {
        /**
         * Creates the single card from the array and is appended when createFlashCardElement is called
         */
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
