package sistemaInv.backend;

import sistemaInv.frontend.*;

import su.interference.core.*;
import su.interference.persistent.*;

public class Principal {

	public static void main(String[] args)
	{
		new VentPrincipal();
	}
	
	public void iniciarDB()
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
