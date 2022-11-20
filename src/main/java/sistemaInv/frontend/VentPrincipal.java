package sistemaInv.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void inicializarComponentes()
	{
		JPanel panelIzq = new JPanel();
		JTabbedPane panelPrinc = new JTabbedPane(JTabbedPane.LEFT);
		Statusbar panelInf = new Statusbar();
		
		panelPrinc.addTab(null, new JPanel());
		panelPrinc.setTabComponentAt(0, new JLabel("hello", UIManager.getIcon("OptionPane.informationIcon"), SwingConstants.CENTER));
		
		panelIzq.setBackground(Color.BLACK);
		//gridC.weightx = 0.2;
		//gridC.fill = GridBagConstraints.BOTH;
		
		JButton btn = new JButton("Abrir");
		btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		panelIzq.add(btn);
		panelInf.añadir(new JLabel("test1"));
		panelInf.añadir(new JLabel("0.0.1"));
		
		//getContentPane().add(panelIzq, BorderLayout.WEST);
		getContentPane().add(panelPrinc, BorderLayout.CENTER);
		getContentPane().add(panelInf, BorderLayout.SOUTH);
	}
}
