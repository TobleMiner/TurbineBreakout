package de.mvcturbine.breakout.ui.lighthouse;

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
}
