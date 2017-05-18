package br.com.util;

import java.awt.Dimension;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Maestria extends JFrame implements ListSelectionListener {
  private JLabel label;

  public Maestria() {
    super("Gestão de Material e Patrimônio");

    //Create an instance of SplitPaneDemo
    SplitPaneDemo splitPaneDemo = new SplitPaneDemo();
    JSplitPane top = splitPaneDemo.getSplitPane();
    splitPaneDemo.getImageList().addListSelectionListener(this);

    //XXXX: Bug #4131528, borders on nested split panes accumulate.
    //Workaround: Set the border on any split pane within
    //another split pane to null. Components within nested split
    //panes need to have their own border for this to work well.
    top.setBorder(null);

    //Create a regular old label
    label = new JLabel("Click on an image name in the list.", JLabel.CENTER);

    //Create a split pane and put "top" (a split pane)
    //and JLabel instance in it.
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, top,
        label);
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(180);

    //Provide minimum sizes for the two components in the split pane
    top.setMinimumSize(new Dimension(100, 50));
    label.setMinimumSize(new Dimension(100, 30));

    //Add the split pane to this frame
    getContentPane().add(splitPane);
  }

  public void valueChanged(ListSelectionEvent e) {
    if (e.getValueIsAdjusting())
      return;

    JList theList = (JList) e.getSource();
    if (theList.isSelectionEmpty()) {
      label.setText("Nothing selected.");
    } else {
      int index = theList.getSelectedIndex();
      label.setText("Selected image number " + index);
    }
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    //Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);

    //Create and set up the window.
    JFrame frame = new Maestria();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}

