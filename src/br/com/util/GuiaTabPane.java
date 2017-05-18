package br.com.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
 
/*
 * Creating and using GuiaTabPane example  
 */
public class GuiaTabPane { //extends JFrame {    
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private final JTabbedPane pane;
    private final JScrollPane scrPanel;
    private String tituloGuia  ;
    private int    numGuia ;
    private JMenuItem tabComponentsItem ;
    private JMenuItem scrollLayoutItem  ;
     
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                //Turn off metal's use of bold fonts
               UIManager.put("swing.boldMetal", Boolean.FALSE);
               new GuiaTabPane("Teste Guia com botão", 0, new JTabbedPane(), new JScrollPane());
            }
        });
    }
     
    public GuiaTabPane(String title, int numeroGuia, JTabbedPane pane, JScrollPane scpPanel) {
    	tituloGuia = new String(title);
    	numGuia = numeroGuia;
    	this.pane = pane;
    	this.scrPanel = scpPanel;
    	inicializar();
        //super(title);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //initMenu();        
        //add(pane);        
    }
     
    public void inicializar() {
       
    	pane.add(tituloGuia, new JLabel(tituloGuia));
    	initTabComponent(numGuia);
        tabComponentsItem.setSelected(true);
        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        scrollLayoutItem.setSelected(false);
        //setSize(new Dimension(400, 200));
        //setLocationRelativeTo(null);
        //setVisible(true);
    }
     
    public void adicionaGuia(String titulo, int numRegistros) {
        //pane.add(titulo+" "+numRegistros, new JLabel(titulo+" "+numRegistros));
        initTabComponent(numGuia);
        pane.addTab(titulo+ " "+numRegistros, scrPanel);
        tabComponentsItem.setSelected(true);
        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        scrollLayoutItem.setSelected(false);
        //setSize(new Dimension(400, 200));
        //setLocationRelativeTo(null);
        //setVisible(true);
    }
     
    private void initTabComponent(int i) {
        pane.setTabComponentAt(i,
                 new ButtonTabComponent(pane));
    }    
 
    //Setting menu
     
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        //create Options menu
        tabComponentsItem = new JCheckBoxMenuItem("Use TabComponents", true);
        tabComponentsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK));
        tabComponentsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < pane.getTabCount(); i++) {
                    if (tabComponentsItem.isSelected()) {
                        initTabComponent(i);
                    } else {
                        pane.setTabComponentAt(i, null);
                    }
                }
            }
        });
        scrollLayoutItem = new JCheckBoxMenuItem("Set ScrollLayout");
        scrollLayoutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        scrollLayoutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pane.getTabLayoutPolicy() == JTabbedPane.WRAP_TAB_LAYOUT) {
                    pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                } else {
                    pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
                }
            }
        });
        JMenuItem resetItem = new JMenuItem("Reset JTabbedPane");
        resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
        resetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inicializar();
            }
        });
        
       // JMenu optionsMenu = new JMenu("Opções");
       // optionsMenu.add(tabComponentsItem);
       // optionsMenu.add(scrollLayoutItem);
       // optionsMenu.add(resetItem);
       // menuBar.add(optionsMenu);
       // setJMenuBar(menuBar);
    }
}