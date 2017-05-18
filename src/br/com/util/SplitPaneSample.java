package br.com.util;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class SplitPaneSample {
  public static void main(String args[]) {
    JFrame vFrame = new JFrame("JSplitPane Sample");
    vFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JSplitPane vSplitPane = new JSplitPane();
    vSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    vFrame.getContentPane().add(vSplitPane, BorderLayout.CENTER);
    vFrame.setSize(300, 150);
    vFrame.setVisible(true);

    JFrame hFrame = new JFrame("JSplitPane Sample");
    JSplitPane hSplitPane = new JSplitPane();
    hFrame.getContentPane().add(hSplitPane, BorderLayout.CENTER);
    hFrame.setSize(300, 150);
    hFrame.setVisible(true);
  }
}