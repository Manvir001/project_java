
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class MainMenu extends JPanel {

	JMenuBar mbar = null;
	JMenuItem closeButton = null;
	JMenuItem addCardButton = null;
	JMenu tools = null;

	public MainMenu() {

		mbar = new JMenuBar();
		JMenu m = new JMenu("Fil");
		tools = new JMenu("Verktøy");

		closeButton = new JMenuItem("Avslutt");
		addCardButton = new JMenuItem("Lag et nytt kort");

		m.add(closeButton);
		tools.add(addCardButton);
		tools.add(new JSeparator());

		mbar.add(m);
		mbar.add(tools);
	}

	public JMenuBar getJMenuBar() {
		return mbar;
	}

	public JMenuItem getCloseButton() {
		return closeButton;
	}

	public JMenuItem getAddCardButton() {
		return addCardButton;
	}

	public void addMenuItem(JMenuItem item) {
		tools.add(item);
	}

}
