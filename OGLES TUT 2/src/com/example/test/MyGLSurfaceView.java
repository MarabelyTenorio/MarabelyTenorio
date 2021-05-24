package com.example.test;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceView extends GLSurfaceView
{
	private MyRenderer m_renderer;
	
	public MyGLSurfaceView(Context context)
	{
		super(context);
		
		setEGLContextClientVersion(2);
		m_renderer = new MyRenderer(context);
		setRenderer(m_renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

}
