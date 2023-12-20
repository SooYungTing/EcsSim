package university;

import facilities.Facility;
import facilities.buildings.Building;

public class University {
  // Human resource department for handling staff and salaries.
  private HumanResource humanResource;
  // financial budget for the university
  private float budget;
  // handles buildings and facilities
  private Estate estate;
  // reputation of the university
  private int reputation;

  public Estate getEstate() {
    return estate;
  }

  public void setEstate(Estate estate) {
    this.estate = estate;
  }

  /*
  - Constructor for University
  - initializes budget, estate, reputation, and human resources*/

  public University(int funding) {
    this.budget = funding;
    this.estate = new Estate();
    this.reputation = 0;
    this.humanResource = new HumanResource();
  }

  // validate the type of facility (Hall, Lab, or Theatre) for construction
  private boolean sanitiseFacilityType(String type) {
    return type.equals("Hall")
        || type.equals("Lab")
        || type.equals("Theatre")
        || type.equals("Library");
  }

  /*
  - constructs a new facility of the specified type and name
  - deducts cost from the budget and increase the university's reputation
  - returns the created facility or null if it cannot be built*/

  public Facility build(String type, String name) {
    if (sanitiseFacilityType(type)) {
      Facility newFacility = estate.addFacility(type, name);
      int cost = newFacility.getBuildingCost();
      if (this.budget >= cost) {
        this.budget -= cost;
        System.out.println("Current Budget: " + this.budget);
        this.reputation += 100;
        System.out.println("Current Reputation: " + this.reputation);

        return newFacility;
      }
    } else {
      return null;
    }
    return null;
  }

  /*
  - upgrades a building and increase its level
  - throws an exception if building is not part of the university or if the budget is insufficient*/
  public void upgrade(Building building) throws Exception {
    if (!estate.contains(building)) {
      throw new Exception("Building is not part of the university.");
    }
    int upgradeCost = building.getUpgradeCost();
    if (upgradeCost == -1 || this.budget < upgradeCost) {
      throw new Exception(
          "Cannot upgrade building. Insufficient budget or building is at maximum level.");
    }
    building.increaseLevel();
    this.budget -= upgradeCost;
    System.out.println("Current Budget: " + this.budget);
    this.reputation += 50;
    System.out.println("Current Reputation: " + this.reputation);
  }

  // returns the current budget of the university
  public float getBudget() {
    return this.budget;
  }

  // sets the reputation of the university after updated
  public void setReputation(int reputation) {
    this.reputation = reputation;
  }

  // returns the current budget of the university
  public int getReputation() {
    return this.reputation;
  }

  // sets the budget of the university after it has been deducted
  public void setBudget(float budget) {
    this.budget = budget;
  }

  // returns the human resource department of the university.
  public HumanResource getHumanResource() {
    return humanResource;
  }
}
