package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.opengl.GLES20;

public class Shader 
{
	public static final int POSITION_OFFSET = 0;
	public static final int COLOR_OFFSET = 3;
	public static final int VERTEX_STRIDE = (3 + 4) * 4;
	
	private int m_iProgramId;
	private int m_iVertexShaderId;
	private int m_iFragmentShaderId;
	
	private int m_iPositionId;
	private int m_iColorId;
	
	public Shader(Context ctx)
	{
		String sVertexShader = readFile(ctx, R.raw.vertex_shader);
		String sFragmentShader = readFile(ctx, R.raw.fragment_shader);
		
		m_iVertexShaderId = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
		m_iFragmentShaderId = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
		
		GLES20.glShaderSource(m_iVertexShaderId, sVertexShader);
		GLES20.glShaderSource(m_iFragmentShaderId, sFragmentShader);
		
		GLES20.glCompileShader(m_iVertexShaderId);
		GLES20.glCompileShader(m_iFragmentShaderId);
		
		m_iProgramId = GLES20.glCreateProgram();
		GLES20.glAttachShader(m_iProgramId, m_iVertexShaderId);
		GLES20.glAttachShader(m_iProgramId, m_iFragmentShaderId);
		GLES20.glLinkProgram(m_iProgramId);
		
		//data ids
		m_iPositionId = GLES20.glGetAttribLocation(m_iProgramId, "in_position");
		m_iColorId = GLES20.glGetAttribLocation(m_iProgramId, "in_color");
	}

	public int getPositionId(){ return m_iPositionId; }
	public int getColorId() { return m_iColorId; }	
	
	public void setShader()
	{
		GLES20.glUseProgram(m_iProgramId);
	}
	
	private String readFile(Context ctx, int rsrcId)
	{
		InputStream inputStream = ctx.getResources().openRawResource(rsrcId);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		String nextLine;
		StringBuilder body = new StringBuilder();
		
		try
		{
			while((nextLine = bufferedReader.readLine()) != null)
			{
				body.append(nextLine);
				body.append('\n');
			}
		}
		catch(IOException e)
		{
			return null;
		}
		
		return body.toString();
	}
}
