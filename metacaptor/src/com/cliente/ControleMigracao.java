package com.cliente;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.util.br.Estrutura;
import com.util.br.ExtratorGeafin;


public class ControleMigracao extends JFrame {
	
	private JTable tbCamposdepara;
	private final Action action = new SwingAction();
	
	static Vector<String> nomesTabelas = new Vector<String>();
	
	static Vector<Estrutura> propriedadesTabelaOrigem = new Vector<Estrutura>();
	
	static Vector<Estrutura> propriedadesTabelaDestino = new Vector<Estrutura>();
	
	JComboBox<String> cbModulos;
	
	JComboBox<String> cbTabelasDestino;
	
	JComboBox <String>cbTabelasOrigem;
	
	JList <Estrutura>lstPropriedadeTab_origem;
	
	JList <Estrutura>lstPropriedadesTab_destino;
	
	GerenteEventosCbx ouvidor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControleMigracao frame = new ControleMigracao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ControleMigracao() {
		setTitle("An\u00E1lise e migra\u00E7\u00E3o de dados entre plataformas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1077, 708);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 124, 0, 755, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 103, 0, 0, 0, 0, 0, 69, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblMdulosDeOrigem = new JLabel("M\u00F3dulos de origem");
		GridBagConstraints gbc_lblMdulosDeOrigem = new GridBagConstraints();
		gbc_lblMdulosDeOrigem.anchor = GridBagConstraints.WEST;
		gbc_lblMdulosDeOrigem.weightx = 0.5;
		gbc_lblMdulosDeOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_lblMdulosDeOrigem.gridx = 1;
		gbc_lblMdulosDeOrigem.gridy = 1;
		getContentPane().add(lblMdulosDeOrigem, gbc_lblMdulosDeOrigem);
		
		cbModulos = new JComboBox();
		cbModulos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				JComboBox cb = (JComboBox) evt.getSource();

			    Object item = evt.getItem();

			    if (evt.getStateChange() == ItemEvent.SELECTED) {
			      // Item was just selected
			    	System.out.println("Item selecionado: " + item.toString());
			    	popularComboTabelasOrigem(item.toString());
			      //Call a method that populate object box of tables in module	
			    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
			      // Item is no longer selected
			    }
			  }
				
		});
		
		cbModulos.setModel(new DefaultComboBoxModel(new String[] {"M\u00F3dulo ALW", "M\u00F3dulo AX", "M\u00F3dulo BP", "M\u00F3dulo CP", "M\u00F3dulo CR", "M\u00F3dulo CRW", "M\u00F3dulo CTW", "M\u00F3dulo FIW", "M\u00F3dulo FRW", "M\u00F3dulo IM", "M\u00F3dulo IMW", "M\u00F3dulo JBPM", "M\u00F3dulo OP", "M\u00F3dulo RT", "M\u00F3dulo SG", "M\u00F3dulo SGW", "M\u00F3dulo TT"}));
		cbModulos.setMaximumRowCount(1);
		GridBagConstraints gbc_cbModulos = new GridBagConstraints();
		gbc_cbModulos.anchor = GridBagConstraints.NORTH;
		gbc_cbModulos.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbModulos.insets = new Insets(0, 0, 5, 5);
		gbc_cbModulos.gridx = 1;
		gbc_cbModulos.gridy = 2;
		getContentPane().add(cbModulos, gbc_cbModulos);
		//cbModulos.addItemListener(ouvidor);
		cbModulos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//For selected module, select all tables in schema wish starts the table name with string
			}
		});
		
		JLabel lblTabelasDoMdulo = new JLabel("Tabelas M\u00F3dulo Selecionado");
		GridBagConstraints gbc_lblTabelasDoMdulo = new GridBagConstraints();
		gbc_lblTabelasDoMdulo.anchor = GridBagConstraints.WEST;
		gbc_lblTabelasDoMdulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTabelasDoMdulo.gridx = 2;
		gbc_lblTabelasDoMdulo.gridy = 1;
		getContentPane().add(lblTabelasDoMdulo, gbc_lblTabelasDoMdulo);
		
		JLabel lblTabelasDeDestino = new JLabel("Tabelas de Destino");
		GridBagConstraints gbc_lblTabelasDeDestino = new GridBagConstraints();
		gbc_lblTabelasDeDestino.anchor = GridBagConstraints.WEST;
		gbc_lblTabelasDeDestino.insets = new Insets(0, 0, 5, 0);
		gbc_lblTabelasDeDestino.gridx = 3;
		gbc_lblTabelasDeDestino.gridy = 1;
		getContentPane().add(lblTabelasDeDestino, gbc_lblTabelasDeDestino);
		
		cbTabelasOrigem = new JComboBox();
		cbTabelasOrigem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				
				JComboBox cb = (JComboBox) evt.getSource();

			    Object item = evt.getItem();

			    if (evt.getStateChange() == ItemEvent.SELECTED) {
			      // Item was just selected
			    	System.out.println("Item selecionado: " + item.toString());
			    	popularLstPropriedadesTabelaOrigem(item.toString());
			      //Call a method that populate object box of tables in module	
			    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
			      // Item is no longer selected
			    }
			  }
				
		});
		GridBagConstraints gbc_cbTabelasOrigem = new GridBagConstraints();
		gbc_cbTabelasOrigem.anchor = GridBagConstraints.NORTH;
		gbc_cbTabelasOrigem.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTabelasOrigem.gridheight = 1;
		gbc_cbTabelasOrigem.weightx = 0.5; //
		gbc_cbTabelasOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_cbTabelasOrigem.gridx = 2;
		gbc_cbTabelasOrigem.gridy = 2;
		getContentPane().add(cbTabelasOrigem, gbc_cbTabelasOrigem);
		
		cbTabelasDestino = new JComboBox();
		cbTabelasDestino.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				JComboBox cb = (JComboBox) evt.getSource();
			    Object item = evt.getItem();
			    if (evt.getStateChange() == ItemEvent.SELECTED) {
			      // Item was just selected
			    	System.out.println("Item selecionado: " + item.toString());
			    	popularLstPropriedadesTabelaDestino(item.toString());
			      //Call a method that populate object box of tables in module	
			    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
			    	popularComboTabelasDestino();
			    }
			  }
				
		});
		cbTabelasDestino.setMaximumRowCount(1);
		GridBagConstraints gbc_cbTabelasDestino = new GridBagConstraints();
		gbc_cbTabelasDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTabelasDestino.anchor = GridBagConstraints.NORTH;
		gbc_cbTabelasDestino.insets = new Insets(0, 0, 1, 0);
		gbc_cbTabelasDestino.gridwidth = 1;
		gbc_cbTabelasDestino.gridx = 3;
		gbc_cbTabelasDestino.gridy = 2;
		getContentPane().add(cbTabelasDestino, gbc_cbTabelasDestino);
		
		JLabel lblPropriedadesDaTabela = new JLabel("Propriedades da Tabela");
		GridBagConstraints gbc_lblPropriedadesDaTabela = new GridBagConstraints();
		gbc_lblPropriedadesDaTabela.anchor = GridBagConstraints.WEST;
		gbc_lblPropriedadesDaTabela.insets = new Insets(0, 0, 5, 5);
		gbc_lblPropriedadesDaTabela.gridx = 1;
		gbc_lblPropriedadesDaTabela.gridy = 3;
		getContentPane().add(lblPropriedadesDaTabela, gbc_lblPropriedadesDaTabela);
		
		lstPropriedadeTab_origem = new JList();
		GridBagConstraints gbc_lstPropriedadeTab_origem_1 = new GridBagConstraints();
		gbc_lstPropriedadeTab_origem_1.insets = new Insets(0, 0, 5, 5);
		gbc_lstPropriedadeTab_origem_1.gridx = 2;
		gbc_lstPropriedadeTab_origem_1.gridy = 4;
		gbc_lstPropriedadeTab_origem_1.gridwidth = 3;
		getContentPane().add(lstPropriedadeTab_origem, gbc_lstPropriedadeTab_origem_1);
		lstPropriedadeTab_origem.setVisibleRowCount(10);
		
		JLabel lblPropriedadesDaTabela_1 = new JLabel("Propriedades da tabela de destino");
		GridBagConstraints gbc_lblPropriedadesDaTabela_1 = new GridBagConstraints();
		gbc_lblPropriedadesDaTabela_1.anchor = GridBagConstraints.WEST;
		gbc_lblPropriedadesDaTabela_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblPropriedadesDaTabela_1.gridx = 3;
		gbc_lblPropriedadesDaTabela_1.gridy = 3;
		getContentPane().add(lblPropriedadesDaTabela_1, gbc_lblPropriedadesDaTabela_1);
		
		lstPropriedadesTab_destino = new JList();
		GridBagConstraints gbc_lstPropriedadesTab_destino = new GridBagConstraints();
		gbc_lstPropriedadesTab_destino.insets = new Insets(0, 0, 5, 0);
		gbc_lstPropriedadesTab_destino.gridx = 3;
		gbc_lstPropriedadesTab_destino.gridy = 4;
		gbc_lstPropriedadeTab_origem_1.gridwidth = 3;
		getContentPane().add(lstPropriedadesTab_destino, gbc_lstPropriedadesTab_destino);
		lstPropriedadesTab_destino.setVisibleRowCount(10);
		
		JScrollPane paneTabOrigem = new JScrollPane();
		
		GridBagConstraints gbc_lstPropriedadeTab_origem = new GridBagConstraints();
		gbc_lstPropriedadeTab_origem_1.insets = new Insets(0, 0, 5, 5);
		gbc_lstPropriedadeTab_origem_1.fill = GridBagConstraints.BOTH;
		gbc_lstPropriedadeTab_origem_1.gridx = 2;
		gbc_lstPropriedadeTab_origem_1.gridy = 6;
		getContentPane().add(paneTabOrigem, gbc_lstPropriedadeTab_origem_1);
		
		JButton btnAdicionaOPar = new JButton("ADICIONAR O PAR");
		GridBagConstraints gbc_btnAdicionaOPar = new GridBagConstraints();
		gbc_btnAdicionaOPar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdicionaOPar.gridx = 2;
		gbc_btnAdicionaOPar.gridy = 7;
		getContentPane().add(btnAdicionaOPar, gbc_btnAdicionaOPar);
		JScrollPane paneTabDestino = new JScrollPane();
		
		GridBagConstraints gbc_lstPropriedadesTab_destino_1 = new GridBagConstraints();
		gbc_lstPropriedadesTab_destino.insets = new Insets(0, 0, 5, 0);
		gbc_lstPropriedadesTab_destino.fill = GridBagConstraints.BOTH;
		gbc_lstPropriedadesTab_destino.gridx = 3;
		gbc_lstPropriedadesTab_destino.gridy = 8;
		getContentPane().add(paneTabDestino, gbc_lstPropriedadesTab_destino_1);
		
		tbCamposdepara = new JTable();
		GridBagConstraints gbc_tbCamposdepara = new GridBagConstraints();
		gbc_tbCamposdepara.insets = new Insets(0, 0, 5, 5);
		gbc_tbCamposdepara.fill = GridBagConstraints.BOTH;
		gbc_tbCamposdepara.gridx = 2;
		gbc_tbCamposdepara.gridy = 9;
		gbc_lstPropriedadeTab_origem_1.gridwidth = 4;
		getContentPane().add(tbCamposdepara, gbc_tbCamposdepara);
		
		JButton btnCarregarDados = new JButton("CARREGAR");
		btnCarregarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCarregarDados.setAction(action);
		GridBagConstraints gbc_btnCarregarDados = new GridBagConstraints();
		gbc_btnCarregarDados.insets = new Insets(0, 0, 5, 5);
		gbc_btnCarregarDados.gridx = 2;
		gbc_btnCarregarDados.gridy = 10;
		getContentPane().add(btnCarregarDados, gbc_btnCarregarDados);
		
		
		GerenteEventosCbx ouvidor = new GerenteEventosCbx();
		
		
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "CARREGAR");
			putValue(SHORT_DESCRIPTION, "Executa a carga de dados dos campos selecionados para a tabela de destino.");
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Evento executado para = " + e.toString());
		}
	}
	
