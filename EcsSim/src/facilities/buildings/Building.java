package facilities.buildings;

public interface Building {
  // returns the current level of the buildings
  int getLevel();

  // increases the level of a building during upgrade
  void increaseLevel();

  // returns the cost required to upgrade a building
  int getUpgradeCost();

  // returns the current capacity of the building
  int getCapacity();

  // returns the maximum level where the building can be upgraded
  int getMaxLevel();

  // returns the name of the building
  String getName();

  // returns the type of the building (e.g. Hall, Lab, Theatre)
  String getType();
}
