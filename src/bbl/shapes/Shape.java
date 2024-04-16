package bbl.shapes;

import java.util.Objects;


public abstract class Shape
{
	protected long id;

	public Shape(long id)
	{
		this.id = id;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
	
	public abstract int square();
	public abstract int perimeter();

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Shape))
			return false;
		Shape other = (Shape) obj;
		return id == other.id;
	}
	
}
