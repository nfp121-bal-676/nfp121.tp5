package question2;

import javax.swing.*;
import java.util.*;

public class IHMListe3 extends JFrame {
    public IHMListe3() {
        List<String> liste = Chapitre2CoreJava2.listeDesMots();
        Map<String, Integer> table = Chapitre2CoreJava2.occurrencesDesMots(liste);
        JPanelListe3 jPanelListe = new JPanelListe3(liste, table);
        add(jPanelListe);
        setLocation(120,150);
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        new IHMListe3();
    }

}
