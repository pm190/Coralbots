package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import uk.ac.hw.pm190.coralbots.simulation.CellContentType;
import uk.ac.hw.pm190.coralbots.simulation.Pattern;
import uk.ac.hw.pm190.coralbots.simulation.XYPair;

/**
 * 
 * @author Patrick Mackinder
 */
public class PatternPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private final PatternCellPanel[][] panels = new PatternCellPanel[3][3];
	
	public PatternPanel(Pattern pattern)
	{
		JPanel contents = new JPanel(new GridLayout(3,3));
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				PatternCellPanel panel = new PatternCellPanel(pattern.getCellContentType(new XYPair(i, j)));
				contents.add(panel, i, j);
				panels[i][j] = panel;
			}
		}
		add(contents);
	}
	
	public Pattern getCurrentPattern()
	{
		CellContentType[][] cells = new CellContentType[3][3];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				cells[i][j] = panels[i][j].getSelectedContent();
			}
		}
		return new Pattern(cells);
	}
}

class PatternCellPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private final JComboBox<CellContentType> list;
	
	public PatternCellPanel(CellContentType content)
	{
		List<CellContentType> contents = Arrays.asList(new CellContentType[] {
			CellContentType.WATER, 
			CellContentType.CORAL,
			CellContentType.ROCK});
		list = new JComboBox<CellContentType>(contents.toArray(new CellContentType[]{}));
		list.setSelectedIndex(contents.indexOf(content));
		add(list);
	}
	
	public CellContentType getSelectedContent()
	{
		return (CellContentType) list.getSelectedItem();
	}
}
