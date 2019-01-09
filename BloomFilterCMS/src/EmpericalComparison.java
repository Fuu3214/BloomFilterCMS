
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EmpericalComparison {
	
	//Computes Bloom filter creation time
	//Dynamically compute average time BloomDifferential and NaiveDifferential takes for each query
	
	public static void Compare(){
		long startCreate, endCreate;
		startCreate = System.currentTimeMillis();    
		BloomfilterMurmur bloomfilter = BloomDifferential.create();
		endCreate = System.currentTimeMillis();   
		System.out.println("Bloom Filter Creation time: " + (endCreate - startCreate) + "ms");
		System.out.println("*****************************************************");
		
		String obj = "D:\\projects\\535\\PA\\grams.txt";

		File file = new File(obj);
        BufferedReader reader = null;
		try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            long bloomTime = 0;
            long naiveTime = 0;
            int count = 0;
            while ((tempString = reader.readLine()) != null) {
            	count++;
            	
        		long start, end;
        		start = System.currentTimeMillis();    
        		BloomDifferential.retrieveRecord(bloomfilter, tempString); 
        		end = System.currentTimeMillis();   
        		bloomTime += end - start;
        		
        		start = System.currentTimeMillis();    
        		NaiveDifferential.retrieveRecord(tempString);
        		end = System.currentTimeMillis();   
        		naiveTime += end - start;
            	
        		System.out.println("BloomDifferential average time: " + (float)bloomTime/count + "ms");
        		System.out.println("NaiveDifferential average time: " + (float)naiveTime/count + "ms");
        		System.out.println("-------------------------------------------------------");
            }
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		
	}
}
