package Simulation;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.OBJLoader;
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
		
		RawModel model = OBJLoader.loadObjModel("Pumpkin", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("Pumpkin_Texture"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -4), 0, 0, 0, 1);
		
		Camera camera = new Camera();
		
		while (!Display.isCloseRequested()) 
		{
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			
			//Render things here
			renderer.render(entity, shader);
			camera.move();
			entity.increaseRotation(0, 1, 0);
			
			shader.stop();
			DisplayManager.updateDisplay(); 
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
