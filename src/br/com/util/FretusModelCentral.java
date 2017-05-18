package br.com.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.util.Vector;

public class FretusModelCentral extends ProcedimentosApoio {

	public FretusModelCentral() {

		loadJdbcDriversUrls();

		loadVars(); // inicializa comandos sql para para BDs.

		initialize();
	}

	private void initialize() {

		frm = new JFrame();
		frm.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\dev\\workspace\\vetor\\WebContent\\imagens\\mineradorp.jpg"));
		
		frm.setBounds(100, 100, 812, 573);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		go = new GuiasOpcoes();
		psptcon = new JSptConexao(); 
		jmigr = new JSptMigracao();
		jmigr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				try {
					JSptMigracao.setMetaDados();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		frm.setTitle(psptcon.obterNomeCliente() + " — Minerador \u2014Migra\u00E7\u00E3o de Dados");
		// jsptconsulta = new JSptConsultas();
		// jmg = new JSptModelosGraficos();
		// jma = new JSptMontadorAlgebrico();
		// jrs = new JSptRelacionamentoSemantico();
		// esgex = new EstruturaGlobalExtracao();
		tg = new TunicaGeral();

		JPanel panel = new JPanel();
		frm.getContentPane().add(go, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		tbdMacrofuncoes.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent chevet) {
				// Preparar para aceitar novas conexões
				JTabbedPane tbpsel = (JTabbedPane) chevet.getSource();
				String nomeTab = tbpsel.getName();
				if (primeiraCarga)
					primeiraCarga = false;
				else {
					int index = tbpsel.getSelectedIndex();
					switch (index) {
					case 0:
						try {
							JSptConexao.setMetaDados();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
						break;
					case 1:
						try {
							JSptMigracao.setMetaDados();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
						break;
					case 2: 
						try {
							JPanelMetaDados.setMetaDados();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "Procedimento não desenvolvido.");
						break;
					}
					
				}
			}
		});
		tbdMacrofuncoes.add("Conexão", psptcon);
		tbdMacrofuncoes.add("Migração", jmigr);
		// tbdMacrofuncoes.add("Consultas", jsptconsulta);
		// tbdMacrofuncoes.add("Gráficos", jmg);
		// tbdMacrofuncoes.add("Álgebra", jma);
		// tbdMacrofuncoes.add("Relacionamento", jrs);
		// tbdMacrofuncoes.add("Estrutura Global Genérica", esgex.splitPane);
		tbdMacrofuncoes.add("Túncia Geral", tg);
		frm.getContentPane().add(tbdMacrofuncoes, BorderLayout.CENTER);
		
		typesbtwplics.add("char");
		typesbtwplics.add("varchar");
		typesbtwplics.add("String");
		typesbtwplics.add("byte");
		typesbtwplics.add("CHAR");
		typesbtwplics.add("VARCHAR");
		typesbtwplics.add("STRING");
		typesbtwplics.add("BYTE");
		
		ProcedimentosApoio.iniciarVariaveis();

	}
	
    public static void atualizarTabsMestre(JScrollPane pscpMaster, String nomeTabela) {
		
    	//GuiaTabPane gtp = new GuiaTabPane(nomeTabela, ++numTab, tg.tbdPmaster, atualScrollPane);
		tg.setScrollPaneMestre(pscpMaster, nomeTabela);
		
	}
    
    public static void atualizarTabsDetalhe(JScrollPane pscpDetalhe, String nomeTabela) {
		
    	//GuiaTabPane gtp = new GuiaTabPane(nomeTabela, ++numTab, tg.tbdPmaster, atualScrollPane);
		tg.setScrollPaneMestre(pscpDetalhe, nomeTabela);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FretusModelCentral tinc = new FretusModelCentral();

					tinc.frm.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	
	

	private static JTabbedPane getTbdMacrofuncoes() {
		return tbdMacrofuncoes;
	}

	protected static Connection restaurarConexaoDestino() {
		
		return psptcon.conexaoDestino;
	}
	
	private static void setTbdMacrofuncoes(JTabbedPane tbdMacrofuncoes) {
		
		FretusModelCentral.tbdMacrofuncoes = tbdMacrofuncoes;
	}
	
	protected static void mostrarProjecaoRegistroMestre(Vector<String> vtituloscolunas, 
			   Vector<String> vtiposdecolunas,	
               Vector<Object> vconteudoRecord, 
                  String ptabela) {
		
		tg.projecaoLinhaRegistro(vtituloscolunas, vtiposdecolunas, vconteudoRecord, ptabela);
	}

	private JFrame getFrm() {
		return frm;
	}

	private void setFrm(JFrame frm) {
		this.frm = frm;
	}

	private static JTabbedPane tbdMacrofuncoes = new JTabbedPane(JTabbedPane.TOP);

	private JFrame frm;
	
	
	private GuiasOpcoes go;
	private static JSptConexao psptcon;
	private JSptMigracao jmigr;
	private JSptConsultas jsptconsulta;
	private JSptModelosGraficos jmg;
	private JSptMontadorAlgebrico jma;
	private JSptRelacionamentoSemantico jrs;
	//private EstruturaGlobalExtracao esgex;
	private static TunicaGeral tg;
	protected static int numTab;

}
