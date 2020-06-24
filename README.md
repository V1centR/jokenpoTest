# API REST Jokenpo

**Config project:** 
```sh
$ git clone https://github.com/V1centR/jokenpoTest.git
$ gradle build; gradle bootRun
```
**POST** localhost:8080/play
```sh
{
      "player1":[{"nome": "Miguel","hand": "papel"}],
      "player2":[{"nome": "Arthur","hand": "pedra"}]
}
```
**response:**
```sh
{
    "nome": "Miguel",
    "hand": "PAPEL",
    "status": "VENCEU"
}
```
