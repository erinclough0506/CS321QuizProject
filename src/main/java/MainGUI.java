import java.util.Scanner;
import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
public class MainGUI {
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

        JLabel Title=new JLabel("Quiz Program"); // Set Title
        TitleP.add(Title,BorderLayout.NORTH);      // Define Title Layout
        Font TitleF=new Font("Courier",Font.BOLD,60);
        Title.setFont(TitleF);

        // Create Buttons
        JPanel Buttons=new JPanel();               // Create Button Panel
        Buttons.setLayout(new FlowLayout());                 // Define Button Layout
        JButton PreSetQ= new JButton("Study Options");
        JButton CreateQ= new JButton("Create Questions");
        Buttons.add(PreSetQ); // Set Button Layout
        Buttons.add(CreateQ);
        // Add Action to the buttons
        PreSetQ.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Study Topics Pressed");
                PickTypeofTest();
                Menu.dispose();

            }
        });
        CreateQ.addActionListener(new ActionListener()
        {
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

        //Menu.setState(Frame.NORMAL);
        Menu.add(Title, BorderLayout.NORTH);
        Menu.add(Buttons,BorderLayout.CENTER);
        //Menu.setLocationRelativeTo(null);
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
            {
                System.out.println("FlashCards Pressed");
                FlashCardForm.getOFCQuestions();
                Tests.dispose();

            }
        });
        MultiChoice.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("MultiChoice Pressed");
                QuestionForm.getOMCQuestions();
                Tests.dispose();
            }
        });
        True_F.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("True False Pressed");
                True_FalseForm.createTest();
                Tests.dispose();

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
    public static void showFlash()
    {
        // Pick Type of Flash Card
    }
    public static void showMulti()
    {
        System.out.println("Show Multi");

    }
    public static void showT_F()
    {
        // Pick type of True False
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
    public static void showTest()
    {
        // Pick type of Test
    }
    public static void DisplayResult()
    {
        // Displays Result of Test
    }
}