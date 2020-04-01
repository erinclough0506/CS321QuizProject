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
        // ****************** ADD text to Explain what to do when on menu screen
        // Possibly make panel bigger??
        JPanel TitleP= new JPanel();              // Create Menu Panel
        TitleP.setLayout(new BorderLayout());     // Define Layout
        JPanel Buttons=new JPanel();               // Create Button Panel
        Buttons.setLayout(new FlowLayout());                 // Define Button Layout
        JLabel Title=new JLabel("Quiz Program"); // Set Title
        TitleP.add(Title,BorderLayout.NORTH);      // Define Title Layout
        Font TitleF=new Font("Courier",Font.BOLD,60);
        Title.setFont(TitleF);


        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;
        // Create Buttons
        JButton PreSetQ= new JButton("Study Topics");
        JButton CreateQ= new JButton("Create Questions");
        Buttons.add(PreSetQ); // Set Button Layout
        Buttons.add(CreateQ);
        final JFrame Menu=new JFrame(); // Create a Frame to Combine the two layout
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


        Menu.setState(Frame.NORMAL);
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

        JPanel Choice= new JPanel();              // Create Menu Panel
        Choice.setLayout(new BorderLayout());     // Define Layout

        JPanel Buttons=new JPanel();               // Create Button Panel
        Buttons.setLayout(new FlowLayout());       // Define Button Layout

        JLabel Title=new JLabel("Choose a Test or Study Option from the choices below "); // Set Title
        Choice.add(Title,BorderLayout.CENTER);     // Define Title Layout
        Font TitleF=new Font("Courier",Font.BOLD,20);
        Title.setFont(TitleF);
        final JFrame Tests=new JFrame();                 // Create a Frame to Combine layouts

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;

        // Create Buttons
        JButton FlashCards= new JButton("FlashCards");
        JButton MultiChoice= new JButton("Multiple Choice");
        JButton True_F= new JButton("True False");
        JButton FTest= new JButton("Test All");

        FlashCards.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("FlashCards Pressed");
                showFlash();
                Tests.dispose();

            }
        });
        MultiChoice.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("MultiChoice Pressed");
                showMulti();
                Tests.dispose();
            }
        });
        True_F.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("True False Pressed");
                showT_F();
                Tests.dispose();

            }
        });
        FTest.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("FTest Pressed");
                showTest();
                Tests.dispose();
            }
        });

        Buttons.add(FlashCards); // Set Button Layout
        Buttons.add(MultiChoice);
        Buttons.add(True_F);
        Buttons.add(FTest);

        Tests.setState(Frame.NORMAL);
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
        // Pick Type of Multiple Choice
    }
    public static void showT_F()
    {
        // Pick type of True False
    }
    public static void showQDesign()
    {
        // Will gO to Question Form
        JPanel Choice= new JPanel();              // Create Menu Panel
        Choice.setLayout(new BorderLayout());     // Define Layout

        JPanel Buttons=new JPanel();               // Create Button Panel
        Buttons.setLayout(new FlowLayout());       // Define Button Layout

        JLabel Title=new JLabel("Choose a type of Questions you want to create from the choices below"); // Set Title
        Choice.add(Title,BorderLayout.CENTER);     // Define Title Layout
        Font TitleF=new Font("Courier",Font.BOLD,20);
        Title.setFont(TitleF);
        final JFrame Tests=new JFrame();                 // Create a Frame to Combine layouts

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); // Get Screen Size
        int height=screenSize.height;
        int width=screenSize.width;

        // Create Buttons
        JButton FlashCards= new JButton("FlashCards");
        JButton MultiChoice= new JButton("Multiple Choice");
        JButton True_F= new JButton("True False");
        //JButton FTest= new JButton("Test All");

        FlashCards.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("FlashCards Pressed");
                //FlashCardForm.createFlashcard();
                Tests.dispose();

            }
        });
        MultiChoice.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("MultiChoice Pressed");
                //QuestionForm.createQuestion();
                Tests.dispose();
            }
        });
        True_F.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("True False Pressed");
                //QuestionForm.CreateTrueFalse();
                Tests.dispose();

            }
        });


        Buttons.add(FlashCards); // Set Button Layout
        Buttons.add(MultiChoice);
        Buttons.add(True_F);

        Tests.setState(Frame.NORMAL);
        Tests.add(Title, BorderLayout.NORTH);
        Tests.add(Buttons,BorderLayout.CENTER);
        Tests.setPreferredSize(new Dimension(width/2,height/2));
        Tests.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tests.pack();
        Tests.setVisible(true);  // Choose from 3 different choices
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


