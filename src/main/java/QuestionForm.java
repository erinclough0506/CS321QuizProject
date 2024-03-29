import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

// ************************************************************************************************************
// 1. Add Code for if both RADIO BUTTONS were pressed go to an icon error or find a way so that only one or the other is pressed
public class QuestionForm {

    private static JTextArea questionMC;
    private static JLabel questionOMC=new JLabel("");
    private static JTextArea A;
    private static JTextArea B;
    private static JTextArea C;
    private static JTextArea D;
    private static JRadioButton aButton = new JRadioButton("a");
    private static JRadioButton bButton = new JRadioButton("b");
    private static JRadioButton cButton = new JRadioButton("c");

    static int counter = 0;
    static String aTrue = "0";
    static String bTrue = "0";
    static String cTrue = "0";

    static boolean end=false;
    static int numb=0;
    static int numb2=1;

    static JFrame Test = new JFrame();

    static JButton nextQuestion = new JButton("Next Question");
    static JButton MenuR = new JButton("Return to Main");
    static JPanel Buttons = new JPanel();
    static JPanel Buttons2 = new JPanel();

    private static ArrayList<MultipleChoice> MCList = new ArrayList<MultipleChoice>();
    private static ArrayList<MultipleChoice> OMCList = new ArrayList<MultipleChoice>();

    static int score = 0;

