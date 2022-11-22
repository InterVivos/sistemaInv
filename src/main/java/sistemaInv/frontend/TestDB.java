package sistemaInv.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.TableColumn;

import su.interference.core.Config;

public class TestDB extends JSplitPane {

	public TestDB()
	{
		super(JSplitPane.VERTICAL_SPLIT);
		
		String[] tablas = Config.getConfig().REGISTER_CLASSES;
		String[] tablas1 = new String[tablas.length];
		LinkedList<String>[] camposTabla = new LinkedList[tablas.length]; 
		int i = 0;
		for (String tabla : tablas)
		{
			try
			{
				Class clase = Class.forName(tabla);
				Field[] campos = clase.getDeclaredFields();
				camposTabla[i] = new LinkedList<String>();
				for(Field campo : campos)
				{
					System.out.println(campo.getName());
					camposTabla[i].add(campo.getName());
				}
				
				tablas1[i] = clase.getSimpleName();
				++i;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			tabla = tabla.substring(tabla.lastIndexOf('.')+1);
			//System.out.println(tabla);
		}
		add(new Insertador(tablas1, camposTabla));
		add(new Visor(tablas1, camposTabla));
		
	}
}

class Insertador extends JPanel
{
	JTextArea comandos = new JTextArea();
	
	public Insertador(String[] tablas, LinkedList<String>[] campos)
	{
		super(new GridBagLayout());
		GridBagConstraints gridC = new GridBagConstraints();
		gridC.anchor = GridBagConstraints.WEST;
		
		
		JComboBox<String> tablasDb = new JComboBox<String>(tablas);
		add(new JLabel("Seleccionar tabla: "), gridC);
		++gridC.gridy;
		add(tablasDb, gridC);
		++gridC.gridy;
		add(Box.createVerticalStrut(16), gridC);
		gridC.gridwidth = 2;
		gridC.fill = GridBagConstraints.HORIZONTAL;
		for (String campo : campos[0])
		{
			JLabel txtLbl = new JLabel(campo);
			JTextField txt = new JTextField();
			++gridC.gridy;
			add(txtLbl, gridC);
			++gridC.gridy;
			add(txt, gridC);
		}
		gridC.fill = GridBagConstraints.BOTH;
		gridC.gridy = 2;
		gridC.gridheight = 3;
		gridC.insets = new Insets(0,16,0,0);
		add(new JButton("Insertar"), gridC);
		
		//gridC.gridy += 1;
		JButton ejecutar = new JButton("Ejecutar");
		ejecutar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sistemaInv.backend.OperacionesDB.ejecutarComando(comandos.getText());
			}
		});
		add(ejecutar, gridC);
		
		gridC.gridy = 6;
		gridC.gridx += 3;
		gridC.gridwidth = 4;
		gridC.gridheight = 6;
		add(new JScrollPane(comandos), gridC);
	}
}

class Visor extends JPanel
{
	public Visor(String[] tablas, LinkedList<String>[] campos)
	{
		super(new GridBagLayout());
		GridBagConstraints gridC = new GridBagConstraints();
		
		JComboBox<String> tablasDrop = new JComboBox<String>(tablas);
		
		add(new JLabel("Seleccionar tabla: "), gridC);
		++gridC.gridy;
		add(tablasDrop, gridC);
		
		++gridC.gridy;
		add(Box.createVerticalStrut(16), gridC);
		
		++gridC.gridy;
		gridC.gridwidth = 4;
		gridC.fill = GridBagConstraints.BOTH;
		gridC.weightx = 0.5;
		gridC.weighty = 0.9;
		JTable datos = new JTable();
		
		for (String campo : campos[0])
		{
			TableColumn encabezado = new TableColumn();
			encabezado.setHeaderValue(campo);
			datos.getColumnModel().addColumn(encabezado);
		}
		
		JScrollPane visorTabla = new JScrollPane(datos);
		add(visorTabla, gridC);
	}
}