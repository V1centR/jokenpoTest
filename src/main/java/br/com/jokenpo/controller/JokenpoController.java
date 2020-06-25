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
	
	boolean Player3Present = false;
	private Rules execHandPlayer3 = null;
	private String handPlayerName3 = null;
	
	@PostMapping(value="/play", produces = MediaType.APPLICATION_JSON_VALUE)
	private Player verificarJogada(@RequestBody ObjectNode data) {
		
		String handPlayer1 = extUserInfo(data,"player1","hand");
		String handPlayer1Name = extUserInfo(data,"player1","nome");
		
		String handPlayer2 = extUserInfo(data,"player2","hand");
		String handPlayer2Name = extUserInfo(data,"player2","nome");
		
		//Setup Players
		
		//Player player2 = new Player();
		
		Rules execHandPlayer1 = setHandPlayer(handPlayer1);
		Rules execHandPlayer2 = setHandPlayer(handPlayer2);
			
		//Detect terceiro player
		if(data.size() == 3) {
			
			System.out.println("DATA SIZE::::: " + data.size());
			
			String handPlayer3 = data.get("player3").get(0).get("hand").asText();
			handPlayerName3 = data.get("player3").get(0).get("nome").asText();
			
			execHandPlayer3 = setHandPlayer(handPlayer3);
			Player3Present = true;
		}else {
			execHandPlayer3 = null;
		}
		//################
		
			
		if(execHandPlayer1.ganha(execHandPlayer2) || execHandPlayer1.ganha(execHandPlayer3)) {			
			
			Player namedPlayer = setupPlayer(handPlayer1Name, execHandPlayer1, "VENCEU");
			
			return namedPlayer;
			
		}else if(execHandPlayer2.ganha(execHandPlayer1) || execHandPlayer1.ganha(execHandPlayer3)){
			
			Player namedPlayer = setupPlayer(handPlayer2Name, execHandPlayer2, "VENCEU");
			
			return namedPlayer;
				
		} else if(execHandPlayer3.ganha(execHandPlayer1) || execHandPlayer3.ganha(execHandPlayer2)){
			
			Player namedPlayer = setupPlayer(handPlayerName3, execHandPlayer3, "VENCEU");
			
			return namedPlayer;
			
		} else {
			
			
			Player namedPlayer = new Player();
			
			namedPlayer.setMessage("O jogo empatou!");
			
			return namedPlayer;
		}

}	
	
	public Player setupPlayer(String name, Object hand, String status) {
		
		Player player = new Player();
		
		player.setHand(hand.toString());
		player.setNome(name);
		player.setStatus("VENCEU");
		
		return player;
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
	
	private String extUserInfo(ObjectNode data, String player, String campo) {
		
		return data.get(player).get(0).get(campo).asText();
	}
}
