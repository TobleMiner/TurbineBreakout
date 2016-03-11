package de.mvcturbine.breakout.ui.lighthouse;

import java.io.IOException;

import de.mvcturbine.breakout.network.lighthouse.LhNetwork;
import de.mvcturbine.ui.View;

/**
 * Base class for views to display on the lighthouse
 * 
 * @author tsys
 *
 */
public abstract class LightHouseView implements View
{
	/** The lighthouse network interface */
	protected final LhNetwork lighthouse;

	/**
	 * Constructs a new lighthouse view with the given network interface
	 * 
	 * @param lighthouse
	 *            The lighthouse network interface
	 */
	public LightHouseView(LhNetwork lighthouse)
	{
		this.lighthouse = lighthouse;
	}

	/**
	 * Tries to connect to the lighthouse
	 * 
	 * @param host
	 *            The host to connect to
	 * @param port
	 *            The port to connect to
	 * @return true if connection was established successfully
	 */
	public boolean connect(String host, int port)
	{
		try
		{
			this.lighthouse.connect(host, port);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
