package facilities;

public class Facility {
  // name of facility
  String name;

  // constructor for creating a new Facility with a name given

  public Facility(String name) {
    // sets the name of the facility
    this.name = name;
  }

  // returns the name of the facility
  public String getName() {
    return name;
  }

  /*
  - returns the building cost of the facility
  - here, it's set to a default value of 0
  - method can be overridden in subclasses to return specific costs*/
  public int getBuildingCost() {
    return 0;
  }
}
