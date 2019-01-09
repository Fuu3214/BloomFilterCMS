
import java.util.UUID;

public class FalsePositive {
	
	public static String randStringGenerator() {
		return UUID.randomUUID().toString();
		//Use UUID to generate random Strings, it is guaranteed to have very small probability to generate same String
		//Reference https://en.wikipedia.org/wiki/Universally_unique_identifier
	}
	
	public static void test( int bitsPerElement ) {
		
		int setSize = 10000;
		int L = bitsPerElement;//number of bits in int
		
		int testSize = 500000;
		
		BloomfilterFNV v = new BloomfilterFNV(setSize, L);
		BloomfilterMurmur vv = new BloomfilterMurmur(setSize, L);
		BloomfilterRan vvv = new BloomfilterRan(setSize, L);
		
		for(int i = 0; i < setSize; i++) {
			String UUID = randStringGenerator();
			v.add(UUID);
			vv.add(UUID);
			vvv.add(UUID);
		}
		
		int countFNV = 0;
		int countMur = 0;
		int countRand = 0;
		for(int i = 0; i < testSize; i++) {
			String UUID = randStringGenerator();
			if(v.appears(UUID)){
				countFNV++;
			}
			if(vv.appears(UUID)){
				countMur++;
			}
			if(vvv.appears(UUID)){
				countRand++;
			}
		}
		
		System.out.println("dataSize: " + setSize + "\nnumber of hash functions: " + vv.numHashes() + "\nnumber of testing Strings: " + testSize + "\n" );
		System.out.println(" Theoratic ratial: " + Math.pow(0.618, (double)L));
		System.out.println(" FNV - false positive: " + countFNV + ", ratial: " + (double)countFNV/testSize);
		System.out.println(" Murmur - false positive: " + countMur + ", ratial: " + (double)countMur/testSize);
		System.out.println(" Random - false positive: " + countRand + ", ratial: " + (double)countRand/testSize);
	}			
}
