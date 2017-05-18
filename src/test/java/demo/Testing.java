package test.java.demo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Testing extends JFrame {
	private BufferedImage image;

	public Testing() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 200);
		setLocation(200, 100);
		JPanel panel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				try {
					// Image imagem = new
					// ImageIcon("/com/resc/br/FretusModel.jpg").getImage();
					// Image imagem = getImage(getCodeBase(),
					// "/com/resc/br/FretusModel.jpg");
					try {
						image = ImageIO.read(new File("/com/resc/br/FretusModel.jpg"));
					} catch (IOException ex) {
						// handle exception...
					}
					g.drawImage(image, 100, 100, this);
				} catch (Exception e) {
					System.out.println("Não leu a imagem");
				}
			}
		};
		Container contentPane = getContentPane();
		Testing.jpanelInJscp(contentPane);
		contentPane.add(panel);

	}

	public static void main(String[] args) {
		new Testing().setVisible(true);
	}

	public static void jpanelInJscp(Container contentPane) {

		JScrollPane scroll = new JScrollPane();
		GridBagLayout gbl = new GridBagLayout();

		JButton next = new JButton("Next");
		// SpringLayout layout = new SpringLayout();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(gbl);

		GridBagConstraints gbcComponent = new GridBagConstraints();
		gbcComponent.gridwidth = 1;
		gbcComponent.gridheight = 1;
		gbcComponent.insets = new Insets(0, 0, 5, 5);
		contentPane.setLayout(new BorderLayout());

		int j = 25;
		for (int i = 0; i < 10; i++) {
			JLabel label = new JLabel("Enter Name " + i);
			JTextField text = new JTextField(15);
			gbcComponent.gridx = 0;
			gbcComponent.gridy = i;
			mainPanel.add(label, gbcComponent);
			gbcComponent.gridx = 1;
			mainPanel.add(text, gbcComponent);
			j += 30;
		}
		gbcComponent.gridx = 1;
		gbcComponent.gridy = 11;
		mainPanel.add(next, gbcComponent);
		mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), 1500));
		scroll.setPreferredSize(new Dimension(500, 500));
		scroll.setViewportView(mainPanel);
		scroll.add(mainPanel);
		contentPane.add(scroll);
		contentPane.add(mainPanel, BorderLayout.SOUTH);

	}

	public static JScrollPane jpninJscp(Container contentPane, Vector<String> vtituloscolunas,
			Vector<String> vtiposdecolunas, Vector<Object> vconteudoRecord) {

		
		int coordx = 0;   //NÃO VARIA
		int coordy = 0;
		
		JScrollPane scroll = new JScrollPane();
		GridBagLayout gbl = new GridBagLayout();

		JButton next = new JButton("Next");
		// SpringLayout layout = new SpringLayout();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(gbl);

		GridBagConstraints gbcComponent = new GridBagConstraints();
		gbcComponent.gridwidth = 1;
		gbcComponent.gridheight = 1;
		gbcComponent.insets = new Insets(0, 0, 5, 5);
		contentPane.setLayout(new BorderLayout());
		
		String stitulocoluna = "";
		Iterator<String> ititulos = vtituloscolunas.iterator();
		while (ititulos.hasNext()) {
			try {
				stitulocoluna = (String) ititulos.next();
			} catch (Exception e) {
				break;
			}
			gbcComponent.gridx = coordx;
			gbcComponent.gridy = coordy++;
			mainPanel.add(new JLabel(stitulocoluna), gbcComponent);
		}
		
		coordx++; //coordenada x = 1
		coordy = 0;
		String sconteudocoluna = "";
		Iterator<Object> itconteudoCol = vconteudoRecord.iterator();
		while (itconteudoCol.hasNext()) {
			Object conteudoCol = itconteudoCol.next();
			System.out.println("Conteúdo coluna = " + conteudoCol.toString());
			sconteudocoluna = conteudoCol.toString();
			gbcComponent.gridx = coordx;
			gbcComponent.gridy = coordy++;
			mainPanel.add(new JTextField(sconteudocoluna), gbcComponent);
		}
		
		int j = 25;
		for (int i = 0; i < 10; i++) {
			JLabel label = new JLabel("Enter Name " + i);
			JTextField text = new JTextField(15);
			gbcComponent.gridx = 2;
			gbcComponent.gridy = i;
			mainPanel.add(label, gbcComponent);
			gbcComponent.gridx = 3;
			mainPanel.add(text, gbcComponent);
			j += 30;
		}
		gbcComponent.gridx = 1;
		gbcComponent.gridy = 11;
		mainPanel.add(next, gbcComponent);
		mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), 1500));
		scroll.setPreferredSize(new Dimension(500, 500));
		scroll.setViewportView(mainPanel);
		scroll.add(mainPanel);
		return scroll;
		// contentPane.add(mainPanel, BorderLayout.SOUTH);

	}
}
/*
 * layout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST,
 * mainPanel); layout.putConstraint(SpringLayout.NORTH, label, j,
 * SpringLayout.NORTH, mainPanel); layout.putConstraint(SpringLayout.NORTH,
 * text, j, SpringLayout.NORTH, mainPanel);
 * layout.putConstraint(SpringLayout.WEST, text, 20, SpringLayout.EAST, label);
 */