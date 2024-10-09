package fr.matlaio.tennis;

import fr.matlaio.tennis.service.TennisGameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(OutputCaptureExtension.class)
class TennisApplicationTest {

  @Mock
  private TennisGameService tennisGameService;

  @InjectMocks
  private TennisApplication tennisApplication;

  @BeforeEach
  public void setUp() {
    openMocks(this);
  }

  @Test
  public void run_validInput_successGameOutput(CapturedOutput capturedOutput) {
    String inputGamePoints = "ABABAA";
    String expected = """
        Player A : 15 / Player B : 0
        Player A : 15 / Player B : 15
        Player A : 30 / Player B : 15
        Player A : 30 / Player B : 30
        Player A : 40 / Player B : 30
        Player A wins the game""";

    when(tennisGameService.playGame(inputGamePoints)).thenReturn(expected);
    tennisApplication.run(inputGamePoints);
    verify(tennisGameService, times(1)).playGame(inputGamePoints);
    assertTrue(capturedOutput.getOut().contains(expected));
  }

  @Test
  public void run_noInput_throwError() {
    String[] inputGamePoints = {};
    assertThrows(IllegalArgumentException.class, () -> tennisApplication.run(inputGamePoints));
  }

  @Test
  public void run_overMuchInputs_throwError() {
    String[] inputGamePoints = {"AAAA", "BBBB"};
    assertThrows(IllegalArgumentException.class, () -> tennisApplication.run(inputGamePoints));
  }

}