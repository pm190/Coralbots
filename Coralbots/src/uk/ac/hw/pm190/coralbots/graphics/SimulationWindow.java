package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Patrick Mackinder
 */
public class SimulationWindow extends JFrame implements ItemListener
{
	private static final long serialVersionUID = 1L;
	private JPanel imagePane = new JPanel(new CardLayout());
	
	public SimulationWindow()
	{
	}
	
	public void display(WorldImage worldImage)
	{
		worldImage.visitAttributes();
		JPanel containerPane = new JPanel(new BorderLayout());
		JPanel menuPane = new JPanel();
		
		int numPanels = worldImage.getPanels().size();
		WorldAttribute[] attributes = new WorldAttribute[numPanels];
		List<WorldAttributeDisplay> wads = worldImage.getPanels();
		
		WorldAttributeDisplay wad;
		WorldAttribute attr;
		for(int i = 0; i < numPanels; i++)
		{
			wad = wads.get(i);
			attr = wad.getAttribute();
			attributes[i] = attr;
			imagePane.add(wad, attr.toString());
		}
        
		JComboBox<WorldAttribute> cb = new JComboBox<WorldAttribute>(attributes);
        cb.setEditable(false);
        cb.addItemListener(this);
        menuPane.add(cb);
		
        containerPane.add(menuPane, BorderLayout.NORTH);
		containerPane.add(imagePane, BorderLayout.CENTER);
		add(containerPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setTitle("Simulation Results");
		setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent evt)
	{
		CardLayout cl = (CardLayout)(imagePane.getLayout());
        cl.show(imagePane, ((WorldAttribute)evt.getItem()).toString());
	}
}
