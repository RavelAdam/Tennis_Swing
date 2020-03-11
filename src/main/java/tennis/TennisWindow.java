package tennis;

import java.awt.*;
import javax.swing.*;
import org.jdesktop.swingx.prompt.PromptSupport;

public class TennisWindow extends JFrame {
    private JPanel m_panelMain;
    private JTextField m_fieldNameRedPlayer;
    private JTextField m_fieldNameBluePlayer;
    private JButton m_buttonScoreRedPlayer;
    private JButton m_buttonScoreBluePlayer;
    private JButton m_buttonGameStartReset;
    private JLabel m_labelScoreGame;

    public TennisWindow() {

        //Set basic info
        setTitle("Tennis with Java Swing");
        setLocationRelativeTo(null);
        setContentPane(m_panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set initial labels/placeholders
        m_buttonScoreRedPlayer.setText("Score!");
        m_buttonScoreBluePlayer.setText("Score!");
        PromptSupport.setPrompt("Enter a player name...", m_fieldNameRedPlayer);
        PromptSupport.setForeground(Color.RED, m_fieldNameRedPlayer);
        PromptSupport.setPrompt("Enter a player name...", m_fieldNameBluePlayer);
        PromptSupport.setForeground(Color.BLUE, m_fieldNameBluePlayer);

        //Reset the info displayed in the window
        resetWindow();

        //Pack all UI elements to fit the window and display it
        pack();
        setVisible(true);
    }

    //Buttons & fields getters
    public JTextField getFieldNameRedPlayer() { return m_fieldNameRedPlayer; }
    public JTextField getFieldNameBluePlayer() { return m_fieldNameBluePlayer; }
    public JButton getButtonScoreRedPlayer() { return m_buttonScoreRedPlayer; }
    public JButton getButtonScoreBluePlayer() { return m_buttonScoreBluePlayer; }
    public JButton getButtonGameStartReset() { return m_buttonGameStartReset; }
    public JLabel getLabelScoreGame() { return m_labelScoreGame; }

    //Score label, player names & button text setters
    public void setLabelScore(String p_text){ m_labelScoreGame.setText(p_text); }
    public void setFieldNameRedPlayer(String p_text) { m_fieldNameRedPlayer.setText(p_text); }
    public void setFieldNameBluePlayer(String p_text) { m_fieldNameBluePlayer.setText(p_text); }
    public void setButtonStartResetText(String p_text) { m_buttonGameStartReset.setText(p_text); }

    //Lock/Unlock score buttons
    public void lockScoreButtons(){
        m_buttonScoreRedPlayer.setEnabled(false);
        m_buttonScoreBluePlayer.setEnabled(false);
    }
    public void unlockScoreButtons(){
        m_buttonScoreRedPlayer.setEnabled(true);
        m_buttonScoreBluePlayer.setEnabled(true);
    }

    //Lock/Unlock player name fields
    public void lockNameFields(){
        m_fieldNameRedPlayer.setEnabled(false);
        m_fieldNameBluePlayer.setEnabled(false);
    }
    public void unlockNameFields(){
        m_fieldNameRedPlayer.setEnabled(true);
        m_fieldNameBluePlayer.setEnabled(true);
    }
    public void emptyNameFields(){
        m_fieldNameRedPlayer.setText("");
        m_fieldNameBluePlayer.setText("");
    }

    //Reset the window (either when you start the game or reset it)
    public void resetWindow(){
        getLabelScoreGame().setText("Enter player names, then press \"Start the game\"");
        unlockNameFields();
        emptyNameFields();
        lockScoreButtons();
        getButtonGameStartReset().setText("Start the game");
    }
}
