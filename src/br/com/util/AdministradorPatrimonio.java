package br.com.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AdministradorPatrimonio extends TunicaGeral {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								AdministradorPatrimonio admp = new AdministradorPatrimonio();
								admp.montarPainelDetalheMestre();
								admp.jfrm.setVisible(true);
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
	
	public AdministradorPatrimonio() {
		
		super();
		inicializador();
		
	}

	private void inicializador() {
		// Somente para apreciar a fluência da montagem:
		jfrm = new JFrame();
		jfrm.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\dev\\workspace\\vetor\\WebContent\\imagens\\mineradorp.jpg"));
		jfrm.setTitle("Minerador \u2014Migra\u00E7\u00E3o de Dados");
		jfrm.setBounds(100, 100, 812, 573);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for (int j = 0; j < dadosMestre[0].length; j++) {
			for (int i = 0; i < dadosMestre.length; i++) {
				detalheDados.add(
						new DetalheDados(new JLabel(colunasMestre[i]), new JTextField((dadosMestre[j][i]).toString())));
				paryx.add(new Paryx(j, i));
			}
		}

		this.settMasterCabecalho(new JTable(dadosMestre, colunasMestre));
		this.settMembersItems(new JTable(dadosDetalhe, colunasMestre));
		montarPainelDetalheMestre();
		this.setScpDetailsMaster(new JScrollPane());
		
		jfrm.getContentPane().add(this.splitPane, BorderLayout.CENTER);
		
	}

	private void montarPainelDetalheMestre() {

		pdetmaster = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		pdetmaster.setLayout(gbl);
		
		int i = 0;
		Iterator<DetalheDados>itr = detalheDados.iterator();
		while(itr.hasNext()) {
			
			DetalheDados dd = itr.next();
			
			Paryx pyx = paryx.get(i++);
			GridBagConstraints gbc = new GridBagConstraints(); 
			gbc.insets = new Insets(0, 0, 5, 0);
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = pyx.x;
			gbc.gridy = pyx.y;
			
			if(dd.getNomecampo() instanceof JLabel) {
				pdetmaster.add(new JLabel(dd.getName()), gbc);
			} else {
				pdetmaster.add(new JTextField(dd.getName()), gbc);
			}
			 
			
		}

	}

	

	public List<DetalheDados> getDatlheDados() {
		return detalheDados;
	}

	class DetalheDados extends JPanel {

		public DetalheDados(JLabel nomecampo, JTextField conteudoCampo) {
			super();
			this.nomecampo = nomecampo;
			this.conteudoCampo = conteudoCampo;
		}

		public JLabel getNomecampo() {
			return nomecampo;
		}

		public void setNomecampo(JLabel nomecampo) {
			this.nomecampo = nomecampo;
		}

		public JTextField getConteudoCampo() {
			return conteudoCampo;
		}

		public void setConteudoCampo(JTextField conteudoCampo) {
			this.conteudoCampo = conteudoCampo;
		}

		private     JLabel   nomecampo;
		private JTextField   conteudoCampo;

	}
	
	class Paryx {

		private int y;
		private int x;
		
		public Paryx(int y, int x) {
			super();
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
		
	}

	String[] colunasMestre = { "Nome", "Telefone", "Email" };

	Object[][] dadosMestre = { { "Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com" },
			{ "João da Silva", "48 8890-3345", "joaosilva@hotmail.com" },
			{ "Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com" } };

	String[] colunasDetalhe = { "Nome", "Telefone", "Email" };

	Object[][] dadosDetalhe = { { "Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com" },
			{ "João da Silva", "48 8890-3345", "joaosilva@hotmail.com" },
			{ "Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com" } };

	Vector<String> vnomesColunas = new Vector<String>();
	Vector<Object> vconteudosColunas = new Vector<Object>();

	private List<DetalheDados> detalheDados = new ArrayList<DetalheDados>();
	private List<Paryx> paryx = new ArrayList<Paryx>();
	
	private JPanel pdetmaster;
	private JPanel pdetmember;
	
	private static JFrame jfrm;

}
