package sistemaInv.backend;

import sistemaInv.frontend.*;

import su.interference.core.*;
import su.interference.persistent.*;

public class Principal {

	public static void main(String[] args)
	{
		Principal princ = new Principal();
		boolean correcto = princ.iniciarDB();
		if (correcto) new VentPrincipal();
	}
	
	public boolean iniciarDB1()
	{
		return true;
	}
	
	public boolean iniciarDB()
	{
		try
		{
			Instance instancia = Instance.getInstance();
			final Session sesion = Session.getSession();
			Runtime.getRuntime().addShutdownHook(new Thread(() ->
			{
				try
				{
					instancia.shutdownInstance();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				System.out.println("Stopped");
			}));
			
			sesion.setUserId(Session.ROOT_USER_ID);
			instancia.startupInstance(sesion);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
