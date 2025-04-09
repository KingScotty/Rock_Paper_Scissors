import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;
    private JTextArea resultTextArea;
    private JTextField playerScoreField;
    private JTextField computerScoreField;
    private JTextField tieScoreField;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //makes it load as 75% the screen size, probably.
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize.width * 3 / 4, screenSize.height * 3 / 4);
        setLocationRelativeTo(null);
        JLabel titleLabel = new JLabel("Esoteric Rock Paper Scissors");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centers the whole label in the NO

        ImageIcon rawIcon = new ImageIcon("src/rockpaperscissors.jpg");
        Image scaledImage = rawIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon imageicon = new ImageIcon(scaledImage);

        titleLabel.setIcon(imageicon);
        titleLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        setIconImage(imageicon.getImage());

        add(titleLabel, BorderLayout.NORTH);
        //results
        resultTextArea = new JTextArea(10, 10);
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("Monospaced", Font.PLAIN, 24));
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        add(scrollPane, BorderLayout.CENTER);

        //button stuff
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Make your move"));

        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");
        JButton quitButton = new JButton("Quit");

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        //makes the buttons work
        rockButton.addActionListener(e -> playRound(Move.ROCK)); //will add playRound later
        paperButton.addActionListener(e -> playRound(Move.PAPER));
       scissorsButton.addActionListener(e -> playRound(Move.SCISSORS));
        quitButton.addActionListener(e -> System.exit(0));
        //Score panel
        JPanel scorePanel = new JPanel( new GridLayout(3, 2));
        scorePanel.setBorder(BorderFactory.createTitledBorder("Score"));

        scorePanel.add(new JLabel("Player Wins:"));
        playerScoreField = new JTextField("0");
        playerScoreField.setEditable(false);
        scorePanel.add(playerScoreField);

        scorePanel.add(new JLabel("Computer Wins:"));
        computerScoreField = new JTextField("0");
        computerScoreField.setEditable(false);
        scorePanel.add(computerScoreField);

        scorePanel.add(new JLabel("Ties:"));
        tieScoreField = new JTextField("0");
        tieScoreField.setEditable(false);
        scorePanel.add(tieScoreField);
        //adding the score panel to the frame
        add(scorePanel, BorderLayout.EAST);
        //game logic


    }
    private void playRound(Move playerMove) {
        Move computerMove = Move.values()[new Random().nextInt(3)];
        String result = "";

        if (playerMove == computerMove) {
            ties++;
            result = "Tie!";
        } else if ((playerMove == Move.ROCK && computerMove == Move.SCISSORS) ||
                (playerMove == Move.PAPER && computerMove == Move.ROCK) ||
                (playerMove == Move.SCISSORS && computerMove == Move.PAPER)) {
            playerWins++;
            result = playerMove + " beats " + computerMove + ". Player wins!";
        } else {
            computerWins++;
            result = computerMove + " beats " + playerMove + ". Computer wins!";
        }

        resultTextArea.append(result + "\n");
        updateScoreFields();
    }

    private void updateScoreFields() {
        playerScoreField.setText(String.valueOf(playerWins));
        computerScoreField.setText(String.valueOf(computerWins));
        tieScoreField.setText(String.valueOf(ties));
    }
    public enum Move {
        ROCK, PAPER, SCISSORS
    }

}