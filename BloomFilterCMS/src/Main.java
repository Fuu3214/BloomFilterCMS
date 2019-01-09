
public class Main {

	public static void falsePositive() {
		
		int [] testBPE = {4,8,10}; //test different bitsPerElement
		for(int bitsPerElement : testBPE) {
			System.out.println("bitsPerElement: " + bitsPerElement);
			FalsePositive.test(bitsPerElement);
			System.out.println("-------------------------------------------------");
		}
		
	}
				
	public static void main(String[] args) {
		System.out.println("FalsePositive ");
		System.out.println("-------------------------------------------------");
		falsePositive();
		System.out.println("=================================================");
		System.out.println("CMS testing");
		System.out.println("-------------------------------------------------");
		testCMS.test();
		System.out.println("=================================================");
		System.out.println("HeavyHitters");
		System.out.println("-------------------------------------------------");
		HeavyHitters.getHeavyHitters();
		System.out.println("=================================================");
		System.out.println("EmpericalComparison");
		System.out.println("-------------------------------------------------");
		EmpericalComparison.Compare();
		System.out.println("=================================================");
		
	}	
	
	
}
