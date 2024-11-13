public class Adventure {
  private InputProcessor inputProcessor;
  private Place currentPlace;

  public Adventure() {
    setupPlaces();
    inputProcessor = new InputProcessor();
  }

  private void setupPlaces() {
    Place entry, hall, bar, lab, directorRoom, library, hiddenRoom, garden, dining, roof;

    entry = new Place("at the majestic entry of a historic university, cloaked in mystique.");
    hall = new Place("inside a grand lecture hall with dust-covered chalkboards and dim lights.");
    bar = new Place("in a charming bar on campus with a jazzy ambiance.");
    lab = new Place("in a state-of-the-art computer lab filled with blinking screens.");
    directorRoom = new Place("in the cluttered office of the head professor, lined with old books.");
    library = new Place("in an expansive library with countless shelves of ancient books.");
    hiddenRoom = new Place("in a secret room beneath the library, with glowing crystals and strange symbols.");
    garden = new Place("in a serene garden with colorful blooms and chirping birds.");
    dining = new Place("in the lively cafeteria filled with the scent of fresh coffee.");
    roof = new Place("on the university rooftop, offering a star-lit view of the campus.");

    entry.defineExits(null, hall, garden, bar);
    hall.defineExits(null, null, null, entry);
    bar.defineExits(null, entry, null, null);
    garden.defineExits(entry, library, dining, null);
    dining.defineExits(garden, null, null, null);
    lab.defineExits(directorRoom, roof, null, null);
    directorRoom.defineExits(null, lab, null, null);
    library.defineExits(null, hiddenRoom, null, garden);
    hiddenRoom.defineExits(null, null, library, null);
    roof.defineExits(null, null, null, lab);

    currentPlace = entry;
  }

  public void startGame() {
    printWelcomeMessage();

    boolean gameRunning = true;
    while (gameRunning) {
      Action action = inputProcessor.interpretAction();
      gameRunning = !processAction(action);
    }
    System.out.println("Thanks for playing. Goodbye!");
  }

  private void printWelcomeMessage() {
    System.out.println();
    System.out.println("Welcome to the World of Zuul!");
    System.out.println("A simple yet captivating text adventure.");
    System.out.println("Type 'info' if you need assistance.");
    System.out.println();
    System.out.println("You are currently " + currentPlace.getDetails());

    printExits();
  }

  private boolean processAction(Action action) {
    boolean exitGame = false;

    if (action.isUndefined()) {
      System.out.println("Unknown command...");
      return false;
    }

    String actionName = action.getMainCommand();
    if (actionName.equals("info")) {
      printHelp();
    } else if (actionName.equals("move")) {
      navigate(action);
    } else if (actionName.equals("exit")) {
      exitGame = true;
    }

    return exitGame;
  }

  private void printHelp() {
    System.out.println("Lost? Wandering around the campus...");
    System.out.println();
    System.out.println("Available commands are:");
    System.out.println("   move exit info");
  }

  private void navigate(Action action) {
    if (!action.hasSubCommand()) {
      System.out.println("Move where?");
      return;
    }

    String direction = action.getSubCommand();

    Place nextPlace = null;
    if (direction.equals("north")) {
      nextPlace = currentPlace.northExit;
    } else if (direction.equals("east")) {
      nextPlace = currentPlace.eastExit;
    } else if (direction.equals("south")) {
      nextPlace = currentPlace.southExit;
    } else if (direction.equals("west")) {
      nextPlace = currentPlace.westExit;
    }

    if (nextPlace == null) {
      System.out.println("There's no path that way!");
    } else {
      currentPlace = nextPlace;
      System.out.println("You are now " + currentPlace.getDetails());
      printExits();
    }
  }

  private void printExits() {
    System.out.print("Available directions: ");
    if (currentPlace.northExit != null) {
      System.out.print("north ");
    }
    if (currentPlace.eastExit != null) {
      System.out.print("east ");
    }
    if (currentPlace.southExit != null) {
      System.out.print("south ");
    }
    if (currentPlace.westExit != null) {
      System.out.print("west ");
    }
    System.out.println();
  }
}