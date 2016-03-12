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
	/**
	 * Buffering on the lighthouse simulation is strange. Just keep hammering
	 * frames at it
	 */
	private boolean simulation = false;

	/** Width of the lighthouse display */
	private static int WIDTH = 28;
	/** Height of the lighthouse display */
	private static int HEIGHT = 14;
	/** Frames per second on the lighthouse */
	private static int FPS = 30;
	/** KV pair separator on lighthouse messages */
	private static String KV_SEPARATOR = "=";

	/** TCP socket to use for lighthouse communication */
	private Socket lightSocket;
	/** Reader to buffer up a line of text from the lighthouse */
	private BufferedReader input;
	/** Stream to write my frames to */
	private DataOutputStream output;
	/** Thread to run network stuff on */
	private Thread nwThread;

	/** Connection status */
	private boolean connected = false;

	/** Buffer to hold the current frame */
	private byte[] frame;

	/** Number of frames to buffer */
	private int frameBuffNum = FPS / 3;

	/** Parser for lighthouse responses */
	private KVPairParser parser;

	/**
	 * Constructs a new lighthouse network worker
	 */
	public LhNetwork()
	{
		this.frame = new byte[HEIGHT * WIDTH * 3];
		this.parser = new KVPairParser(KV_SEPARATOR);
	}

	/**
	 * Connect to the lighthouse at {@code host}:{@code port} (Supports IPv6!
	 * (Better than TeamSpeak!))
	 * 
	 * @param host
	 *            Host to connect to
	 * @param port
	 *            Port to connect to
	 * @throws IOException
	 *             If a connection error occurred
	 */
	public void connect(String host, int port) throws IOException
	{
		this.lightSocket = new Socket(host, port);
		this.input = new BufferedReader(
				new InputStreamReader(this.lightSocket.getInputStream()));
		this.output = new DataOutputStream(this.lightSocket.getOutputStream());
		this.nwThread = new Thread(this);
		this.nwThread.start();
		this.connected = true;
	}

	public boolean tryConnect(String host, int port)
	{
		try
		{
			connect(host, port);
			return true;
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * Checks if we are still connected
	 * 
	 * @return true if connection is still established
	 */
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
				String[] kvpair = this.parser.getKV(str);
				switch(kvpair[0])
				{
					case ("bufLen"):
						int bufferedFrames = Integer.parseInt(kvpair[1]);
						System.out.format("%d %s buffered\n", bufferedFrames,
								bufferedFrames == 1 ? "frame" : "frames");
						int missingFrames = this.frameBuffNum - bufferedFrames;
						if(this.simulation) missingFrames = 1;
						for(int i = 0; i < missingFrames; i++)
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

	/**
	 * Sends a frame to the lighthouse
	 * 
	 * @return true if frame was successfully sent
	 */
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

	/**
	 * Set frame and convert it from BGR to RGB
	 * 
	 * @param img
	 *            The image to load into the frame buffer
	 */
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

	/**
	 * Returns the size of the lighthouse
	 * 
	 * @return the size of the lighthouse
	 */
	public Size2D getSize()
	{
		return new Size2D(WIDTH, HEIGHT);
	}

	/**
	 * @return the simulation
	 */
	public boolean isSimulation()
	{
		return simulation;
	}

	/**
	 * @param simulation
	 *            the simulation to set
	 */
	public void setSimulation(boolean simulation)
	{
		this.simulation = simulation;
	}
}
