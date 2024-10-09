package fr.matlaio.tennis.service;

import org.springframework.stereotype.Service;


@Service
public class TennisGameService {

  /**
   * Run the game from the sequence points given by prompt.
   *
   * @param sequence from user input
   * @return the game resolution
   */
  public String playGame(String sequence) {
    sequence = clearSequence(sequence);

    StringBuilder gameResults = new StringBuilder();

    int scorePlayerA = 0, scorePlayerB = 0;

    char[] points = sequence.toCharArray();
    for (int i = 0; i < points.length; i++) {
      char point = points[i];
      if (point == 'A') {
        scorePlayerA++;
      } else if (point == 'B') {
        scorePlayerB++;
      } else {
        throw new IllegalArgumentException(String.format("Bad characters in sequence : %s.", sequence));
      }

      gameResults.append(resolveGame(scorePlayerA, scorePlayerB)).append("\n");

      // check if the game is well finished
      if (isFinished(scorePlayerA, scorePlayerB) && i != (points.length - 1)) {
        throw new IllegalArgumentException("Bad input after the game resolution.");
      }
    }
    return gameResults.toString();
  }

  /**
   * Determine if the game is finished.
   * Verify the player point to finds.
   *
   * @param scorePlayerA score of player A
   * @param scorePlayerB score of player B
   * @return true is game is finished
   */
  private boolean isFinished(int scorePlayerA, int scorePlayerB) {
    return (scorePlayerA >= 4 && scorePlayerA - scorePlayerB >= 2) ||
           (scorePlayerB >= 4 && scorePlayerB - scorePlayerA >= 2);
  }

  /**
   * Format the game status for the printer.
   *
   * @param scorePlayerA score of player A
   * @param scorePlayerB score of player B
   * @return the current game status point as string.
   */
  private String resolveGame(int scorePlayerA, int scorePlayerB) {
    if ((scorePlayerA >= 4 || scorePlayerB >= 4) && (scorePlayerA != scorePlayerB)) {
      if (scorePlayerA >= 4 && scorePlayerA - scorePlayerB >= 2) {
        return "Player A wins the game";
      } else if (scorePlayerB >= 4 && scorePlayerB - scorePlayerA >= 2) {
        return "Player B wins the game";
      } else if (scorePlayerA > scorePlayerB) {
        return String.format("Player A : %s ADVANTAGE / Player B : %s", formatScore(3), formatScore(3));
      } else {
        return String.format("Player A : %s / Player B : %s ADVANTAGE", formatScore(3), formatScore(3));
      }
    } else {
      return String.format("Player A : %s / Player B : %s", formatScore(scorePlayerA), formatScore(scorePlayerB));
    }
  }

  /**
   * Format internal score to tennis score.
   *
   * @param score score to format
   * @return the score following tennis rule 15,30,40 ...
   */
  private String formatScore(int score) {
    // Deuce score
    if (score >= 4) return "40";

    // score without deuce
    return switch (score) {
      case 0 -> "0";
      case 1 -> "15";
      case 2 -> "30";
      case 3 -> "40";
      default -> throw new IllegalStateException(String.format("Unexpected score value: %s.", score));
    };
  }

  /**
   * Clear the given string to remove useless space at beginning and at the end.
   *
   * @param sequence string to format
   * @return cleaned string
   */
  private String clearSequence(String sequence) {
    return sequence.trim();
  }


}
