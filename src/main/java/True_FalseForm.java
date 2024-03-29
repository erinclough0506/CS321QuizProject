import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.transform.dom.DOMSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class True_FalseForm {
    private static JTextArea question;
    private static int counter=0;
    private static String T_F="F";
    private static String True_FALSE="F";
    private static int numb=0;
    private static int numb2=1;
    private static boolean end= false;
    public static ArrayList<True_False> TF_List = new ArrayList<True_False>();
    public static ArrayList<True_False> OTF_List = new ArrayList<True_False>();
    static int scoreT=0;
    static String  aTrue = "0";
    static String  bTrue = "0";


    /**
     * This function creates true/false questions and stores them in an XML file.
     */
    public static void CreateTrueFalse() { // Make a TRUE_false form class

        // Create Frame
        final JFrame frame = new JFrame();
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
        final JRadioButton T = new JRadioButton("True");
        final JRadioButton F = new JRadioButton("False");
        Buttons.add(T);
        Buttons.add(F);

        JPanel NextButtons = new JPanel();               // Create Button Panel
        NextButtons.setLayout(new FlowLayout());       // Define Button Layout
        JButton newQuestion = new JButton("Next Question");
        JButton Submit = new JButton("Submit");
        NextButtons.add(Submit);
        NextButtons.add(newQuestion);
        // Button Action Listener
        T.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("True Selected");
                T_F="1";

                F.setSelected(false);

            }
        });
        F.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("False Selected");
                T_F="0";
                T.setSelected(false);

            }
        });
        newQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Next Question Pressed");
                True_False Form = new True_False(counter, question.getText(), T_F);
                TF_List.add(Form);
                counter = TF_List.size();
                T.setSelected(false);
                F.setSelected(false);
                clearPage();



            }
        });
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // If user is ready to submit test
                System.out.println("Submit Pressed");
                // Adds Last question to test
                True_False Form = new True_False(counter, question.getText(), T_F);
                TF_List.add(Form);
                counter = TF_List.size();
                TF_file (); // Sends questions to file
                TF_List.clear(); // Clears array
                MainGUI.showMainMenu();
                frame.dispose();


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
                JOptionPane.showMessageDialog(null, "Type a statement or question into the text area.\n If the statement written is true then select true if not select false.\n Press next to create more questions and when you are finished select Submit ");

            }
        });
        frame.setJMenuBar(Help);
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

    /**
     * this function clears the page to allow for more user input when creating questions.
     */
    private static void clearPage() {
        question.setText("");
        question.requestFocus();
    }

//--------------------------------------------------------------------------------------------------------------
// Create True False test method and display
//--------------------------------------------------------------------------------------------------------------


    /**
     * This functions retrieves the user's stored question data and
     * passes control to the T/F test after it is recieved.
     */
    public static void getTFQuestions()
    {
        // Class that sends selected Multiple choice form to an array
        numb =0;
        numb2=1;
        getQuestionsTF(OTF_List);
        createTest();
    }

    /**
     * This function is the Testing interface the user see when taking a true/false test. It determines whether answers
     * are right or wrong, and calculates your grade at the end
     */
    public static void createTest() {

        final JFrame frame = new JFrame();

        //Temporary item, XML information will need to be inserted into the Text File.

        //Create panel
        JPanel Top = new JPanel();
        Top.setLayout(new BorderLayout());
        final JLabel Output = new JLabel(OTF_List.get(numb).getQuestion());
        Top.add(Output, BorderLayout.NORTH);

        //-------------------------------------------------------
        //creates the Buttons
        final JPanel Buttons = new JPanel();
        Buttons.setLayout(new FlowLayout());
        //Set True Button
        final JRadioButton TRUE = new JRadioButton("TRUE");
        TRUE.setBackground(Color.GREEN);

        //Set False Button
        final JRadioButton FALSE = new JRadioButton("FALSE");
        FALSE.setBackground(Color.RED);
        Buttons.add(TRUE);
        Buttons.add(FALSE);

        //Sets next
        final JButton next = new JButton("Next");
        final JButton Menu= new JButton("Return to Main");
        Buttons.add(next);
        Buttons.add(Menu);

        // Action Listener for buttons
        TRUE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(TRUE.isSelected()) {
                    // Radio Button is set to true
                    System.out.println("true selected");
                    FALSE.setSelected(false);
                    True_FALSE = "True";
                    aTrue = "1";
                    bTrue = "3";

                }
                else {
                    System.out.println("True is not selected");
                    aTrue = "0";
                }
//a comment
            }
        });
        //-----------------------------------------------------------
        FALSE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(FALSE.isSelected()) {
                    // Radio False button set to true
                    System.out.println("false selected");
                    TRUE.setSelected(false);
                    True_FALSE = "False";
                    aTrue = "3";
                    bTrue = "0";
                }
                else{
                    System.out.println("False no selected");
                    bTrue = "1";

                }
            }
        });

