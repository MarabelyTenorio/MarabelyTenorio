package com.example.test;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

public class MyRenderer implements GLSurfaceView.Renderer
{
	private Model m_model;
	private Shader m_shader;
	private Context m_ctx;
	
	public MyRenderer(Context ctx)
	{
		m_ctx = ctx;
	}
	
	public void onSurfaceCreated(GL10 unused, EGLConfig config)
	{
		GLES20.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		
		m_model = new Model();
		m_shader = new Shader(m_ctx);
	}
	
	public void onDrawFrame(GL10 unused)
	{
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		
		m_model.draw(m_shader);
	}
	
	public void onSurfaceChanged(GL10 unused, int width, int height)
	{
		GLES20.glViewport(0, 0, width, height);
	}
}
