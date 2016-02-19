import java.util.*;
import java.io.*;
class output
{
	BufferedReader br = null;
	String itemList [] = new String[3];
	Vector<Vector<String>> MainList = new Vector<Vector<String>>();
	List<String> list = new ArrayList<String>();
	String Commands[] = new String[10];
	float min[] = new float[10];
	int y =0;
	public output(String args[])
	{
		Commands = args;
	}
	public  Vector<String> CSVtoArrayList(String CSVString) 
	{
		        Vector<String> Result = new Vector<String>();
		if (CSVString != null) 
		{
			String[] splitData = CSVString.split(",");
			for (int i = 0; i < splitData.length; i++) 
			{
				if (!(splitData[i] == null) || !(splitData[i].length() == 0)) 
				{
					Result.add(splitData[i].trim());
				}
			}
		}
		return Result;
	}
	public void init()
	{	
		try 
		{
			String line;
			br = new BufferedReader(new FileReader("data.csv"));
			while ((line = br.readLine()) != null) 
			{
				MainList.add(CSVtoArrayList(line));
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (br != null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ReadCommandLine();
		CompareNow();	
	}
	public void CompareNow()
	{
		try
		{
			int OuterListSize = MainList.size();
			HashMap<Integer,Float> map=new HashMap<Integer,Float>();          
			for(int i=0;i<OuterListSize;i++){
				Vector<String> items = MainList.get(i);
				if(items.containsAll(list)){
					float price = Float.parseFloat(items.get(1));
					int hotelId = Integer.parseInt(items.get(0));
					map.put(hotelId,price);
				}
			}
			Float min = Collections.min(map.values());
			int Index = 0; 
			for (Object o : map.keySet()){
			      if (map.get(o).equals(min)){
				Index = (Integer)o;
			      }
			}
			System.out.println(Index +" , "+ min);
		}catch(Exception e)
		{
			System.out.println("\n No Match Found Please Try Later with Other Items");			
		}
	}
	/*public void CheckMinimum(Vector<String> items,int i,int y,float min[])
	{
		float minn = min[0];
		for(int j=1;j<y;j++)
		{
			if(minn > min[j])
			{
				minn = min[j];
			}	
		}	
		System.out.println("Found Min " + minn + " at " + i);
	}*/
	public void ReadCommandLine()
	{
		if(Commands.length > 0)
		{
			for(int i=0;i<Commands.length;i++)
			{
				list.add(Commands[i]);
			}
		}	
		System.out.println(list);
	}
	public static void main(String args[])
	{
		output s = new output(args);
		s.init();
	}
}
