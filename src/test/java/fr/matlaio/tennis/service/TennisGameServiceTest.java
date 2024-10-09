package fr.matlaio.tennis.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TennisGameServiceTest {

  private static Stream<Arguments> playGame_paramScenarios() {
    return Stream.of(
        Arguments.of("AAAA",
            """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 40 / Player B : 0
                Player A wins the game
                """),
        Arguments.of("BBBB",
            """
                Player A : 0 / Player B : 15
                Player A : 0 / Player B : 30
                Player A : 0 / Player B : 40
                Player B wins the game
                """),
        Arguments.of("ABABAA",
            """
                Player A : 15 / Player B : 0
                Player A : 15 / Player B : 15
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Player A wins the game
                """),
        Arguments.of("BBAABB",
            """
                Player A : 0 / Player B : 15
                Player A : 0 / Player B : 30
                Player A : 15 / Player B : 30
                Player A : 30 / Player B : 30
                Player A : 30 / Player B : 40
                Player B wins the game
                """),
        Arguments.of("AAABBBAA",
            """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 40 / Player B : 0
                Player A : 40 / Player B : 15
                Player A : 40 / Player B : 30
                Player A : 40 / Player B : 40
                Player A : 40 ADVANTAGE / Player B : 40
                Player A wins the game
                """),
        Arguments.of("BBBAAABB",
            """
                Player A : 0 / Player B : 15
                Player A : 0 / Player B : 30
                Player A : 0 / Player B : 40
                Player A : 15 / Player B : 40
                Player A : 30 / Player B : 40
                Player A : 40 / Player B : 40
                Player A : 40 / Player B : 40 ADVANTAGE
                Player B wins the game
                """),
        Arguments.of("ABABABABABABABABABABAA",
            """
                Player A : 15 / Player B : 0
                Player A : 15 / Player B : 15
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Player A : 40 / Player B : 40
                Player A : 40 ADVANTAGE / Player B : 40
                Player A : 40 / Player B : 40
                Player A : 40 ADVANTAGE / Player B : 40
                Player A : 40 / Player B : 40
                Player A : 40 ADVANTAGE / Player B : 40
                Player A : 40 / Player B : 40
                Player A : 40 ADVANTAGE / Player B : 40
                Player A : 40 / Player B : 40
                Player A : 40 ADVANTAGE / Player B : 40
                Player A : 40 / Player B : 40
                Player A : 40 ADVANTAGE / Player B : 40
                Player A : 40 / Player B : 40
                Player A : 40 ADVANTAGE / Player B : 40
                Player A : 40 / Player B : 40
                Player A : 40 ADVANTAGE / Player B : 40
                Player A wins the game
                """)
    );
  }

  @ParameterizedTest
  @MethodSource
  void playGame_paramScenarios(String input, String expected) {
    TennisGameService tennisGameService = new TennisGameService();
    assertEquals(expected, tennisGameService.playGame(input));
  }

  @Test
  void playGame_badCharacter_illegalArgumentException(){
    TennisGameService tennisGameService = new TennisGameService();
    assertThrowsExactly(IllegalArgumentException.class, () -> tennisGameService.playGame("AAACBA"));
  }

  @Test
  void playGame_overmuchPoints_illegalArgumentException(){
    TennisGameService tennisGameService = new TennisGameService();
    assertThrowsExactly(IllegalArgumentException.class, () -> tennisGameService.playGame("AABBAABBA"));
  }

}