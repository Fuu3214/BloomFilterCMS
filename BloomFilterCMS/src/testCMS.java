
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testCMS {
	private static final String SHAKESPEAR = "shakespear.txt";
	public static String stringFilter (String str){
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~£¡@#£¤%¡­¡­&*£¨£©¡ª¡ª+|{}¡¾¡¿¡®£»£º¡±¡°¡¯¡££¬¡¢£¿]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
	
	public static void test() {
		
		ArrayList<String> fileInput = new ArrayList<String>();
		
		float epsilon = (float)1/100;
		float delta = (float)Math.pow(2, -20);
		
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
		System.out.println("creating CMS");
		CMS cms = new CMS(epsilon, delta, fileInput);
		cms.add(fileInput);
		System.out.println("created");
		System.out.println("Testing approximate frequency of following words: ");
		System.out.println("-love:" + cms.approximateFrequency("love"));
		System.out.println("-and:" + cms.approximateFrequency("and"));
		System.out.println("-peace:" + cms.approximateFrequency("peace"));
		
		int count = 0;
		for(String str: fileInput) {
			if(str.toLowerCase().equals("love"))
				count ++;
		}
		System.out.println("Real - love:" + count);
	}
}
