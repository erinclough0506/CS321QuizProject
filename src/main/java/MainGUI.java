import java.util.Scanner;
import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
public class MainGUI {
    // Initializes Files used for testing
    static File TF= new File("True_False.xml");
    static File MC= new File("Multiple_Choice.xml");
    static File FC=new File("Flashcard.xml");

    /**
     * This function is the main screen that redirects the use to where they need to go in the program.
     */
    public static void showMainMenu()
    {
        // Create Frame
        final JFrame Menu=new JFrame();

        // Create Title Panel
        JPanel TitleP= new JPanel();              // Create Menu Panel
        TitleP.setLayout(new BorderLayout());     // Define Layout
        // Create Label
        JLabel Title=new JLabel("Quiz Program"); // Set Title
        TitleP.add(Title,BorderLayout.NORTH);      // Define Title Layout
        Font TitleF=new Font("Courier",Font.BOLD,60);
        Title.setFont(TitleF);

        // Create Buttons
        JPanel Buttons=new JPanel();               // Create Button Panel
        Buttons.setLayout(new FlowLayout());                 // Define Button Layout
        JButton PreSetQ= new JButton("Study Options");
        JButton CreateQ= new JButton("Create Questions");
        // Add Buttons to button panel
        Buttons.add(PreSetQ); // Set Button Layout
        Buttons.add(CreateQ);
        // Add Action to the buttons
        PreSetQ.addActionListener(new ActionListener()
        {
            // If Test from preset questions was pressed
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Study Topics Pressed");
                PickTypeofTest(); //
                Menu.dispose();

            }
        });
        CreateQ.addActionListener(new ActionListener()
        {
            // If Create questions was pressed
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Create Questions Pressed");
                showQDesign();
                Menu.dispose();
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
                JOptionPane.showMessageDialog(null, "Select Create Questions to create your own Flashcards, Multiple Choice or a True False Question Test. \n If you have already created questions then select Study Options to test it");

            }
        });

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;

        // Adds components to frame
        Menu.add(Title, BorderLayout.NORTH);
        Menu.add(Buttons,BorderLayout.CENTER);
        Menu.setJMenuBar(Help);
        // Sets Size of screen
        Menu.setPreferredSize(new Dimension(width/2,height/2));
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu.pack();
        Menu.setVisible(true);

    }

    /** After thr user selects to start the program, they are
     * taken to this screen in which they chose how they would like to study.
     * They then have the option to select either Flashcards, Multiple Choice, or T/F.
     * This function will catch the user if they do not have an XML file
     * stored locally to start quizzing and redirects them to the Question Design.
     */
    public static void PickTypeofTest()
    {

        // Create Frame
        final JFrame Tests=new JFrame();
        // Create Menu Panel
        JPanel Choice= new JPanel();
        Choice.setLayout(new BorderLayout());     // Define Layout
        JLabel Title=new JLabel("Choose a Test or Study Option from the Choices below "); // Set Title
        Choice.add(Title,BorderLayout.CENTER);     // Define Title Layout
        Font TitleF=new Font("Courier",Font.BOLD,20);
        Title.setFont(TitleF);

        // Create Button Panel
        JPanel Buttons=new JPanel();               // Create Button Panel
        Buttons.setLayout(new FlowLayout());       // Define Button Layout


        // Create Buttons
        JButton FlashCards= new JButton("FlashCards");
        JButton MultiChoice= new JButton("Multiple Choice");
        JButton True_F= new JButton("True False");
        JButton MenuB= new JButton("Return to Main");

        FlashCards.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            { // Checks to see if FlashCard xml file exists
                if(FC.exists()) {
                    System.out.println("FlashCards exists");
                    FlashCardForm.getOFCQuestions();
                    Tests.dispose();
                }
                else
                {
                    System.out.println("FlashCards DNE");
                    showError();
                    Tests.dispose();
                }

            }
        });
        MultiChoice.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {// Checks to see if MultiChoice xml file exists
                if (MC.exists()) {
                    System.out.println("MultiChoice Pressed");
                    QuestionForm.getOMCQuestions();
                    Tests.dispose();
                }
                else
                {
                    System.out.println("FlashCards DNE");
                    showError();
                    Tests.dispose();
                }
            }
        });
        True_F.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {// Checks to see if True_False xml file exists
                if (TF.exists()) {
                    System.out.println("True False Pressed");
                    True_FalseForm.getTFQuestions();
                    Tests.dispose();
                }
                else
                {
                    System.out.println("FlashCards DNE");
                    showError();
                    Tests.dispose();
                }

            }
        });
        MenuB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {// Checks to see if True_False xml file exists
               showMainMenu();
                Tests.dispose();
            }
        });


        Buttons.add(FlashCards); // Add Buttons to Panel Buttons
        Buttons.add(MultiChoice);
        Buttons.add(True_F);
        Buttons.add(MenuB);

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
                JOptionPane.showMessageDialog(null, "If you have already created questions to for a test or Flashcard then select which option you want to choose");

            }
        });
        Tests.setJMenuBar(Help);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;

        // Add Components to Tests frame
        Tests.add(Title, BorderLayout.NORTH);
        Tests.add(Buttons,BorderLayout.CENTER);

        Tests.setPreferredSize(new Dimension(width/2,height/2));
        Tests.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tests.pack();
        Tests.setVisible(true);  // Choose from 3 different choices
    }
    /** This function asks the user for what type of question they would
     * like to create, then redirects them to the functions in which their
     * respective class is built. EX: When flashcard is hit, it redirects to FlashcardForm.createFlashcard()
     */
    public static void showQDesign()
    {

        // Create a Frame
        final JFrame Tests=new JFrame();
        // Create Title Panel
        JPanel Choice= new JPanel();
        Choice.setLayout(new BorderLayout());     // Define Layout
        JLabel Title=new JLabel("Choose to Create Study Flashcards or Test Questions"); // Set Title
        Choice.add(Title,BorderLayout.CENTER);     // Define Title Layout
        Font TitleF=new Font("Courier",Font.BOLD,20);
        Title.setFont(TitleF);

        // Create Button Panel
        JPanel Buttons=new JPanel();
        Buttons.setLayout(new FlowLayout());       // Define Button Layout

        // Create Buttons
        JButton FlashCards= new JButton("FlashCards");
        JButton TestQ= new JButton("Test Questions");
        JButton MenuB= new JButton("Return to Main");

        // Create Action for Buttons
        FlashCards.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("FlashCards Pressed");
                FlashCardForm.CreateFlashcard();
                Tests.dispose();

            }
        });
        TestQ.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Test Questions Pressed");
                QuestionForm.QFormMenu();
                Tests.dispose();
            }
        });
        MenuB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Test Questions Pressed");
                showMainMenu();
                Tests.dispose();
            }
        });


        Buttons.add(FlashCards); // Set Button Layout
        Buttons.add(TestQ);
        Buttons.add(MenuB);
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
                JOptionPane.showMessageDialog(null, "Test Option allows you to create a Multiple Choice Test or a TrueFalse Test if neither then choose the flashcard option");

            }
        });
        Tests.setJMenuBar(Help);



        // Set Screen Size
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;

        // Add Components to Tests Frame
        //Tests.setState(Frame.NORMAL);
        Tests.add(Title, BorderLayout.NORTH);
        Tests.add(Buttons,BorderLayout.CENTER);
        Tests.setPreferredSize(new Dimension(width/2,height/2));
        Tests.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tests.pack();
        Tests.setVisible(true);
    }
    public static void showError()
    {
        /**
         * This functions catches the user if they do not have a set of questions stored to test on.
         */
        // Create Frame
        final JFrame frame =new JFrame();
        frame.setLayout(new BorderLayout());
        JLabel label=new JLabel("Error: Create Questions first");
        Font TitleF=new Font("Courier",Font.BOLD,20);
        label.setFont(TitleF);
        // Create Panel
        final JPanel buttons=new JPanel(new FlowLayout());
        // Create Buttons
        JButton Main=new JButton ("Return to Main");
        Main.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                showMainMenu();
                frame.dispose();
            }
        });
        // Add Components to Panels
        buttons.add(Main);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;

        // Add Components to Frame
        frame.add(label, BorderLayout.NORTH);
        frame.add(buttons,BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(width/2,height/2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}