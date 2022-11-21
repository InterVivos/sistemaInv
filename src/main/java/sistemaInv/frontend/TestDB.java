package sistemaInv.frontend;

import java.awt.*;
import java.lang.reflect.Field;

import javax.swing.*;

import su.interference.core.Config;

public class TestDB extends JSplitPane {

	public TestDB()
	{
		super(JSplitPane.VERTICAL_SPLIT);
		
		String[] tablas = Config.getConfig().REGISTER_CLASSES;
		String[] tablas1 = new String[tablas.length];
		int i = 0;
		for (String tabla : tablas)
		{
			try
			{
				Class clase = Class.forName(tabla);
				Field[] campos = clase.getDeclaredFields();
				for(Field campo : campos)
				{
					System.out.println(campo.getName());
				}
				
				tablas1[i] = clase.getSimpleName();
				++i;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			tabla = tabla.substring(tabla.lastIndexOf('.')+1);
			System.out.println(tabla);
		}
		
		add(new Insertador(tablas1));
		add(new Visor());
		
	}
}

class Insertador extends JPanel
{
	public Insertador(String[] tablas)
	{
		super(new GridBagLayout());
		GridBagConstraints gridC = new GridBagConstraints();
		gridC.anchor = GridBagConstraints.WEST;
		
		
		JComboBox<String> tablasDb = new JComboBox<String>(tablas);
		add(new JLabel("Seleccionar tabla: "), gridC);
		++gridC.gridy;
		add(tablasDb, gridC);
		gridC.gridy = 1;
		++gridC.gridx;
		add(Box.createVerticalStrut(16), gridC);
	}
}

class Visor extends JPanel
{
	public Visor()
	{
		super(new GridBagLayout());
		GridBagConstraints gridC = new GridBagConstraints();
		
		JComboBox<String> tablas = new JComboBox<String>(new String[]{"Hola", "Adios"});
		add(tablas, gridC);
		
		++gridC.gridy;
		gridC.fill = GridBagConstraints.HORIZONTAL;
		JTable datos = new JTable();
		add(datos, gridC);
	}
}