    /**
     * This function sees if the user would like to create either true/false or multiple choice questions.
     * If they would like to create T/F, they continue to createQuestion() in this class, if not, they are redirected to True_FalseForm
     */
    public static void QFormMenu() {
        // Create Frame
        final JFrame selector = new JFrame();
        selector.setLayout(new BorderLayout());
        // Create Title
        JLabel label1 = new JLabel("Please select which type of Question set you would like to build.");
        selector.add(label1, BorderLayout.NORTH);
        Font TitleF = new Font("Courier", Font.BOLD, 20);
        label1.setFont(TitleF);
        // Create Buttons
        JPanel buttons = new JPanel();
        JButton Multi = new JButton("Multiple Choice");
        JButton TF = new JButton("True/False");
        JButton Return = new JButton("Return to Create Questions");
        buttons.setLayout(new FlowLayout());
        buttons.add(Multi);
        buttons.add(TF);
        buttons.add(Return);
        // Action Listener for buttons
        Multi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Multiple Choice Selected");
                createQuestion();
                selector.dispose();

            }
        });
        TF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("TrueFalse Selected");
                True_FalseForm.CreateTrueFalse();
                selector.dispose();

            }
        });
        Return.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Return Selected");
                MainGUI.showQDesign();
                selector.dispose();

            }
        });
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height = screenSize.height;
        int width = screenSize.width;

        selector.add(label1, BorderLayout.NORTH);
        selector.add(buttons, BorderLayout.CENTER);
        selector.setPreferredSize(new Dimension(width / 2, height / 2));
        selector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selector.pack();
        selector.setVisible(true);
    }
    //***************************Create MC Question*************************************************************************************

    /**
     * This function creates an array list of user entered data. This array list is stored and later used for testing.
     */
    public static void createQuestion() {
        // Create Frame
        final JFrame frame = new JFrame();
        Container Answer2Panel = frame.getContentPane();

        Answer2Panel.setLayout(new BoxLayout(Answer2Panel, BoxLayout.Y_AXIS));

        // Create Title
        JLabel label = new JLabel("Enter your Question and Select the A,B,or C button that corresponds with the correct answer.");
        Font TitleF = new Font("Courier", Font.BOLD, 20);
        label.setFont(TitleF);

        // Create Text Boxes
        questionMC = new JTextArea(1, 6);
        //JTextField question = new JTextField();
        questionMC.setText("Enter a Question");
        questionMC.setLineWrap(true);
        questionMC.setWrapStyleWord(true);

        A = new JTextArea(4, 70);
        A.setText("Enter an Answer");
        B = new JTextArea(4, 70);
        B.setText("Enter an Answer");
        C = new JTextArea(4, 70);
        C.setText("Enter an Answer");

        // Scroll Panes
        JScrollPane QScroll = new JScrollPane(questionMC);
        QScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        QScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Create Radio Button Action Listener
        aButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                /**
                 * Action listener for button A.
                 */
                if (aButton.isSelected()) {
                    System.out.println("ButtonA Selected");
                    aTrue = "1";
                    bTrue = "0";
                    cTrue = "0";

                    bButton.setSelected(false);
                    cButton.setSelected(false);

                } else {
                    System.out.println("ButtonA not selected");
                    aTrue = "0";
                }
            }
        });
        bButton.addActionListener(new ActionListener() {
            /**
             * Action listener for button B.
             * @param event the action of pressing the button
             */
            public void actionPerformed(ActionEvent event) {
                if (bButton.isSelected()) {
                    System.out.println("ButtonB Selected");
                    bTrue = "1";
                    aTrue = "0";
                    cTrue = "0";

                    aButton.setSelected(false);
                    cButton.setSelected(false);

                } else {
                    System.out.println("ButtonB not selected");
                    bTrue = "0";
                }

            }
        });
        cButton.addActionListener(new ActionListener() {

            /**
             * Action Listener for button C.
             */
            public void actionPerformed(ActionEvent event) {

                if (cButton.isSelected()) {
                    System.out.println("ButtonC Selected");
                    cTrue = "1";
                    aTrue = "0";
                    bTrue = "0";

                    aButton.setSelected(false);
                    bButton.setSelected(false);

                } else {
                    System.out.println("ButtonC not selected");
                    cTrue = "0";
                }

            }
        });



        // Create Buttons
        JButton Submit = new JButton("Submit");
        JButton newQuestion = new JButton("Next Question");
        // Action Listener for Buttons
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Submit Selected");
                // Saves last question
                MultipleChoice Form = new MultipleChoice(counter, questionMC.getText(), A.getText(), B.getText(), C.getText(), aTrue, bTrue, cTrue);
                MCList.add(Form);
                counter = MCList.size();
                System.out.println("Size of Final ArrayList " + MCList.size());
                File(); // Saves questions from array to xml folder
                MCList.clear(); // Clears array
                MainGUI.showMainMenu(); // returns to main menu
                frame.dispose();


            }
        });
        newQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Next Question Selected");
                // Saves question and answers to array
                MultipleChoice Form = new MultipleChoice(counter, questionMC.getText(), A.getText(), B.getText(), C.getText(), aTrue, bTrue, cTrue);
                MCList.add(Form);
                counter = MCList.size();
                System.out.println("Size of ArrayList " + MCList.size());
                clearMCPage();

            }
        });

        // Create Panels
        JPanel Titled = new JPanel();
        Titled.setLayout(new BorderLayout());
        Titled.add(label, BorderLayout.NORTH);
        Titled.add(QScroll, BorderLayout.CENTER);
        JPanel aPanel = new JPanel();
        aPanel.setLayout(new FlowLayout());
        aPanel.add(aButton);
        aPanel.add(A);
        JPanel bPanel = new JPanel();
        bPanel.setLayout(new FlowLayout());
        bPanel.add(bButton);
        bPanel.add(B);
        JPanel cPanel = new JPanel();
        cPanel.setLayout(new FlowLayout());
        cPanel.add(cButton);
        cPanel.add(C);

        // Add Buttons to Button Panel
        Buttons.setLayout(new FlowLayout());
        Buttons.add(Submit);
        Buttons.add(newQuestion);
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
                JOptionPane.showMessageDialog(null, "Type a question into the question text area.\n Then type 3 answers into one in each answer text area make sure one of them is the correct answer. .\n Select which one is the correct answer then select either Next or Submit test ");

            }
        });
        frame.setJMenuBar(Help);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height = screenSize.height;
        int width = screenSize.width;
        // Add Panels to Frame
        Answer2Panel.add(Titled);
        Titled.setAlignmentX(Component.CENTER_ALIGNMENT);
        Answer2Panel.add(aPanel);
        aPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Answer2Panel.add(bPanel);
        bPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Answer2Panel.add(cPanel);
        cPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Answer2Panel.add(Buttons);
        frame.setPreferredSize(new Dimension(width / 2, height / 2));
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    //***************************************************************************************************************************************
    //************************Create Array to hold prebuilt Questions************************************************************************

    /**
     * This function gets the user data that was previously stored in an XML file and calls MCPlayer() to be tested on.
     */
    public static void getOMCQuestions()
    {
        // Class that sends selected Multiple choice form to an array
        numb=0; // Reinitialize
        numb2=1;
        getQuestions(OMCList);
        MCPlayer();
    }
    //*******************Displays Test for Multiple Choice*******************************************************************************

    /**
     * This function is the main testing interface for Multiple Choice. It determines whether answers
     * are right or wrong, and calculates your grade at the end.
     */
    public static void MCPlayer() {

        // Answers Panel

        Container AnswersPanel = Test.getContentPane();
        AnswersPanel.setLayout(new BoxLayout(AnswersPanel, BoxLayout.Y_AXIS));
        // Create Text Boxes
        questionOMC.setText(OMCList.get(numb).getQuestion());
        Font TitleF = new Font("Courier", Font.BOLD, 20);
        questionOMC.setFont(TitleF);

        aButton.setText(OMCList.get(numb).getAnswerA());
        bButton.setText(OMCList.get(numb).getAnswerB());
        cButton.setText(OMCList.get(numb).getAnswerC());

        // Create Radio Button Action Listener
        aButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (aButton.isSelected()) {
                    System.out.println("ButtonA Selected");
                    aTrue = "1";
                    bTrue = "0";
                    cTrue = "0";

                    bButton.setSelected(false);
                    cButton.setSelected(false);

                } else {
                    System.out.println("ButtonA not selected");
                    aTrue = "0";
                }
            }
        });
        bButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (bButton.isSelected()) {
                    System.out.println("ButtonB Selected");
                    bTrue = "1";
                    aTrue = "0";
                    cTrue = "0";

                    aButton.setSelected(false);
                    cButton.setSelected(false);

                } else {
                    System.out.println("ButtonB not selected");
                    bTrue = "0";
                }

            }
        });
        cButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (cButton.isSelected()) {
                    System.out.println("ButtonC Selected");
                    cTrue = "1";
                    aTrue = "0";
                    bTrue = "0";

                    aButton.setSelected(false);
                    bButton.setSelected(false);

                } else {
                    System.out.println("ButtonC not selected");
                    cTrue = "0";
                }

            }
        });

        nextQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Next Question Selected");

                //checking for correct answer
                if((aTrue.equals("1")) && (aTrue.equals(OMCList.get(numb).getA())))
                {
                    score = score + 1;
                }
                else if(bTrue.equals("1") && bTrue.equals(OMCList.get(numb).getB()))
                {
                    score = score + 1;
                }
                else if(cTrue.equals("1") && cTrue.equals(OMCList.get(numb).getC()))
                {
                    score = score + 1;
                }


                if (!end) {
                    numb=numb+1;
                    numb2=numb2+1; // Counter to iterate through questions and answers
                    // Outputs Test for user to read
                    questionOMC.setText(OMCList.get(numb).getQuestion());
                    aButton.setText(OMCList.get(numb).getAnswerA());
                    bButton.setText(OMCList.get(numb).getAnswerB());
                    cButton.setText(OMCList.get(numb).getAnswerC());
                    aButton.setSelected(false);
                    bButton.setSelected(false);
                    cButton.setSelected(false);
                    if (numb2==OMCList.size())
                    {
                        // If end of test has been reached
                        nextQuestion.setText("Submit Test");
                        end = true;
                    }
                }

                else
                {
                    // If test has ended Show score
                    final JFrame gradeDisplay = new JFrame();
                    JPanel FinalP = new JPanel();
                    FinalP.setLayout(new BorderLayout());
                    JLabel Title = new JLabel("End Of Quiz!");
                    FinalP.add(Title,BorderLayout.NORTH);
                    Font TitleF = new Font("Courier", Font.BOLD,60);
                    Title.setFont(TitleF);
                    gradeDisplay.add(Title,BorderLayout.NORTH);
                    JLabel gMessage = new JLabel();
                    int numberOfQuestions = OMCList.size();
                    float finalGrade = ((float)score/(float)numberOfQuestions)*100;
                    gMessage.setText("You got " + score + " out of " +numberOfQuestions + " questions correct!"  +" Overall score: "+ finalGrade +"%");
                    FinalP.add(gMessage,BorderLayout.CENTER);
                    Font gMessageF = new Font("Courier",Font.PLAIN,20); // Set Grade Output Font
                    gMessage.setFont(gMessageF);
                    gradeDisplay.add(gMessage,BorderLayout.CENTER);
                    JPanel Buttons = new JPanel();
                    Buttons.setLayout( new FlowLayout());
                    numberOfQuestions=0;
                    score=0;
                    OMCList.clear();
                    JButton Return = new JButton("Return to Main Menu");
                    Return.setBackground(Color.LIGHT_GRAY);
                    JButton quit = new JButton("Exit Program");
                    quit.setBackground(Color.RED);
                    Buttons.add(Return);
                    Buttons.add(quit);
                    gradeDisplay.add(Buttons,BorderLayout.SOUTH);


                    Return.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            System.out.println("Return to Main Menu Pressed.");
                            OMCList.clear();
                            MainGUI.showMainMenu();
                            gradeDisplay.dispose();
                        }
                    });
                    quit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            System.exit(1);
                            gradeDisplay.dispose();
                        }
                    });
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
                    int height = screenSize.height;
                    int width = screenSize.width;
                    // Output Grade Display to a set size
                    gradeDisplay.setVisible(true);
                    gradeDisplay.setPreferredSize(new Dimension(width / 2, height / 2));
                    FinalP.setVisible(true);
                    gradeDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    gradeDisplay.pack();
                    Test.dispose();
                }
            }
        });
        MenuR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Menu Pressed");
                OMCList.clear(); // Clear Array List
                MainGUI.showMainMenu();
            }
        });

        Buttons2.setLayout(new FlowLayout());
        Buttons2.add(nextQuestion);
        Buttons2.add(MenuR);

        // Add Panels to Frame
        AnswersPanel.add(questionOMC);
        questionOMC.setAlignmentX(Component.LEFT_ALIGNMENT);
        AnswersPanel.add(aButton);
        AnswersPanel.add(bButton);
        AnswersPanel.add(cButton);

        AnswersPanel.add(Buttons2);
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
                JOptionPane.showMessageDialog(null, "Read the question.\n Then select which option is the correct answer.\n Continue answering each question by selecting next and when test is over select Submit test to see your result");

            }
        });
        Test.setJMenuBar(Help);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height = screenSize.height;
        int width = screenSize.width;
        Test.setPreferredSize(new Dimension(width / 2, height / 2));
        Test.setVisible(true);
        Test.pack();
        Test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * This function clears the page so more user data can be input into the array.
     */
    private static void clearMCPage() {
        questionMC.setText("Enter a Question");
        questionMC.requestFocus();
        A.setText("Enter an Answer");
        B.setText("Enter an Answer");
        C.setText("Enter an Answer");

        aButton.setSelected(false);
        bButton.setSelected(false);
        cButton.setSelected(false);

        aTrue = "F";
        bTrue = "F";
        cTrue = "F";

    }
    /**
     * This function give user data they have created in createQuestion() from an XML file to the test interface.
     * @param List is an array is that is used to store the information into the file in the order it was input.
     */
    public static void getQuestions(ArrayList<MultipleChoice> List) {
        // Create Frame

        try {
            File MCXML = new File("Multiple_Choice.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(MCXML);
            System.out.println("Root element:"+ doc.getDocumentElement().getNodeName());
            NodeList QuestionList = doc.getElementsByTagName("QuestionNum");
            System.out.println("Length=" + QuestionList.getLength());
            for (int i = 0; i < QuestionList.getLength(); i++) {
                Node QuestionNode = QuestionList.item(i);
                if (QuestionNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element QuestionElement = (Element) QuestionNode;
                    String QuestionId = QuestionElement.getAttribute("Number");
                    System.out.println("Question Number = " + QuestionId);
                    String QuestionName = QuestionElement.getElementsByTagName("Question").item(0).getTextContent();
                    System.out.println("Question = " + QuestionName);
                    String QuestionResponse1 = QuestionElement.getElementsByTagName("Response1").item(0).getTextContent();
                    System.out.println("Answer1= " + QuestionResponse1);
                    String QuestionFlag1 = QuestionElement.getElementsByTagName("Flag_1").item(0).getTextContent();
                    System.out.println("Answer1 Flag = " + QuestionFlag1);

                    String QuestionResponse2 = QuestionElement.getElementsByTagName("Response_2").item(0).getTextContent();
                    System.out.println("Answer2 = " + QuestionResponse2);
                    String QuestionFlag2 = QuestionElement.getElementsByTagName("Flag_2").item(0).getTextContent();
                    System.out.println("Answer2 Flag = " + QuestionFlag2);
                    String QuestionResponse3 = QuestionElement.getElementsByTagName("Response_3").item(0).getTextContent();
                    System.out.println("Answer3  = " + QuestionResponse3);
                    String QuestionFlag3 = QuestionElement.getElementsByTagName("Flag_3").item(0).getTextContent();
                    System.out.println("Answer3 Flag = " + QuestionFlag3);
                    MultipleChoice Form = new MultipleChoice(i, QuestionName, QuestionResponse1, QuestionResponse2, QuestionResponse3, QuestionFlag1, QuestionFlag2, QuestionFlag3);
                    List.add(Form);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't Save");
        }
    }
    /**
     * This function stores the user data into an XML file.
     */
    public static void File() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("Questions");
            doc.appendChild(rootElement);
            for (MultipleChoice multipleChoice : MCList) {

                //Set question number in the id section
                Element NumberElement = doc.createElement("QuestionNum");
                NumberElement.setAttribute("Number", "" + multipleChoice.getNum());
                rootElement.appendChild(NumberElement);
                //---------------------------------------------------------------
                //Set the Question
                Element QuestionElement = doc.createElement("Question");
                QuestionElement.setTextContent(multipleChoice.getQuestion());
                NumberElement.appendChild(QuestionElement);
                //----------------------------------------------------------------
                //Set Response 1
                Element ResponseElement1 = doc.createElement("Response1");
                ResponseElement1.setTextContent(multipleChoice.getAnswerA());
                NumberElement.appendChild(ResponseElement1);
                //----------------------------------------------------------------
                //Set Flag1
                Element Flag_Element1 = doc.createElement("Flag_1");
                Flag_Element1.setTextContent(multipleChoice.getA());
                NumberElement.appendChild(Flag_Element1);
                //-----------------------------------------------------------------
                //Set Response 2
                Element Response_Element2 = doc.createElement("Response_2");
                Response_Element2.setTextContent(multipleChoice.getAnswerB());
                NumberElement.appendChild(Response_Element2);
                //-----------------------------------------------------------------
                //Set Flag2
                Element Flag_Element2 = doc.createElement("Flag_2");
                Flag_Element2.setTextContent(multipleChoice.getB());
                NumberElement.appendChild(Flag_Element2);
                //-----------------------------------------------------------------
                //Set Response 3
                Element Response_Element3 = doc.createElement("Response_3");
                Response_Element3.setTextContent(multipleChoice.getAnswerC());
                NumberElement.appendChild(Response_Element3);
                //-----------------------------------------------------------------
                //Set Flag3
                Element Flag_Element3 = doc.createElement("Flag_3");
                Flag_Element3.setTextContent(multipleChoice.getC());
                NumberElement.appendChild(Flag_Element3);
                //------------------------------------------------------------------

                   /* Element NumberElementEnd = doc.createElement("QuestionNum");
                    NumberElement.setAttribute("Number", "");
                    rootElement.appendChild(NumberElementEnd);*/


                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                StreamResult result = new StreamResult(new File("Multiple_Choice.xml"));
                DOMSource dom = new DOMSource(doc);
                transformer.transform(dom, result);
            }

        }

        catch(Exception e){
            e.printStackTrace();
            System.out.println("Couldn't Save");

        }
    }

}
