import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SearchResultPanel extends JPanel implements ActionListener {

	private File selectedFolder;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JTextField searchText;
	private ArrayList<String> listOfPdfs;

	public SearchResultPanel() {
		setLayout(new GridLayout(1, 1));

		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
		leftPanel.setBorder(border);
		add(leftPanel);
		add(new JScrollPane(leftPanel));

		rightPanel = new JPanel();
		searchText = new JTextField(10);
		rightPanel.add(searchText);
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		rightPanel.add(searchButton);

		Border border2 = BorderFactory.createLineBorder(Color.BLACK, 5);
		rightPanel.setBorder(border2);
		add(rightPanel);
	}

	public void setSelectedFolder(File folder) {
		this.selectedFolder = folder;
	}

	public void actionPerformed(ActionEvent evt) {
		String arg = evt.getActionCommand();

		System.out.println(searchText.getText());
	}

	public void display() {
		if (listOfPdfs.size() > 0) {
			for (String pdfName : listOfPdfs) {
				System.out.println(pdfName);
				JLabel pdfNameLabel = createLabel(pdfName);

				leftPanel.add(pdfNameLabel);
			}
		} else {
			JLabel noRes = new JLabel("No Results");

			leftPanel.add(noRes);
		}
	}

	public void searchPdfsInFolder() {
		final File folder = this.selectedFolder;
		listOfPdfs = new ArrayList<String>();
		File directory = folder;
		File[] fList = directory.listFiles();
		getPDFs(fList);

	}

	private void getPDFs(File[] fList) {

		if (fList != null) {
			for (File file : fList) {

				if (file.isFile()) {

					if (file.getName().endsWith(".pdf")) {

						String pdfName = file.getAbsolutePath();

						listOfPdfs.add(pdfName);
						System.out.println(pdfName);
						leftPanel.add(createLabel(pdfName));
						revalidate();
						repaint();
					}
				}

				else if (file.isDirectory()) {
					getPDFs(file.listFiles());
				}

			}
		}

	}

	private JLabel createLabel(String pdfName) {

		JLabel l = new JLabel(pdfName);
		return l;
	}

	public void clearLeftPanel() {
		this.leftPanel.removeAll();
	}

}