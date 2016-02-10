package land.sebastianwie.shopware_uploadtool.uiNew.tabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import land.sebastianwie.shopware_uploadtool.uiNew.Controller;

public class InfoTab extends Tab {
	private static final long serialVersionUID = -7567826011920478738L;
	private static final String infoTxt;

	private JTextArea info;
	private JScrollPane scrollPane;
	private JButton saveButton;
	private Controller c;

	private static final String INFO_FILE = System.getProperty("user.home") + "/.shopwareUploadToolInfo.txt";

	static {
		String tmp = "";
		try {
			tmp = new String(Files.readAllBytes(Paths.get(INFO_FILE)));
		} catch (Exception e) {
			// e.printStackTrace();
			tmp = "Fehler - Keine Infodatei (~/.shopwareUploadToolInfo) vorhanden.";
		}
		infoTxt = tmp;
	}

	@Override
	public boolean isCloseable() {
		return true;
	}

	@Override
	public String getTitle() {
		return "Information";
	}

	@Override
	public void lock() {
		return;
	}

	@Override
	public void unlock() {
		return;
	}

	@Override
	public JButton getCancelButton() {
		return null;
	}

	public InfoTab(Controller co) {
		super.setOpaque(true);
		super.setVisible(true);

		this.c = co;

		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);

		info = new JTextArea(infoTxt);
		info.setEditable(false);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		info.setTabSize(4);

		scrollPane = new JScrollPane(info);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		saveButton = new JButton("Import-Template erstellen");

		layout.putConstraint(SpringLayout.SOUTH, saveButton, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, saveButton, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, saveButton, -10, SpringLayout.EAST, this);
		super.add(saveButton);

		layout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, saveButton);
		layout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, saveButton);
		layout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.NORTH, saveButton);
		super.add(scrollPane);

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.makeTemplate();
			}
		});
	}

}
