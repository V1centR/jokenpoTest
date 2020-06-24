package br.com.jokenpo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.jokenpo.model.Player;
import br.com.jokenpo.model.Rules;

@RestController
@RequestMapping("/jokenpoapi")
public class JokenpoController {
	
	@PostMapping(value="/testeplay", produces = MediaType.APPLICATION_JSON_VALUE)
	private Player verificarJogada(@RequestBody ObjectNode data) {
		
		String handPlayer1 = data.get("player1").get(0).get("hand").asText();
		String handPlayer1Name = data.get("player1").get(0).get("nome").asText();
		
		String handPlayer2 = data.get("player2").get(0).get("hand").asText();
		
		System.out.println("NUMERO de PLAYERS:::: " + data.size());
		
		//Setup Players
		Player player1 = new Player();
		Player player2 = new Player();
		
		if(data.size() == 3) {
			Player player3 = new Player();
		}
		//################
		
		Rules execHandPlayer1 = setHandPlayer(handPlayer1);
		Rules execHandPlayer2 = setHandPlayer(handPlayer2);
		
			
		if(execHandPlayer1.ganha(execHandPlayer2)) {
			
			player1.setHand(execHandPlayer1.toString());
			player1.setNome(handPlayer1Name);
			player1.setStatus("VENCEU");
			
			return player1;
			
		}else {
			
			return null;
		}
		
}	
	
	private Rules setHandPlayer(String handPlayer) {
		
		String hand = handPlayer.trim().toLowerCase();
		
		Rules setHand = null;
		
		switch (hand) {
		case "pedra":
			setHand = Rules.PEDRA;
			break;
		case "papel":
			setHand = Rules.PAPEL;
			break;
		case "tesoura":
			setHand = Rules.TESOURA;
			break;
		case "lagarto":
			setHand = Rules.LAGARTO;
			break;
		case "spock":
			setHand = Rules.SPOCK;
			break;
		}
		
		return setHand;
	}
}
