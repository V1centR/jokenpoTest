package br.com.jokenpo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	private Map<String, String> verificarJogada(@RequestBody ObjectNode data) {
		
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
		
			
		if(execHandPlayer1.ganha(execHandPlayer2) && execHandPlayer1.ganha(execHandPlayer3)) {			
			
			return responseGame(handPlayer1Name, execHandPlayer1, "VENCEU",false);
			
		}else if(execHandPlayer2.ganha(execHandPlayer1) && execHandPlayer2.ganha(execHandPlayer3)){
			
			return responseGame(handPlayer2Name, execHandPlayer2, "VENCEU", false);
				
		} else if(execHandPlayer3.ganha(execHandPlayer1) && execHandPlayer3.ganha(execHandPlayer2)){
			
			return responseGame(handPlayerName3, execHandPlayer3, "VENCEU", false);
			
		} else {
			
			return responseGame(handPlayerName3, execHandPlayer3, "VENCEU", true);

		}

}	
	//@GetMapping(value="/doubledata", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> responseGame(String name, Object hand, String status, boolean draw){
		
		Map<String, String> coordinates = new HashMap<>();
	    
	    if(!draw) {
	    	
	    	coordinates.put("Player", name);
		    coordinates.put("jogada", hand.toString());
		    coordinates.put("status", "VENCEDOR");
	    	
	    }else {
	    	coordinates.put("status", "EMPATE!");
	    	coordinates.put("jogada", hand.toString());
	    }
		
		return coordinates;
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
