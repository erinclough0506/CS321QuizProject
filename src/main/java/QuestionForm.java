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

// ************************************************************************************************************
// 1. Add Code for if both RADIO BUTTONS were pressed go to an icon error or find a way so that only one or the other is pressed
public class QuestionForm {



    private static JTextArea questionMC;
    private static JTextArea A;
    private static JTextArea B;
    private static JTextArea C;
    private static JTextArea D;
    private static JRadioButton aButton = new JRadioButton("a");
    private static JRadioButton bButton = new JRadioButton("b");
    private static JRadioButton cButton = new JRadioButton("c");

    static int counter = 0;
    static String aTrue = "F";
    static String bTrue = "F";
    static String cTrue = "F";


    private static ArrayList<MultipleChoice> MCList = new ArrayList<MultipleChoice>();

    public static void QFormMenu() {
        // Create Frame
        final JFrame selector = new JFrame();
        selector.setLayout(new BorderLayout());
        MCList = new ArrayList<MultipleChoice>();
        // Create Title
        JLabel label1 = new JLabel("Please select which type of Question set you would like to build.");
        selector.add(label1, BorderLayout.NORTH);
        Font TitleF = new Font("Courier", Font.BOLD, 20);
        label1.setFont(TitleF);
        // Create Buttons
        JPanel buttons = new JPanel();
        JButton Multi = new JButton("Multiple Choice");
        JButton TF = new JButton("True/False");
        buttons.setLayout(new FlowLayout());
        buttons.add(Multi);
        buttons.add(TF);
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

    public static void createQuestion() {

        // Create Frame
        final JFrame frame = new JFrame();
        Container AnswersPanel = frame.getContentPane();
        AnswersPanel.setLayout(new BoxLayout(AnswersPanel, BoxLayout.Y_AXIS));

        // Create Title
        JLabel label = new JLabel("Enter your Question and Select the A,B,C, or D button that corresponds with the correct answer.");
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
                if (aButton.isSelected()) {
                    System.out.println("ButtonA Selected");
                    aTrue = "T";
                    bTrue = "F";
                    cTrue = "F";

                    bButton.setSelected(false);
                    cButton.setSelected(false);

                } else {
                    System.out.println("ButtonA not selected");
                    aTrue = "F";
                }
            }
        });
        bButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (bButton.isSelected()) {
                    System.out.println("ButtonB Selected");
                    bTrue = "T";
                    aTrue = "F";
                    cTrue = "F";

                    aButton.setSelected(false);
                    cButton.setSelected(false);

                } else {
                    System.out.println("ButtonB not selected");
                    bTrue = "F";
                }

            }
        });
        cButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (cButton.isSelected()) {
                    System.out.println("ButtonC Selected");
                    cTrue = "T";
                    aTrue = "F";
                    bTrue = "F";

                    aButton.setSelected(false);
                    bButton.setSelected(false);

                } else {
                    System.out.println("ButtonC not selected");
                    cTrue = "F";
                }

            }
        });



        // Create Buttons
        JButton Submit = new JButton("Submit");
        JButton newQuestion = new JButton("Next Question");
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Submit Selected");
                MultipleChoice Form = new MultipleChoice(counter, questionMC.getText(), A.getText(), B.getText(), C.getText(), aTrue, bTrue, cTrue);
                MCList.add(Form);
                counter = MCList.size();
                //JFileChooser fileSave = new JFileChooser();
                System.out.println("Size of Final ArrayList " + MCList.size());
                //fileSave.showSaveDialog(frame);
                saveFile();
                MainGUI.showMainMenu();
                frame.dispose();


            }
        });
        newQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Next Question Selected");
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
        JPanel dPanel = new JPanel();
        dPanel.setLayout(new FlowLayout());
        ;

        JPanel Buttons = new JPanel();
        Buttons.setLayout(new FlowLayout());
        Buttons.add(Submit);
        Buttons.add(newQuestion);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height = screenSize.height;
        int width = screenSize.width;
        // Add Panels to Frame
        AnswersPanel.add(Titled);
        Titled.setAlignmentX(Component.CENTER_ALIGNMENT);
        AnswersPanel.add(aPanel);
        aPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        AnswersPanel.add(bPanel);
        bPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        AnswersPanel.add(cPanel);
        cPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        AnswersPanel.add(Buttons);
        frame.setPreferredSize(new Dimension(width / 2, height / 2));
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



    public static void editQuestion() {


    }



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

    public static void saveFile() {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("Questions");
            doc.appendChild(rootElement);
            for (int i = 0; i < MCList.size(); i++) {

                //Set question number in the id section
                Element NumberElement = doc.createElement("QuestionNum");
                NumberElement.setAttribute("Number", "" + MCList.get(i).getNum());
                rootElement.appendChild(NumberElement);
                //---------------------------------------------------------------
                //Set the Question
                Element QuestionElement = doc.createElement("Question");
                QuestionElement.setTextContent(MCList.get(i).getQuestion());
                NumberElement.appendChild(QuestionElement);
                //----------------------------------------------------------------
                //Set Response 1
                Element ResponseElement1 = doc.createElement("Response1");
                ResponseElement1.setTextContent(MCList.get(i).getAnswerA());
                NumberElement.appendChild(ResponseElement1);
                //----------------------------------------------------------------
                //Set Flag1
                Element Flag_Element1 = doc.createElement("Flag_1");
                Flag_Element1.setTextContent(MCList.get(i).getA());
                NumberElement.appendChild(Flag_Element1);
                //-----------------------------------------------------------------
                //Set Response 2
                Element Response_Element2 = doc.createElement("Response_2");
                Response_Element2.setTextContent(MCList.get(i).getAnswerB());
                NumberElement.appendChild(Response_Element2);
                //-----------------------------------------------------------------
                //Set Flag2
                Element Flag_Element2 = doc.createElement("Flag_2");
                Flag_Element2.setTextContent(MCList.get(i).getB());
                NumberElement.appendChild(Flag_Element2);
                //-----------------------------------------------------------------
                //Set Response 3
                Element Response_Element3 = doc.createElement("Response_3");
                Response_Element3.setTextContent(MCList.get(i).getAnswerC());
                NumberElement.appendChild(Response_Element3);
                //-----------------------------------------------------------------
                //Set Flag3
                Element Flag_Element3 = doc.createElement("Flag_3");
                Flag_Element3.setTextContent(MCList.get(i).getC());
                NumberElement.appendChild(Flag_Element3);
                //------------------------------------------------------------------

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource dom = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Multiple_Choice.xml"));
            transformer.transform(dom, result);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't Save");
        }
    }
}
