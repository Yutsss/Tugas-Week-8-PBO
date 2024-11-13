import java.util.Scanner;

public class InputProcessor {
  private ActionList actions;
  private Scanner scanner;

  public InputProcessor() {
    actions = new ActionList();
    scanner = new Scanner(System.in);
  }

  public Action interpretAction() {
    String userInput;
    String primaryCommand = null;
    String secondaryCommand = null;

    System.out.print("> ");

    userInput = scanner.nextLine();

    Scanner tokenizer = new Scanner(userInput);
    if (tokenizer.hasNext()) {
      primaryCommand = tokenizer.next();
      if (tokenizer.hasNext()) {
        secondaryCommand = tokenizer.next();
      }
    }

    if (actions.isValidAction(primaryCommand)) {
      return new Action(primaryCommand, secondaryCommand);
    } else {
      return new Action(null, secondaryCommand);
    }
  }
}