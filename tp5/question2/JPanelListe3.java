package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;
import java.util.Stack;

public class JPanelListe3 extends JPanel implements ActionListener, ItemListener {

    
    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private List<String> liste;
    private Map<String, Integer> occurrences;
    private Originator originator = new Originator();
    private Caretaker caretaker = new Caretaker();    

    public JPanelListe3(List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;

        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);


        if(liste!=null && occurrences!=null){
            afficheur.setText(liste.getClass().getName() + " et "+ occurrences.getClass().getName());
            texte.setText(liste.toString());
        }else{
            texte.setText("la classe Chapitre2CoreJava semble incomplète");
        }

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

        boutonRechercher.addActionListener(this);
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);
        boutonOccurrences.addActionListener(this);
        boutonRetirer.addActionListener(this);
        saisie.addActionListener(this);
        boutonAnnuler.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : "
                    + saisie.getText() + " -->  " + res);
            } else if (ae.getSource() == boutonRetirer) {
                List<String> l = new ArrayList<>(this.liste);
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie
                    .getText());
                if(res) {
                    save(l);
                }
                afficheur
                .setText("résultat du retrait de tous les éléments commençant par -->  "
                    + saisie.getText() + " : " + res);
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null)
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                else
                    afficheur.setText(" -->  ??? ");
            } else if(ae.getSource() == boutonAnnuler){
                             
                try{
                    if(!caretaker.isEmpty()){
                        liste = originator.restoreFromMemento(caretaker.getMemento());
                        occurrences = Chapitre2CoreJava2.occurrencesDesMots(this.liste); 
                    } 
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            texte.setText(liste.toString());

        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        List<String> listeBis = new ArrayList<String>(this.liste);
        boolean flag = false;
        if (ie.getSource() == ordreCroissant){
            flag = true;
            if(flag) {
                save(listeBis);
            }
            Collections.sort(this.liste);
        }
        else if (ie.getSource() == ordreDecroissant) {
            flag = true;
            if(flag) {
                save(listeBis);
            }
            Collections.sort(this.liste, Collections.reverseOrder());   
        }

        texte.setText(liste.toString());
    }
    private void save(List<String> list){
        list = new ArrayList<>(this.liste);
        originator.set(list);
        caretaker.addMemento(originator.saveToMemento());
    }
    
    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
        boolean resultat = false;
        List<String> l = this.liste;
        Iterator<String> iter = l.iterator();
        while(iter.hasNext()) {
            String s = iter.next();
            if (s.startsWith(prefixe)) {
                iter.remove();
                resultat = true;
                this.occurrences.put(s, 0);
            }
        }
        return resultat;
    }

}