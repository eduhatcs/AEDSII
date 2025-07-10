public class Main {

	public static int[] vetorOrdenado(int[] vetA, int[] vetB) {
		int m = vetA.length -1,
		    n = vetB.length -1,
		    x = m + n + 2,
		    i = 0;
		int[] vetC = new int[x];

		while (m >= 0 || n >= 0) {
			if (m >= 0 && n >= 0 && m == n) {
				if (vetA[m] < vetB[n]) {
					vetC[i] = vetA[m];
					i++; m--;
				}
				else {
					vetC[i] = vetB[n];
					i++; n--;
				}
			}
			else if (m >= 0 && m > n) {
				vetC[i] = vetA[m];
				i++; m--;
			}
			else {
				vetC[i] = vetB[n];
				i++; n--;
			}
		}

		return vetC;
	}

	public static void main(String[] args) {
		int[] vetA = {46, 38, 22, 10};
		int[] vetB = {57, 33, 21};

		int[] vetC = vetorOrdenado(vetA, vetB);

		for (int i = 0; i < vetC.length; i++) {
			System.out.print(vetC[i] + " ");
		}
		System.out.println();
	}
}
