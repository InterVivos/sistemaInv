package sistemaInv.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class VentPrincipal extends JFrame
{
	public VentPrincipal()
	{
		super();
		
		inicializarComponentes();
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void inicializarComponentes()
	{
		JTabbedPane panelPrinc = new JTabbedPane(JTabbedPane.LEFT);
		Statusbar panelInf = new Statusbar();
		
		panelPrinc.addTab(null, new JPanel());
		panelPrinc.setTabComponentAt(0, new JLabel("hello", UIManager.getIcon("OptionPane.informationIcon"), SwingConstants.CENTER));
		
		añadirPestaña(panelPrinc, new JPanel(), "Inventario", "resources/package-regular-48.png");
		añadirPestaña(panelPrinc, new JPanel(), "Ventas", "resources/purchase-tag-solid-48.png");
		añadirPestaña(panelPrinc, new TestDB(), "Test", "resources/test-tube-regular-48.png");
		//panelTest.setDividerLocation(0.5);
		
		//gridC.weightx = 0.2;
		//gridC.fill = GridBagConstraints.BOTH;
		
		panelInf.añadir(new JLabel("test1"));
		panelInf.añadir(new JLabel("0.0.1"));
		
		//getContentPane().add(panelIzq, BorderLayout.WEST);
		getContentPane().add(panelPrinc, BorderLayout.CENTER);
		getContentPane().add(panelInf, BorderLayout.SOUTH);
	}
	
	private void añadirPestaña(JTabbedPane panel, Container contenido, String titulo, String direccionIcono)
	{
		panel.addTab(null, contenido);
		JLabel lbl = new JLabel(titulo, new ImageIcon(direccionIcono), SwingConstants.CENTER);
		lbl.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.setTabComponentAt(panel.getTabCount() - 1, lbl);
	}
}
