public class FlashCard
{
    int num = 1;
    String term = null;
    String definition = null;

    public FlashCard(int num, String term, String definition)
    {
        this.num = num;
        this.term = term;
        this.definition = definition;

    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
