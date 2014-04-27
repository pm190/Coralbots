package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Patrick Mackinder
 */
public class RuleBuilderPanelFactory
{
	public static RuleBuilderPanel createRuleBuilderPanel()
	{
		RuleBuilderPanel rbp = new RuleBuilderPanel();
		rbp.init();
		rbp.addRuleFileListListener(new RuleFileChangeListener(rbp));
		return rbp;
	}
}

class RuleFileChangeListener implements ActionListener
{
	private final RuleBuilderPanel rbp;
	
	public RuleFileChangeListener(RuleBuilderPanel rbp)
	{
		this.rbp = rbp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		rbp.changeRuleFile();
	}
}