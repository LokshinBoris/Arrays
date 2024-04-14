package bbl.shapes;

public class Rectangle extends Shape
{
	private int width;
	private int height;
	public Rectangle(long id, int width, int height)
	{
		super(id);
		this.width = width;
		this.height = height;
	}
	@Override
	public int square()
	{
		return this.width*this.height;
	}
	@Override
	public int perimeter()
	{
		return 2*(this.width+this.height);
	}
	
}
