package cs206b;

public class DoubleArraySeq implements Cloneable{
	
	private int items;
	private int curr;
	private double[] data;
	
	public DoubleArraySeq()
	{
		data = new double[10];
		items = 0;
		curr = 0;
	}

	public DoubleArraySeq(int initialCapacity)
	{
		if(initialCapacity>0){
			data = new double[initialCapacity];
			items =0;
			curr = 0;
		}
		else{
			throw new IllegalArgumentException("Input argument should be non-negative.");
		}
	}

	public void addAfter(double element)
	{
		ensureCapacity(items+1);
		if(isCurrent()){
			for(int i=items-1;i>curr+1;i--)
				data[i] = data[i-1];
			data[curr+1]=element;
		}
		else{
			data[items]=element;
		}
		items++;
		advance();
	}

	public void addBefore(double element)
	{
		ensureCapacity(items+1);
		if(isCurrent()){
			for(int i=items-1;i>curr;i--)
				data[i] = data[i-1];
			data[curr]=element;
		}
		else{
			for(int i=items;i>0;i--)
				data[i] = data[i-1];
			data[0] = element;
		}
		items++;
	}

	public void addAll(DoubleArraySeq addend)
	{
		if (addend.data != null){
		ensureCapacity(items+addend.items);
		//system.copyarray(addend,0,data,items,addend.items);
		for (int i=items;i<items+addend.items;i++)
			data[i]=addend.data[i-items];
		}
		else{
			throw new NullPointerException("addend is null!");
		}
	}

	public void advance()
	{
	/*
		if(isCurrent()){
			if(curr==items-1) curr = -1;
			else curr++;
		}
		else{
			throw new IllegalStateException("There's no current element!");
		}
	*/
		curr++;
	}

	public DoubleArraySeq clone()
	{
		DoubleArraySeq ret;
		try
		{
			ret = (DoubleArraySeq) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new RuntimeException("This class does not implement Cloneable.");
		}
		ret.data=data.clone();
		return ret;
	}

	public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2)
	{
		if(s1.data!=null && s2.data!=null){
			DoubleArraySeq ret = new DoubleArraySeq(s1.data.length+s2.data.length);
			for(int i=0;i<s1.data.length;i++)
				ret.data[i] = s1.data[i];
			for(int i=s1.data.length;i<s1.data.length+s2.data.length;i++)
				ret.data[i] = s2.data[i];
			return ret;
		}
		else throw new NullPointerException("There's null input!");
	}

	public void ensureCapacity(int minimumCapacity)
	{
		double[] temp = new double[minimumCapacity];
		if(minimumCapacity>items){
			for (int i=0;i<items;i++)
				temp[i]=data[i];
			data=temp;
		}
	}

	public int getCapacity()
	{
		return data.length;
	}

	public double getCurrent()
	{
		if(isCurrent()) return data[curr];
		else throw new IllegalStateException("Current element does not exist!");
	}

	public boolean isCurrent()
	{
		boolean answer = false;
		if(curr <= data.length)
			answer = true;
		return answer;
	}

	public void removeCurrent()
	{
		if(isCurrent()){
			for(int i=curr;i<items-1;i++)
				data[i]=data[i+1];
			items--;
			ensureCapacity(items);
			
		}
		else throw new IllegalStateException("There's no current element!");
	}

	public int size()
	{
		return items;
	}

	public void start()
	{
		if(data.length>0) curr=0;
		else throw new NullPointerException("data is empty!");
	}

	public void trimToSize()
	{
		double[] trim = new double[items];
		for(int i=0;i<items;i++)
			trim[i] = data[i];
		data=trim;
	}
	public static DoubleArraySeq reverse(DoubleArraySeq seq)
	{
		int n = seq.data.length;
		DoubleArraySeq reversed = new DoubleArraySeq(n);
		for(int i=0;i<n;i++){
			reversed.data[n-i-1]=seq.data[i];
		}
		return reversed;
	}
	public double getMax()
	{
		double ret = data[0];
		if(size()!=0){
			for(int i=1;i<size();i++)
				if(ret<=data[i]) ret = data[i];
			return ret;
		}
		else throw new IllegalStateException("There's no element in seqence!");
	}
	public double getMin()
	{
		double ret = data[0];
		if (size()!=0){
			for(int i=1;i<size();i++)
				if(ret>=data[i]) ret = data[i];
			return ret;
		}
		else throw new IllegalStateException("There's no element in seqence!");
	}

	public DoubleArraySeq insertSeqAt(DoubleArraySeq seq, int index)
	{
		if(seq.size()!=0){
			if(index>=1 && index<=items){
				int n = seq.data.length;
				DoubleArraySeq ret = new DoubleArraySeq(n+items);
				for(int i=0;i<index-1;i++)
					ret.data[i]=data[i];
				for(int i=index-1;i<n+index-1;i++)
					ret.data[i]=seq.data[i-index+1];
				for(int i=n+index-1;i<n+items;i++)
					ret.data[i]=data[i-n];
				return ret;
			}
			else throw new IllegalStateException("Unappropriate index!");
		}
		else throw new NullPointerException("seq is null.");
	}

}