//	
	public void popularComboTabelasOrigem(String modulo) {
		
		//Leitura do metadados....
		nomesTabelas.addAll(ExtratorGeafin.capturarTabelasModulo(modulo, "origem")); 
		
		Iterator<String> i = nomesTabelas.iterator();
		while (i.hasNext()) {
			cbTabelasOrigem.addItem(i.next());
		}
	}
	
	public void popularComboTabelasDestino() {
		
		//Leitura do metadados....
		nomesTabelas.removeAllElements();
		nomesTabelas.clear();
		nomesTabelas.addAll(ExtratorGeafin.capturarTabelasModulo("geafin", "destino")); 
		
		Iterator<String> i = nomesTabelas.iterator();
		while (i.hasNext()) {
			cbTabelasDestino.addItem(i.next());
		}
	}

	public void popularLstPropriedadesTabelaOrigem(String tabela) {
		
		DefaultListModel model = new DefaultListModel();
		
		lstPropriedadeTab_origem.setModel(model);
		
		propriedadesTabelaOrigem.removeAllElements();
		propriedadesTabelaOrigem.clear();
		
		propriedadesTabelaOrigem.addAll(ExtratorGeafin.obterColunasTiposTabela(tabela, "origem"));
		
		Iterator<Estrutura> i = propriedadesTabelaOrigem.iterator();
		while (i.hasNext()) {
			Estrutura estru = i.next();
			model.addElement(estru.getNomeCampo());
			System.out.println(estru.getNomeCampo());
		}
	}
	
	public void popularLstPropriedadesTabelaDestino(String tabela) {

		DefaultListModel model = new DefaultListModel();

		lstPropriedadesTab_destino.setModel(model);

		propriedadesTabelaDestino.removeAllElements();
		propriedadesTabelaDestino.clear();

		propriedadesTabelaDestino.addAll(ExtratorGeafin.obterColunasTiposTabela(tabela, "destino"));

		Iterator<Estrutura> i = propriedadesTabelaDestino.iterator();
		while (i.hasNext()) {
			Estrutura estru = i.next();
			model.addElement(estru.getNomeCampo());
			System.out.println(estru.getNomeCampo());
		}
	}
}

class GerenteEventosCbx implements ItemListener {
	  // This method is called only if a new item has been selected.
	  public void itemStateChanged(ItemEvent evt) {
	    JComboBox cb = (JComboBox) evt.getSource();

	    Object item = evt.getItem();

	    if (evt.getStateChange() == ItemEvent.SELECTED) {
	      // Item was just selected
	    	System.out.println("Item selecionado: " + item.toString());
	      //Call a method that populate object box of tables in module	
	    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
	      // Item is no longer selected
	    }
	  }
	}
