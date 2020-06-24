package br.com.jokenpo.model;

public enum Rules {

	PAPEL {
		@Override
		public boolean ganha(Rules naipe) {return naipe == Rules.PEDRA || naipe == Rules.SPOCK;}

		@Override
		public boolean perde(Rules naipe) {return naipe == Rules.TESOURA || naipe == Rules.LAGARTO;}

	},
	LAGARTO {
		@Override
		public boolean ganha(Rules naipe) {return naipe == Rules.SPOCK || naipe == Rules.PAPEL;}

		@Override
		public boolean perde(Rules naipe) {return naipe == Rules.TESOURA || naipe == Rules.PEDRA;}
	},
	TESOURA {
		@Override
		public boolean ganha(Rules naipe) {return naipe == Rules.LAGARTO || naipe == Rules.PAPEL;}

		@Override
		public boolean perde(Rules naipe) {return naipe == Rules.SPOCK || naipe == Rules.PEDRA;}
	},	
	SPOCK {
		@Override
		public boolean ganha(Rules naipe) {return naipe == Rules.TESOURA || naipe == Rules.PEDRA;}

		@Override
		public boolean perde(Rules naipe) {return naipe == Rules.LAGARTO || naipe == Rules.PAPEL;}
	},
	PEDRA {
		@Override
		public boolean ganha(Rules naipe) {return naipe == Rules.LAGARTO || naipe == Rules.TESOURA;}
		@Override
		public boolean perde(Rules naipe) {return naipe == Rules.SPOCK || naipe == Rules.PAPEL;}
	};

	public abstract boolean ganha(Rules naipe);
	public abstract boolean perde(Rules naipe);
}
