package tennis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TennisController {

    //The controller has access to the game window and the tennis game
    TennisWindow m_window;
    TennisGame m_game;

    //Default constructor
    public TennisController(){
        m_window = new TennisWindow();
        m_game = new TennisGame();

        //Add button handling
        m_window.getButtonGameStartReset().addActionListener(new handlingButtonStartReset());
        m_window.getButtonScoreRedPlayer().addActionListener(new handlingButtonScoreRed());
        m_window.getButtonScoreBluePlayer().addActionListener(new handlingButtonScoreBlue());
    }

    public class handlingButtonStartReset implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //Start the match
            if (m_window.getButtonGameStartReset().getText().equals("Start the game")){

                //Set the names
                if (!m_window.getFieldNameRedPlayer().getText().isEmpty())
                    m_game.setRedPlayerName(m_window.getFieldNameRedPlayer().getText());
                m_window.setFieldNameRedPlayer(m_game.getRedPlayerName()); //"Red Player" by default

                if (!m_window.getFieldNameBluePlayer().getText().isEmpty())
                    m_game.setBluePlayerName(m_game.getBluePlayerName());
                m_window.setFieldNameBluePlayer(m_game.getBluePlayerName()); //"Blue Player" by default

                //Enable the scoring buttons & lock the name fields down
                m_window.lockNameFields();
                m_window.unlockScoreButtons();

                //Display the current score & the middle button
                updateScoreDisplay();
                m_window.setButtonStartResetText("Reset the game");
            }

            //or Reset the game
            else{
                m_game.resetGame();
                m_window.resetWindow();
            }
        }
    }

    //Handling the case where the red player scores
    public class handlingButtonScoreRed implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            m_game.redPlayerScored();
            if (m_game.getRedPlayerScore() == TennisGame.Score.GAME)
                m_window.lockScoreButtons();
            updateScoreDisplay();
        }
    }

    //Handling the case where the blue player scores
    public class handlingButtonScoreBlue implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            m_game.bluePlayerScored();
            if (m_game.getBluePlayerScore() == TennisGame.Score.GAME)
                m_window.lockScoreButtons();
            updateScoreDisplay();
        }
    }

    //Update the score
    private void updateScoreDisplay(){ m_window.setLabelScore(m_game.displayScore()); }
}
