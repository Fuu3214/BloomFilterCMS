
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeavyHitters {
	private static final String SHAKESPEAR = "D:\\projects\\535\\PA\\shakespear.txt";
	
	public static String stringFilter (String str){
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~£¡@#£¤%¡­¡­&*£¨£©¡ª¡ª+|{}¡¾¡¿¡®£»£º¡±¡°¡¯¡££¬¡¢£¿]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    
    public static int countHitter(float q, ArrayList<String> heavyHitter, ArrayList<String> fileInput) {
		int hitterCounter = 0;
		for(String x : heavyHitter) {
			int cnt = 0;
			for(String t : fileInput) {
				if(t.equals(x)) cnt++;
			}
			if(cnt >= q * fileInput.size()) hitterCounter++;
		}
		return hitterCounter;
    }

	public static void getHeavyHitters() {
		ArrayList<String> fileInput = new ArrayList<String>();
		
		float epsilon = (float)1/100;
		float delta = (float)Math.pow(2, -20);
		float q = (float) 0.04;
		float r= (float) 0.03;
		
		System.out.println("reading file...");
        File file = new File(SHAKESPEAR);
        Scanner s;
		try {
			s = new Scanner(file);
			String str = null;
			while(s.hasNext()) {
				str = s.next().toLowerCase();
				if(str.length() >= 3 && !str.equals("the")) {
					fileInput.add(stringFilter(str));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("creating CMS while finding HeavyHitters");
		CMS cms = new CMS(epsilon, delta, fileInput);
		ArrayList<String> heavyHitter = cms.approximateHeavyHitter(fileInput, q, r);
		
		System.out.println("contents of HeavyHitters: " + heavyHitter);
		
		System.out.println("number of " + 0.04 + "-HeavyHitters is " + countHitter((float)0.04, heavyHitter, fileInput)); 
		System.out.println("number of " + 0.25 + "-HeavyHitters is " + countHitter((float)0.025, heavyHitter, fileInput)); 
		System.out.println("number of items that is not " + 0.04 + "-HeavyHitters is " + (heavyHitter.size() - countHitter((float)0.04, heavyHitter, fileInput))); 
		
//		System.out.println("total number of Strings added is " + fileInput.size()); 
//		
//		ArrayList<String> simple = new ArrayList<String>();
//		for(String x : fileInput) {
//			if(!simple.contains(x)) simple.add(x);
//		}
//		System.out.println("total number of distinct Strings added is " + simple.size()); 
//		
//		cms.printMemoryUse();

	}
}
