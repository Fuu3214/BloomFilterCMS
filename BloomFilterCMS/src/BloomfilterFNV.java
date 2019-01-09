import java.util.BitSet;

public class BloomfilterFNV {
	private int M;
	private int N;
	private int L;
	
	private BitSet T;
    private int k;
    private int dataSize;
    
    private static final boolean DEBUGGING = false;
  	
	
	BloomfilterFNV(int setSize, int bitsPerElement){
		N = setSize;
		L = bitsPerElement;
		M = N * L;
		T = new BitSet(M);
		k = (int) Math.round((L) * Math.log(2.0));;
		setDataSize(0);
	}
	
	public void add(String x) {
		
		byte[] itemBytes;
		try {
			itemBytes = x.toLowerCase().getBytes("UTF-8");//transform to lower case to make it not case intensive
		} catch (Exception e) {
			itemBytes = x.toLowerCase().getBytes();
		}
		
		long hash1 = MyHashFunc.FNV64_1(itemBytes);
		long hash2 = MyHashFunc.FNV64_2(itemBytes);
		
		boolean flag = false;

		for(int i = 0; i < k; i++) {
			int hash = (int)(MyHashFunc.generator(hash1, hash2, i) & 0x7fffffff) % M;
			flag = T.get(hash);
			if(!flag) break;
		}
		
		if(flag) return;
		
		this.setDataSize(dataSize() + 1);
		
		for(int i = 0; i < k; i++) {
			int hash = (int)(MyHashFunc.generator(hash1, hash2, i) & 0x7fffffff) % M;
			T.set(hash);
		}
		
		if(DEBUGGING) System.out.println(T);
		
	}
	 public boolean appears(String s) {
		 
			byte[] itemBytes;
			try {
				itemBytes = s.toLowerCase().getBytes("UTF-8");//transform to lower case to make it not case intensive
			} catch (Exception e) {
				itemBytes = s.toLowerCase().getBytes();
			}
			
			long hash1 = MyHashFunc.FNV64_1(itemBytes);
			long hash2 = MyHashFunc.FNV64_2(itemBytes);
			
		 for(int i = 0; i < k; i++) {
				int hash = (int)(MyHashFunc.generator(hash1, hash2, i) & 0x7fffffff) % M;
				boolean T_h = T.get(hash);
				if(!T_h) return false;
			}
		 return true;
	 }
	 
	 public int filterSize() {
		 return M;
	 }
	 
	 public int numHashes() {
		 return k;
	 }

	public int dataSize() {
		return dataSize;
	}

	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}
}
