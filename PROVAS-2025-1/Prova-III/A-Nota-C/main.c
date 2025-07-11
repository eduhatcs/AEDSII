#include <stdio.h>
#include <stdlib.h>

int main() {
	int n,
	    k;
	int array[100];
	while (scanf("%d %d", &n, &k) == 2) {
		for (int i = 0; i < n; i++) {
			scanf("%d", &array[i]);
		}

		for (int i = 0; i < k; i++) {
			int maior = i;
			for (int j = i+1; j < n; j++) {
				if (array[j] > array[maior]) {
					maior = j;
				}
			}

			int tmp = array[i];
			array[i] = array[maior];
			array[maior] = tmp;
		}

		int soma = 0;

		for (int i = 0; i < k; i++) {
			soma += array[i];
		}

		printf("%d\n", soma);
	}
}

