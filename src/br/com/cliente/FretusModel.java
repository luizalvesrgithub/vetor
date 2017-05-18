package br.com.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import br.com.util.GuiasOpcoes;
import br.com.util.JPanelMetaDados;
import br.com.util.JSptConexao;
import br.com.util.JSptConsultas;
import br.com.util.JSptMigracao;
import br.com.util.JSptModelosGraficos;
import br.com.util.JSptMontadorAlgebrico;
import br.com.util.JSptRelacionamentoSemantico;
import br.com.util.ProcedimentosApoio;
import br.com.util.TunicaGeral;
import model.Conexao;
import test.java.demo.*;

public class FretusModel extends ProcedimentosApoio {

	public FretusModel() {

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

		JSplitPane splitFrame = new JSplitPane();
		splitFrame.setOrientation(JSplitPane.VERTICAL_SPLIT);
		JPanel panelNorte = new JPanel();
		panelNorte.setAutoscrolls(true);
		panelNorte.setLayout(new BorderLayout());

		JPanel panelCentroSul = new JPanel();
		panelCentroSul.setAutoscrolls(true);
		panelCentroSul.setLayout(new BorderLayout());

		splitFrame.setLeftComponent(panelNorte);
		splitFrame.setRightComponent(panelCentroSul);

		frm.getContentPane().add(splitFrame);

		go = new GuiasOpcoes();

		go.setAutoscrolls(true);

		JSplitPane panelLogin = new JSplitPane();
		panelLogin.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		loginPanel(panelLogin);

		go.addTab("Login de Uso", panelLogin);

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
		frm.setTitle(psptcon.obterNomeCliente() + " — FretusModel Minerador \u2014Migra\u00E7\u00E3o de Dados");
		// jsptconsulta = new JSptConsultas();
		// jmg = new JSptModelosGraficos();
		// jma = new JSptMontadorAlgebrico();
		// jrs = new JSptRelacionamentoSemantico();
		// esgex = new EstruturaGlobalExtracao();
		tg = new TunicaGeral();

		JPanel panel = new JPanel();
		// frm.getContentPane().add(go, BorderLayout.NORTH);
		panelNorte.add(go);

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
		// frm.getContentPane().add(tbdMacrofuncoes, BorderLayout.CENTER);
		tbdMacrofuncoes.setAutoscrolls(true);

		panelCentroSul.add(new JScrollPane(tbdMacrofuncoes));
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

		// GuiaTabPane gtp = new GuiaTabPane(nomeTabela, ++numTab,
		// tg.tbdPmaster, atualScrollPane);
		tg.setScrollPaneMestre(pscpMaster, nomeTabela);
		
	}

	public static void atualizarTabsDetalhe(JScrollPane pscpDetalhe, String nomeTabela) {

		// GuiaTabPane gtp = new GuiaTabPane(nomeTabela, ++numTab,
		// tg.tbdPmaster, atualScrollPane);
		tg.setScrollPaneMestre(pscpDetalhe, nomeTabela);

	}

