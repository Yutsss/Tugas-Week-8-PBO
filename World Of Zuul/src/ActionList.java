public class ActionList {
  private static final String[] validActions = {
      "move", "exit", "info"
  };

  public ActionList() {
  }

  public boolean isValidAction(String action) {
    for (String validAction : validActions) {
      if (validAction.equals(action)) {
        return true;
      }
    }
    return false;
  }
}