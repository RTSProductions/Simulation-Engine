package Entities;

import java.awt.event.MouseEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera
{
	private Vector3f position = new Vector3f(0, 0 ,0);
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera()
	{
		
	}
	
	public void move()
	{
		float moveAt = 0;
		float moveAt2 = 0;
		
		float speed = 0.02f;
		calculatePitch();
		calculateYaw();
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			speed *= 4;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			moveAt = -speed;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			moveAt = speed;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			moveAt2 = speed;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			moveAt2 = -speed;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_E))
		{
			position.y += speed;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_Q))
		{
			position.y -= speed;
		}
		
		float dx = (float) -(moveAt * Math.sin(Math.toRadians(yaw)));
		float dy = (float) (moveAt * Math.sin(Math.toRadians(pitch)));
		float dz = (float) (moveAt * Math.cos(Math.toRadians(yaw)));
		
		float dx2 = (float) -(moveAt2 * Math.sin(Math.toRadians(yaw)));
		float dy2 = (float) (moveAt2 * Math.sin(Math.toRadians(pitch)));
		float dz2 = (float) -(moveAt2 * Math.cos(Math.toRadians(yaw)));
		
		position.x += (dx + dz2);
		position.y += dy;
		position.z += (dz + dx2);
		
		calculatePitch();
		calculateYaw();
	} 
	
	private void calculatePitch()
	{
		if (Mouse.isButtonDown(1))
		{
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
		}
	}
	private void calculateYaw()
	{
		if (Mouse.isButtonDown(1))
		{
			float yawChange = Mouse.getDX() * 0.1f;
			yaw += yawChange;
		}
	}
	
	public Vector3f getPosition() 
	{
		return position;
	}
	
	public float getPitch() 
	{
		return pitch;
	}
	
	public float getYaw() 
	{
		return yaw;
	}
	
	public float getRoll() 
	{
		return roll;
	}
	
	
}
