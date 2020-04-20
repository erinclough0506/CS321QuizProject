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

/**
 * Basic constructor for what the flashcards need tp hold
 */
public class FlashCard {
    int num = 1;
    String term = null;
    String definition = null;

    public FlashCard(int num, String T, String D) {
        this.num = num;
        this.term = T;
        this.definition = D;

    }

    /**
     * returns what number flashcard it is in the list
     * @return returns the int value of num which contains what position the flashcard is in inside the ArrayList.
     */
    public int getNum() {
        return num;
    }

    /**
     * returns the term that specific flashcard holds
     * @return term, which is the string value the term is held in.
     */
    public String getTerm() {
        return term;
    }

    /**
     * returns the definition that specific flashcard has
     * @return definition, which is the string value the definition is held in.
     */
    public String getDefinition() {
        return definition;
    }
}

