attribute vec3 in_position;
attribute vec4 in_color;
varying vec4 color;

void main()
{
	gl_Position = vec4(in_position, 1);
	color = in_color;
}