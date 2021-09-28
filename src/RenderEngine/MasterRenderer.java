package RenderEngine;

import org.lwjgl.opengl.GL11;

public class MasterRenderer 
{	
	public void prepare() 
	{
 		GL11.glClearColor(0, 0, 0.2f, 1); // old color: 0.5f, 0, 1, 1
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
	}

}
