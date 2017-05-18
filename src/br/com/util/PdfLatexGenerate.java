package br.com.util;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PdfLatexGenerate {
	// "C:\Program Files\MiKTeX 2.9\miktex\bin\x64\pdflatex.exe"
	// C:\Program Files\MiKTeX 2.9.5823\miktex\bin\x64\pdflatex.exe
	// -output-directory "C:\Eig\Lehre\Info2\ImagesTemp"
	// "C:\Eig\Lehre\Info2\ImagesTemp\graph.tex"
	private static final String strpdflatexcompiler = "C:\\Program Files\\MiKTeX 2.9\\miktex\\bin\\x64\\pdflatex.exe -max-print-line=120 -interaction=nonstopmode \"%wm\"";

	static JFileChooser fileChooser = new JFileChooser();

	private static String FILENAME = "E:\\WITHME\\TEMPLATE\\diagramatransacao.tex";

	final private static int BUFFER_SIZE = 1024;

	private static Vector<String> vlinhastex = new Vector<String>();

	public static void main(String args[]) {

		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLayout(new BorderLayout());

		String LATEX_EXPRESSION_1 = "4$A=\\(\\array{3,c.cccBCCC$&1&2&3\\\\\\hdash~1&a_{11}&a_{12}&a_{13}\\\\2&a_{21}&a_{22}&a_{23}\\\\3&a_{31}&a_{32}&a_{33}}\\) ";
		byte[] imageData1 = getLaTeXImage(LATEX_EXPRESSION_1);
		JLabel button1 = new JLabel(new ImageIcon(imageData1));
		jframe.add(button1, BorderLayout.NORTH);

		String LATEX_EXPRESSION_2 = "4$\\array{rccclBCB$&f&\\longr[75]^{\\alpha:{-1$f\\rightar~g}}&g\\\\3$\\gamma&\\longd[50]&&\\longd[50]&3$\\gamma\\\\&u&\\longr[75]_\\beta&v}";
		byte[] imageData2 = getLaTeXImage(LATEX_EXPRESSION_2);
		JLabel button2 = new JLabel(new ImageIcon(imageData2));
		jframe.add(button2, BorderLayout.CENTER);

		String LATEX_EXPRESSION_3 = "4$\\hspace{5}\\unitlength{1}\\picture(175,100){~(50,50){\\circle(100)}(1,50){\\overbrace{\\line(46)}^{4$\\;\\;a}}(52,50) {\\line(125)}~(50,52;115;2){\\mid}~(52,55){\\longleftar[60]}(130,56){\\longrightar[35]}~(116,58){r}~(c85,50;80;2){\\bullet} (c85,36){3$-q}~(c165,36){3$q}(42,30){\\underbrace{\\line(32)}_{1$a^2/r\\;\\;\\;}}~}";
		byte[] imageData3 = getLaTeXImage(LATEX_EXPRESSION_3);
		JLabel button3 = new JLabel(new ImageIcon(imageData3));
		jframe.add(button3, BorderLayout.SOUTH);

		String s = null;

		try {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Selecionar Arquivo");
			jframe.getContentPane().add(fileChooser);
			fileChooser.setVisible(true);
			String abspathtofile = "";
			int result = fileChooser.showOpenDialog(jframe);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				abspathtofile = selectedFile.getAbsolutePath();
			}

			if (!abspathtofile.equals("")) {
				FILENAME = (abspathtofile.replace("\\", "\\\\"));
			}

			readTexFile();

			fileChooser.setDialogTitle("Selecione o aplicativo renderizador");
			jframe.getContentPane().add(fileChooser);
			fileChooser.setVisible(true);
			String abspathtorender = "";
			result = fileChooser.showOpenDialog(jframe);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				abspathtorender = selectedFile.getAbsolutePath();
			}

			System.out.println("começando...");

			Process p = null;

			if (!abspathtorender.equals("")) {
				abspathtorender = (abspathtorender.replace("\\", "\\\\")).trim();

				p = Runtime.getRuntime().exec(abspathtorender + " " + abspathtofile);
			} else {
				p = Runtime.getRuntime().exec(strpdflatexcompiler + " " + abspathtofile);
			}

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			System.out.println("Amostra de erros :\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println("(======The long Testa ===================:>)");
				System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Por:\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
			System.out.println("Fim dos erros...");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			System.exit(-1);
		}
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
	}

	public static byte[] getLaTeXImage(String latexString) {
		byte[] imageData = null;
		try {
			// mimetex is asked (on the command line) to convert
			// the LaTeX expression to .gif on standard output:
			Process proc = Runtime.getRuntime().exec(strpdflatexcompiler + " -d \"" + latexString + "\"");
			// get the output stream of the process:
			BufferedInputStream bis = (BufferedInputStream) proc.getInputStream();
			// read output process by bytes blocks (size: BUFFER_SIZE)
			// and stores the result in a byte[] Arraylist:
			int bytesRead;
			byte[] buffer = new byte[BUFFER_SIZE];
			ArrayList<byte[]> al = new ArrayList<byte[]>();
			while ((bytesRead = bis.read(buffer)) != -1) {
				al.add(buffer.clone());
			}
			// convert the Arraylist in an unique array:
			int nbOfArrays = al.size();
			if (nbOfArrays == 1) {
				imageData = buffer;
			} else {
				imageData = new byte[BUFFER_SIZE * nbOfArrays];
				byte[] temp;
				for (int k = 0; k < nbOfArrays; k++) {
					temp = al.get(k);
					for (int i = 0; i < BUFFER_SIZE; i++) {
						imageData[BUFFER_SIZE * k + i] = temp[i];
					}
				}
			}
			bis.close();
			proc.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageData;
	}

	private static void readTexFile() {

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				vlinhastex.addElement(sCurrentLine);
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	protected void initUI() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif", "PDF", "pdf");
		JFrame frame = new JFrame("test");
		Container cp = frame.getContentPane();
		cp.setLayout(new BorderLayout());
		final JButton chooseFile = new JButton("Select file...");

		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("Arquivo escolhido: " + chooser.getSelectedFile().getName());
		}

		chooser.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			        System.out.println("Action");

			      }
			    });
			    
		cp.add(chooseFile);
		frame.pack();
		frame.setVisible(true);
	}
}
