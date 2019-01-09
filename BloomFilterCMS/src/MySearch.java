
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MySearch {

	public static String parseString(String target) {
    	int count = 0;
    	for(int i=0; i<target.length(); i++) {
    		char c = target.charAt(i);
    		if(c == ' ') {
    			count++;
    			if(count == 4) {
    				return target.substring(0, i);
    			}
    		}
    	}
    	return null;
	}
	
	public static String search(String target, String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		  try {
		      reader = new BufferedReader(new FileReader(file));
		      String tempString = null;
		      while ((tempString = reader.readLine()) != null) {
//		    	  System.out.println(tempString + "\n" + target);
		    	  if(target.equals(parseString(tempString))) {
		    		  reader.close();
		    		  return tempString;
		    	  }
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
		  return "Not Found";
	}
	
	
	
	
//Binary Search result wired, checked and and found 700+ lines are not in order.
//Could be better if tell us to use line search in the beginning
		
//	public static int Compare(String a, String b) {
//		int l1 = a.length();
//		int l2 = b.length();
//		for(int i=0; i<l1 && i<l2; i++) {
//    		char c1 = a.charAt(i);
//    		char c2 = b.charAt(i);
//    		if(c1 == c2) ;
//    		else return (c1 > c2)? 1: ( (c1<c2)? -1:0);
//    	}
//		return (l1 > l2)? 1: ( (l1<l2)? -1:0);
//	}
	
//  private static String readLineVarFile(String fileName, int targetLine){
//  	File file = new File(fileName);
//  	BufferedReader reader = null;
//  	int lines = 0;
//      try {
//          reader = new BufferedReader(new FileReader(file));
//          String tempString = null;
//          while ((tempString = reader.readLine()) != null) {
//          	lines++;
//          	if(lines == targetLine) {
//          		return tempString;            		
//          	}
//      	}
//          reader.close();
//      } catch (IOException e) {
//          e.printStackTrace();
//      } finally {
//          if (reader != null) {
//              try {
//                  reader.close();
//              } catch (IOException e1) {
//              }
//
//          }
//      }
//      return null;
//  }
	
	
//	public static void search(String target, String fileName, int totalLines) {
//		
//		int start = 1;
//		int end = totalLines;
//		String midRead = null;
//		while(start <= end) {
//			int mid = start + (end - start)/2;
//			midRead = readLineVarFile(fileName, mid);
//			String midString = parseString(midRead);
//			int compare = Compare(midString, target);
//			System.out.println(midString + " " + compare);
//			if( compare== 1) {
//				end = mid - 1;
//			}
//			else if (compare == 0){
//				System.out.println(midRead);
//				return;
//			}
//			else {
//				start = mid + 1;
//			}
//		}
////		if( target.equals(parseString(midString)))
////			System.out.println(midString);
//		System.out.println("Not found");
//		
//	}
}
