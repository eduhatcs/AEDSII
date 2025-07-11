#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

// Celula para fila de aeronaves
typedef struct Celula {
	int ponto;
	char identificador[40];
	struct Celula *prox;
} Celula;

// Fila
typedef struct Fila {
	Celula *inicio;
	Celula *fim;
} Fila;

// Iniciar fila
void initFila(Fila *f) {
	f->inicio = f->fim = NULL;
}

// Inserir no fim da fila
void enfileirar(Fila *f, char identificador[], int ponto) {
	Celula *tmp = malloc(sizeof(Celula));
	strcpy(tmp->identificador, identificador);
	tmp->ponto = ponto;
	tmp->prox = NULL;

	if (f->fim != NULL) {
		f->fim->prox = tmp;
	}
	else {
		f->inicio = tmp;
	}

	f->fim = tmp;
}

// Remover da fila
Celula *desenfileirar(Fila *f) {
	Celula *rem = NULL;
	if (f->inicio != NULL) {
		rem = f->inicio;
		f->inicio = rem->prox;

		if (!f->inicio) {
			f->fim = NULL;
		}
	}

	return rem;
} 

// Verifica se a fila está vazia
bool filaVazia(Fila *f) {
	return f->inicio == NULL;
}

// Main
int main() {
	Fila entrada;
	initFila(&entrada);

	char identificador[40];
	int ponto = 0;
	bool fim = false;

	while (!fim && scanf("%s", identificador) == 1) {
		if (strcmp(identificador, "0") == 0) {
			fim = true;
		}
		else if (identificador[0] == '-' || identificador[0] == '0') {
			ponto = atoi(identificador);
		}
		else {
			enfileirar(&entrada, identificador, ponto);
		}
	}

	Fila norte,
	     sul,
	     leste,
	     oeste;

	initFila(&norte);
	initFila(&sul);
	initFila(&leste);
	initFila(&oeste);

	Celula *atual;
	while (!filaVazia(&entrada)) {
		atual = desenfileirar(&entrada);

		switch (atual->ponto) {
			case -1: // Oeste
				enfileirar(&oeste, atual->identificador, -1);
				break;
			case -2: // Sul
				enfileirar(&sul, atual->identificador, -2);
				break;
			case -3: // Norte
				enfileirar(&norte, atual->identificador, -3);
				break;
			case -4: // Leste
				enfileirar(&leste, atual->identificador, -4);
				break;
		}

		free(atual);
	}


	// Por ordem de prefência, todos do Oeste primeiro
	while (!filaVazia(&oeste)) {
		atual = desenfileirar(&oeste);
		printf("%s ", atual->identificador);
		free(atual);
	}

	// Por ordem de prefência, alterna entre Norte e Sul
	while (!filaVazia(&norte) || !filaVazia(&sul)) {
		if (!filaVazia(&norte)) {
			atual = desenfileirar(&norte);
			printf("%s ", atual->identificador);
			free(atual);
		}

		if (!filaVazia(&sul)) {
			atual = desenfileirar(&sul);
			printf("%s ", atual->identificador);
			free(atual);
		}
	}

	// Por ordem de prefência, todos do Leste
	while (!filaVazia(&leste)) {
		atual = desenfileirar(&leste);
		printf("%s ", atual->identificador);
		free(atual);
	}

	return 0;
}
