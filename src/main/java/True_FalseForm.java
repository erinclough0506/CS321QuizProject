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

        T.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("True Selected");
                T_F="T";
                F.setSelected(false);

            }
        });
        F.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("False Selected");
                T_F="F";
                T.setSelected(false);

            }
        });
        newQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Next Question Pressed");
                True_False Form = new True_False(counter, question.getText(), T_F);
                TF_List.add(Form);
                counter = TF_List.size();
                clearPage();



            }
        });
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Submit Pressed");
                True_False Form = new True_False(counter, question.getText(), T_F);
                TF_List.add(Form);
                counter = TF_List.size();
                TF_file ();
                MainGUI.showMainMenu();
                frame.dispose();


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

    public static void getTFQuestions()
    {
        // Class that sends selected Multiple choice form to an array
        getQuestionsTF(OTF_List);
        createTest();
    }
    public static void createTest() {

        JFrame frame = new JFrame();

        //Temporary item, XML information will need to be inserted into the Text File.

        //Create panel
        JPanel Top = new JPanel();
        Top.setLayout(new BorderLayout());
        final JLabel Output = new JLabel(OTF_List.get(numb).getQuestion());
        Top.add(Output, BorderLayout.NORTH);

        //-------------------------------------------------------
        //creates the Buttons
        JPanel Buttons = new JPanel();
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
        Buttons.add(next);


        TRUE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("true selected");
                FALSE.setSelected(false);
                True_FALSE="T";
            }
        });
        //-----------------------------------------------------------
        FALSE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("false selected");
                TRUE.setSelected(false);
                True_FALSE="F";
            }
        });


        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("next  pressed");
                if (!end) {
                    TRUE.setSelected(false);
                    FALSE.setSelected(false);
                    True_FALSE = "NA"; //// Answer neither True or False
                    numb = numb + 1;
                    numb2 = numb2 + 1;
                    Output.setText(OTF_List.get(numb).getQuestion());
                    if (numb2>=OTF_List.size())
                    {
                        next.setText("Submit Test");
                        end = true;
                    }
                }
                else
                {

                }
            }
        });


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

    public static void getQuestionsTF(ArrayList<True_False> List) {
        try {
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
    public static void TF_file () {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("Questions");
            doc.appendChild(rootElement);
            for (int i = 0; i < TF_List.size(); i++) {

                //Set Question number in the id
                Element NumberElement = doc.createElement("QuestionNum");
                NumberElement.setAttribute("Number", "" + TF_List.get(i).getNum());
                rootElement.appendChild(NumberElement);
                //---------------------------------------------------------------
                //Set the Question
                Element QuestionElement = doc.createElement("Question");
                QuestionElement.setTextContent(TF_List.get(i).getQuestion());
                NumberElement.appendChild(QuestionElement);
                //----------------------------------------------------------------
                //Set Flag1
                Element Flag_Element1 = doc.createElement("Flag_1");
                Flag_Element1.setTextContent(TF_List.get(i).getFlag());
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


}