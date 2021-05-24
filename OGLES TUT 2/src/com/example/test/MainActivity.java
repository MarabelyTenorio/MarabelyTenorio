package com.example.test;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity 
{
	private GLSurfaceView m_glView;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		m_glView = new MyGLSurfaceView(this);
		setContentView(m_glView);
	}
}
