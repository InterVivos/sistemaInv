package sistemaInv.frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class Statusbar extends JPanel
{
	private Component espaciado = Box.createHorizontalStrut(2);
	
	public Statusbar()
	{
		//super(new GridBagLayout());
		super(new FlowLayout(FlowLayout.RIGHT, 8, 2));
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		
		super.add(Box.createHorizontalGlue());
		super.add(espaciado);
	}
	
	public void añadir(JComponent componente)
	{
		if(getComponentCount()/2 >= 1)
		{
			JSeparator sep = new JSeparator(JSeparator.VERTICAL);
			sep.setPreferredSize(new Dimension(1,22));
			super.add(sep);
		}
		super.add(componente);
		setComponentZOrder(espaciado, getComponentCount() - 1);
	}
}
