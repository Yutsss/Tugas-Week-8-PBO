public class Place {
  private String details;
  public Place northExit;
  public Place southExit;
  public Place eastExit;
  public Place westExit;

  public Place(String details) {
    this.details = details;
  }

  public void defineExits(Place north, Place east, Place south, Place west) {
    if (north != null) {
      northExit = north;
    }
    if (east != null) {
      eastExit = east;
    }
    if (south != null) {
      southExit = south;
    }
    if (west != null) {
      westExit = west;
    }
  }

  public String getDetails() {
    return details;
  }
}