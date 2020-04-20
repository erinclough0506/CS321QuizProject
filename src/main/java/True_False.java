public class True_False{
    /**
     * class that initializes the objects question, flag and number
     */



    int num = 0;
    private String Question = null;
    private String Flag = null;

    public True_False (int num,String Q, String Flag)
    {
        /**
         * Constructor for True False
         */
        this.num = num;
        this.Question = Q;
        this.Flag = Flag ;

    }

    public int getNum(){return num;}
    public String getQuestion(){return Question;}

    public String getFlag(){return Flag;}


}