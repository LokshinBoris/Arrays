package bbl.shapes;

import bbl.shapes.exceptions.*;
import bbl.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class Page implements Iterable<Shape>
{
	public Shape[] shapes = new Shape[0];
	public void addShape(Shape shape)
	{
		if (Arrays.indexOf(shapes, shape) > -1)
		{
			throw new ShapeAlreadyExistsException(shape.getId());
		}
		shapes = Arrays.add(shapes, shape);
	}
	public void addShape(Long[] canvasIds, Shape shape) 
	{
		Canvas canvas = getCanvas(canvasIds);
		canvas.addShape(shape);
	}
	private Canvas getCanvas(Long[] canvasIds)
	{
		Canvas canvas = getCanvasById(shapes, canvasIds[0]);
		for(int i = 1; i < canvasIds.length; i++) {
			canvas = getCanvasById(canvas.shapes, canvasIds[i]);
		}
		return canvas;
	}
	
	private Canvas getCanvasById(Shape[] shapes, Long id)
	{
		Canvas cnv= new Canvas(id.longValue(), new Shape[0]);
		int index = Arrays.indexOf(shapes, cnv);
		
		if(index < 0)
		{
			throw new ShapeNotFoundException(id.longValue());
		}
		
		Shape shape = shapes[index];
		Canvas result = null;
		if (shape instanceof Canvas)
		{
			result = (Canvas)shape;
		} 
		else
		{
			throw new NoCanvasException(id.longValue());
		}
		return result;
	}
	
	public Shape removeShape(long id)
	{
		int index=Arrays.indexOf(shapes, new Square(id,0));
		if(index<0)
		{
			throw new ShapeNotFoundException(id);
		}
		Shape shp= shapes[index];
		shapes=Arrays.removeIf(shapes, a->a.getId()==id);
		return shp;	
	}
	
	
	public Shape removeShape(Long[] canvasIds, long id)
	{
		Canvas canvas = getCanvas(canvasIds);
		int index=Arrays.indexOf(canvas.shapes, new Square(id,0));
		if(index<0)
		{
			throw new ShapeNotFoundException(id);
		}
		Shape shp= canvas.shapes[index];
		canvas.shapes=Arrays.removeIf(canvas.shapes, a->a.getId()==id);
		return shp;
	}
	
	@Override
	public Iterator<Shape> iterator() {
		
		return new PageIterator();
	}
	private class PageIterator implements Iterator<Shape>
	{

		private int current=0;
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
