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
        // Add Action to the buttons
        PreSetQ.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Study Topics Pressed");
            }
        });
        CreateQ.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("Create Questions Pressed");
            }
        });

        JFrame Menu=new JFrame(); // Create a Frame to Combine the two layouts
        Menu.setState(Frame.NORMAL);
        Menu.add(Title, BorderLayout.NORTH);
        Menu.add(Buttons,BorderLayout.CENTER);
        //Menu.setLocationRelativeTo(null);
        Menu.setPreferredSize(new Dimension(width/2,height/2));
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu.pack();
        Menu.setVisible(true);

    }

    public static void showFlash()
    {

    }
    public static void showMult()
    {

    }
    public static void showT_F()
    {

    }
    public static void showQDesign()
    {

    }
    public static void showTest()
    {

    }
    public static void DisplayResult()
    {

    }

}


