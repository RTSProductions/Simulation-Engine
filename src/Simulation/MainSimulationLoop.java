package Simulation;

import org.lwjgl.opengl.Display;

import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.RawModel;
import RenderEngine.Renderer;

public class MainSimulationLoop {

	public static void main(String[] args) 
	{
		DisplayManager.createDisplay("Simulation");
		
		Renderer renderer = new Renderer();
		Loader loader = new Loader();
		
		float[] vertices = 
		{
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
				-0.5f, 0.5f, 0f
		};
		
		int[] indices = 
		{
			0, 1, 3,
			3, 1, 2
		};
		
		RawModel model = loader.loadToVAO(vertices, indices);
		
		while (!Display.isCloseRequested()) 
		{
			renderer.prepare();
			
			renderer.render(model);
			
			DisplayManager.updateDisplay(); 
			
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
