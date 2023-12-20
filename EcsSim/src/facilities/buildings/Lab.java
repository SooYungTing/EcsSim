package facilities.buildings;

import facilities.Facility;

public class Lab extends Facility implements Building {
  // current level of the building
  int level;
  // maximum level this building can be upgraded
  int maxLevel = 5;
  // base capacity of the building at its initial level
  int baseCapacity = 5;
  // base cost to build the building
  int baseBuildingCost = 300;

  // constructor for Lab, sets the initial level to 1

  public Lab(String name) {
    super(name);
    this.level = 1;
  }

  // returns the current level of building
  @Override
  public int getLevel() {
    return level;
  }

  // increases the level of the building by one, up to the maxLevel

  @Override
  public void increaseLevel() {
    if (level < maxLevel) {
      level++;
    }
  }

  /*
  - calculates and returns the cost to upgrade building to next level
  - returns -1 if the building is already at max level*/

  @Override
  public int getUpgradeCost() {
    if (level >= maxLevel) {
      return -1;
    } else {
      return baseBuildingCost * (level + 1);
    }
  }

  /*
  - calculates and returns the current capacity of the building
  - the capacity increases exponentially based on the building's level*/
  @Override
  public int getCapacity() {
    return (int) (baseCapacity * Math.pow(2, (level - 1)));
  }

  // calculates and returns the total building cost based on the current level
  @Override
  public int getBuildingCost() {
    return baseBuildingCost * level;
  }

  // returns the maximum level of building
  @Override
  public int getMaxLevel() {
    return maxLevel;
  }

  // returns the name of the building

  @Override
  public String getName() {
    return super.getName();
  }

  /*
  - returns the type of the building.
  - returns null but can be overridden to provide specific type*/
  @Override
  public String getType() {
    return null;
  }

  // string representation of the facility name.
  public String toString() {
    String name = getName();
    return name; // returns the name instead of memory locations ..
  }
}
