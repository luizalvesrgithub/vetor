package br.com.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.com.util.AdministradorPatrimonio.DetalheDados;
import br.com.util.AdministradorPatrimonio.Paryx;

public class EstruturaGlobalExtracao extends TunicaGeral {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								EstruturaGlobalExtracao ege = new EstruturaGlobalExtracao();
								ege.jfrm.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EstruturaGlobalExtracao() {
		
		super();
		inicializador();
		
	}

	private void inicializador() {
		//Obter nome da empresa proprietária do produto
		jfrm = new JFrame();
		jfrm.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\dev\\workspace\\vetor\\WebContent\\imagens\\mineradorp.jpg"));
		jfrm.setTitle(JSptConexao.getDefaultLocale().getCountry()); //Na verdade deverá ser colocado no título o nome da instituição proprietária do produto.
		jfrm.setTitle("Minerador \u2014Migra\u00E7\u00E3o de Dados: Estrutura Global Extração");
		jfrm.setBounds(100, 100, 812, 573);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setScpDetailsMaster(new JScrollPane());
		
		jfrm.getContentPane().add(this.splitPane, BorderLayout.CENTER);
		
	}

	
	private JFrame     jfrm;
	
}
