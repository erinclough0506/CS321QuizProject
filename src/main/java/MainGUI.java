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
    public static void showMainMenu()
    {
        // Title page with Title and two options to either create a quiz or choose a quiz
        // ***********************************************************************************
        // 1. ADD text to Explain what to do when on menu screen
        // 2. ADD buttons to other menus to be able to go back to Main Menu

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

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;

        // Adds components to frame
        Menu.add(Title, BorderLayout.NORTH);
        Menu.add(Buttons,BorderLayout.CENTER);
        // Sets Size of screen
        Menu.setPreferredSize(new Dimension(width/2,height/2));
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu.pack();
        Menu.setVisible(true);

    }
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


        Buttons.add(FlashCards); // Add Buttons to Panel Buttons
        Buttons.add(MultiChoice);
        Buttons.add(True_F);

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


        Buttons.add(FlashCards); // Set Button Layout
        Buttons.add(TestQ);



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