
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchCard extends JPanel implements ActionListener {

	private Color color;
	private String title;
	private File folder;
	private JLabel selectedFolderLabel;
	private JPanel searchCardMenu;
	private SearchResultPanel searchResultPanel;

	public SearchCard(String title) {
		setTitle(title);
		setLayout(new BorderLayout());
		searchCardMenu = new JPanel();
		searchCardMenu.setLayout(new GridLayout(3, 2));
		searchCardMenu.setSize(600, 100);
		JLabel lab1 = new JLabel(title, JLabel.CENTER);
		searchCardMenu.add(lab1);
		JButton myButton = new JButton("Velg Mappe");
		searchCardMenu.add(myButton);
		add(searchCardMenu, BorderLayout.NORTH);
		setButtonAction(myButton);
		searchResultPanel = new SearchResultPanel();
		searchResultPanel.setVisible(false);
		add(searchResultPanel);
		Color randomColor = RandomColorGenerator.getRandomColor();
		color = randomColor;
		searchResultPanel.setBackground(color);
		searchCardMenu.setBackground(color);

	}

	private void setButtonAction(JButton myButton) {
		myButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (selectedFolderLabel != null) {
					searchCardMenu.remove(selectedFolderLabel);
				}
				selectedFolderLabel = new JLabel("Please wait ....", JLabel.LEFT);
				searchCardMenu.add(selectedFolderLabel);
				selectedFolderLabel.setVisible(false);
				myButton.setVisible(false);

				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File(""));
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(myButton);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File yourFolder = fc.getSelectedFile();
					System.out.println(yourFolder);
					searchResultPanel.setVisible(true);
					searchResultPanel.clearLeftPanel();
					folder = yourFolder;

					if (selectedFolderLabel != null) {
						searchCardMenu.remove(selectedFolderLabel);
					}

					selectedFolderLabel = new JLabel("Valgt mappe: " + folder.getAbsolutePath(), JLabel.LEFT);

					searchResultPanel.setSelectedFolder(folder);

					Thread t1 = new Thread(new Runnable() {
						@Override
						public void run() {

							searchResultPanel.searchPdfsInFolder();
						}
					});
					t1.start();
					searchCardMenu.add(selectedFolderLabel);
					revalidate();
					repaint();
				}

			}
		});
	}

	public Color getColor() {
		return color;
	}

	public void setTitle(String s) {
		this.title = s;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

}