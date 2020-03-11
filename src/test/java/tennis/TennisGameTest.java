package tennis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TennisGameTest {
    @Test
    void TestConstructor() {
        // 1) General case : 2 strings as parameters (should be "Alice" vs "Bob")
        TennisGame generalCase = new TennisGame("Alice", "Bob");
        assertEquals ("Alice", generalCase.getRedPlayerName());
        assertEquals ("Bob", generalCase.getBluePlayerName());
        assertEquals (TennisGame.Score.LOVE, generalCase.getRedPlayerScore());
        assertEquals (TennisGame.Score.LOVE, generalCase.getBluePlayerScore());

        // 2) Specific case 1 : Blue player field left empty (should be "Alice" vs "Blue Player")
        TennisGame  blueFieldEmpty = new TennisGame("Alice", "");
        assertEquals ("Alice", blueFieldEmpty.getRedPlayerName());
        assertEquals ("Blue Player", blueFieldEmpty.getBluePlayerName());

        // 3) Specific case 2 : Red player field left empty (should be "Red Player" vs "Bob")
        TennisGame  redFieldEmpty = new TennisGame("", "Bob");
        assertEquals ("Red Player", redFieldEmpty.getRedPlayerName());
        assertEquals ("Bob", redFieldEmpty.getBluePlayerName());

        // 4) Specific case 3 : Both fields are empty (should be "Red Player" vs "Blue Player")
        TennisGame  bothFieldEmpty = new TennisGame("", "");
        assertEquals ("Red Player", bothFieldEmpty.getRedPlayerName());
        assertEquals ("Blue Player", bothFieldEmpty.getBluePlayerName());

        // 5) Specific case 4 : Default constructor (same result as if both fields were empty)
        TennisGame  defaultConstructor = new TennisGame();
        assertEquals ("Red Player", defaultConstructor.getRedPlayerName());
        assertEquals ("Blue Player", defaultConstructor.getBluePlayerName());
    }

    @Test
    void TestScoring(){
        /* 1) General case 1 : Red player wins the game (40-0 -> Game)
         *  + an additional test to try to score even after winning the game (the score should stay as "GAME")*/
        TennisGame generalCaseRedWin = new TennisGame();
        assertEquals (TennisGame.Score.LOVE, generalCaseRedWin.getRedPlayerScore());
        generalCaseRedWin.redPlayerScored();
        assertEquals (TennisGame.Score.FIFTEEN, generalCaseRedWin.getRedPlayerScore());
        generalCaseRedWin.redPlayerScored();
        assertEquals (TennisGame.Score.THIRTY, generalCaseRedWin.getRedPlayerScore());
        generalCaseRedWin.redPlayerScored();
        assertEquals (TennisGame.Score.FORTY, generalCaseRedWin.getRedPlayerScore());
        generalCaseRedWin.redPlayerScored();
        assertEquals (TennisGame.Score.GAME, generalCaseRedWin.getRedPlayerScore());
        generalCaseRedWin.redPlayerScored();
        assertEquals (TennisGame.Score.GAME, generalCaseRedWin.getRedPlayerScore());

        /* 2) General case 2 : Blue player wins the game (30-40 -> Game)
         *  + an additional test to try to score even after winning the game (the score should stay as "GAME")*/
        TennisGame generalCaseBlueWin = new TennisGame();
        assertEquals (TennisGame.Score.LOVE, generalCaseBlueWin.getBluePlayerScore());
        generalCaseBlueWin.bluePlayerScored();
        assertEquals (TennisGame.Score.FIFTEEN, generalCaseBlueWin.getBluePlayerScore());
        generalCaseBlueWin.bluePlayerScored();
        assertEquals (TennisGame.Score.THIRTY, generalCaseBlueWin.getBluePlayerScore());
        generalCaseBlueWin.bluePlayerScored();
        assertEquals (TennisGame.Score.FORTY, generalCaseBlueWin.getBluePlayerScore());
        generalCaseBlueWin.redPlayerScored();
        assertEquals (TennisGame.Score.FIFTEEN, generalCaseBlueWin.getRedPlayerScore());
        assertEquals (TennisGame.Score.FORTY, generalCaseBlueWin.getBluePlayerScore());
        generalCaseBlueWin.redPlayerScored();
        assertEquals (TennisGame.Score.THIRTY, generalCaseBlueWin.getRedPlayerScore());
        assertEquals (TennisGame.Score.FORTY, generalCaseBlueWin.getBluePlayerScore());
        generalCaseBlueWin.bluePlayerScored();
        assertEquals (TennisGame.Score.GAME, generalCaseBlueWin.getBluePlayerScore());
        generalCaseBlueWin.bluePlayerScored();
        assertEquals (TennisGame.Score.GAME, generalCaseBlueWin.getBluePlayerScore());

        // 3) Specific case 1 : Deuce -> Advantage : Red -> Game : Red
        TennisGame specificCaseRedWinDeuce = new TennisGame();
        specificCaseRedWinDeuce.redPlayerScored(); //15-0
        specificCaseRedWinDeuce.redPlayerScored(); //30-0
        specificCaseRedWinDeuce.redPlayerScored(); //40-0
        specificCaseRedWinDeuce.bluePlayerScored(); //40-15
        specificCaseRedWinDeuce.bluePlayerScored(); //40-30
        specificCaseRedWinDeuce.bluePlayerScored(); //40-40 (Deuce)
        assertEquals (TennisGame.Score.FORTY, specificCaseRedWinDeuce.getRedPlayerScore());
        assertEquals (TennisGame.Score.FORTY, specificCaseRedWinDeuce.getBluePlayerScore());
        specificCaseRedWinDeuce.redPlayerScored(); //A-40 (Advantage : Red);
        assertEquals (TennisGame.Score.ADVANTAGE, specificCaseRedWinDeuce.getRedPlayerScore());
        specificCaseRedWinDeuce.redPlayerScored(); //G-40 (Game : Red);
        assertEquals (TennisGame.Score.GAME, specificCaseRedWinDeuce.getRedPlayerScore());

        // 4) Specific case 2 : Deuce -> Advantage : Red -> Deuce -> Advantage Blue -> Game : Blue
        TennisGame specificCaseBlueWinDeuce = new TennisGame();
        specificCaseBlueWinDeuce.redPlayerScored(); //15-0
        specificCaseBlueWinDeuce.redPlayerScored(); //30-0
        specificCaseBlueWinDeuce.redPlayerScored(); //40-0
        specificCaseBlueWinDeuce.bluePlayerScored(); //40-15
        specificCaseBlueWinDeuce.bluePlayerScored(); //40-30
        specificCaseBlueWinDeuce.bluePlayerScored(); //40-40 (Deuce)
        assertEquals (TennisGame.Score.FORTY, specificCaseBlueWinDeuce.getRedPlayerScore());
        assertEquals (TennisGame.Score.FORTY, specificCaseBlueWinDeuce.getBluePlayerScore());
        specificCaseBlueWinDeuce.redPlayerScored(); //A-40 (Advantage : Red);
        assertEquals (TennisGame.Score.ADVANTAGE, specificCaseBlueWinDeuce.getRedPlayerScore());
        specificCaseBlueWinDeuce.bluePlayerScored(); //Blue scored : 40-40 (Deuce)
        assertEquals (TennisGame.Score.FORTY, specificCaseBlueWinDeuce.getRedPlayerScore());
        assertEquals (TennisGame.Score.FORTY, specificCaseBlueWinDeuce.getBluePlayerScore());
        specificCaseBlueWinDeuce.bluePlayerScored(); //40-A (Advantage : Blue);
        assertEquals (TennisGame.Score.ADVANTAGE, specificCaseBlueWinDeuce.getBluePlayerScore());
        specificCaseBlueWinDeuce.bluePlayerScored(); //40-A (Game : Blue);
        assertEquals (TennisGame.Score.GAME, specificCaseBlueWinDeuce.getBluePlayerScore());
    }

    @Test
    void TestDisplay(){
        //The same tests as the scoring ones, but this time the display function will be tested
        // 1) General case 1 : Red player wins the game (40-0 -> Game)
        TennisGame generalCaseRedWin = new TennisGame();
        assertEquals ("0 - 0", generalCaseRedWin.displayScore());
        generalCaseRedWin.redPlayerScored();
        assertEquals ("15 - 0", generalCaseRedWin.displayScore());
        generalCaseRedWin.redPlayerScored();
        assertEquals ("30 - 0", generalCaseRedWin.displayScore());
        generalCaseRedWin.redPlayerScored();
        assertEquals ("40 - 0", generalCaseRedWin.displayScore());
        generalCaseRedWin.redPlayerScored();
        assertEquals ("Game : Red Player", generalCaseRedWin.displayScore());

        // 2) General case 2 : Blue player wins the game (40-30 -> Game)
        TennisGame generalCaseBlueWin = new TennisGame();
        assertEquals ("0 - 0", generalCaseBlueWin.displayScore());
        generalCaseBlueWin.bluePlayerScored();
        assertEquals ("0 - 15", generalCaseBlueWin.displayScore());
        generalCaseBlueWin.bluePlayerScored();
        assertEquals ("0 - 30", generalCaseBlueWin.displayScore());
        generalCaseBlueWin.bluePlayerScored();
        assertEquals ("0 - 40", generalCaseBlueWin.displayScore());
        generalCaseBlueWin.redPlayerScored();
        assertEquals ("15 - 40", generalCaseBlueWin.displayScore());
        generalCaseBlueWin.redPlayerScored();
        assertEquals ("30 - 40", generalCaseBlueWin.displayScore());
        generalCaseBlueWin.bluePlayerScored();
        assertEquals ("Game : Blue Player", generalCaseBlueWin.displayScore());

        // Specific case 1 : Deuce -> Advantage : Red -> Game : Red
        TennisGame specificCaseRedWinDeuce = new TennisGame();
        assertEquals ("0 - 0", specificCaseRedWinDeuce.displayScore());
        specificCaseRedWinDeuce.redPlayerScored();
        assertEquals ("15 - 0", specificCaseRedWinDeuce.displayScore());
        specificCaseRedWinDeuce.redPlayerScored();
        assertEquals ("30 - 0", specificCaseRedWinDeuce.displayScore());
        specificCaseRedWinDeuce.redPlayerScored();
        assertEquals ("40 - 0", specificCaseRedWinDeuce.displayScore());
        specificCaseRedWinDeuce.bluePlayerScored();
        assertEquals ("40 - 15", specificCaseRedWinDeuce.displayScore());
        specificCaseRedWinDeuce.bluePlayerScored();
        assertEquals ("40 - 30", specificCaseRedWinDeuce.displayScore());
        specificCaseRedWinDeuce.bluePlayerScored();
        assertEquals ("Deuce", specificCaseRedWinDeuce.displayScore());
        specificCaseRedWinDeuce.redPlayerScored();
        assertEquals ("Advantage : Red Player", specificCaseRedWinDeuce.displayScore());
        specificCaseRedWinDeuce.redPlayerScored();
        assertEquals ("Game : Red Player", specificCaseRedWinDeuce.displayScore());

        // Specific case 2 : Deuce -> Advantage : Red -> Deuce -> Advantage Blue -> Game : Blue
        TennisGame specificCaseBlueWinDeuce = new TennisGame();
        assertEquals ("0 - 0", specificCaseBlueWinDeuce.displayScore());
        specificCaseBlueWinDeuce.bluePlayerScored();
        assertEquals ("0 - 15", specificCaseBlueWinDeuce.displayScore());
        specificCaseBlueWinDeuce.bluePlayerScored();
        assertEquals ("0 - 30", specificCaseBlueWinDeuce.displayScore());
        specificCaseBlueWinDeuce.bluePlayerScored();
        assertEquals ("0 - 40", specificCaseBlueWinDeuce.displayScore());
        specificCaseBlueWinDeuce.redPlayerScored();
        assertEquals ("15 - 40", specificCaseBlueWinDeuce.displayScore());
        specificCaseBlueWinDeuce.redPlayerScored();
        assertEquals ("30 - 40", specificCaseBlueWinDeuce.displayScore());
        specificCaseBlueWinDeuce.redPlayerScored();
        assertEquals ("Deuce", specificCaseBlueWinDeuce.displayScore());
        specificCaseBlueWinDeuce.redPlayerScored();
        assertEquals ("Advantage : Red Player", specificCaseBlueWinDeuce.displayScore());
        specificCaseBlueWinDeuce.bluePlayerScored();
        assertEquals ("Deuce", specificCaseBlueWinDeuce.displayScore());
        specificCaseBlueWinDeuce.bluePlayerScored();
        assertEquals ("Advantage : Blue Player", specificCaseBlueWinDeuce.displayScore());
        specificCaseBlueWinDeuce.bluePlayerScored();
        assertEquals ("Game : Blue Player", specificCaseBlueWinDeuce.displayScore());
    }

    @Test
    void TestReset(){
        //General case : reset the names and scores to their default values
        TennisGame gameToReset = new TennisGame("Alice", "Bob");
        gameToReset.redPlayerScored(); //15-0
        gameToReset.redPlayerScored(); //30-0
        gameToReset.redPlayerScored(); //40-0
        gameToReset.bluePlayerScored(); //40-15
        gameToReset.bluePlayerScored(); //40-30
        assertEquals ("Alice", gameToReset.getRedPlayerName());
        assertEquals ("Bob", gameToReset.getBluePlayerName());
        assertEquals(TennisGame.Score.FORTY, gameToReset.getRedPlayerScore());
        assertEquals(TennisGame.Score.THIRTY, gameToReset.getBluePlayerScore());
        gameToReset.resetGame();
        assertEquals ("Red Player", gameToReset.getRedPlayerName());
        assertEquals ("Blue Player", gameToReset.getBluePlayerName());
        assertEquals(TennisGame.Score.LOVE, gameToReset.getRedPlayerScore());
        assertEquals(TennisGame.Score.LOVE, gameToReset.getBluePlayerScore());


    }
}