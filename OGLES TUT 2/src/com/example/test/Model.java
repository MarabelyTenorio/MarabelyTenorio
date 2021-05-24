package com.example.test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;

public class Model 
{
	private FloatBuffer m_vertexBuffer;
	
	static float vertices[] = 
	{
		-1.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
		 1.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f,
		 0.0f,  1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f
	};
	
	private int m_iNumVertices = 3;
	
	public Model()
	{
		ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
		bb.order(ByteOrder.nativeOrder());
		
		m_vertexBuffer = bb.asFloatBuffer();
		m_vertexBuffer.put(vertices);
		m_vertexBuffer.position(0);
	}
	
	public void draw(Shader shader)
	{
		shader.setShader();
		
		m_vertexBuffer.position(Shader.POSITION_OFFSET);
		int posId = shader.getPositionId();
		GLES20.glEnableVertexAttribArray(posId);
		GLES20.glVertexAttribPointer(posId, 3, GLES20.GL_FLOAT, false, 
				                     Shader.VERTEX_STRIDE, m_vertexBuffer);
		
		m_vertexBuffer.position(Shader.COLOR_OFFSET);
		int colorId = shader.getColorId();
		GLES20.glEnableVertexAttribArray(colorId);
		GLES20.glVertexAttribPointer(colorId, 4, GLES20.GL_FLOAT, false, 
				                     Shader.VERTEX_STRIDE, m_vertexBuffer);
		
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, m_iNumVertices);
		
		GLES20.glDisableVertexAttribArray(posId);
		GLES20.glDisableVertexAttribArray(colorId);
	}
}
