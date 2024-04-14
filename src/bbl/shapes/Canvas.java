package bbl.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import bbl.util.Arrays;

public class Canvas extends Shape implements Iterable<Shape>
{

	public Shape[] shapes;
	public Canvas(long id, Shape[] shapes) 
	{
		super(id);	
		this.shapes=Arrays.copy(shapes);
	}
	
	public Shape getShape(long id) 
	{
		// data about an employee with a given id value
		// if the company doesn't have such employee, then return null
		
		int index = Arrays.lineSearch(shapes, a->a.getId()==id);
		return index<0 ? null: shapes[index];
	}
	public Shape[] addShape(Shape shape)
	{
		int index=Arrays.lineSearch(shapes, a->a.getId()==shape.id);
		if(index>=0)
		{
			throw new IllegalStateException( String.format("An shape with id=(%d) alredy in canvas",shape.getId()) );
		}
		shapes=Arrays.add(shapes, shape);
		return shapes;
	}
	public Shape[] removeShape(long id)
	{
		Shape shp= getShape(id);
		if(shp==null)
		{
			throw new NoSuchElementException( String.format("Shape id=(%d) not found",id) );
		}
		shapes=Arrays.removeIf(shapes, a->a.getId()==id);
		return shapes;
	}

	@Override
	public int square()
	{
		int sum=0;
		for(Shape shp: shapes)
		{
			sum=sum+shp.square();
		}		
		return sum;
	}

	@Override
	public int perimeter() {
		int sum=0;
		for(Shape shp: shapes)
		{
			sum=sum+shp.perimeter();
		}		
		return sum;

	}

	@Override
	public Iterator<Shape> iterator() {
		return new CanvasIterator();
	}
	
	private class CanvasIterator implements Iterator<Shape>
	{

		private int current=0;
		//iterating all employees in the ascending order of the ID values
		@Override
		public boolean hasNext()
		{
			return shapes.length<0? false:current<shapes.length;
		}

		@Override
		public Shape next() 
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			return shapes[current++];
		}
		
	}
	
}
