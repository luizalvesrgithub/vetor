/**
 * 
 */
package br.com.util;

import java.awt.Component;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import br.com.cliente.FretusModel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.ListModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author hp
 *
 */
public class JSptMigracao extends JSplitPane {
	/**
	 * 
	 */
	public JSptMigracao() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent fe) {
				Object evento = fe.getSource();
				try {
					JSptMigracao.setMetaDados();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		try {
			// JPanel pMigracao = new JPanel();
			pMigracao.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			setRightComponent(pMigracao);
			GridBagLayout gbl_pMigracao = new GridBagLayout();
			gbl_pMigracao.columnWidths = new int[] { 0, 0, 0, 5, 41, 0 };
			gbl_pMigracao.rowHeights = new int[] { 0, 0, 0, 0, 1, 0, 169, 0, 0, 2, 0 };
			gbl_pMigracao.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
			gbl_pMigracao.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
					Double.MIN_VALUE };
			pMigracao.setLayout(gbl_pMigracao);

			JLabel label = new JLabel("Dados a Migrar/Origem");
			label.setForeground(new Color(139, 0, 0));
			label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.gridwidth = 2;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			pMigracao.add(label, gbc_label);

			JLabel label_7 = new JLabel("Dados a Gravar/Destino");
			label_7.setForeground(new Color(107, 142, 35));
			label_7.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_label_7 = new GridBagConstraints();
			gbc_label_7.gridwidth = 2;
			gbc_label_7.insets = new Insets(0, 0, 5, 5);
			gbc_label_7.gridx = 2;
			gbc_label_7.gridy = 0;
			pMigracao.add(label_7, gbc_label_7);

			JLabel label_1 = new JLabel("M\u00F3dulo:");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_label_1 = new GridBagConstraints();
			gbc_label_1.anchor = GridBagConstraints.EAST;
			gbc_label_1.insets = new Insets(0, 0, 5, 5);
			gbc_label_1.gridx = 0;
			gbc_label_1.gridy = 1;
			pMigracao.add(label_1, gbc_label_1);

			ProcedimentosApoio.cbModulos = new JComboBox<String>();
			ProcedimentosApoio.cbModulos.setMaximumRowCount(10);
			ProcedimentosApoio.cbModulos.setToolTipText(
					"A op\u00E7\u00E3o 'Geral' deve ser usada quando se deseja recuperar todas as tabelas do sistema.");
			try {
				ProcedimentosApoio.cbModulos.setModel(new DefaultComboBoxModel<String>(
						new String[] { "-----------", "Geral ", "ALW", "AX", "BP", "CP", "CR", "CRW", "CTW", "DIVERSOS",
								"FIW", "FRW", "IM", "IMW", "JBPM", "OP", "RPW", "RT", "SG", "SGW", "TEMP", "TT" }));
				GridBagConstraints gbc_cbModulos = new GridBagConstraints();
				gbc_cbModulos.insets = new Insets(0, 0, 5, 5);
				gbc_cbModulos.fill = GridBagConstraints.HORIZONTAL;
				gbc_cbModulos.gridx = 1;
				gbc_cbModulos.gridy = 1;
				// ------------------------------------------------>pMigracao.add(ProcedimentosApoio.cbModulos,
				// gbc_cbModulos);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			cbModulosOrigem = new JComboBox();
			cbModulosOrigem.setModel(new DefaultComboBoxModel<String>(
					new String[] { "-----------", "Geral ", "ALW", "AX", "BP", "CP", "CR", "CRW", "CTW", "DIVERSOS",
							"FIW", "FRW", "IM", "IMW", "JBPM", "OP", "RPW", "RT", "SG", "SGW", "TEMP", "TT" }));
			cbModulosOrigem.setMaximumRowCount(12);

			cbModulosOrigem.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					Object item = evt.getItem();
					String smodulo = item.toString();
					if (evt.getStateChange() == ItemEvent.SELECTED) {
						ProcedimentosApoio.popularComboTabelasOrigem(smodulo);
						populandoCombo = true;
						Iterator<String> i = ProcedimentosApoio.vnomesTabelasOrigem.iterator();
						while (i.hasNext()) {
							cbTabelasModuloOrigem.addItem((String) i.next());
						}
						populandoCombo = false;
						// ProcedimentosApoio.popularArvoreDBorigem(JSptConexao.conexaoOrigem,
						// JSptConexao.cbbBdOrigem.getSelectedItem().toString());
					} else if (evt.getStateChange() == ItemEvent.DESELECTED) {

					}
				}
			});
			GridBagConstraints gbc_cbModulosOrigem = new GridBagConstraints();
			gbc_cbModulosOrigem.insets = new Insets(0, 0, 5, 5);
			gbc_cbModulosOrigem.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbModulosOrigem.gridx = 1;
			gbc_cbModulosOrigem.gridy = 1;
			pMigracao.add(cbModulosOrigem, gbc_cbModulosOrigem);

			JLabel label_2 = new JLabel("M\u00F3dulo:");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_label_2 = new GridBagConstraints();
			gbc_label_2.anchor = GridBagConstraints.EAST;
			gbc_label_2.insets = new Insets(0, 0, 5, 5);
			gbc_label_2.gridx = 2;
			gbc_label_2.gridy = 1;
			pMigracao.add(label_2, gbc_label_2);

			cbModulosDest = new JComboBox<String>();
			cbModulosDest.setModel(new DefaultComboBoxModel(new String[] { "", "Geral", "GLOBAL", "RELACIONAMENTO" }));
			cbModulosDest.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {

					Object item = evt.getItem();
					String smodulo = item.toString();
					if (evt.getStateChange() == ItemEvent.SELECTED) {
						ProcedimentosApoio.popularComboTabelasDestino(smodulo);
						populandoCombo = true;
						Iterator<String> i = ProcedimentosApoio.vnomesTabelasDestino.iterator();
						while (i.hasNext()) {
							cbTabelasModulosDestino.addItem((String) i.next());
						}
						populandoCombo = false;
						// A árvore do MetaDados de destino já está montado
						// ProcedimentosApoio.popularArvoreDBdestino(JSptConexao.conexaoDestino,
						// JSptConexao.cbbBancoDestino.getSelectedItem().toString());
					} else if (evt.getStateChange() == ItemEvent.DESELECTED) {

					}
				}
			});
			cbModulosDest.setToolTipText(
					"Se o sistema n\u00E3o for dividido em m\u00F3dulos funcionais ou subsistema, escolha a op\u00E7\u00E3o GLOBAL.");
			cbModulosDest.setMaximumRowCount(10);
			GridBagConstraints gbcModuloDest = new GridBagConstraints();
			gbcModuloDest.fill = GridBagConstraints.HORIZONTAL;
			gbcModuloDest.insets = new Insets(0, 0, 5, 5);
			gbcModuloDest.gridx = 3;
			gbcModuloDest.gridy = 1;
			pMigracao.add(cbModulosDest, gbcModuloDest);

			JLabel label_3 = new JLabel("Tabelas:");
			label_3.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_label_3 = new GridBagConstraints();
			gbc_label_3.anchor = GridBagConstraints.EAST;
			gbc_label_3.insets = new Insets(0, 0, 5, 5);
			gbc_label_3.gridx = 0;
			gbc_label_3.gridy = 2;
			pMigracao.add(label_3, gbc_label_3);

			cbTabelasModuloOrigem = new JComboBox<String>();
			cbTabelasModuloOrigem.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					Object item = evt.getItem();
					String tabelaSelecionada = item.toString();
					if (evt.getStateChange() == ItemEvent.SELECTED) {// &&
																		// !populandoCombo)
																		// {
						camposDePara = inicialarray;
						popularLstPropriedadesTabelaOrigem(tabelaSelecionada);
						ProcedimentosApoio.executeGetCrossReference(ProcedimentosApoio.conexaoDestino,
								ProcedimentosApoio.catOrigem, ProcedimentosApoio.schOrigem, tabelaSelecionada,
								ProcedimentosApoio.catDestino, ProcedimentosApoio.schDestino, tabelaSelecionada,
								vrelations);

					} else if (evt.getStateChange() == ItemEvent.DESELECTED) {
						// Item is no longer selected
					}
				}
			});
			cbTabelasModuloOrigem.setMaximumRowCount(15);
			GridBagConstraints gbc_cbTabelasModuloOrigem = new GridBagConstraints();
			gbc_cbTabelasModuloOrigem.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbTabelasModuloOrigem.insets = new Insets(0, 0, 5, 5);
			gbc_cbTabelasModuloOrigem.gridx = 1;
			gbc_cbTabelasModuloOrigem.gridy = 2;
			pMigracao.add(cbTabelasModuloOrigem, gbc_cbTabelasModuloOrigem);

			JLabel label_4 = new JLabel("Tabelas:");
			label_4.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_label_4 = new GridBagConstraints();
			gbc_label_4.anchor = GridBagConstraints.EAST;
			gbc_label_4.insets = new Insets(0, 0, 5, 5);
			gbc_label_4.gridx = 2;
			gbc_label_4.gridy = 2;
			pMigracao.add(label_4, gbc_label_4);

			cbTabelasModulosDestino = new JComboBox<String>();
			cbTabelasModulosDestino.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					Object item = evt.getItem();
					String tabelaSelecionada = item.toString();
					if (evt.getStateChange() == ItemEvent.SELECTED) { // &&
																		// !populandoCombo)
																		// {
						camposDePara = inicialarray;
						popularLstPropriedadesTabelaDestino(tabelaSelecionada);
						ProcedimentosApoio.executeGetCrossReference(ProcedimentosApoio.conexaoDestino,
								ProcedimentosApoio.catOrigem, ProcedimentosApoio.schOrigem, item.toString(),
								ProcedimentosApoio.catDestino, ProcedimentosApoio.schDestino, item.toString(),
								vrelations);
						// analiseRelacionamentosTabela(item.toString());
					} else if (evt.getStateChange() == ItemEvent.DESELECTED) {
						//
					}
				}
			});
			cbTabelasModulosDestino.setMaximumRowCount(15);
			GridBagConstraints gbc_cbTabelasModulosDestino = new GridBagConstraints();
			gbc_cbTabelasModulosDestino.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbTabelasModulosDestino.insets = new Insets(0, 0, 5, 5);
			gbc_cbTabelasModulosDestino.gridx = 3;
			gbc_cbTabelasModulosDestino.gridy = 2;
			pMigracao.add(cbTabelasModulosDestino, gbc_cbTabelasModulosDestino);

			JLabel label_5 = new JLabel("Propriedades Tabela Origem");
			label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_label_5 = new GridBagConstraints();
			gbc_label_5.gridwidth = 2;
			gbc_label_5.insets = new Insets(0, 0, 5, 5);
			gbc_label_5.gridx = 0;
			gbc_label_5.gridy = 3;
			pMigracao.add(label_5, gbc_label_5);

			JLabel label_6 = new JLabel("Propriedades Tabela Destino");
			label_6.setHorizontalAlignment(SwingConstants.CENTER);
			label_6.setForeground(new Color(160, 82, 45));
			label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_label_6 = new GridBagConstraints();
			gbc_label_6.gridwidth = 2;
			gbc_label_6.insets = new Insets(0, 0, 5, 5);
			gbc_label_6.gridx = 2;
			gbc_label_6.gridy = 3;
			pMigracao.add(label_6, gbc_label_6);

			scpPropTabOrigem = new JScrollPane((Component) null);
			scpPropTabOrigem.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			GridBagConstraints gbc_scpPropTabOrigem = new GridBagConstraints();
			gbc_scpPropTabOrigem.fill = GridBagConstraints.BOTH;
			gbc_scpPropTabOrigem.gridwidth = 2;
			gbc_scpPropTabOrigem.insets = new Insets(0, 0, 5, 5);
			gbc_scpPropTabOrigem.gridx = 0;
			gbc_scpPropTabOrigem.gridy = 4;
			//
			ProcedimentosApoio.modelEstruturaOrigem = new DefaultTableModel(camposOrigem,
					ProcedimentosApoio.nomesColunas) {
				private static final long serialVersionUID = 1L;

				@Override
				public Class getColumnClass(int column) {
					return getValueAt(0, column).getClass();
				}
			};
			ProcedimentosApoio.jtbLstPropriedadesTabOrigem = new JTable(new DefaultTableModel(
					new Object[][] { { null, null, null }, { null, null, null }, { null, null, null },
							{ null, null, null }, { null, null, null }, { null, null, null }, },
					new String[] { "Nome Propriedade", "Tipo Conte\u00FAdo", "Tamanho" }));
			ProcedimentosApoio.jtbLstPropriedadesTabOrigem.setSurrendersFocusOnKeystroke(true);
			ProcedimentosApoio.jtbLstPropriedadesTabOrigem.setFillsViewportHeight(true);
			ProcedimentosApoio.jtbLstPropriedadesTabOrigem.setColumnSelectionAllowed(true);
			ProcedimentosApoio.jtbLstPropriedadesTabOrigem.setCellSelectionEnabled(true);
			ProcedimentosApoio.jtbLstPropriedadesTabOrigem
					.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			// lstPropriedadesTabOrigem = new JList(vpropstableorigem);
			// lstPropriedadesTabOrigem.setVisibleRowCount(4);
			GridBagConstraints gbcJpPropTbOrigem = new GridBagConstraints();
			gbcJpPropTbOrigem.insets = new Insets(0, 0, 5, 5);
			gbcJpPropTbOrigem.gridwidth = 2;
			gbcJpPropTbOrigem.fill = GridBagConstraints.BOTH;
			gbcJpPropTbOrigem.gridx = 0;
			gbcJpPropTbOrigem.gridy = 4;
			ProcedimentosApoio.jpTabPropiedadesTaborigem = new JScrollPane(
					ProcedimentosApoio.jtbLstPropriedadesTabOrigem);
			ProcedimentosApoio.jpTabPropiedadesTaborigem.setBounds(1, 2, 100, 200);
			ProcedimentosApoio.jpTabPropiedadesTaborigem
					.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

			// JScrollPane panelstPropriedadesTabOrigem = new
			// JScrollPane(lstPropriedadesTabOrigem);
			pMigracao.add(ProcedimentosApoio.jpTabPropiedadesTaborigem, gbcJpPropTbOrigem);
			//
			/*
			 * ProcedimentosApoio.jtbLstPropriedadesTabOrigem = new JTable(new
			 * DefaultTableModel( new Object[][] { { null, null, null }, { null,
			 * null, null }, { null, null, null }, { null, null, null }, { null,
			 * null, null }, { null, null, null }, }, new String[] {
			 * "Nome Propriedade", "Tipo Conte\u00FAdo", "Tamanho" }));
			 * ProcedimentosApoio.jtbLstPropriedadesTabOrigem.
			 * setSurrendersFocusOnKeystroke(true);
			 * ProcedimentosApoio.jtbLstPropriedadesTabOrigem.
			 * setFillsViewportHeight(true);
			 * ProcedimentosApoio.jtbLstPropriedadesTabOrigem.
			 * setColumnSelectionAllowed(true);
			 * ProcedimentosApoio.jtbLstPropriedadesTabOrigem.
			 * setCellSelectionEnabled(true);
			 * ProcedimentosApoio.jtbLstPropriedadesTabOrigem .setBorder(new
			 * MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))); //
			 * lstPropriedadesTabOrigem = new JList(vpropstableorigem); //
			 * lstPropriedadesTabOrigem.setVisibleRowCount(4);
			 * GridBagConstraints gbcJpPropTbOrigem = new GridBagConstraints();
			 * gbcJpPropTbOrigem.insets = new Insets(0, 0, 5, 5);
			 * gbcJpPropTbOrigem.gridwidth = 2; gbcJpPropTbOrigem.fill =
			 * GridBagConstraints.BOTH; gbcJpPropTbOrigem.gridx = 0;
			 * gbcJpPropTbOrigem.gridy = 4;
			 * ProcedimentosApoio.jpTabPropiedadesTaborigem = new
			 * JScrollPane(ProcedimentosApoio.jtbLstPropriedadesTabOrigem);
			 * ProcedimentosApoio.jpTabPropiedadesTaborigem.setBounds(1, 2, 100,
			 * 200); ProcedimentosApoio.jpTabPropiedadesTaborigem
			 * .setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null,
			 * null, null, null));
			 * 
			 * // pMigracao.add(scpPropTabOrigem, gbc_scpPropTabOrigem);
			 */

			ProcedimentosApoio.modelEstruturaDestino = new DefaultTableModel(camposDestino,
					ProcedimentosApoio.nomesColunas) {
				private static final long serialVersionUID = 1L;

				@Override
				public Class getColumnClass(int column) {
					return getValueAt(0, column).getClass();
				}
			};
			ProcedimentosApoio.jtbLstPropriedadesTabDestino = new JTable(new DefaultTableModel(
					new Object[][] { { null, null, null }, { null, null, null }, { null, null, null },
							{ null, null, null }, { null, null, null }, { null, null, null }, },
					new String[] { "Nome Propriedade", "Tipo Conte\u00FAdo", "Tamanho" }));
			GridBagConstraints gbc_lstPropriedadesTabelaDestino = new GridBagConstraints();
			gbc_lstPropriedadesTabelaDestino.gridwidth = 2;
			gbc_lstPropriedadesTabelaDestino.insets = new Insets(0, 0, 5, 0);
			gbc_lstPropriedadesTabelaDestino.fill = GridBagConstraints.BOTH;
			gbc_lstPropriedadesTabelaDestino.gridx = 2;
			gbc_lstPropriedadesTabelaDestino.gridy = 4;
			ProcedimentosApoio.jpTabPropiedadesTabDestino = new JScrollPane(
					ProcedimentosApoio.jtbLstPropriedadesTabDestino);
			pMigracao.add(ProcedimentosApoio.jpTabPropiedadesTabDestino, gbc_lstPropriedadesTabelaDestino);

			/*
			 * JScrollPane scpPropTabDestino = new JScrollPane((Component)
			 * null); GridBagConstraints gbc_scpPropTabDestino = new
			 * GridBagConstraints(); gbc_scpPropTabDestino.fill =
			 * GridBagConstraints.BOTH; gbc_scpPropTabDestino.gridwidth = 2;
			 * gbc_scpPropTabDestino.insets = new Insets(0, 0, 5, 5);
			 * gbc_scpPropTabDestino.gridx = 2; gbc_scpPropTabDestino.gridy = 4;
			 * pMigracao.add(scpPropTabDestino, gbc_scpPropTabDestino);
			 */

			JButton btnSelecionarParPropriedade = new JButton("Selecionar Par");
			btnSelecionarParPropriedade
					.setIcon(new ImageIcon(JSptMigracao.class.getResource("/com/resc/br/Retort.png")));
			btnSelecionarParPropriedade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent aevt) {

					try {
						// O ideal é que se selecione o valor da linha como
						// instância de Estrutura
						int idxselrows[] = ProcedimentosApoio.jtbLstPropriedadesTabOrigem.getSelectedRows();
						for (int i = 0; i < idxselrows.length; i++) {
							System.out.println((String) ProcedimentosApoio.jtbLstPropriedadesTabOrigem.getModel()
									.getValueAt(idxselrows[i], 0));

							popularLstParesDePara(new Estrutura(cbTabelasModuloOrigem.getSelectedItem().toString() + "."
									+ (String) ProcedimentosApoio.jtbLstPropriedadesTabOrigem.getModel()
											.getValueAt(idxselrows[i], 0),

									(String) ProcedimentosApoio.jtbLstPropriedadesTabOrigem.getModel()
											.getValueAt(idxselrows[i], 1),

									(String) ProcedimentosApoio.jtbLstPropriedadesTabOrigem.getModel()
											.getValueAt(idxselrows[i], 2)),

									new Estrutura(
											(String) ProcedimentosApoio.jtbLstPropriedadesTabDestino.getModel()
													.getValueAt(ProcedimentosApoio.jtbLstPropriedadesTabDestino
															.getSelectedRow(), 0),
											(String) ProcedimentosApoio.jtbLstPropriedadesTabDestino.getModel()
													.getValueAt(ProcedimentosApoio.jtbLstPropriedadesTabDestino
															.getSelectedRow(), 1),
											(String) ProcedimentosApoio.jtbLstPropriedadesTabDestino.getModel()
													.getValueAt(ProcedimentosApoio.jtbLstPropriedadesTabDestino
															.getSelectedRow(), 2)));
						}
					} catch (Exception e) {
						System.out.println("Erro em Popular vetor pares de propriedade: " + e.getMessage());
						e.printStackTrace();
					}

					pMigracao.remove(ProcedimentosApoio.jpTabPropiedadesTabDePara);

					ProcedimentosApoio.jtbLstPropriedadesTabDePara = new JTable(
							ProcedimentosApoio.modelEstruturaDePara);
					ProcedimentosApoio.jpTabPropiedadesTabDePara = new JScrollPane(
							ProcedimentosApoio.jtbLstPropriedadesTabDePara);
					GridBagConstraints gbcTabDePara = new GridBagConstraints();
					gbcTabDePara.gridwidth = 4;
					gbcTabDePara.insets = new Insets(0, 0, 5, 0);
					gbcTabDePara.fill = GridBagConstraints.BOTH;
					gbcTabDePara.gridx = 0;
					gbcTabDePara.gridy = 6;
					pMigracao.add(ProcedimentosApoio.jpTabPropiedadesTabDePara, gbcTabDePara);

					validate();
					repaint();

				}
			});
			btnSelecionarParPropriedade.setToolTipText(
					"Pode ocorrer de mais de uma propriedade da tabela de origem se destine a um \u00FAnico campo da tabela de destino. Neste caso ser\u00E1 constru\u00EDda a concatena\u00E7\u00E3o dos campos no comando de sele\u00E7\u00E3o da tabela de origem. Exemplo: Select a, (ddd+op+numfone) as fone from table_alvo.");
			GridBagConstraints gbc_btnSelecionarParPropriedade = new GridBagConstraints();
			gbc_btnSelecionarParPropriedade.gridwidth = 2;
			gbc_btnSelecionarParPropriedade.insets = new Insets(0, 0, 5, 5);
			gbc_btnSelecionarParPropriedade.gridx = 1;
			gbc_btnSelecionarParPropriedade.gridy = 5;
			pMigracao.add(btnSelecionarParPropriedade, gbc_btnSelecionarParPropriedade);

			JButton btnLimpar = new JButton("Limpar");
			btnLimpar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent aev) {

					ProcedimentosApoio.vparesdepara.addElement(new Parestrutura(new Estrutura(), new Estrutura()));

					Object[][] acont = new Object[ProcedimentosApoio.vparesdepara.size()][6];

					int n = 0;

					Iterator<Parestrutura> i = ProcedimentosApoio.vparesdepara.iterator();
					while (i.hasNext()) {
						Parestrutura pardepara = i.next();
						acont[n][0] = pardepara.destino.getNomeCampo();
						acont[n][1] = pardepara.destino.getTipoCampo();
						acont[n][2] = pardepara.destino.getTamanho();
						acont[n][3] = pardepara.origem.getNomeCampo();
						acont[n][4] = pardepara.origem.getTipoCampo();
						acont[n][5] = pardepara.origem.getTamanho();
						n++;
					}

					camposDePara = acont;
					pMigracao.remove(ProcedimentosApoio.jpTabPropiedadesTabDePara);

					ProcedimentosApoio.jtbLstPropriedadesTabDePara = new JTable(
							ProcedimentosApoio.modelEstruturaDePara);
					ProcedimentosApoio.jpTabPropiedadesTabDePara = new JScrollPane(
							ProcedimentosApoio.jtbLstPropriedadesTabDePara);
					GridBagConstraints gbcTabDePara = new GridBagConstraints();
					gbcTabDePara.gridwidth = 4;
					gbcTabDePara.insets = new Insets(0, 0, 5, 0);
					gbcTabDePara.fill = GridBagConstraints.BOTH;
					gbcTabDePara.gridx = 0;
					gbcTabDePara.gridy = 6;
					pMigracao.add(ProcedimentosApoio.jpTabPropiedadesTabDePara, gbcTabDePara);

					validate();
					repaint();

				}
			});
			btnLimpar.setIcon(new ImageIcon(JSptMigracao.class.getResource("/com/resc/br/Delete.png")));
			GridBagConstraints gbc_btnLimpar = new GridBagConstraints();
			gbc_btnLimpar.insets = new Insets(0, 0, 5, 0);
			gbc_btnLimpar.gridx = 3;
			gbc_btnLimpar.gridy = 5;
			pMigracao.add(btnLimpar, gbc_btnLimpar);

			ProcedimentosApoio.jtbLstPropriedadesTabDePara = new JTable(ProcedimentosApoio.modelEstruturaDePara);
			ProcedimentosApoio.jtbLstPropriedadesTabDePara.setSurrendersFocusOnKeystroke(true);
			ProcedimentosApoio.jtbLstPropriedadesTabDePara.setToolTipText(
					"Aten\u00E7\u00E3o! Esta situa\u00E7\u00E3o pode ocorre: v\u00E1rios campos de uma mesma tabela para um \u00FAnico e v\u00E1rios campos de tabelas distintas para um \u00FAnico.");
			GridBagConstraints gbc_lstParesDePara = new GridBagConstraints();
			gbc_lstParesDePara.gridwidth = 4;
			gbc_lstParesDePara.insets = new Insets(0, 0, 5, 0);
			gbc_lstParesDePara.fill = GridBagConstraints.BOTH;
			gbc_lstParesDePara.gridx = 0;
			gbc_lstParesDePara.gridy = 6;
			ProcedimentosApoio.jpTabPropiedadesTabDePara = new JScrollPane(
					ProcedimentosApoio.jtbLstPropriedadesTabDePara);
			ProcedimentosApoio.jpTabPropiedadesTabDePara.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
			pMigracao.add(ProcedimentosApoio.jpTabPropiedadesTabDePara, gbc_lstParesDePara);

			JPanel panel_1 = new JPanel();
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.anchor = GridBagConstraints.BASELINE_LEADING;
			gbc_panel_1.insets = new Insets(0, 0, 5, 0);
			gbc_panel_1.gridx = 4;
			gbc_panel_1.gridy = 6;
			pMigracao.add(panel_1, gbc_panel_1);
			panel_1.setLayout(new GridLayout(0, 1, 0, 0));

			JLabel lblOrdemCampos = new JLabel("Ordem Campos");
			panel_1.add(lblOrdemCampos);

			JButton button_2 = new JButton("");
			button_2.setIcon(new ImageIcon(JSptMigracao.class.getResource("/com/resc/br/Down.png")));
			button_2.setToolTipText("Mover a linha selecionada para cima.");
			panel_1.add(button_2);

			JButton button_3 = new JButton("");
			button_3.setIcon(new ImageIcon(JSptMigracao.class.getResource("/com/resc/br/Up.png")));
			button_3.setToolTipText("Mover a linha selecionada para baixo.");
			panel_1.add(button_3);

			JButton btnSimularScript = new JButton("Simular Script");
			btnSimularScript.setIcon(new ImageIcon(Minerador.class.getResource("/com/resc/br/Wrench.png")));
			btnSimularScript.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						ProcedimentosApoio.taScriptMigracao.setText(popularScriptCarga().toString());
						JSptMontadorAlgebrico.taSelectCommands.setText(ProcedimentosApoio.vinsertSelect.toString());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Falha na geração do Script de Gravação!");
					}

				}
			});
			btnSimularScript.setVerticalAlignment(SwingConstants.TOP);
			GridBagConstraints gbc_btnSimularScript = new GridBagConstraints();
			gbc_btnSimularScript.anchor = GridBagConstraints.NORTH;
			gbc_btnSimularScript.insets = new Insets(0, 0, 5, 5);
			gbc_btnSimularScript.gridx = 0;
			gbc_btnSimularScript.gridy = 7;
			pMigracao.add(btnSimularScript, gbc_btnSimularScript);

			JButton btnMigrarDados = new JButton("Gravar Dados");
			btnMigrarDados.setIcon(new ImageIcon(JSptMigracao.class.getResource("/com/resc/br/Database.png")));
			btnMigrarDados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Os dados serão gravados a partir do script apresentado.
					// Pode ocorrer que apenas uma amostra do script esteja
					// apresentado na tela. Isto porque pode ocorrer de se ter
					// milhões ou mesmo bilhões de registros a serem gravados.
					// Quando isto ocorrer, estará informado em quadro próprio a
					// quantidade de registro e a frequência de commits a serem
					// executados.
					// 1. Executar a partir do conteúdo de taScriptMigracao
					gravarDadosSelecionados();

				}
			});
			btnMigrarDados.setVerticalAlignment(SwingConstants.TOP);
			GridBagConstraints gbc_btnMigrarDados = new GridBagConstraints();
			gbc_btnMigrarDados.anchor = GridBagConstraints.NORTH;
			gbc_btnMigrarDados.gridwidth = 2;
			gbc_btnMigrarDados.insets = new Insets(0, 0, 5, 5);
			gbc_btnMigrarDados.gridx = 1;
			gbc_btnMigrarDados.gridy = 7;
			pMigracao.add(btnMigrarDados, gbc_btnMigrarDados);

			JLabel label_9 = new JLabel("Script de Migra\u00E7\u00E3o");
			label_9.setToolTipText(
					"O Script de migra\u00E7\u00E3o \u00E9 o texto gerado para execu\u00E7\u00E3o em banco de dados. Dever\u00E1 estar apto a ser copiado para um software qualquer ou simplemente ser executado no aplicativo. O Script \u00E9 quebrado a cada mil registros para gerar pontos de commit e assim melhorar a performance e n\u00E3o impactar nas atividades do ambiente global.");
			label_9.setForeground(Color.BLUE);
			label_9.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_label_9 = new GridBagConstraints();
			gbc_label_9.insets = new Insets(0, 0, 5, 5);
			gbc_label_9.gridx = 3;
			gbc_label_9.gridy = 7;
			pMigracao.add(label_9, gbc_label_9);

			ProcedimentosApoio.taScriptMigracao = new JTextArea();
			ProcedimentosApoio.taScriptMigracao.setTabSize(4);
			ProcedimentosApoio.taScriptMigracao.setText(
					"INSERT INTO tabela_destino (Vetor(campos)) Values (SELECT vetor(campos) from tabela de origem.");
			JScrollPane jspTaScriptMigracao = new JScrollPane(ProcedimentosApoio.taScriptMigracao);
			jspTaScriptMigracao.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
			ProcedimentosApoio.taScriptMigracao.setEditable(true);
			GridBagConstraints gbc_taScriptMigracao = new GridBagConstraints();
			gbc_taScriptMigracao.gridwidth = 4;
			gbc_taScriptMigracao.fill = GridBagConstraints.BOTH;
			gbc_taScriptMigracao.gridx = 0;
			gbc_taScriptMigracao.gridy = 9;
			pMigracao.add(jspTaScriptMigracao, gbc_taScriptMigracao);

			jspDiretorioConexoes = new JSplitPane();
			jspDiretorioConexoes.setOrientation(JSplitPane.VERTICAL_SPLIT);

			setLeftComponent(jspDiretorioConexoes);
			try {

				jspBdOrigem = new JScrollPane(ProcedimentosApoio.treeConexaoOrigem);
				jspDiretorioConexoes.setLeftComponent(jspBdOrigem);

				jspBdDestino = new JScrollPane(ProcedimentosApoio.treeConexaoDestino);
				jspDiretorioConexoes.setRightComponent(jspBdDestino);

			} catch (Exception e) {
				// TODO: handle exception
			}
			/*
			 * setLeftComponent(jsppanel); GridBagLayout gbl_panel = new
			 * GridBagLayout(); gbl_panel.columnWidths = new int[] { 318, 0 };
			 * gbl_panel.rowHeights = new int[] { 14, 0, 0 };
			 * gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
			 * gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE
			 * }; jsppanel.setLayout(gbl_panel);
			 * 
			 * JLabel lblInstruesDeUso = new
			 * JLabel("Instru\u00E7\u00F5es de uso"); GridBagConstraints
			 * gbc_lblInstruesDeUso = new GridBagConstraints();
			 * gbc_lblInstruesDeUso.insets = new Insets(0, 0, 5, 0);
			 * gbc_lblInstruesDeUso.anchor = GridBagConstraints.NORTHWEST;
			 * gbc_lblInstruesDeUso.gridx = 0; gbc_lblInstruesDeUso.gridy = 0;
			 * jsppanel.add(lblInstruesDeUso, gbc_lblInstruesDeUso);
			 * 
			 * JTextPane txtpnDeveseSelecionarAs = new JTextPane();
			 * txtpnDeveseSelecionarAs.
			 * setText("Deve-se selecionar as base de dados de origem e de destino."
			 * ); GridBagConstraints gbc_txtpnDeveseSelecionarAs = new
			 * GridBagConstraints(); gbc_txtpnDeveseSelecionarAs.fill =
			 * GridBagConstraints.BOTH; gbc_txtpnDeveseSelecionarAs.gridx = 0;
			 * gbc_txtpnDeveseSelecionarAs.gridy = 1;
			 * jsppanel.add(txtpnDeveseSelecionarAs,
			 * gbc_txtpnDeveseSelecionarAs);
			 */

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	//
	//
	public Vector<String> popularScriptCarga() {

		if (ProcedimentosApoio.conexaoDestino.equals(null) || ProcedimentosApoio.conexaoOrigem.equals(null))
			return (new Vector<String>());

		return ProcedimentosApoio.selectInsert(ProcedimentosApoio.conexaoDestino, ProcedimentosApoio.conexaoOrigem,
				cbTabelasModuloOrigem.getSelectedItem().toString(),
				ProcedimentosApoio.cbTabelasDestino.getSelectedItem().toString(), camposDePara, true);
	}

	//
	//
	public void popularLstPropriedadesTabelaOrigem(String tabela) {

		ProcedimentosApoio.propriedadesTabelaOrigem.removeAllElements();
		ProcedimentosApoio.propriedadesTabelaOrigem.clear();

		ProcedimentosApoio.propriedadesTabelaOrigem.addAll(ProcedimentosApoio.obterColunasTiposTabela(tabela, "origem",
				ProcedimentosApoio.conexaoOrigem, JSptConexao.cbbBdOrigem.getSelectedItem().toString()));

		Object[][] acont = new Object[ProcedimentosApoio.propriedadesTabelaOrigem.size()][3];
		int n = 0;
		Iterator<Estrutura> i = ProcedimentosApoio.propriedadesTabelaOrigem.iterator();
		while (i.hasNext()) {
			Estrutura estru = i.next();
			acont[n][0] = estru.getNomeCampo();
			acont[n][1] = estru.getTipoCampo();
			acont[n][2] = estru.getTamanho();
			n++;
		}

		camposOrigem = acont;

		ProcedimentosApoio.modelEstruturaOrigem = new DefaultTableModel(camposOrigem, ProcedimentosApoio.nomesColunas) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};

		pMigracao.remove(ProcedimentosApoio.jpTabPropiedadesTaborigem);

		try {
			ProcedimentosApoio.jtbLstPropriedadesTabOrigem = new JTable(ProcedimentosApoio.modelEstruturaOrigem);
			ProcedimentosApoio.jpTabPropiedadesTaborigem = new JScrollPane(
					ProcedimentosApoio.jtbLstPropriedadesTabOrigem);
			GridBagConstraints gbcPropTorigem = new GridBagConstraints();
			gbcPropTorigem.insets = new Insets(0, 0, 5, 5);
			gbcPropTorigem.gridwidth = 2;
			gbcPropTorigem.fill = GridBagConstraints.BOTH;
			gbcPropTorigem.gridx = 0;
			gbcPropTorigem.gridy = 4;
			pMigracao.add(ProcedimentosApoio.jpTabPropiedadesTaborigem, gbcPropTorigem);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		validate();
		repaint();

	}

	public void popularLstPropriedadesTabelaDestino(String tabela) {

		ProcedimentosApoio.propriedadesTabelaDestino.removeAllElements();
		ProcedimentosApoio.propriedadesTabelaDestino.clear();

		ProcedimentosApoio.propriedadesTabelaDestino
				.addAll(ProcedimentosApoio.obterColunasTiposTabela(tabela, "destino", ProcedimentosApoio.conexaoDestino,
						JSptConexao.cbbBancoDestino.getSelectedItem().toString()));

		Object[][] acont = new Object[ProcedimentosApoio.propriedadesTabelaDestino.size()][3];

		int n = 0;

		Iterator<Estrutura> i = ProcedimentosApoio.propriedadesTabelaDestino.iterator();
		while (i.hasNext()) {
			Estrutura estru = i.next();
			acont[n][0] = estru.getNomeCampo();
			acont[n][1] = estru.getTipoCampo();
			acont[n][2] = estru.getTamanho();
			System.out.println(estru.getNomeCampo());
			n++;
		}

		camposDestino = acont;

		ProcedimentosApoio.modelEstruturaDestino = new DefaultTableModel(camposDestino,
				ProcedimentosApoio.nomesColunas) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};

		pMigracao.remove(ProcedimentosApoio.jpTabPropiedadesTabDestino);
		try {
			ProcedimentosApoio.jtbLstPropriedadesTabDestino = new JTable(ProcedimentosApoio.modelEstruturaDestino);
			ProcedimentosApoio.jpTabPropiedadesTabDestino = new JScrollPane(
					ProcedimentosApoio.jtbLstPropriedadesTabDestino);
			GridBagConstraints gbcPropTdestino = new GridBagConstraints();
			gbcPropTdestino.gridwidth = 2;
			gbcPropTdestino.insets = new Insets(0, 0, 5, 0);
			gbcPropTdestino.fill = GridBagConstraints.BOTH;
			gbcPropTdestino.gridx = 2;
			gbcPropTdestino.gridy = 4;
			pMigracao.add(ProcedimentosApoio.jpTabPropiedadesTabDestino, gbcPropTdestino);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		validate();
		repaint();

	}

	/**
	 * @param newOrientation
	 */
	public JSptMigracao(int newOrientation) {
		super(newOrientation);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newContinuousLayout
	 */
	public JSptMigracao(int newOrientation, boolean newContinuousLayout) {
		super(newOrientation, newContinuousLayout);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public JSptMigracao(int newOrientation, Component newLeftComponent, Component newRightComponent) {
		super(newOrientation, newLeftComponent, newRightComponent);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newContinuousLayout
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public JSptMigracao(int newOrientation, boolean newContinuousLayout, Component newLeftComponent,
			Component newRightComponent) {
		super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void setMetaDados() {

		try {

			jspBdOrigem = new JScrollPane(ProcedimentosApoio.treeConexaoOrigem);
			jspDiretorioConexoes.setLeftComponent(jspBdOrigem);

			jspBdDestino = new JScrollPane(ProcedimentosApoio.treeConexaoDestino);
			jspDiretorioConexoes.setRightComponent(jspBdDestino);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void popularLstParesDePara(Estrutura campoOrigem, Estrutura campoDestino) {

		ProcedimentosApoio.vparesdepara.addElement(new Parestrutura(campoOrigem, campoDestino));

		Object[][] acont = new Object[ProcedimentosApoio.vparesdepara.size()][6];

		int n = 0;

		Iterator<Parestrutura> i = ProcedimentosApoio.vparesdepara.iterator();
		while (i.hasNext()) {
			Parestrutura pardepara = i.next();

			acont[n][0] = pardepara.destino.getNomeCampo();
			acont[n][1] = pardepara.destino.getTipoCampo();
			acont[n][2] = pardepara.destino.getTamanho();
			acont[n][3] = pardepara.origem.getNomeCampo();
			acont[n][4] = pardepara.origem.getTipoCampo();
			acont[n][5] = pardepara.origem.getTamanho();
			System.out.println(
					"Par de Para -> " + pardepara.origem.getNomeCampo() + "<-->" + pardepara.destino.getNomeCampo());
			n++;
		}

		camposDePara = acont;

		ProcedimentosApoio.modelEstruturaDePara = new DefaultTableModel(camposDePara,
				ProcedimentosApoio.nomesColunasDePara) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};

		pMigracao.remove(ProcedimentosApoio.jpTabPropiedadesTabDePara);

		ProcedimentosApoio.jtbLstPropriedadesTabDePara = new JTable(ProcedimentosApoio.modelEstruturaDePara);
		ProcedimentosApoio.jpTabPropiedadesTabDePara = new JScrollPane(ProcedimentosApoio.jtbLstPropriedadesTabDePara);
		GridBagConstraints gbcTabDePara = new GridBagConstraints();
		gbcTabDePara.gridwidth = 4;
		gbcTabDePara.insets = new Insets(0, 0, 5, 0);
		gbcTabDePara.fill = GridBagConstraints.BOTH;
		gbcTabDePara.gridx = 0;
		gbcTabDePara.gridy = 6;
		pMigracao.add(ProcedimentosApoio.jpTabPropiedadesTabDePara, gbcTabDePara);

		validate();
		repaint();

	}

	protected void popularLstParesDePara(ListModel<Estrutura> lmodelOrigem, int indSelOrigem,
			ListModel<Estrutura> lmodelDestino, int indSelDestino) {
		// Adiciona o par de estrutura ao vetor associado à lista de Pares de
		// propriedades
		Estrutura eorigem = lmodelOrigem.getElementAt(0);
		Estrutura edestino = lmodelDestino.getElementAt(0);
		ProcedimentosApoio.vparesdepara.addElement(new Parestrutura(eorigem, edestino));

	}

	protected void popularLstParesDePara(String campoOrigem, String campoDestino) {

		ProcedimentosApoio.vparesdepara
				.addElement(new Parestrutura(new Estrutura(campoOrigem, "", ""), new Estrutura(campoDestino, "", "")));

		Object[][] acont = new Object[ProcedimentosApoio.vparesdepara.size()][6];

		int n = 0;

		Iterator<Parestrutura> i = ProcedimentosApoio.vparesdepara.iterator();
		while (i.hasNext()) {
			Parestrutura pardepara = i.next();

			acont[n][0] = pardepara.origem.getNomeCampo();
			acont[n][1] = pardepara.origem.getTipoCampo();
			acont[n][2] = pardepara.origem.getTamanho();
			acont[n][3] = pardepara.destino.getNomeCampo();
			acont[n][4] = pardepara.destino.getTipoCampo();
			acont[n][5] = pardepara.destino.getTamanho();
			System.out.println(
					"Par de Para -> " + pardepara.origem.getNomeCampo() + "<-->" + pardepara.destino.getNomeCampo());
			n++;
		}

		camposDePara = acont;

		ProcedimentosApoio.modelEstruturaDePara = new DefaultTableModel(camposDePara,
				ProcedimentosApoio.nomesColunasDePara) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};

		ProcedimentosApoio.jtbLstPropriedadesTabDePara = new JTable(ProcedimentosApoio.modelEstruturaDePara);
		GridBagConstraints gbcTabDePara = new GridBagConstraints();
		gbcTabDePara.gridwidth = 4;
		gbcTabDePara.insets = new Insets(0, 0, 5, 0);
		gbcTabDePara.fill = GridBagConstraints.BOTH;
		gbcTabDePara.gridx = 0;
		gbcTabDePara.gridy = 6;
		ProcedimentosApoio.jpTabPropiedadesTabDePara = new JScrollPane(ProcedimentosApoio.jtbLstPropriedadesTabDePara);
		pMigracao.add(ProcedimentosApoio.jpTabPropiedadesTabDePara, gbcTabDePara);

		validate();
		repaint();

	}

	protected void gravarDadosSelecionados() {
		// verificar se o conteúdo a ser gravado está em arquivo...
		String scomandos = ProcedimentosApoio.taScriptMigracao.getText();
		String[] comandos = scomandos.split("\\^");
		try {
			for (int i = 0; i < comandos.length; i++) {
				String comdSql = comandos[i].replaceFirst("\\[", "");
				int ultimaPosicao = comdSql.length();
				comdSql = comdSql.endsWith("\\]") ? comdSql.substring(0, (ultimaPosicao - 1)) : comdSql;
				if (ProcedimentosApoio.conexaoDestino.isClosed()) {
					ProcedimentosApoio.conexaoDestino = FretusModel.restaurarConexaoDestino();
				}
				PreparedStatement preparedStmt = ProcedimentosApoio.conexaoDestino.prepareStatement(comdSql);
				preparedStmt.execute();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Acesso a dados sem sucesso ou a conexão com o banco não efetivada!" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	protected static boolean populandoCombo = false;
	private static final long serialVersionUID = 1L;

	protected static JScrollPane scpPropTabOrigem;
	protected static JPanel pMigracao = new JPanel();
	protected static JComboBox<String> cbTabelasModulosDestino;
	protected static JComboBox<String> cbTabelasModuloOrigem;
	protected static JComboBox<String> cbModulosDest;
	protected static JComboBox<String> cbModulosOrigem;

	protected static JSplitPane jspDiretorioConexoes;
	protected static JScrollPane jspBdOrigem;
	protected static JScrollPane jspBdDestino;

	private Object[][] camposOrigem = null;
	private Object[][] camposDestino = null;
	private Object[][] inicialarray = null;
	private Object[][] camposDePara = { { "Or1", "ot1", "olen1", "para1", "part1", "parlen1" },
			{ "Or2", "ot2", "olen2", "para1", "part1", "parlen1" },
			{ "Or3", "ot3", "olen3", "para1", "part1", "parlen1" } };

	private static Vector<String> vrelations = new Vector<String>();

}
