import java.util.*;
import java.io.*;

class Sample
{
	BufferedReader br = null;
	String itemList [] = new String[3];
	Vector<ArrayList<String>> MainList = new Vector<ArrayList<String>>();
	List<String> list = new ArrayList<String>();
	String Commands[] = new String[10];
	float min[] = new float[10];
	int y =0;
	HashMap<Integer,Float> map1=new HashMap<Integer,Float>();          
	HashMap<Integer,String> AllItems=new HashMap<Integer,String>(); 
	HashMap<Integer,Float> SampleMap=new HashMap<Integer,Float>();                   
	public Sample(String args[])
	{
		Commands = args;
	}
	public  ArrayList<String> CSVtoArrayList(String CSVString) 
	{
	       ArrayList<String> Result = new ArrayList<String>();
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
		SampleMapped();
		//StoreMapValues();
		String result1[] = new String[list.size()];
		for(int i=0;i<list.size();i++)
		{
			result1[i]=CompareNow(list.get(i));
			//System.out.println(result1[i]);	
		}
	}
	public void StoreMapValues()
	{
		int OuterListSize = MainList.size();
		int counter  = 0;
		int x = 0,h=0;
		for(int i=0;i<OuterListSize;i++)
		{
			ArrayList<String> items = MainList.get(i);
			ArrayList<String> newArrayList = new ArrayList<String>(items);
			ArrayList<String> newSubArrayList = new ArrayList<String>(newArrayList.subList(2,newArrayList.size()));
			for(int t=0;t<newSubArrayList.size();t++)
			{
				AllItems.put(h,newSubArrayList.get(t));		
				h++;
			}
		}
		
		
		List<String> ItemsList = new ArrayList<String>(AllItems.values());
		for(int r=0;r<ItemsList.size();r++)
		{
			for(int s=0;s<list.size();s++)
			{
				if(ItemsList.get(r).equals(list.get(s)))
					counter++;
			}
		}
		int hotels[] = new int[counter];
		float prices[] = new float[counter];
		for(int i=0;i<OuterListSize;i++)
		{
			ArrayList<String> items = MainList.get(i);
			for(int j = 0;j< list.size();j++)
			{
				if(items.contains(list.get(j)))
				{
					int hotelId = Integer.parseInt(items.get(0));
					float price1 = Float.parseFloat(items.get(1));	
					hotels[x] = hotelId;
					prices[x] = price1;		
					x++;
				}
			}
		}
		//System.out.print(x);
		for(int i = 0;i<counter;i++)
		{
			System.out.println("I " + i);
			map1.put(hotels[i],prices[i]);		
		}
		//System.out.print(map1);
	}
	public String CompareNow(String str)
	{
			int OuterListSize = MainList.size();
			HashMap<Integer,Float> map=new HashMap<Integer,Float>();          
			//System.out.println(list);
			for(int i=0;i<OuterListSize;i++)
			{
				ArrayList<String> items = MainList.get(i);
				
				if(items.contains(str))
				{
					float price = Float.parseFloat(items.get(1));
					int hotelId = Integer.parseInt(items.get(0));
					map.put(hotelId,price);
				}
			}
			//System.out.println(map);

			Float min = Collections.min(map.values());
			int Index = 0; 
			for (Object o : map.keySet())
			{
			      if (map.get(o).equals(min))
			      {
				Index = (Integer)o;
			      }
			}
		return (Index+":"+min);
	}
	public void SampleMapped()
	{
		int OuterListSize = MainList.size();
		int k= 0;
		for(int i=0;i<OuterListSize;i++)
		{
			ArrayList<String> items = MainList.get(i);
			ArrayList<String> newArrayList = new ArrayList<String>(items);
			ArrayList<String> newSubArrayList = new ArrayList<String>(newArrayList.subList(0,2));
			for(int t=0;t<newSubArrayList.size();t++)
			{
				int Id = Integer.parseInt(newSubArrayList.get(0));
				float price = Float.parseFloat(newSubArrayList.get(1));
				if (SampleMap.containsKey(Id)) 
				{
					float price1=SampleMap.get(Id);
					float total = price1;
			        } else 
				{
			        	SampleMap.put(Id,price);
				//	System.out.println(Id + " " + price);    
			        }       
			}
			
		}
		System.out.println(SampleMap);
	}
	public void ReadCommandLine()
	{
		if(Commands.length > 0)
		{
			for(int i=0;i<Commands.length;i++)
			{
				list.add(Commands[i]);
			}
		}	
		//System.out.println(list);
	} // Close of ReadCoomandLine
	public static void main(String args[]){
		Sample s = new Sample(args);
		s.init();
	}  // Close of Main Method
}  // Close of Class 
