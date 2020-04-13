import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class True_FalseTest extends JFrame{
    private JPanel Flashcard;
    private JFormattedTextField trueFalseFormattedTextField;
    private JButton FALSEButton;
    private JPanel rootPanel;
    private JButton TRUEButton;
    private JLabel Question;

    public True_FalseTest() {
        add(rootPanel);
        setTitle("True False");
        setSize(400, 500);



        TRUEButton.setBackground(Color.GREEN);
        TRUEButton.setBounds(40,50,50,60);

        FALSEButton.setBackground(Color.RED);
        FALSEButton.setBounds(40,50,50,60);

        //--------------------------------------



        //TestMenu item = new TestMenu();
        //JLabel Prompt = new JLabel();
        //Prompt.setText(item.Hello());
       // Question.setText(item.Hello());
        //---------------------------------------


        FALSEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        TRUEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }
}