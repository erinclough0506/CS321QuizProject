import java.util.Scanner;
import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;


public class main {
    /**
     * This is the first screen that is seen by the user when they start the program up. Is asks the user to either
     * Start or Quit.
     * @param args stores an array of arguments when a program is ran via command line.
     */
    public static void main(String[] args) {
        System.out.println("Running Program");
        // Create Frame
        final JFrame StarterMessage = new JFrame();
        StarterMessage.setLayout(new BorderLayout());

        // Create Title Panel
        JPanel TitleP= new JPanel();              // Create Menu Panel
        TitleP.setLayout(new BorderLayout());     // Define Layout
        JLabel Title=new JLabel("Quizzing Program"); // Set Title
        TitleP.add(Title,BorderLayout.NORTH);      // Define Title Layout



        // Create Buttons
        JPanel Buttons=new JPanel();               // Create Button Panel
        Buttons.setLayout(new FlowLayout());
        JButton StartP=new JButton("Start");
        JButton QuitP=new JButton("Quit");
        Buttons.add(StartP); // Set Button Layout
        Buttons.add(QuitP);
        StartP.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                MainGUI.showMainMenu();
                StarterMessage.dispose();
            }
        });
        QuitP.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                // Exits out of program
                System.exit(1);
                StarterMessage.dispose();
            }
        });
        StarterMessage.add(TitleP, BorderLayout.NORTH);
        StarterMessage.add(Buttons,BorderLayout.CENTER);
        StarterMessage.setPreferredSize(new Dimension(150,100));
        StarterMessage.setLocationRelativeTo(null);
        StarterMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StarterMessage.pack();
        StarterMessage.setVisible(true);
    }

}