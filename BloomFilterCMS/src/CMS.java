
import java.util.ArrayList;

public class CMS {
	
	private int M;
	private int k;
	private int[][] CMS;
	private int N;
//	private float epsilon;

	CMS(float epsilon, float delta, ArrayList<String> s){
//		this.epsilon = epsilon;
		M = (int) (2/epsilon);
		k = (int) (Math.log(1/delta)/Math.log(2));
		CMS = new int[k][M];
		N = s.size();
	}
	
	public void add(ArrayList<String> s) {
		for(String x : s) {
			add(x);
		}
	}
	
	private void add(String x) {
		
		byte[] itemBytes;
		try {
			itemBytes = x.toLowerCase().getBytes("UTF-8");//transform to lower case to make it not case intensive
		} catch (Exception e) {
			itemBytes = x.toLowerCase().getBytes();
		}
		
		long hash1 = MyHashFunc.Murmur64_1(itemBytes);
		long hash2 = MyHashFunc.Murmur64_2(itemBytes);
		

		for(int i = 0; i < k; i++) {
			int hash = (int)(MyHashFunc.generator(hash1, hash2, i) & 0x7fffffff) % M;
			CMS[i][hash]++;
		}
		
//		if(DEBUGGING) PrintCMS();
		
	}
	
	public int approximateFrequency(String x){		
		byte[] itemBytes;
		try {
			itemBytes = x.toLowerCase().getBytes("UTF-8");//transform to lower case to make it not case intensive
		} catch (Exception e) {
			itemBytes = x.toLowerCase().getBytes();
		}
		
		long hash1 = MyHashFunc.Murmur64_1(itemBytes);
		long hash2 = MyHashFunc.Murmur64_2(itemBytes);

		int min = Integer.MAX_VALUE;
		for(int i = 0; i < k; i++) {
			int hash = (int)(MyHashFunc.generator(hash1, hash2, i) & 0x7fffffff) % M;
			int tmp = CMS[i][hash];
			min = min > tmp ? tmp : min;
		}
		return min;
	}
	
	public ArrayList<String> approximateHeavyHitter(ArrayList<String> s, float q, float r) {
//		int pN =  (int) ((r + epsilon) * N); // A{x|\hat{f_x} >= (r + \epsilon)N}
		int pN =  (int) (q * N);//this one is better, but since q = (r + \epsilon) in our case they give the same result
		ArrayList<String> heavyHitter = new ArrayList<String>();

		for(String x : s) {
			String tmp = x.toLowerCase();
			add(tmp);
			if(!heavyHitter.contains(tmp)) {
				int fx_hat = approximateFrequency(tmp);
				if(fx_hat >= pN) heavyHitter.add(tmp);
			}
		}

		return heavyHitter;
	}
		
	public void PrintCMS() {
		for(int i = 0; i < k; i++) {
			System.out.print("k_" + (i+1) + ": ");
			for(int j =0; j< M; j++) {
				System.out.print(CMS[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public void printMemoryUse() {
		System.out.println("Memory consumption of this CMS is: " + (k * M * 4 * 8) + "bits");
	}
	
	
}
