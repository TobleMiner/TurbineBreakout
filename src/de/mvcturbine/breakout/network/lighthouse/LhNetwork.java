package de.mvcturbine.breakout.network.lighthouse;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import de.mvcturbine.util.config.KVPairParser;
import de.mvcturbine.util.geom.Size2D;

public class LhNetwork implements Runnable
{
	private static boolean SIMULATION = true;

	private static int WIDTH = 28;
	private static int HEIGHT = 14;
	private static int FPS = 30;
	private static String KV_SEPARATOR = "=";

	private Socket lightSocket;
	private BufferedReader input;
	private DataOutputStream output;
	private Thread nwThread;

	private boolean connected = false;

	private byte[] frame;

	private int frameBuffNum = FPS / 2;

	private KVPairParser parser;

	public LhNetwork()
	{
		this.frame = new byte[HEIGHT * WIDTH * 3];
		this.parser = new KVPairParser(KV_SEPARATOR);
	}

	public void connect(String host, int port) throws IOException
	{
		this.lightSocket = new Socket(host, port);
		this.input = new BufferedReader(
				new InputStreamReader(this.lightSocket.getInputStream()));
		this.output = new DataOutputStream(lightSocket.getOutputStream());
		this.nwThread = new Thread(this);
		this.nwThread.start();
		this.connected = true;
	}

	public boolean connected()
	{
		return this.lightSocket != null && this.connected &&
				this.lightSocket.isConnected();
	}

	private boolean checkConnenction()
	{
		if(this.lightSocket == null) return false;
		if(this.lightSocket.isClosed() || !this.lightSocket.isConnected()) return false;
		return true;
	}

	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				String str = this.input.readLine();
				System.out.println(str);
				String[] kvpair = this.parser.getKV(str);
				switch(kvpair[0])
				{
					case ("bufLen"):
						int bufferedFrames = Integer.parseInt(kvpair[1]);
						if(bufferedFrames <= this.frameBuffNum || SIMULATION)
						{
							sendFrame();
						}
						break;
					default:
						System.out.println("Unknow kv data: " + str);
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
				if(!checkConnenction())
				{
					System.out.println("Lighthouse closed the connection");
					this.connected = false;
					break;
				}
			}
			catch(IllegalArgumentException e)
			{
				System.out.println("Received non KV data: " + e.getMessage());
			}
		}
	}

	synchronized public boolean sendFrame()
	{
		if(!this.connected) return false;
		try
		{
			this.output.write(this.frame, 0, this.frame.length);
			return true;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public void setFrame(BufferedImage img)
	{
		for(int y = 0; y < img.getHeight(); y++)
		{
			for(int x = 0; x < img.getWidth(); x++)
			{
				int color = img.getRGB(x, y);
				this.frame[(y * img.getWidth() + x) *
						3] = (byte) ((color & 0xFF0000) >> 16);
				this.frame[(y * img.getWidth() + x) * 3 +
						1] = (byte) ((color & 0x00FF00) >> 8);
				this.frame[(y * img.getWidth() + x) * 3 + 2] = (byte) (color & 0x0000FF);
			}
		}
	}

	public Size2D getSize()
	{
		return new Size2D(WIDTH, HEIGHT);
	}
}
