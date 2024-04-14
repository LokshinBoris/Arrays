package bbl.shapes;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bbl.employees.Company;
import bbl.employees.Employee;



class ShapeTest {
	private static final long ID1 = 1;
	private static final int  W1 = 10;
	private static final int  H1 = 50;
	private static final long ID2 = 2;
	private static final int  H2 = 50;
	private static final long ID3 = 3;
	private static final long ID4 = 4;
	private static final long ID5 = 5;
	private static final long ID6 = 6;
	
	Rectangle shp1=new Rectangle(ID1,W1,H1);
	Square shp2=new Square(ID2,H2);
	Canvas shp3=new Canvas(ID3, new Shape[] {shp1,shp2} );
	Rectangle shp4=new Rectangle(ID4,W1,H2);
	Canvas shp5=new Canvas(ID5, new Shape[] {} );
	
	Canvas canvas;
	@BeforeEach
	void setCanvas()
	{
		canvas = new Canvas(ID6, new Shape[] {shp1, shp2, shp3, shp4, shp5 });
	}
	
	@Test
	void addShapeTest()
	{
		assertThrowsExactly(IllegalStateException.class,() -> canvas.addShape(shp2));
		Square shp6 = new Square(29,1 );
		Canvas expectedCanvas = new Canvas(11,new Shape[] {shp1,shp2,shp3,shp4,shp5,shp6});
		canvas.addShape(shp6);
		assertArrayEquals(expectedCanvas.shapes, canvas.shapes);
	}

	@Test 
	void getShapeTest()
	{
		assertTrue(shp3.equals(canvas.getShape((long)3)));
	}
	
	@Test
	void removeShapeTest()
	{
		assertThrowsExactly(NoSuchElementException.class,() -> canvas.removeShape(200));
		Shape[] expectedShap1 = new Shape[] {shp1, shp2, shp4, shp5 };
		canvas.removeShape(ID3);
		assertArrayEquals(expectedShap1, canvas.shapes);
	}
	
	@Test
	void squareAndPerimetrTest()
	{
		assertEquals(500,shp1.square());
		assertEquals(120,shp1.perimeter());
		assertEquals(2500,shp2.square());
		assertEquals(200,shp2.perimeter());
		assertEquals(3000,shp3.square());
		assertEquals(320,shp3.perimeter());
		assertEquals(500,shp4.square());
		assertEquals(120,shp4.perimeter());
		assertEquals(0,shp5.square());
		assertEquals(0,shp5.perimeter());
		shp5.addShape(shp3);
		shp5.addShape(shp4);
		assertEquals(3500,shp5.square());
		assertEquals(440,shp5.perimeter());
	}
	
	
}
