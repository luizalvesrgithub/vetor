package br.com.util;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.com.cliente.FretusModel;

public class TunicaGeral extends JSplitPane {

	public TunicaGeral() {

		super();

		sptComposition = new JSplitPane();
		sptComposition.setOrientation(JSplitPane.VERTICAL_SPLIT);

		jpnavegadorMetadados = new JPanelMetaDados<ConnectionFactory>(new ConnectionFactory());
		GridBagLayout gridBagLayout = (GridBagLayout) jpnavegadorMetadados.getLayout();
		GridBagConstraints gbcJpnave = new GridBagConstraints();
		gbcJpnave.gridx = 0;
		gbcJpnave.gridy = 0;
		gbcJpnave.gridwidth = 1;
		gbcJpnave.gridheight = 1;
		gridBagLayout.setConstraints(jpnavegadorMetadados, gbcJpnave);
		gridBagLayout.columnWidths = new int[] { 100, 100, 5 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.1 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0 };
		// jpnavegadorMetadados.setC(gbcJpnave);

		sptMasterEntitty = new JSplitPane();
		sptComposition.setLeftComponent(sptMasterEntitty);

		tbdPmaster = new JTabbedPane(JTabbedPane.TOP);
		tbdPmaster.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent arg0) {
				--FretusModel.numTab;
			}
		});
		sptMasterEntitty.setLeftComponent(tbdPmaster);

		scpMaster = new JScrollPane();
		// tbdPmaster.addTab("Cebecalho", null, scpMaster, null);

		tMasterCabecalho = new JTable();
		tMasterCabecalho.setPreferredScrollableViewportSize(new Dimension(300, 200));
		tMasterCabecalho.setFillsViewportHeight(true);

		scpDetailsMaster = new JScrollPane();

		addComponentsToPane(scpDetailsMaster);
		// sptMasterEntitty.setRightComponent(arg0);
		sptMasterEntitty.setRightComponent(scpDetailsMaster);

		sptMemberEntity = new JSplitPane();
		sptComposition.setRightComponent(sptMemberEntity);

		tbdPmembersItems = new JTabbedPane(JTabbedPane.TOP);
		sptMemberEntity.setLeftComponent(tbdPmembersItems);

		scpMembersItems = new JScrollPane();
		// tbdPmembersItems.addTab("Membros/Itens", null, scpMembersItems,
		// null);

		tMembersItems = new JTable();
		tMembersItems.setPreferredScrollableViewportSize(new Dimension(300, 200));
		tMembersItems.setFillsViewportHeight(true);
		scpMembersItems.add(tMembersItems);

		scpDetailsMembers = new JScrollPane();
		sptMemberEntity.setRightComponent(scpDetailsMembers);

		// ---

		this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

		this.setLeftComponent(jpnavegadorMetadados);
		this.setRightComponent(sptComposition);
		this.setOneTouchExpandable(true);
		this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.setResizeWeight(0.5);
		this.setOneTouchExpandable(true);
		this.setDividerSize(8);
		this.setContinuousLayout(false);
		// ---

		// splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		// jpnavegadorMetadados, sptComposition);
		// splitPane.setOneTouchExpandable(true);
		// splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		// splitPane.setResizeWeight(0.5);
		// splitPane.setOneTouchExpandable(true);
		// splitPane.setDividerSize(8);
		// splitPane.setContinuousLayout(false);

	}

	private void addComponentsToPane(JScrollPane scpDetailsMaster2) {

		Container pane = new Container();
		scpDetailsMaster2.add(pane);

	}

	public void settMasterCabecalho(JTable tMasterCabecalho) {
		this.tMasterCabecalho = tMasterCabecalho;
	}

	public void settMembersItems(JTable tMembersItems) {
		this.tMembersItems = tMembersItems;
	}

	public void setSptComposition(JSplitPane sptComposition) {
		this.sptComposition = sptComposition;
	}

	public void setSptMasterEntitty(JSplitPane sptMaterEntitty) {
		this.sptMasterEntitty = sptMaterEntitty;
	}

	public void setTbdPmaster(JTabbedPane ptbdPmaster) {
		TunicaGeral.tbdPmaster = ptbdPmaster;
	}

	public void setScrollPaneMestre(JScrollPane pscpMaster, String cabecalho) {

		scpMaster = pscpMaster;
		tbdPmaster.addTab(cabecalho + "(" + ProcedimentosApoio.Nregistros + ")", null, pscpMaster, null);
		initTabMestreComponent(numTabMestre++);
		repaint();

	}

	/** 
	 * 
	 * */
	public void setScrollPaneDetalhe(JScrollPane pscpDetalhe, String cabecalho) {

		scpMembersItems = pscpDetalhe;
		tbdPmembersItems.addTab(cabecalho + "(" + ProcedimentosApoio.Nregistros + ")", null, pscpDetalhe, null);
		initTabDetalheComponent(numTabDetalhe++);

		repaint();

	}

	/**
	 * @param vetor
	 *            com titulos das colunas e
	 * @param vetor
	 *            com conteúdo de uma linha selecionada em uma tabela a qual
	 * @param nome
	 *            da tabela Fazer a projeção de linha para coluna: linha de
	 *            cabeçalho e de conteúdo transpostas.
	 */
	public void projecaoLinhaRegistro(Vector<String> vtituloscolunas, Vector<String> vtiposdecolunas,
			Vector<Object> vconteudoRecord, String ptabela) {

		// Map<String, Object> mapstrobj = new HashMap<String, Object>();
		// scpDetailsMaster
		projecaoLinha = new JPanel();
		GridBagLayout gblProj = new GridBagLayout();
		gblProj.columnWidths = new int[] { 0, 0, 0, 5, 41, 0 };
		gblProj.rowHeights = new int[] { 0, 0, 0, 0, 1, 0, 169, 0, 0, 2, 0 };
		gblProj.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gblProj.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		// projecaoLinha.setBorder(new BorderLayout());
		projecaoLinha.setLayout(gblProj);

		projecaoLinha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ptabela,
				TitledBorder.CENTER, TitledBorder.TOP));
		GridBagConstraints gbcComponent = new GridBagConstraints();
		gbcComponent.gridwidth = 1;
		gbcComponent.gridheight = 1;
		gbcComponent.insets = new Insets(0, 0, 5, 5);

		try {

			int coordx = 0; // NÃO VARIA
			int coordy = 0;
			String stitulocoluna = "";
			Iterator<String> ititulos = vtituloscolunas.iterator();
			while (ititulos.hasNext()) {
				try {
					stitulocoluna = ititulos.next();
				} catch (Exception e) {
					break;
				}
				gbcComponent.gridx = coordx;
				gbcComponent.gridy = coordy++;
				JLabel jl = new JLabel(stitulocoluna);
				jl.setPreferredSize(new Dimension(100, 25));
				projecaoLinha.add(jl, gbcComponent);
			}

			coordx++; // coordenada x = 1
			coordy = 0;
			String sconteudocoluna = "";
			Iterator<Object> itconteudoCol = vconteudoRecord.iterator();
			while (itconteudoCol.hasNext()) {
				Object conteudoCol = itconteudoCol.next();
				// System.out.println("Conteúdo coluna = " +
				// conteudoCol.toString());
				sconteudocoluna = conteudoCol.toString();
				gbcComponent.gridx = coordx;
				gbcComponent.gridy = coordy++;
				projecaoLinha.add(new JTextField(sconteudocoluna, 20), gbcComponent);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// colocar o resultado em um frame interno
		// JInternalFrameDetalheLinha.projecaoLinha(projecaoLinha);
		scpDetailsMaster.setViewportView(projecaoLinha);
		// scpDetailsMaster.add(projecaoLinha);
		scpDetailsMaster.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scpDetailsMaster.setPreferredSize(new Dimension(200, 150));
		sptMasterEntitty.setRightComponent(scpDetailsMaster);

		// selecionar os membros da ocorrência selecionada
		obterMembrosRegistroMestre(ptabela, vconteudoRecord);
		repaint();

	}

	private <T> void obterMembrosRegistroMestre(String ptabela, Vector<Object> vconteudoRecord) {
		// inserir lógica de seleção dos registros filhos da ocorrência mestre
		// considerar a passagem de parâmetro só da linha registro

		String titulos[] = {};
		Object[][] arrayRegistros = {};

		List<T> list = new ArrayList<>();
		try {
			list = JPanelMetaDados.obterChavesPrimarias(ptabela);
		} catch (Exception e) {
			System.out.println("Deu errado...");
		}
		
		ImageIcon closeXIcon = new ImageIcon("D:\\git\\workspacegit\\vetor\\src\\com\\resc\\br\\Cancel.gif");
		Dimension closeButtonSize;

		final JPanel content = new JPanel();
		JScrollPane tabscp = new JScrollPane();
		JPanel tab = new JPanel();
		tab.setOpaque(false);

		closeButtonSize = new Dimension(closeXIcon.getIconWidth() + 2, closeXIcon.getIconHeight() + 2);

		JLabel tabLabel = new JLabel("Tab " + (++tabCounter));

		JButton tabCloseButton = new JButton(closeXIcon);
		tabCloseButton.setPreferredSize(closeButtonSize);
		tabCloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int closeTabNumber = tbdPmembersItems.indexOfComponent(content);
				tbdPmembersItems.removeTabAt(closeTabNumber);
			}
		});

		JTable jtablegeneric = new JTable();
		String nomeTabelaFilha = new String();
		tab.add(tabLabel, BorderLayout.WEST);
		tab.add(tabCloseButton, BorderLayout.EAST);
		Iterator<T> ilist = list.iterator();
		while (ilist.hasNext()) { // o ideal será array de titulos e a
									// correspondente jtable
			UtilToGenerics<String, String, String> ug = (UtilToGenerics<String, String, String>) ilist.next();
			nomeTabelaFilha = ug.getTabela();
		}
		// isso deverá ser feito
		Iterator<UtilToGenerics> itabfilha = (Iterator<UtilToGenerics>) list.iterator();
		while (itabfilha.hasNext()) {
			UtilToGenerics<String, String, String> ug = itabfilha.next();
			String tabelafilha = ug.getTabela();
			
			ProcedimentosApoio.jtableMestre = new JTable(
					ProcedimentosApoio.popularJTable(ProcedimentosApoio.conexaoOrigem, tabelafilha));
			// setListenerEventTable();//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<===============================

			ProcedimentosApoio.jtableMestre.setPreferredScrollableViewportSize(new Dimension(300, 200));
			ProcedimentosApoio.jtableMestre.setFillsViewportHeight(true);
			scpMaster = new JScrollPane(ProcedimentosApoio.jtableMestre);
			scpMaster.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "BD Origem",
					TitledBorder.CENTER, TitledBorder.TOP));

			tabscp.add(jtablegeneric); // aqui a jtable deverá conter os
										// registros da tabela filha;
			tbdPmembersItems.addTab(null, content);
			tbdPmembersItems.setTabComponentAt(tbdPmembersItems.getTabCount() - 1, tabscp);
		}
		// tbdPmembersItems.setTabComponentAt(tbdPmembersItems.getTabCount() -
		// 1, tab);

	}

	private void initTabMestreComponent(int i) {
		tbdPmaster.setTabComponentAt(i, new ButtonTabComponent(tbdPmaster));
	}

	private void initTabDetalheComponent(int i) {
		tbdPmembersItems.setTabComponentAt(i, new ButtonTabComponent(tbdPmembersItems));
	}

	public void setScpDetailsMaster(JScrollPane scpDetailsMaster) {
		this.scpDetailsMaster = scpDetailsMaster;
	}

	public void setScpDetailsMembers(JScrollPane scpDetailsMembers) {
		this.scpDetailsMembers = scpDetailsMembers;
	}

	public void setSptMemberEntity(JSplitPane sptMemberEntity) {
		this.sptMemberEntity = sptMemberEntity;
	}

	public void setTbdPmembersItems(JTabbedPane tbdPmembersItems) {
		this.tbdPmembersItems = tbdPmembersItems;
	}

	public void setScpMembersItems(JScrollPane scpMembersItems) {
		this.scpMembersItems = scpMembersItems;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FretusModel fremo = new FretusModel();
					fremo.setFrmVisibilidade(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public static String getTituloTabMestreCorrente(int itab) {

		return tbdPmaster.getTitleAt(itab);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static int numTabMestre = 0;

	protected static int numTabDetalhe = 0;

	protected JTable tMasterCabecalho;

	protected JTable tMembersItems;

	protected JSplitPane sptComposition;

	protected JSplitPane sptMasterEntitty;

	protected static JTabbedPane tbdPmaster;

	protected JPanel projecaoLinha;

	protected JScrollPane scpMaster;

	protected JScrollPane scpDetailsMaster;

	protected JScrollPane scpDetailsMembers;

	protected JSplitPane sptMemberEntity;

	protected JTabbedPane tbdPmembersItems;

	protected JScrollPane scpMembersItems;

	protected JPanel jpnavegadorMetadados;

	protected JSplitPane splitPane;

	private int tabCounter = 0;

}

/*
 * Container janela = getContentPane(); setLayout(null);
 * 
 * //Define os rótulos dos botões JLabel labelCep = new JLabel("CEP: "); JLabel
 * labelTel = new JLabel("Telefone: "); JLabel labelCpf = new JLabel("CPF: ");
 * JLabel labelData = new JLabel("Data: "); labelCep.setBounds(50,40,100,20);
 * labelTel.setBounds(50,80,100,20); labelCpf.setBounds(50,120,100,20);
 * labelData.setBounds(50,160,100,20);
 * 
 * //Define as máscaras MaskFormatter mascaraCep = null; MaskFormatter
 * mascaraTel = null; MaskFormatter mascaraCpf = null; MaskFormatter mascaraData
 * = null;
 * 
 * try{ mascaraCep = new MaskFormatter("#####-###"); mascaraTel = new
 * MaskFormatter("(##)####-####"); mascaraCpf = new
 * MaskFormatter("#########-##"); mascaraData = new MaskFormatter("##/##/####");
 * mascaraCep.setPlaceholderCharacter('_');
 * mascaraTel.setPlaceholderCharacter('_');
 * mascaraCpf.setPlaceholderCharacter('_');
 * mascaraData.setPlaceholderCharacter('_'); } catch(ParseException excp) {
 * System.err.println("Erro na formatação: " + excp.getMessage());
 * System.exit(-1); }
 * 
 * //Seta as máscaras nos objetos JFormattedTextField JFormattedTextField
 * jFormattedTextCep = new JFormattedTextField(mascaraCep); JFormattedTextField
 * jFormattedTextTel = new JFormattedTextField(mascaraTel); JFormattedTextField
 * jFormattedTextCpf = new JFormattedTextField(mascaraCpf); JFormattedTextField
 * jFormattedTextData = new JFormattedTextField(mascaraData);
 * jFormattedTextCep.setBounds(150,40,100,20);
 * jFormattedTextTel.setBounds(150,80,100,20);
 * jFormattedTextCpf.setBounds(150,120,100,20);
 * jFormattedTextData.setBounds(150,160,100,20);
 * 
 * //Adiciona os rótulos e os campos de textos com máscaras na tela
 * janela.add(labelCep); janela.add(labelTel); janela.add(labelCpf);
 * janela.add(labelData); janela.add(jFormattedTextCep);
 * janela.add(jFormattedTextTel); janela.add(jFormattedTextCpf);
 * janela.add(jFormattedTextData); setSize(400, 250);
 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setVisible(true);
 */