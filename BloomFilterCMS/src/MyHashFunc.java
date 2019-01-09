
import java.math.BigInteger;

public class MyHashFunc {
	
  	private static final long FNV1a_64_INIT 	= 0xcbf29ce484222325L;
  	private static final long FNV1a_64_INIT_v2 	= 0x64CCDD2004F57157L;//another offset for a different hash function
  	private static final long FNV1a_PRIME_64 	= 0x100000001b3L;
  	private static final long FNV1a_PRIME_64_v2 = 0x5A43C12AA40A8261L;
  	private static final int MURMUR_SEED 		= 0x116BB17F;

  	public static long generator(long hash1, long hash2, int k) {
  		//generate a new hash function from two old hash function
  		//this implementation follows this paper: https://www.eecs.harvard.edu/~michaelm/postscripts/tr-02-05.pdf
  		return hash1 + k * hash2;
  	}
  	
  	public static int generator(int hash1, int hash2, int k) {
  		//generate a new hash function from two old hash function
  		//this implementation follows this paper: https://www.eecs.harvard.edu/~michaelm/postscripts/tr-02-05.pdf
  		return hash1 + k * hash2;
  	}
  	
  	public static long FNV64_1(byte[] data) {
  		long hash = FNV1a_64_INIT;
  		for (int i = 0; i < data.length; i++) {
  			hash ^= (data[i] & 0xff);
	      	hash *= FNV1a_PRIME_64;
      	}
    	return hash;
  	}
  	public static long FNV64_2(byte[] data) {
  		long hash = FNV1a_64_INIT_v2;
  		for (int i = 0; i < data.length; i++) {
  			hash ^= (data[i] & 0xff);
	      	hash *= FNV1a_PRIME_64_v2;
      	}
    	return hash;
  	}
  	
  	public static long Murmur64_1(byte[] data) {
  		long hash = MurmurHash.hash64(data, data.length);
    	return hash;
  	}
  	
  	public static long Murmur64_2(byte[] data) {
  		long hash = MurmurHash.hash64(data, data.length, MURMUR_SEED);
    	return hash;
  	}
  	
  	public static int[] RandomHash(int M, int k) {
  		int P = M;
  		for(int i=0; i<k; i++) {
  	  		P = nextPrime(P);
  		}//Use different P to ensure hash function is good for bloom filter
  		int[] theta = new int[3];
  		theta[0] = (int)(Math.random() * (double)(P-1));
  		theta[1] = (int)(Math.random() * (double)(P-1));
  		theta[2] = P;
    	return theta;
  	}
  	
  	
  	public static int nextPrime(int n) { 
	    BigInteger b = new BigInteger(Integer.toUnsignedString(n)); 
	    return Integer.parseInt(b.nextProbablePrime().toString()); 
	} 
  	

  	
}
