package de.mvcturbine.breakout.ui.lighthouse;

import java.io.IOException;

import de.mvcturbine.breakout.network.lighthouse.LhNetwork;
import de.mvcturbine.ui.View;

public abstract class LightHouseView implements View
{
	protected final LhNetwork lighthouse;

	public LightHouseView(LhNetwork lighthouse)
	{
		this.lighthouse = lighthouse;
	}

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
