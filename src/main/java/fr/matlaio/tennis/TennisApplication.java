package fr.matlaio.tennis;

import fr.matlaio.tennis.service.TennisGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TennisApplication implements CommandLineRunner {

  @Autowired
  private TennisGameService tennisGameService;

  public static void main(String[] args) {
    SpringApplication.run(TennisApplication.class, args);
  }

  /**
   * Handle the argument given from user and then start game.
   *
   * @param args argument from console
   */
  @Override
  public void run(String... args) {
    if (args.length != 1) {
      throw new IllegalArgumentException("Missing tennis sequence. Example : ABBAAA");
    }
    String points = args[0];
    System.out.println(tennisGameService.playGame(points));
  }
}
