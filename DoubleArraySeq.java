package cs206b;

public class DoubleArraySeq {

	public DoubleArraySeq()
	{
		data = new double[10];
		int used = 0;
		int curridx = 0;
		
	}

	public DoubleArraySeq(int initialCapacity)
	{
		if(initialCapacity>0){
			data = new double[initialCapacity];
			int used =0;
			int curridx = 0;
		}
		else{
			throw new IllegalArgumentException("Input argument should be non-negative.");
		}
	}

	public void addAfter(double element)
	{
		
	}

	public void addBefore(double element)
	{

	}

	public void addAll(DoubleArraySeq addend)
	{

	}

	public void advance()
	{

	}

	public DoubleArraySeq clone()
	{

	}

	public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2)
	{

	}

	public void ensureCapacity(int minimumCapacity)
	{

	}

	public int getCapacity()
	{

	}

	public double getCurrent()
	{

	}

	public boolean isCurrent()
	{

	}

	public void removeCurrent()
	{

	}

	public int size()
	{

	}

	public void start()
	{

	}

	public void trimToSize()
	{

	}
	public static DoubleArraySeq reverse(DoubleArraySeq seq)
	{

	}
	public double getMax()
	{

	}
	public double getMin()
	{

	}

	public DoubleArraySeq insertSeqAt(DoubleArraySeq seq, int index)
	{

	}

}
