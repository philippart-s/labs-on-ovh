package fr.wilda;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine.Command;

@TopCommand
@Command(mixinStandardHelpOptions = true, subcommands = {Create.class, Delete.class, List.class})
public class SchoolEnv implements Runnable {

  @Override
  public void run() {
    // main entry, uniquely to display help message
  }
}
