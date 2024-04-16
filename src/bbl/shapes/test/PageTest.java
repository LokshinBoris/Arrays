package bbl.shapes.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import bbl.shapes.*;
import bbl.shapes.exceptions.*;

class PageTest {
	private static final long ID1 = 1;
	private static final int  W1 = 10;
	private static final long ID2 = 2;
	private static final int  H2 = 50;
	private static final long ID3 = 3;
	private static final long ID4 = 4;
	private static final long ID5 = 5;
	private static final long ID6 = 6;
	
	Square shp1=new Square(ID1,H2);
	Rectangle shp2=new Rectangle(ID2,W1,H2);
	Canvas shp3=new Canvas(ID3, new Shape[] {shp1} );
	Canvas shp4=new Canvas(ID4, new Shape[] {shp2} );
	Canvas shp5=new Canvas(ID5, new Shape[] {shp3, shp4, shp1} );
	
	Page page;
	@BeforeEach
	void setCanvas()
	{
		page = new Page();
		page.addShape(shp1);
		page.addShape(shp5);
		
	//					Page
	//			/					\
	//		shp1					shp5
	//						/		  |			\
	//					shp3		shp4		shp1
	//					/			  \
	//				shp1			shp2	
		
	}

	@Test
	void testAddShapeShape()
	{
		Rectangle shp6=new Rectangle(ID6,W1,H2);	
		Shape[] expectedShape=new Shape[] {shp1,shp5,shp6};
		assertThrowsExactly(ShapeAlreadyExistsException.class,() -> page.addShape(shp1));
		page.addShape(shp6);
		assertArrayEquals(expectedShape,page.shapes);
	}

	@Test
	void testAddShapeLongArrayShape()
	{
		Rectangle shp6=new Rectangle(ID6,W1,H2);	
		Shape[] expectedShape1=new Shape[] {shp3,shp4,shp1,shp6};
		Long[] path1=new Long[] {(long) 5};
		page.addShape(path1, shp6);
		assertArrayEquals(expectedShape1,shp5.shapes);
		Long[] path2=new Long[] {(long)5, (long)4};
		assertThrowsExactly(ShapeAlreadyExistsException.class,() -> page.addShape(path2,shp2));
		Long[] path3=new Long[] {(long)5, (long)1};
		assertThrowsExactly(NoCanvasException.class,() -> page.addShape(path3,shp2));
	}

	@Test
	void testRemoveShapeLong() 
	{
		Shape[] expectedShape1=new Shape[] {shp5};
		Shape shp=page.removeShape(ID1);
		assertTrue(shp1.equals(shp));
		assertArrayEquals(expectedShape1,page.shapes);
		assertThrowsExactly(ShapeNotFoundException.class,() ->page.removeShape(ID3));			
	}


	@Test
	void testRemoveShapeArrayLong() 
	{
		Long[] path1=new Long[] {(long) 5};
		assertThrowsExactly(ShapeNotFoundException.class,() ->page.removeShape(path1,ID2));
		Long[] path2=new Long[] { (long) 1};
		assertThrowsExactly(NoCanvasException.class,() ->page.removeShape(path2,ID1));
		Long[] path3=new Long[] {(long) 5, (long) 4};
		Shape[] expectedShape1=new Shape[0] ;
		assertEquals(shp2,page.removeShape(path3,ID2));
		assertArrayEquals(expectedShape1,shp4.shapes);
		Shape[] expectedShape2=new Shape[] { shp3,shp1 } ;
		page.removeShape(path1, ID4);
		assertArrayEquals(expectedShape2,shp5.shapes);
	}

	

	@Test
	void testIterator() 
	{	
		Canvas con=new Canvas(ID5, new Shape[0] );
		Iterator<Shape> itEmpty=con.iterator();
		assertFalse(itEmpty.hasNext());
		Iterator<Shape> it=shp5.iterator();
		assertTrue(it.hasNext());
		assertTrue(shp3.equals(it.next()) );
		assertTrue(it.hasNext());
		assertTrue(shp4.equals(it.next()) );
		assertTrue(it.hasNext());
		assertTrue(shp1.equals(it.next()) );
		assertFalse(it.hasNext());
		assertThrowsExactly(NoSuchElementException.class,() -> it.next());	
	}

}
