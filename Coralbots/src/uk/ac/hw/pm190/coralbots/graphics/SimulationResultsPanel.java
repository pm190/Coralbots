package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimulationResultsPanel extends JPanel implements ItemListener
{	
	private static final long serialVersionUID = 1L;
	private final CardLayout cardLayout = new CardLayout();
	private final JPanel imagePane = new JPanel(cardLayout);
	
	public SimulationResultsPanel(WorldImage worldImage, final CoralbotsApplicationWindow coralbotsWindow)
	{
		worldImage.visitAttributes();
		JPanel menuPane = new JPanel();
		
		int numPanels = worldImage.getPanels().size();
		WorldAttribute[] attributes = new WorldAttribute[numPanels];
		List<WorldAttributeVisitor> wads = worldImage.getPanels();
		
		WorldAttributeVisitor wav;
		WorldAttribute attr;
		for(int i = 0; i < numPanels; i++)
		{
			wav = wads.get(i);
			attr = wav.getAttribute();
			attributes[i] = attr;
			imagePane.add(wav, attr.toString());
		}
        
		JComboBox<WorldAttribute> cb = new JComboBox<WorldAttribute>(attributes);
        cb.setEditable(false);
        cb.addItemListener(this);
        menuPane.add(cb);
		
        JPanel topPane = new JPanel();
        topPane.setLayout(new BoxLayout(topPane, BoxLayout.LINE_AXIS));
        JTextField score = new JTextField();
        score.setEditable(false);
        float result = worldImage.getWorld().getRating();
        score.setText(String.format("Score: %.2f%%", result));
        
        topPane.add(score);
        topPane.add(menuPane);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createHorizontalGlue());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				coralbotsWindow.showPanel(ApplicationPanel.SIMULATION);
			}
        });
        buttonPane.add(backButton);
        
        setLayout(new BorderLayout());
        add(topPane, BorderLayout.NORTH);
        
        JPanel imagePaneContainer = new JPanel();
        imagePaneContainer.setLayout(new BoxLayout(imagePaneContainer, BoxLayout.LINE_AXIS));
        imagePaneContainer.add(Box.createHorizontalGlue());
        imagePaneContainer.add(imagePane);
        imagePaneContainer.add(Box.createHorizontalGlue());
        
		add(imagePaneContainer, BorderLayout.CENTER);
		add(buttonPane, BorderLayout.SOUTH);
	}

	@Override
	public void itemStateChanged(ItemEvent evt) 
	{
        cardLayout.show(imagePane, ((WorldAttribute)evt.getItem()).toString());		
	}
}
