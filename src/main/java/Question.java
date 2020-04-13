import java.util.*;
//
public class Question {

    int num = 0;
    String QPrompt = null;
    String Response1 = null;
    String Response2 = null;
    String Response3 = null;
    String Response_Flag1;
    String Response_Flag2;
    String Response_Flag3;
    public Question (int num, String QPrompt, String Response1, String Response2,String Response3,String Response_Flag1,String Response_Flag2,String Response_Flag3) {

        this.num = num;
        this.QPrompt = QPrompt;
        this.Response1 = Response1;
        this.Response2 = Response2;
        this.Response3 = Response3;
        this.Response_Flag1 = Response_Flag1;
        this.Response_Flag2 = Response_Flag2;
        this.Response_Flag3 = Response_Flag3;
    }


    public int Getnum()
    {
        return num;
    }
    public String Qprompt(){
        return QPrompt;
    }

    public String Response1(){
        return Response1;}
    public String Response2(){
        return Response2;}
    public String Response3(){
        return Response3;}
    public String Response_Flag1(){
        return Response_Flag1;}
    public String Response_Flag2(){
        return Response_Flag2;}
    public String Response_Flag3(){
        return Response_Flag3;}

}
