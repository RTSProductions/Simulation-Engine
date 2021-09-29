package Simulation;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
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
		
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		Loader loader = new Loader();
		
		float[] vertices = {			
				-0.5f,0.5f,0,	
				-0.5f,-0.5f,0,	
				0.5f,-0.5f,0,	
				0.5f,0.5f,0,		
				
				-0.5f,0.5f,1,	
				-0.5f,-0.5f,1,	
				0.5f,-0.5f,1,	
				0.5f,0.5f,1,
				
				0.5f,0.5f,0,	
				0.5f,-0.5f,0,	
				0.5f,-0.5f,1,	
				0.5f,0.5f,1,
				
				-0.5f,0.5f,0,	
				-0.5f,-0.5f,0,	
				-0.5f,-0.5f,1,	
				-0.5f,0.5f,1,
				
				-0.5f,0.5f,1,
				-0.5f,0.5f,0,
				0.5f,0.5f,0,
				0.5f,0.5f,1,
				
				-0.5f,-0.5f,1,
				-0.5f,-0.5f,0,
				0.5f,-0.5f,0,
				0.5f,-0.5f,1
				
		};
		
		float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};
		
		
		RawModel model = loader.loadToVAO(vertices, indices, textureCoords);
		ModelTexture texture = new ModelTexture(loader.loadTexture("Test_Texture"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -4), 0, 0, 0, 1);
		
		Camera camera = new Camera();
		
		float amount = -0.1f;
		
		while (!Display.isCloseRequested()) 
		{
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			
			//Render things here
			renderer.render(entity, shader);
			camera.move();
			entity.increaseRotation(1, 1, 1);
			
			shader.stop();
			DisplayManager.updateDisplay(); 
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
