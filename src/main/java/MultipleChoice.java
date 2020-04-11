public class MultipleChoice {
    int num=0;
    private String Question=null;
    private String AnswerA=null;
    private String AnswerB=null;
    private String AnswerC=null;

    String A1;
    String B1;
    String C1;
    String D1;


    public MultipleChoice (int num, String q,String a1, String a2, String a3,String A,String B, String C) {
        // write your code here
        this.num=num;
        this.Question=q;
        this.AnswerA=a1;
        this.AnswerB=a2;
        this.AnswerC=a3;

        this.A1=A;
        this.B1=B;
        this.C1=C;


    }
    public int getNum()
    {
        return num;
    }

    public  String getQuestion()
    {
        return Question;
    }

    public String getAnswerA()
    {
        return AnswerA;
    }

    public String getAnswerB()
    {
        return AnswerB;
    }

    public String getAnswerC()
    {
        return AnswerC;
    }


    public String getA()
    {
        return A1;
    }

    public String getB()
    {
        return B1;
    }

    public String getC()
    {
        return C1;
    }




    /*   private static void getQuestion(Document doc)
    {
        NodeList QuestionList = doc.getElementsByTagName("QuestionNum");
        for(int i=0; i<QuestionList.getLength(); i++)
        {
            Node QuestionNode = QuestionList.item(i);
            if(QuestionNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element QuestionElement = (Element) QuestionNode;
                String QuestionId = QuestionElement.getElementsByTagName("num").item(0).getTextContent();
                String QuestionName = QuestionElement.getElementsByTagName("Question").item(0).getTextContent();
                String QuestionFlag = QuestionElement.getElementsByTagName("Flag").item(0).getTextContent();
                System.out.println("Question Number = " + QuestionId);
                System.out.println("Question = " + QuestionName);
                System.out.println("Question Flag = " + QuestionFlag);

            }
        }
    }
    }
  */

}