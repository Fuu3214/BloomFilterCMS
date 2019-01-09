
import java.util.BitSet;

public class BloomfilterRan {
	private int M;
	private int N;
	private int L;
	
	private BitSet T;
    private int k;
    private int dataSize;
    
    private static final boolean DEBUGGING = false;
    
    private int[] theta_1;
    private int[] theta_2;
  	
	
    BloomfilterRan(int setSize, int bitsPerElement){
		N = setSize;
		L = bitsPerElement;
		M = N * L;
		T = new BitSet(M);
		k = (int) Math.round((L) * Math.log(2.0));

		dataSize = 0;
		
		theta_1 = MyHashFunc.RandomHash(M,1);
		theta_2 = MyHashFunc.RandomHash(M,2);
	}
	
	public void add(String x) {
		
		int hashCode = x.toLowerCase().hashCode();//transform to lower case to make it not case intensive
		
		int hash1 = (theta_1[0] * hashCode + theta_1[1]) % theta_1[2];
		int hash2 = (theta_2[0] * hashCode + theta_2[1]) % theta_2[2];
		
		boolean flag = false;

		for(int i = 0; i < k; i++) {
			int hash = (int)(MyHashFunc.generator(hash1, hash2, i) & 0x7fffffff) % M;
			flag = T.get(hash);
			if(!flag) break;
		}
		
		if(flag) return;
		
		this.setDataSize(dataSize() + 1);
		
		for(int i = 0; i < k; i++) {
			int hash = (MyHashFunc.generator(hash1, hash2, i) & 0x7fffffff) % M;
			T.set(hash);
		}
		
		if(DEBUGGING) System.out.println(T);
		
	}
	 public boolean appears(String s) {
		 
		 int hashCode = s.toLowerCase().hashCode();//transform to lower case to make it not case intensive
			
		 int hash1 = (theta_1[0] * hashCode + theta_1[1]) % theta_1[2];
		 int hash2 = (theta_2[0] * hashCode + theta_2[1]) % theta_2[2];
			
		 for(int i = 0; i < k; i++) {
				int hash = (MyHashFunc.generator(hash1, hash2, i) & 0x7fffffff) % M;
				boolean T_h = T.get(hash);
				if(!T_h) return false;
			}
		 return true;
	 }
	 
	 public int filterSize() {
		 return M;
	 }
	 
	 public int dataSize() {
		 return dataSize;
	 }

	 public void setDataSize(int dataSize) {
		 this.dataSize = dataSize;
	 }
	 
	 public int numHashes() {
		 return k;
	 }
}
