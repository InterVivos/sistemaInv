package sistemaInv.frontend;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import su.interference.core.Config;
import su.interference.persistent.Session;

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
					//System.out.println(campo.getName());
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
	LinkedList<String>[] campos;
	GridBagConstraints gridC = new GridBagConstraints();
	
	public Insertador(String[] tablas, LinkedList<String>[] campos)
	{
		super(new GridBagLayout());
		
		this.campos = campos;
		
		gridC.anchor = GridBagConstraints.WEST;
		
		
		JComboBox<String> tablasDb = new JComboBox<String>(tablas);
		add(new JLabel("Seleccionar tabla: "), gridC);
		++gridC.gridy;
		add(tablasDb, gridC);
		++gridC.gridy;
		add(Box.createVerticalStrut(16), gridC);
		gridC.gridwidth = 2;
		gridC.fill = GridBagConstraints.HORIZONTAL;
		
		tablasDb.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED) cambiarCampos(tablasDb.getSelectedIndex());
			}
		});
		
//		for (String campo : campos[0])
//		{
//			JLabel txtLbl = new JLabel(campo);
//			JTextField txt = new JTextField();
//			++gridC.gridy;
//			add(txtLbl, gridC);
//			++gridC.gridy;
//			add(txt, gridC);
//		}
		gridC.fill = GridBagConstraints.BOTH;
		gridC.gridy = 2;
		gridC.gridx = 2;
		gridC.gridheight = 3;
		gridC.insets = new Insets(0,16,0,0);
		JButton insertar = new JButton("insertar");
		insertar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				insertarBtn(tablasDb.getSelectedItem().toString(), tablasDb.getSelectedIndex());
			}
		});
		add(insertar, gridC);
		
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
		gridC.gridx = 4;
		add(ejecutar, gridC);
		
		gridC.gridy = 6;
		gridC.gridx = 2;
		gridC.gridwidth = 4;
		gridC.gridheight = 16;
		gridC.weighty = 0.9;
		add(new JScrollPane(comandos), gridC);
		gridC.weighty = 0;
		
		cambiarCampos(0);
	}
	
	private void cambiarCampos(int indice)
	{
		int numComponentes = (getComponentCount() - 6)/2;
		int numComponentesActual = numComponentes;
		//System.out.printf("numComp %d\n", numComponentes);
		//if(numComponentes == campos[indice].size()) return;
		if(numComponentes < campos[indice].size()) numComponentes = campos[indice].size();
		gridC.gridx = 0;
		gridC.gridy = 2;
		gridC.gridwidth = 2;
		gridC.gridheight = 1;
		gridC.insets = new Insets(0,0,0,0);
		gridC.fill = GridBagConstraints.HORIZONTAL;
		//System.out.printf("array size %d\n", campos[indice].size());
		int i;
		for (i = 0; i < numComponentes; ++i)
		{
			if( i >= campos[indice].size())
			{
				//System.out.printf("removed %s\n", this.getComponent(campos[indice].size()+5).toString());
				remove(getComponentCount() - 1);
				//System.out.printf("removed %s\n", this.getComponent(campos[indice].size()+5).toString());
				remove(getComponentCount() - 1);
			}
			else if(i < numComponentesActual)
			{
				//System.out.printf("label %d with %d, componetes%d\n",i, i*2+3, numComponentesActual);
				((JLabel)getComponent(i*2+6)).setText(campos[indice].get(i));
				gridC.gridy+=2;
			}
			else
			{
				JLabel txtLbl = new JLabel(campos[indice].get(i));
				JTextField txt = new JTextField();
				++gridC.gridy;
				add(txtLbl, gridC);
				++gridC.gridy;
				add(txt, gridC);
			}
			this.repaint();
		}
//		if(this.getComponentCount() <= 6)
//		{
//			for (String campo : campos[indice])
//			{
//				JLabel txtLbl = new JLabel(campo);
//				JTextField txt = new JTextField();
//				++gridC.gridy;
//				add(txtLbl, gridC);
//				++gridC.gridy;
//				add(txt, gridC);
//			}
//		}
//		else
//		{
//			
//		}
	}
	
	private void insertarBtn(String objeto, int indice)
	{
		objeto = "sistemaInv.db." + objeto;
		int numComponentes = (getComponentCount()-6)/2;
		Class clase;
		Object objetoClase;
		try
		{
			clase = Class.forName(objeto);
			objetoClase = Session.getSession().newEntity(clase, new Object[]{});
		}
		catch (ClassNotFoundException | IllegalArgumentException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		String[] cont = new String[campos[indice].size()];
		for(int i = 0; i < numComponentes; ++i)
		{
			cont[i] = ((JTextField)getComponent(i*2+7)).getText();
		}
		try
		{
			Method metodo = clase.getMethod("insertarDatos", String[].class);
			metodo.invoke(objetoClase, (Object)cont);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		sistemaInv.backend.OperacionesDB.insertarEnTabla(objetoClase);
	}
}

class Visor extends JPanel
{
	LinkedList<String>[] campos;
	JComboBox<String> tablasDrop;
	JTable datos = new JTable();
	
	/**
	 * @param tablas
	 * @param campos
	 */
	public Visor(String[] tablas, LinkedList<String>[] campos)
	{
		super(new GridBagLayout());
		GridBagConstraints gridC = new GridBagConstraints();
		this.campos = campos;
		
		tablasDrop = new JComboBox<String>(tablas);
		tablasDrop.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				cambiarTabla(tablasDrop.getSelectedIndex());
			}
		});
		
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
		
		cambiarTabla(0);
		/*
		 * for (String campo : campos[0]) { TableColumn encabezado = new TableColumn();
		 * encabezado.setHeaderValue(campo);
		 * datos.getColumnModel().addColumn(encabezado); }
		 */
		
		JScrollPane visorTabla = new JScrollPane(datos);
		add(visorTabla, gridC);
	}
	
	private void cambiarTabla(int indice)
	{
		datos.setModel(new DefaultTableModel(campos[indice].toArray(), 0));
		datos.getTableHeader().setReorderingAllowed(false);
	}
}