	private static void loginPanel(JSplitPane panelLogin) {

		final JTable leftTable = new JTable(new SimpleColorTableModel());
		PicPanel panelDireito = new PicPanel("\\com\\resc\\br\\FretusModel.jpg");
		JPanel panelEsquerdo = new JPanel();
		TableModel tmodel = leftTable.getModel();

		leftTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Object o = e.getSource().getClass();
				int linhas[] = leftTable.getSelectedRows();// getSelectedRowCount();
				int colunas[] = leftTable.getSelectedColumns();
				conectarBancoSelecionado(linhas, colunas);
			}
		});

		panelEsquerdo.setBounds(0, 0, 300, 300);
		panelEsquerdo.setPreferredSize(new Dimension(300, 300));
		panelDireito.setLayout(null);
		leftTable.setFillsViewportHeight(true);
		leftTable.setDefaultRenderer(Color.class, new ColorTableCellRenderer());

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fretusmodel", "root",
					"LARmysql07101957");
			try {
				Statement stmt = con.createStatement();
				ResultSet rst = stmt.executeQuery("SELECT * from fretusmodel.conexao");
				while (rst.next()) {
					Conexao conex = new Conexao();
					conex.setBancodedados(rst.getString("bancodedados"));
					conex.setDriverbanco(rst.getString("driverbanco"));
					conex.setBancoconexao(rst.getString("bancoconexao"));
					conex.setUsuario(rst.getString("usuario"));
					((SimpleColorTableModel) tmodel).addRow(new Object[] { conex.getBancoconexao(),
							conex.getDriverbanco(), conex.getBancodedados(), conex.getUsuario(), Color.CYAN });
				}
			} catch (SQLException e) {
				System.out.println("SQLException " + e.getMessage());
			} finally {
				con.close();
			}
		} catch (Exception e) {

		}

		
		JTableHeader theader = leftTable.getTableHeader();
		theader.setBackground(Color.orange);
		for(int i = 0; i < leftTable.getColumnCount(); i++) {
			tcol = leftTable.getColumnModel().getColumn(i);
			tcol.setCellRenderer(new CustomTableCellRenderer());
		}
		
		JScrollPane scpconexoes = new JScrollPane(leftTable);
		scpconexoes.setBounds(0, 0, 300, 300);
		panelEsquerdo.add(scpconexoes);

		panelLogin.setLeftComponent(panelEsquerdo);
		panelLogin.setRightComponent(panelDireito);

		JLabel userLabel = new JLabel("Usuário");

		userLabel.setBounds(10, 20, 80, 25);
		panelDireito.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panelDireito.add(userText);

		JLabel passwordLabel = new JLabel("Senha");
		passwordLabel.setBounds(10, 50, 80, 25);
		panelDireito.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 50, 165, 25);
		panelDireito.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 86, 80, 25);
		panelDireito.add(loginButton);
		
		JLabel lblWarming = new JLabel("\u00C9 poss\u00EDvel selecionar mais de um banco para conex\u00E3o.");
		//..................(x   ,y , width, height)
		lblWarming.setBounds(110, 86, 259, 25);
		panelDireito.add(lblWarming);

		//imagemFundoPanel(panelDireito);

	}

	protected static void conectarBancoSelecionado(int[] linha, int[] colunas) {
		// Fazer conexão com a senha do usuário

	}

	public static JPanel imagemFundoPanel(JPanel panelDireito) {
		//ImagePanel imagePanel = new ImagePanel(new ImageIcon("/com/resc/br/FretusModel.jpg").getImage());
	
	    PicPanel mainPanel = new PicPanel("\\com\\resc\\br\\FretusModel.jpg");
	    mainPanel.setLayout(null);
	    mainPanel.setBounds(0,0,500,500);
		
	    
		panelDireito.add(mainPanel);
		return panelDireito;
	}

	private static JTabbedPane getTbdMacrofuncoes() {
		return tbdMacrofuncoes;
	}

	public static Connection restaurarConexaoDestino() {

		return psptcon.conexaoDestino;
	}

	private static void setTbdMacrofuncoes(JTabbedPane tbdMacrofuncoes) {

		FretusModel.tbdMacrofuncoes = tbdMacrofuncoes;
	}

	public static void mostrarProjecaoRegistroMestre(Vector<String> vtituloscolunas, Vector<String> vtiposdecolunas,
			Vector<Object> vconteudoRecord, String ptabela) {

		tg.projecaoLinhaRegistro(vtituloscolunas, vtiposdecolunas, vconteudoRecord, ptabela);
		tg.setScpDetailsMembers(Testing.jpninJscp(contentPane, vtituloscolunas, vtiposdecolunas, vconteudoRecord));
		tg.repaint();
		
		
	}

	private JFrame getFrm() {
		return frm;
	}

	private void setFrm(JFrame frm) {
		this.frm = frm;
	}
	
	public void setFrmVisibilidade(boolean visibilidade) {
		this.frm.setVisible(visibilidade);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FretusModel fremo = new FretusModel();
					fremo.frm.setVisible(true);
					contentPane = fremo.frm.getContentPane();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	
	private static JTabbedPane tbdMacrofuncoes = new JTabbedPane(JTabbedPane.TOP);
	
	private JFrame frm;
	private static Container contentPane;
	private GuiasOpcoes go;
	private static JSptConexao psptcon;
	private JSptMigracao jmigr;
	private JSptConsultas jsptconsulta;
	private JSptModelosGraficos jmg;
	private JSptMontadorAlgebrico jma;
	private JSptRelacionamentoSemantico jrs;
	// private EstruturaGlobalExtracao esgex;
	private static TunicaGeral tg;
	public static int numTab;
}

class ImagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image img;

	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}

class SimpleColorTableModel extends DefaultTableModel {

	
	private static final long serialVersionUID = 1L;

	public SimpleColorTableModel() {

		addColumn("Banco de Dados");
		addColumn("Driver");
		addColumn("Banco Conexão");
		addColumn("Usuário");
		addColumn("Status");

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		Class clazz = String.class;

		switch (columnIndex) {

		case 1:
			clazz = Color.class;
			break;

		}
		return clazz;
	}
}

class ColorTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		setText(null);
		if (value instanceof Color) {

			setOpaque(true);
			setBackground((Color) value);

		}
		return this;
	}

	public static void setLineTableRenderColor(JTable table, int index) {
		if (index % 2 == 0) {
			table.setGridColor(new Color(204,255,229));
		} else {
			table.setBackground(new Color(255,226,204));
		}

	}

}

class CustomTableCellRenderer extends DefaultTableCellRenderer {
	
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
		if (isSelected) {
			cell.setBackground(Color.green);
		} else {
			if (row % 2 == 0) {
				cell.setBackground(new Color(204,255,229));
			} else {
				cell.setBackground(new Color(255,226,204));
			}
		}
		return cell;
	}
}

class PicPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
    private int w,h;
    public PicPanel(String fname){

        //reads the image
        try {
            image = ImageIO.read(new File(fname));
            w = image.getWidth();
            h = image.getHeight();

        } catch (IOException ioe) {
            System.out.println("Não posso ler o arquivo.");
            //System.exit(0);
        }

    }

    public Dimension getPreferredSize() {
        return new Dimension(w,h);
    }
    //this will draw the image
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}


