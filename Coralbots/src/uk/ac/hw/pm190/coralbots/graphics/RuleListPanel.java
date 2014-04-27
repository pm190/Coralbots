package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.hw.pm190.coralbots.simulation.Rule;

/**
 * 
 * @author Patrick Mackinder
 */
public class RuleListPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private DefaultListModel<RuleNamePair> ruleListModel = new DefaultListModel<RuleNamePair>();
	private final JList<RuleNamePair> ruleList;
	private final Map<String, Rule> ruleMap = new HashMap<String, Rule>();
	private int counter = 1;

	public RuleListPanel(Collection<Rule> rules, final RuleBuilderPanel rbp)
	{
		JPanel ruleListPanel = new JPanel();
		ruleListPanel.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				RuleNamePair rp = new RuleNamePair("New Rule " + counter++, new Rule());
				ruleListModel.addElement(rp);
				ruleMap.put(rp.getName(), rp.getRule());
			}
		});
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(ruleList.getSelectedIndex() > -1)
				{
					ruleMap.remove(ruleList.getSelectedValue().getName());
					ruleListModel.remove(ruleList.getSelectedIndex());
				}
			}
		});
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				rbp.saveRules(ruleMap.values());
			}
		});
		buttonPanel.add(add);
		buttonPanel.add(delete);
		buttonPanel.add(save);
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
		listPanel.add(RuleBuilderPanel.leftJustify(new JLabel("Rules")));
		List<RuleNamePair> rulePairs = new ArrayList<RuleNamePair>();
		if(rules != null)
		{
			int ruleNumber = 1;
			for(Rule rule : rules)
			{
				RuleNamePair rulePair = new RuleNamePair("Rule " + ruleNumber++, rule);
				ruleListModel.addElement(rulePair);
				ruleMap.put(rulePair.getName(), rulePair.getRule());
			}
		}
		else
		{
			rulePairs.add(new RuleNamePair("No valid rules", null));
		}
		ruleList = new JList<RuleNamePair>(ruleListModel);
		ruleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ruleList.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if(!e.getValueIsAdjusting())
				{
					if(ruleList.getSelectedValue() != null)
					{
						rbp.ruleChanged(ruleList.getSelectedValue().getRule());
					}
				}
			}
		});
		ruleList.setSelectedIndex(0);
		listPanel.add(ruleList);
		
		ruleListPanel.add(buttonPanel, BorderLayout.NORTH);
		ruleListPanel.add(listPanel, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(ruleListPanel);
	}
	
	public Rule getSelectedRule()
	{
		if(ruleList != null && ruleList.getSelectedValue() != null)
		{
			return ruleList.getSelectedValue().getRule();
		}
		return null;
	}
}
