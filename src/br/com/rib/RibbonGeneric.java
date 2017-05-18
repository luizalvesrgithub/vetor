package br.com.rib;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;


public class RibbonGeneric {
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public RibbonGeneric() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{214, 6, 214, 0};
		gridBagLayout.rowHeights = new int[]{39, 170, 53, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		frm.getContentPane().setLayout(gridBagLayout);
		
		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.anchor = GridBagConstraints.NORTH;
		gbc_splitPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.gridwidth = 3;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		frm.getContentPane().add(splitPane, gbc_splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setLeftComponent(panel_2);
		
		JPanel panel_3 = new JPanel();
		splitPane_1.setRightComponent(panel_3);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane_2);
		
		JPanel panel_4 = new JPanel();
		splitPane_2.setLeftComponent(panel_4);
		
		JPanel panel_5 = new JPanel();
		splitPane_2.setRightComponent(panel_5);
		
		JSplitPane splitPane_7 = new JSplitPane();
		splitPane_7.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbc_splitPane_7 = new GridBagConstraints();
		gbc_splitPane_7.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane_7.gridheight = 3;
		gbc_splitPane_7.gridwidth = 3;
		gbc_splitPane_7.fill = GridBagConstraints.BOTH;
		gbc_splitPane_7.gridx = 0;
		gbc_splitPane_7.gridy = 1;
		frm.getContentPane().add(splitPane_7, gbc_splitPane_7);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane_7.setLeftComponent(tabbedPane);
		
		JSplitPane splitPane_3 = new JSplitPane();
		tabbedPane.addTab("Danose", null, splitPane_3, null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		splitPane_7.setRightComponent(tabbedPane_1);
		
		JSplitPane splitPane_4 = new JSplitPane();
		tabbedPane_1.addTab("Outravez", null, splitPane_4, null);
		
		JSplitPane splitPane_5 = new JSplitPane();
		GridBagConstraints gbc_splitPane_5 = new GridBagConstraints();
		gbc_splitPane_5.gridheight = 2;
		gbc_splitPane_5.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane_5.gridwidth = 3;
		gbc_splitPane_5.fill = GridBagConstraints.BOTH;
		gbc_splitPane_5.gridx = 0;
		gbc_splitPane_5.gridy = 4;
		frm.getContentPane().add(splitPane_5, gbc_splitPane_5);
		
		JDesktopPane desktopPane = new JDesktopPane();
		splitPane_5.setLeftComponent(desktopPane);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		splitPane_5.setRightComponent(internalFrame);
		internalFrame.setVisible(true);
		
	}

	/**
	 * @param args
	 */
	public static void main(String args []) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RibbonGeneric rg = new RibbonGeneric();
					
					rg.frm.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JFrame frm = new JFrame();
}