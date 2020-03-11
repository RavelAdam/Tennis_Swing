package tennis;

import java.util.EnumMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TennisGame {

    //Enum for scoring
    enum Score{
        LOVE,
        FIFTEEN,
        THIRTY,
        FORTY,
        ADVANTAGE,
        GAME;

        @Nullable
        public Score getNext() {
            return this.ordinal() < Score.values().length - 1
                    ? Score.values()[this.ordinal() + 1]
                    : null;
        };

        public int getOrdinal() { return this.ordinal(); }
    }

    //Player names and scores
    private Score m_redPlayerScore;
    private Score m_bluePlayerScore;
    private String m_redPlayerName;
    private String m_bluePlayerName;

    //Variable to display the score as strings (as EnumMap)
    EnumMap<Score, String> m_scoreDisplay;

    //Constructor
    public TennisGame(@NotNull String p_redPlayerName, @NotNull String p_bluePlayerName){

        //Initialize player names
        if (p_redPlayerName.isEmpty()) m_redPlayerName = "Red Player";
        else m_redPlayerName = p_redPlayerName;

        if (p_bluePlayerName.isEmpty()) m_bluePlayerName = "Blue Player";
        else m_bluePlayerName = p_bluePlayerName;

        //Initialize player scores
        m_redPlayerScore = m_bluePlayerScore = Score.LOVE;

        //Initialize the score display
        m_scoreDisplay = new EnumMap<Score, String>(Score.class);
        m_scoreDisplay.put(Score.LOVE, "0");
        m_scoreDisplay.put(Score.FIFTEEN, "15");
        m_scoreDisplay.put(Score.THIRTY, "30");
        m_scoreDisplay.put(Score.FORTY, "40");
    }

    //Default constructor (calls the constructor with empty strings as parameters)
    public TennisGame(){
        this("","");
    }

    //Names and scores getters
    public Score getRedPlayerScore() { return m_redPlayerScore; }
    public Score getBluePlayerScore() { return m_bluePlayerScore; }
    public String getRedPlayerName() { return m_redPlayerName; }
    public String getBluePlayerName() { return m_bluePlayerName; }

    //Names setters
    public void setRedPlayerName(String p_redPlayerName) { m_redPlayerName = p_redPlayerName; }
    public void setBluePlayerName(String p_bluePlayerName) { m_bluePlayerName = p_bluePlayerName; }

    //Red player scoring method
    public void redPlayerScored(){

        //Can't score if the player already won
        if (m_redPlayerScore!= Score.GAME){

            //Case where the red player just scored the game point without the advantage(40-0, 40-15, 40-30)
            if (m_redPlayerScore == Score.FORTY && m_redPlayerScore.getOrdinal() > m_bluePlayerScore.getOrdinal()) m_redPlayerScore = Score.GAME;

                //Case where the red player just scored when the blue player had the advantage (back to deuce)
            else if (m_bluePlayerScore == Score.ADVANTAGE) m_bluePlayerScore = Score.FORTY;

                /*General case : proceed to the next score value
                 * NB : this also works when the red player wins game point after getting the advantage*/
            else m_redPlayerScore = m_redPlayerScore.getNext();
        }
    }

    //Blue player scoring method (same logic)
    public void bluePlayerScored(){
        if (m_bluePlayerScore != Score.GAME) {
            if (m_bluePlayerScore == Score.FORTY && m_bluePlayerScore.getOrdinal() > m_redPlayerScore.getOrdinal()) m_bluePlayerScore = Score.GAME;
            else if (m_redPlayerScore == Score.ADVANTAGE) m_redPlayerScore = Score.FORTY;
            else m_bluePlayerScore = m_bluePlayerScore.getNext();
        }
    }

    //The score display method
    public String displayScore(){

        //Deuce
        if (m_redPlayerScore == Score.FORTY && m_bluePlayerScore == Score.FORTY) return "Deuce";

            //Advantage
        else if (m_redPlayerScore == Score.ADVANTAGE) return "Advantage : " + m_redPlayerName;
        else if (m_bluePlayerScore == Score.ADVANTAGE) return "Advantage : " + m_bluePlayerName;

            //Game
        else if (m_redPlayerScore == Score.GAME) return "Game : " + m_redPlayerName;
        else if (m_bluePlayerScore == Score.GAME) return "Game : " + m_bluePlayerName;

            // General case : "Red Score - Blue Score" (for example : 30 - 15)
        else return m_scoreDisplay.get(m_redPlayerScore) + " - " + m_scoreDisplay.get(m_bluePlayerScore);
    }

    //The game reset method
    public void resetGame(){
        m_redPlayerName = "Red Player";
        m_bluePlayerName = "Blue Player";
        m_redPlayerScore = Score.LOVE;
        m_bluePlayerScore = Score.LOVE;
    }
}
