package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import uk.ac.hw.pm190.coralbots.simulation.Rule;
import uk.ac.hw.pm190.coralbots.simulation.Rules;

/**
 * 
 * @author Patrick Mackinder
 */
public class RuleBuilderPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private final JComboBox<String> ruleFileList;
	private final JPanel rulesPanel = new JPanel(new BorderLayout());
	private final JPanel west = new JPanel(new BorderLayout());
	private final JPanel center = new JPanel(new BorderLayout());
	
	protected RuleBuilderPanel()
	{
		setLayout(new BorderLayout());
		JPanel ruleFilesPanel = new JPanel();
		ruleFilesPanel.setLayout(new BoxLayout(ruleFilesPanel, BoxLayout.LINE_AXIS));
		ruleFilesPanel.add(new JLabel("File:"));
		File rulesFolder = new File("test/resources");
		File[] files = rulesFolder.listFiles();
		String[] ruleFileNames = new String[files.length];
		for(int i = 0; i < files.length; i++)
		{
			ruleFileNames[i] = files[i].getAbsolutePath();
		}
		ruleFileList = new JComboBox<String>(ruleFileNames);
		ruleFilesPanel.add(ruleFileList);
		
		rulesPanel.setBorder(BorderFactory.createEmptyBorder(
                5, //top
                0,     //left
                5, //bottom
                0));   //right
		rulesPanel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
		
		rulesPanel.add(center, BorderLayout.CENTER);
		
		add(ruleFilesPanel, BorderLayout.NORTH);
		add(rulesPanel, BorderLayout.CENTER);
	}
	
	public void init()
	{
		changeRuleFile();
	}
	
	public void addRuleFileListListener(ActionListener listener)
	{
		ruleFileList.addActionListener(listener);
	}
	
	public void changeRuleFile()
	{
		west.removeAll();
		RuleListPanel ruleListPanel = new RuleListPanel(getRulesForFile(ruleFileList.getSelectedItem().toString()), this);
		west.add(ruleListPanel, BorderLayout.CENTER);
		west.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.EAST);
		rulesPanel.add(west, BorderLayout.WEST);
		if(ruleListPanel.getSelectedRule() != null)
		{
			ruleChanged(ruleListPanel.getSelectedRule());
		}
		this.validate();
	}
	
	private Collection<Rule> getRulesForFile(String filePath)
	{
		Collection<Rule> rules = null;
		try
		{
			rules = Rules.parseRulesFile(new File("resources/rules.xsd"), new File(filePath));
		}
		catch(SAXException | IOException | ParserConfigurationException e)
		{
			//TODO display suitable error
		}
		return rules;
	}
	
	public void ruleChanged(Rule rule)
	{
		if(rule != null)
		{
			center.removeAll();
			center.add(new RulePanel(rule), BorderLayout.CENTER);
			rulesPanel.add(center, BorderLayout.CENTER);
		}
		this.validate();
	}
	
	public static Component leftJustify(JLabel label)  
	{
	    Box  b = Box.createHorizontalBox();
	    b.add(label);
	    b.add(Box.createHorizontalGlue());
	    return b;
	}
	
	public void saveRules(Collection<Rule> collection)
	{
		Rules.writeRules(new File("resources/rules.xsd"), new File((String) ruleFileList.getSelectedItem()), collection);
	}
}
