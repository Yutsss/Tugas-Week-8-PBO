public class Action {
  private String mainCommand;
  private String subCommand;

  public Action(String mainCommand, String subCommand) {
    this.mainCommand = mainCommand;
    this.subCommand = subCommand;
  }

  public String getMainCommand() {
    return mainCommand;
  }

  public String getSubCommand() {
    return subCommand;
  }

  public boolean isUndefined() {
    return (mainCommand == null);
  }

  public boolean hasSubCommand() {
    return (subCommand != null);
  }
}