package Simulation;

import org.lwjgl.opengl.Display;

import RenderEngine.DisplayManager;
import RenderEngine.MasterRenderer;

public class MainSimulationLoop {

	public static void main(String[] args) 
	{
		DisplayManager.createDisplay("Simulation");
		
		MasterRenderer renderer = new MasterRenderer();
		
		while (!Display.isCloseRequested()) 
		{
			
			renderer.prepare();
			
			DisplayManager.updateDisplay();
			
		}
		
		DisplayManager.closeDisplay();

	}

}
