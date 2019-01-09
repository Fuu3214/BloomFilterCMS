
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BloomDifferential {
	
	private final static String DIFFFILE = "D:\\projects\\535\\PA\\DiffFile.txt";
		
    public static void addKeysFromFile(BloomfilterMurmur Bloomfilter) {
        File file = new File(DIFFFILE);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	int count = 0;
//            	System.out.println(tempString + "\n");
            	for(int i=0; i<tempString.length(); i++) {
            		char c = tempString.charAt(i);
            		if(c == ' ') {
            			count++;
            			if(count == 4) {
//            				System.out.println(tempString.substring(0, i) + "\n");
            				Bloomfilter.add(tempString.substring(0, i));
            			}
            		}
            	}
//            	break;
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
    public static int getNumberOfLines() {
    	File file = new File(DIFFFILE);
    	BufferedReader reader = null;
    	int lines = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            while (reader.readLine() != null) {
            	lines++;
        	}
            reader.close();
           	return lines;
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
        return 0;
    }
	public static BloomfilterMurmur create() {
		System.out.println("creating...");
		int setSize = getNumberOfLines();
		BloomfilterMurmur bloomfilter = new BloomfilterMurmur(setSize,10);
		addKeysFromFile(bloomfilter);
		System.out.println("Done. Size of bloomfilter: " + bloomfilter.dataSize());
		return bloomfilter;
	}
	
	public static void retrieveRecord(BloomfilterMurmur Bloomfilter,String key){
		System.out.println("Bloomfilter Looking for: " + key);
    	if(!Bloomfilter.appears(key)) {
    		System.out.println("-Bloomfilter says: Not exists");
//    		break;
    	}
    	else {
    		System.out.println("-Goto Database, result: " + MySearch.search(key, DIFFFILE));
//    		break;
    	}
	}
	
}
