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


public class FlashCard {
    int num = 1;
    String term = null;
    String definition = null;

    public FlashCard(int num, String T, String D) {
        this.num = num;
        this.term = T;
        this.definition = D;

    }

    public int getNum() {
        return num;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }
}

