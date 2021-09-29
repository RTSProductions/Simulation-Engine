package Simulation;

import org.lwjgl.opengl.Display;

import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.Renderer;

import Shaders.StaticShader;
import Textures.ModelTexture;

public class MainSimulationLoop {

	public static void main(String[] args) 
	{
		DisplayManager.createDisplay("Simulation");
		
		Renderer renderer = new Renderer();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		
		float[] vertices = 
		{
		      -0.5f, 0.5f, 0f,
		      -0.5f, -0.5f, 0f,
		       0.5f, -0.5f, 0f,
		       0.5f, 0.5f, 0f,
		 };
		        
		 int[] indices = 
		 {
		         0,1,3,
		         3,1,2
		 };
		 
		 float[] textureCoords = 
		 {
				 0, 0,
				 0, 1,
				 1, 1,
				 1, 0
		 };
		
		RawModel model = loader.loadToVAO(vertices, indices, textureCoords);
		ModelTexture texture = new ModelTexture(loader.loadTexture("Test_Texture"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while (!Display.isCloseRequested()) 
		{
			renderer.prepare();
			shader.start();
			
			//Render things here
			renderer.render(texturedModel);
			
			shader.stop();
			DisplayManager.updateDisplay(); 
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
