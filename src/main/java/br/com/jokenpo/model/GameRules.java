package br.com.jokenpo.model;

public enum GameRules {
	
	//SPOCK ganha de: 	TESOURA e PEDRA
	//TESOURA ganha de: LAGARTO e PAPEL
	//PAPEL ganha de:	PEDRA | a prova de SPOCK
	//PEDRA ganha de: 	TESOURA e Lagarto
	//LAGARTO ganha:	PAPEL e SPOCK
	
	TESOURA(1),PAPEL(2),PEDRA(3),LAGARTO(4),SPOCK(5);
	
	//Esquema STPPL TESOURA(1-5-0-3-0) P,P = PAPEL, PEDRA respectivamente
	
	//primeira posição é o ID da mão
	//as outras posições são os ID's das outras mãos
	///se for zero na posição da respectiva mão, é pq ela não ganha.
	
	
	//SPOCK		(5-0-N-0-4)
	//TESOURA	(1-5-0-3-0)
	//PAPEL		(2-N-0-3-0)
	//PEDRA		(3-1-0-0-4)
	//LAGARTO	(4-5-2-0-0)
	
	public int valueItem;
	
	GameRules(int valor) {
		valueItem = valor;
	}

}
