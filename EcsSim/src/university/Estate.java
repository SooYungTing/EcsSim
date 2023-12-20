package university;

import facilities.Facility;
import facilities.buildings.Building;
import facilities.buildings.Hall;
import facilities.buildings.Lab;
import facilities.buildings.Theatre;
import java.util.ArrayList;

public class Estate {
  // list of all the facilities (e.g halls, labs, theatres)
  private ArrayList<Facility> facilities;

  // constructor for Estate
  // initializes the list of facilities

  public Estate() {
    facilities = new ArrayList<Facility>();
  }

  // returns an array of all facilities in the estate
  public Facility[] getFacilities() {
    Facility[] facilitiesArray = new Facility[facilities.size()];
    facilities.toArray(facilitiesArray);
    return facilitiesArray;
  }

  /*
  - adds a facility of the specified type and name to estate
  - types supported are "Hall", "Lab", and "Theatre"
  - returns the newly added facility
  - or if type is not support then it returns null*/
  public Facility addFacility(String type, String name) {
    Facility newFacility;
    switch (type) {
      case "Hall":
        newFacility = new Hall(name);
        break;
      case "Lab":
        newFacility = new Lab(name);
        break;
      case "Theatre":
        newFacility = new Theatre(name);
        break;
      default:
        return null;
    }
    facilities.add(newFacility);
    return newFacility;
  }

  /*
  - calculates and returns the total maintenance cost for all building facilities in estate
  - maintenance cost is calculated as 10% of each building's capacity */
  public float getMaintenanceCost() {
    float totalCost = 0;
    for (Facility facility : facilities) {
      if (facility instanceof Building) {
        totalCost += (float) (0.1 * ((Building) facility).getCapacity());
      }
    }
    return totalCost;
  }

  /*
  - calculates and returns the number of students currently in the university
  - it is calculated based on the minimum capacity among halls, labs, and theatres*/

  public int getNumberOfStudents() {
    int totalHallCapacity = 0;
    int totalLabCapacity = 0;
    int totalTheatreCapacity = 0;

    for (Facility facility : facilities) {
      /*System.out.println("get number students facility list   " + facility.getClass());*/
      if (facility instanceof Hall) {
        totalHallCapacity += ((Hall) facility).getCapacity();
      } else if (facility instanceof Lab) {
        totalLabCapacity += ((Lab) facility).getCapacity();
      } else if (facility instanceof Theatre) {
        totalTheatreCapacity += ((Theatre) facility).getCapacity();
      }
    }

    /*System.out.println(" this is the hall capacity " + totalHallCapacity);
    System.out.println(" this is the lab capacity " + totalLabCapacity);
    System.out.println(" this is the theatre capacity " + totalTheatreCapacity);*/
    // returns the minimum of the total capacities
    return Math.min(Math.min(totalHallCapacity, totalLabCapacity), totalTheatreCapacity);
  }

  /*
  - checks if a specific building is part of the estate's facilities
  - if the building is found it will return true
  - otherwise it returns false*/
  public boolean contains(Building building) {
    for (Facility facility : facilities) {
      if (facility.equals(building)) {
        return true;
      }
    }
    return false;
  }
}
