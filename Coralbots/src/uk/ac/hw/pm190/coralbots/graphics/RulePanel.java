package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import uk.ac.hw.pm190.coralbots.simulation.CellContentType;
import uk.ac.hw.pm190.coralbots.simulation.PatternCellLocation;
import uk.ac.hw.pm190.coralbots.simulation.Rule;

/**
 * 
 * @author Patrick Mackinder
 */
public class RulePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private final PatternPanel upperPatternPanel;
	private final PatternPanel lowerPatternPanel;
	private final JComboBox<PatternCellLocation> locs = new JComboBox<PatternCellLocation>(PatternCellLocation.values());
	private final JComboBox<CellContentType> types = new JComboBox<CellContentType>(new CellContentType[] {
			CellContentType.WATER,
			CellContentType.CORAL,
			CellContentType.ROCK
	});
	
	public RulePanel(Rule rule)
	{
		setLayout(new BorderLayout());
		JPanel contents = new JPanel();
		contents.setLayout(new BoxLayout(contents, BoxLayout.PAGE_AXIS));
		upperPatternPanel = new PatternPanel(rule.getUpperPattern());
		lowerPatternPanel = new PatternPanel(rule.getLowerPattern());
		JPanel changePanel = new JPanel(new FlowLayout());
		changePanel.add(new JLabel("Change location"));
		
		locs.setSelectedItem(rule.getCellToChange());
		changePanel.add(locs);
		changePanel.add(new JLabel("to"));
		types.setSelectedItem(rule.getChangeType());
		changePanel.add(types);
		
		contents.add(RuleBuilderPanel.leftJustify(new JLabel("Upper Pattern")));
		contents.add(upperPatternPanel);
		contents.add(new JSeparator(JSeparator.HORIZONTAL));
		contents.add(RuleBuilderPanel.leftJustify(new JLabel("Lower Pattern")));
		contents.add(lowerPatternPanel);
		contents.add(new JSeparator(JSeparator.HORIZONTAL));
		contents.add(RuleBuilderPanel.leftJustify(new JLabel("Cell Modification")));
		contents.add(changePanel);
		add(contents);
	}
	
	public Rule getCurrentRule()
	{
		return new Rule(upperPatternPanel.getCurrentPattern(), lowerPatternPanel.getCurrentPattern(), (PatternCellLocation)locs.getSelectedItem(), (CellContentType)types.getSelectedItem());
	}
}
