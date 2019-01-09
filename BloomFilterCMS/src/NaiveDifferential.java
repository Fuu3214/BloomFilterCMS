

public class NaiveDifferential {
	
	private final static String DIFFFILE = "D:\\projects\\535\\PA\\DiffFile.txt";
	
	public static void retrieveRecord(String key){
		System.out.println("Naively Looking for: " + key);
		System.out.println("-Database says: " + MySearch.search(key, DIFFFILE));
//    		break;
	}
}