// shifts through each question and last question prompts the reader to submit the test for score
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("next  pressed");

                String tNew = null;


                System.out.print("This is True- " + aTrue);
                System.out.print("This is False-" + bTrue);
                System.out.println("inside list- "+ OTF_List.get(numb).getFlag());
                tNew = OTF_List.get(numb).getFlag();


                System.out.println(tNew);
                // System.out.println(fNew);

                if (aTrue.equals(tNew)){
                    scoreT = scoreT+1;
                }
                else if(bTrue.equals(tNew)){
                    scoreT = scoreT+1;
                }


                if (!end) {
                    numb = numb + 1;
                    numb2 = numb2 + 1;
                    TRUE.setSelected(false);
                    FALSE.setSelected(false);
                    True_FALSE = "NA"; //// Answer neither True or False

                    Output.setText(OTF_List.get(numb).getQuestion());
                    if (numb2>=OTF_List.size())
                    {
                        next.setText("Submit Test");
                        end = true;

                    }
                }
                else
                {
                    // When test is over prints the result of test
                    printResult();
                    frame.dispose();
                }

            }
        });
        Menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Menu  pressed");
                MainGUI.showMainMenu();
                frame.dispose();
                OTF_List.clear();


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
                JOptionPane.showMessageDialog(null, "Read the statement at the top if it is true select the true button if it is false select the false button.\n Click to the next question until test is finished then select Submit Test to receive your results");

            }
        });
        frame.setJMenuBar(Help);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height = screenSize.height;
        int width = screenSize.width;
        frame.setPreferredSize(new Dimension(width / 2, height / 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(Top, BorderLayout.NORTH);
        frame.add(Buttons, BorderLayout.CENTER);
        frame.pack();
        Top.setVisible(true);
        frame.setVisible(true);



    }

    /**
     * This function give user data they have created in createTrueFalse() from an XML file to the testing interface.
     * @param List is an array is that is used to store the information into the file in the order it was input.
     */
    public static void getQuestionsTF(ArrayList<True_False> List) {
        try {
            // Gets xml formated questions to display for user
            File T_F = new File("True_False.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(T_F);
            System.out.println("rootElement:" + doc.getDocumentElement().getNodeName());
            NodeList QuestionList = doc.getElementsByTagName("QuestionNum");
            System.out.println("Length = " + QuestionList.getLength());
            for (int i = 0; i < QuestionList.getLength(); i++) {
                Node QuestionNode = QuestionList.item(i);
                if (QuestionNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element QuestionElement = (Element) QuestionNode;
                    String QuestionId = QuestionElement.getAttribute("Number");
                    System.out.println("Question Number = " + QuestionId);
                    String QuestionName = QuestionElement.getElementsByTagName("Question").item(0).getTextContent();
                    System.out.println("Question = " + QuestionName);
                    String Flag = QuestionElement.getElementsByTagName("Flag_1").item(0).getTextContent();
                    System.out.println("Answer1 = " + Flag);

                    True_False Build = new True_False(i, QuestionName,Flag);
                    List.add(Build);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to save");
        }
    }

    /**
     * This function stores the user data into an XML file.
     */
    public static void TF_file () {
        try {
            // Writes user input test questions to an xml file
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("Questions");
            doc.appendChild(rootElement);
            for (True_False true_false : TF_List) {

                //Set Question number in the id
                Element NumberElement = doc.createElement("QuestionNum");
                NumberElement.setAttribute("Number", "" + true_false.getNum());
                rootElement.appendChild(NumberElement);
                //---------------------------------------------------------------
                //Set the Question
                Element QuestionElement = doc.createElement("Question");
                QuestionElement.setTextContent(true_false.getQuestion());
                NumberElement.appendChild(QuestionElement);
                //----------------------------------------------------------------
                //Set Flag1
                Element Flag_Element1 = doc.createElement("Flag_1");
                Flag_Element1.setTextContent(true_false.getFlag());
                NumberElement.appendChild(Flag_Element1);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                StreamResult result = new StreamResult(new File("True_False.xml"));
                DOMSource dom = new DOMSource(doc);
                transformer.transform(dom, result);


            }


        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
     * This function prints the result of the True False test and gives the user their grade.
     */
    public static void printResult()
    {
        // Local Variables
        int numberOfQuestions = OTF_List.size();
        float finalGrade = ((float)scoreT/(float)numberOfQuestions)*100;
        // Create Result Frame
        final JFrame gradeDisplay = new JFrame();
        // Create Panels in Frame
        JPanel FinalP = new JPanel();// Panel to Hold Labels
        FinalP.setLayout(new BorderLayout());
        JPanel Buttons = new JPanel(); // Panel to Hold Buttons
        Buttons.setLayout( new FlowLayout());
        // Create Labels to Print Results
        JLabel Title = new JLabel("End Of Quiz!");
        JLabel gMessage = new JLabel();
        gMessage.setText("You got " + scoreT + " out of " + OTF_List.size() + " questions correct!"+" Overall score: "+ finalGrade +"%");
        // Create Fonts
        Font TitleF = new Font("Courier", Font.BOLD,60);
        Font gMessageF = new Font("Courier",Font.PLAIN,20);
        Title.setFont(TitleF);
        gMessage.setFont(gMessageF);
        // Add Labels to Panel
        FinalP.add(Title,BorderLayout.NORTH);
        FinalP.add(gMessage,BorderLayout.CENTER);
        // Create Buttons
        JButton Return = new JButton("Return to Main Menu");
        JButton quit=new JButton("Exit Program");
        Return.setBackground(Color.LIGHT_GRAY);


        // Button ActionListener
        Return.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // If returning to main reset variables and array
                numb=0;
                numb2=1;
                end=false;
                MainGUI.showMainMenu();
                gradeDisplay.dispose();
                OTF_List.clear();
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(1);
                gradeDisplay.dispose();
            }
        });

        // Add Buttons to Button Panel
        Buttons.add(Return);
        Buttons.add(quit);

        // Set Size of Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height = screenSize.height;
        int width = screenSize.width;

        // Add Panels to Frame gradeDisplay
        gradeDisplay.add(FinalP,BorderLayout.NORTH);
        gradeDisplay.add(Buttons,BorderLayout.CENTER);
        // Code to display
        gradeDisplay.setPreferredSize(new Dimension(width / 2, height / 2));
        gradeDisplay.setVisible(true);
        gradeDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeDisplay.pack();
    }



}