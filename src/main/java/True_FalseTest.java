import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class True_FalseTest{



    int num = 0;
    private String Question = null;
    private String Flag = null;

    public True_FalseTest (int num,String Q, String Flag)
    {
        this.num = num;
        this.Question = Q;
        this.Flag = Flag ;

    }

    public int getNum(){return num;}
    public String getQuestion(){return Question;};
    public String getFlag(){return Flag;}



}





