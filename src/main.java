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


    public static void main(String[] args) {

        System.out.println("Running Program");
        JFrame StarterMessage = new JFrame();
        JButton StartP=new JButton("Start Quiz Program");

        StartP.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                MainGUI.showMainMenu();
                StarterMessage.dispose();
            }
        });
        StarterMessage.setLayout(new FlowLayout());
        StarterMessage.add(StartP);
        StarterMessage.setPreferredSize(new Dimension(100,100));
        StarterMessage.setLocationRelativeTo(null);
        StarterMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StarterMessage.pack();
        StarterMessage.setVisible(true);
    }
}
