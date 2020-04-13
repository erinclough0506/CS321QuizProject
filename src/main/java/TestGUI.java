import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TestGUI extends JFrame
{
    private JButton trueFalseButton;
    private JButton flashCardsButton;
    private JButton multipleChoiceButton;
    private JFormattedTextField infoSelectWhichWayFormattedTextField;
    private JPanel rootPanel;

    public TestGUI() {

       //this uses the from designer
        add(rootPanel);
        setTitle("Test Menu");
        setSize(400,500);




        multipleChoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        trueFalseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });


        flashCardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }
}




