
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame implements ActionListener {

	SearchCard previousPanel = null;
	SearchCard currentPanel = null;
	ArrayList<SearchCard> listOfCards = null;
	MainMenu mainMenu = null;
	int number = 0;

	public MainWindow() {
		this.setTitle("PDFsearch");
		this.setSize(1000, 600);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setMenuBar();
		listOfCards = new ArrayList<SearchCard>();
		this.setVisible(true);
	}

	public void setMenuBar() {

		mainMenu = new MainMenu();
		this.setJMenuBar(mainMenu.getJMenuBar());
		mainMenu.getCloseButton().addActionListener(this);
		mainMenu.getAddCardButton().addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent evt) {
		String arg = evt.getActionCommand();
		if (arg.equals("Avslutt"))
			System.exit(0);

		if (arg.equals("Lag et nytt kort")) {

			if (currentPanel != null) {
				currentPanel.setVisible(false);
			}

			SearchCard newSearchCard = new SearchCard("KortNummer " + number++);
			currentPanel = newSearchCard;
			currentPanel.setVisible(true);
			this.add(newSearchCard, BorderLayout.CENTER);
			listOfCards.add(newSearchCard);
			JMenuItem menuItem = new JMenuItem(newSearchCard.getTitle());

			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					Integer index = getIndexOfSwitchedCard(newSearchCard);
					SearchCard searchCardClicked = listOfCards.get(index);
					System.out.println(searchCardClicked.getTitle() + " clicked");

					if (currentPanel != null) {
						currentPanel.setVisible(false);
					}
					currentPanel = searchCardClicked;
					currentPanel.setVisible(true);
				}

				private Integer getIndexOfSwitchedCard(SearchCard searchCard) {
					String t = searchCard.getTitle();
					String lastChar = t.substring(t.length() - 1);
					Integer index = Integer.parseInt(lastChar);
					return index;
				}
			});

			mainMenu.addMenuItem(menuItem);
			this.revalidate();
			this.repaint();
		}
	}

}
