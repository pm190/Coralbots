package uk.ac.hw.pm190.coralbots.graphics;

import javax.swing.JFrame;

/**
 * 
 * @author Patrick Mackinder
 */
public class RuleBuilderPanelTest
{
	public static void main(String args[])
	{
		JFrame frame = new JFrame();
		frame.add(RuleBuilderPanelFactory.createRuleBuilderPanel());
		frame.setSize(600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
