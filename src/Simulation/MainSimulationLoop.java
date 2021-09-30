package Simulation;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
import Entities.Light;
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
		
		RawModel pumpkinModel = OBJLoader.loadObjModel("Pumpkin", loader);
		ModelTexture pumpkinTexture = new ModelTexture(loader.loadTexture("Pumpkin_Texture"));
		TexturedModel pumpkinTexturedModel = new TexturedModel(pumpkinModel, pumpkinTexture);
		Entity pumpkin = new Entity(pumpkinTexturedModel, new Vector3f(0, 0, -4), 0, 0, 0, 1);
		
		RawModel planeModel = OBJLoader.loadObjModel("Plane", loader);
		ModelTexture planeTexture = new ModelTexture(loader.loadTexture("Plane_Texture"));
		TexturedModel planeTexturedModel = new TexturedModel(planeModel, planeTexture);
		
		Entity plane = new Entity(planeTexturedModel, new Vector3f(0, -0.8f, -4), 0, 0, 0, 10);
		
		Light light = new Light(new Vector3f(0, 0, -2), new Vector3f(1, 1, 1));
		
		Camera camera = new Camera();
		
		while (!Display.isCloseRequested()) 
		{
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			shader.loadLight(light);
			
			//Render things here
			renderer.render(pumpkin, shader);
			renderer.render(plane, shader);
			camera.move();
			pumpkin.increaseRotation(0, 1, 0);
			light.moveTo(camera.getPosition());
			
			shader.stop();
			DisplayManager.updateDisplay(); 
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
