package edu.neu.coe.info6205.union_find;

public class HWQUPC_Solution {
	public static void main(String args[]) {

		int n = 350;

		for (int i = 0; i < 7; i++) {
			System.out.println("Number of nodes: " + n);
			int no_pairs = createHWQUPC(n);
			n = n * 4;
		}
	}

    private static int createHWQUPC(int n) {

        int no_connections = 0;
        int no_pairs = 0;

        UF_HWQUPC uf = new UF_HWQUPC(n);
        while (uf.components() != 1) {
            int x = (int) (Math.random() * (n));
            int y = (int) (Math.random() * (n));
            no_pairs += 1;
            if (uf.connected(x, y) == false) {
                uf.union(x, y);
                no_connections++;
            }
        }
        System.out.println("Number of connections:" + no_connections);
        System.out.println("Number of pairs:" + no_pairs + "\n");
        return no_pairs;
    }
    

}