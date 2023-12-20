import facilities.Facility;
import facilities.buildings.Building;
import java.io.*;
import java.util.*;
import university.HumanResource;
import university.Staff;
import university.University;

public class EcsSim {
  private University university;
  private ArrayList<Staff> staffMarket;
  private List<Staff> employedStaff;
  private Building buildings;

  private Staff staff;

  HumanResource hr = new HumanResource();
  private float budget;
  private int reputation;
  private int remainingStudents;
  Random random = new Random();

  // Initializing variables that can be used for the later on parts
  public EcsSim(University university, ArrayList<Staff> staffMarket) {
    this.university = university;
    this.staffMarket = staffMarket;
    this.employedStaff = new ArrayList<>();
    this.staff = new Staff("Default Name", 0); // Initialize with default values
    this.hr = new HumanResource();
    this.random = new Random();
    this.budget = university.getBudget();
    this.reputation = university.getReputation();
  }

  public void simulate() {
    /*
    - Call the methods to do the calculations for beginning of the year
    - Call the methods to do the calculations for middle of the year
    - Call the methods to do the calculations for end of the year*/
    System.out.println("Simulating a year in the university...");
    beginOfYear();
    duringTheYear();
    endOfYear();
    System.out.println("Year simulation completed.");
  }

  private void beginOfYear() {
    System.out.println("Beginning of the year activities starting...");
    // call the method for the upgrade/build algorithm
    try {
      upgradeOrBuild();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // call method increase the budget based on student
    budgetBasedOnStudents();
    // call method to hire additional staffs from market to supervise/teach the students
    hiringStaff();
    System.out.println("Beginning of the year activities completed.");
  }

  private void upgradeOrBuild() throws Exception {
    // check if the university's budget is zero or negative
    if (university.getBudget() <= 0) {
      System.out.println("Insufficient funds to build/upgrade.");
      return;
    }
    // arrays of the possible facility types that can be built or upgraded
    String[] types = {"Lab", "Hall", "Theatre", "Library"};
    // generate a random index to select a facility type from the types array
    int randomIndex = random.nextInt(types.length);
    // select a facility type based on the random index
    String selectedType = types[randomIndex];
    String[] names;
    String selectedName = "";
    // checks the type of facility selected and assign corresponding names

    if (selectedType.equals("Lab")) {
      // possible names to select for labs
      names =
          new String[] {
            "Zepler",
            "Mountbatten",
            "AB",
            "CD",
            "EF",
            "GH",
            "IJ",
            "KL",
            "MN",
            "OP",
            "QR",
            "ST",
            "UV",
            "WX",
            "YZ"
          };
    } else if (selectedType.equals("Hall")) {
      // possible names to select for hall

      names =
          new String[] {
            "Archers Road",
            "City Gateway",
            "Erasmus Park",
            "Glen Eyre",
            "Highfield Hall",
            "Wessex Lane",
            "Mayflower"
          };
    } else if (selectedType.equals("Theatre")) {
      // possible names to select for theatre

      names =
          new String[] {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
          };
    } else {
      // possible names to select for library
      names =
          new String[] {
            "Hartley Library",
            "Health Services Library",
            "National Oceanographic Library",
            "Winchester School of Art Library"
          };
    }
    // select a name for the facility
    selectedName = names[randomIndex];
    // randomly determine how many facilities to upgrade or build, in the range of 1 to 3

    int buildingsToUpgradeOrBuild = random.nextInt(2) + 1;

    boolean shouldBuild;
    // loops through to either upgrade or build buildings based on the determined number of
    // buildings
    for (int j = 0; j <= buildingsToUpgradeOrBuild && budget >= 1200; j++) {

      // if it's the first iteration (j == 0) and there are no existing facilities, then set the
      // number of buildings to upgrade or build to zero
      if (j == 0 && university.getEstate().getFacilities().length == 0) {
        buildingsToUpgradeOrBuild = 0;

        // builds a new hall name h1
        Facility newFacility1 = university.build("Hall", "h1");
        System.out.println("Hall h1 has been built");

        // builds a new Lab name l1
        Facility newFacility2 = university.build("Lab", " l1");
        System.out.println("Lab l1 has been built");

        // builds a new theatre named t1
        Facility newFacility3 = university.build("Theatre", "t1");
        System.out.println("Theatre t1 has been built");

        // builds a new library named Lib1
        Facility newFacility4 = university.build("Library", "Lib1");
        System.out.println("Library Lib1 has been built");

        // iterate over the newly created facilities
        for (Facility facility :
            Arrays.asList(newFacility1, newFacility2, newFacility3, newFacility4)) {
          buildings = (Building) facility; // casting from Facility to Building
        }
        continue;
      } else {
        shouldBuild = random.nextBoolean(); // Randomly decide whether to build or upgrade
      }
      // if the boolean return false for should build then run this
      if (!shouldBuild) {
        // check if the current level of the building is less than its maximum level allowed
        if (buildings.getLevel() < buildings.getMaxLevel()) {

          // if the building is not at its maximum level then upgrade it
          university.upgrade(buildings);

          System.out.println(buildings + " has been upgraded");
        } else {
          // If the building is at max level, set shouldBuild to true to build a new building
          shouldBuild = true;
        }
      }
      // if the boolean return true for should build then run this
      if (shouldBuild) {
        // constructs a new facility at the university using the specified type and name
        Facility newFacility = university.build(selectedType, selectedName);
        // Cast the new facility to a Building type.
        buildings = (Building) newFacility;

        System.out.println(selectedType + " " + selectedName + " has been built");
      }
    }
  }

  private float budgetBasedOnStudents() {
    // retrieve the current budget of the university
    float currentBudget = university.getBudget();

    /*
    - increase the budget based on the number of students
    - each student contributes a fixed amount of 10 ECScoins to the budget.*/
    currentBudget += university.getEstate().getNumberOfStudents() * 10;

    // updates the university budget with new value
    university.setBudget(currentBudget);

    // print for logging
    System.out.println("Budget after calculating students: " + currentBudget);

    // returns with the updated budget value
    return currentBudget;
  }

  /*
  - Sorts the available staff market based on their skills from high to low
  - hires a random number of staff in between 1 and 5 */
  private void hiringStaff() {
    // only hire staffs if budget is more than 1200
    if (university.getBudget() >= 1200) {
      // sorts the staff market in descending skill order
      Collections.sort(
          staffMarket,
          new Comparator<Staff>() {
            public int compare(Staff s1, Staff s2) {
              return Integer.compare(s2.getSkill(), s1.getSkill());
            }
          });

      // determining the number of staffs to hire
      int staffToHire = random.nextInt(3) + 1;

      // list to store the hired staffs
      List<Staff> hiredStaffList = new ArrayList<>();

      // loop to hire the top skilled staff member based on the number of staff to hire generated
      // checks to see if the staff market is empty
      for (int i = 0; i < staffToHire && !staffMarket.isEmpty(); i++) {
        // adding the hired staff into the list
        hiredStaffList.add(staffMarket.get(i));
      }

      // loops through all the list of hired staff
      for (Staff staff : hiredStaffList) {
        employedStaff.add(staff); // add to the list of employed staff
        university.getHumanResource().addStaff(staff); // adds the staff to the university resources
        staffMarket.remove(staff); // removing the staff from the staff market
      }
      System.out.println(employedStaff + "is hired");
    }
  }

  private void duringTheYear() {
    System.out.println("Middle of the year activities starting...");
    // random number generator within the number of students
    Random random = new Random();
    // get the total number of students in the university
    remainingStudents = university.getEstate().getNumberOfStudents();

    // loop through each of the employed staffs
    for (Staff staff : employedStaff) {
      System.out.println("remaining students before deduction: " + remainingStudents);

      // if there's no more remaining students then break the loop
      if (remainingStudents <= 0) {
        break;
      }
      // assigning a random number of students to the staff
      // this number will be in between 1 and the current total number of students we have
      int studentsForThisStaff = random.nextInt(remainingStudents) + 1;

      // calls a method and allocated the number of students for this staff
      staff.instruct(studentsForThisStaff);

      System.out.println("students for the staff: " + studentsForThisStaff);

      // deduct the number of students that has been allocated from the total number of students
      remainingStudents -= studentsForThisStaff;

      System.out.println("Remaining student: " + remainingStudents);
      System.out.println("Reputation after instruct student:" + university.getReputation());
      System.out.println("Middle of the year activities completed.");
    }
  }

  private void endOfYear() {
    System.out.println("End of the year activities starting...");

    // Pay the estate's maintenance cost
    float maintenanceCost = university.getEstate().getMaintenanceCost();
    System.out.println("Maintenance Cost: " + maintenanceCost);

    // Pay staff salary
    float totalSalary = university.getHumanResource().getTotalSalary();
    System.out.println("total salary:" + totalSalary);

    // calculates the total costs to be paid
    float payments = maintenanceCost + totalSalary;

    // get the budget of the university
    budget = university.getBudget();

    // reduce the costs to be paid from the budget
    budget -= payments;

    System.out.println("budget left is " + budget);

    // iterator for the list of employed staff
    Iterator<Staff> iterator = employedStaff.iterator();

    // loops through the employed staff using the iterator
    while (iterator.hasNext()) {
      // gets the next staff from the iterator
      Staff currentStaff = iterator.next();

      // Increase the years of teaching for all employed staffs
      currentStaff.increaseYearsOfTeaching();

      System.out.println(
          currentStaff + " has been teaching for " + currentStaff.getYearsOfTeaching() + " year");

      // Check if staff should leave
      if (shouldStaffLeave(currentStaff)) {
        System.out.println(currentStaff.getName() + " has left");
        iterator.remove(); // Remove using iterator to avoid ConcurrentModificationException
      } else {
        System.out.println(
            currentStaff + " has a stamina of " + currentStaff.getStamina() + " before replenish");

        // Replenish stamina for staying staffs
        currentStaff.replenishStamina();

        System.out.println(currentStaff + " has a stamina of " + currentStaff.getStamina());
      }
    }
    reputation = university.getReputation();

    // Deduct reputation points for each uninstructed student
    reputation -= remainingStudents;

    System.out.println("EOY remaining student:" + remainingStudents);
    university.setReputation(reputation);
    System.out.println("This university has a reputation of " + reputation);

    System.out.println("End of the year activities completed.");
  }

  private boolean shouldStaffLeave(Staff staff) {
    // logic to decide if the staff should leave
    /* condition for the staff to leave if:
    - if the staff teaches for more than 30 years and if the stamina of the staff is less than 10*/
    return staff.getYearsOfTeaching() >= 30 || staff.getStamina() <= 10;
  }

  // Simulates EcsSim over a specified number of years
  public void simulate(int years) {
    // Loops through each year of the simulation starting from 0

    for (int i = 0; i <= years; i++) {
      // prints the current year number
      System.out.println("*** Year " + i + " ***");
      // calls the simulate method to process the activities in the year
      simulate();
    }
    // after completing all years of simulation, pause the thread for 500 milliseconds
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      // if it's interrupted then print a message to indicate the interruption
      System.out.println("Simulation Interrupted");
    }
  }

  public static void main(String[] args) {
    // default path to staff configuration file
    String staffConfigFile = "defaultStaffConfig.txt";

    // default initial funding amount for the university
    int initialFunding = 200000;

    // default number to run the years of simulation
    int yearsToRun = 50; // staff.txt 2000 50
    String outputFileName = "EcsSimOutput.txt";

    // if command line arguments are provided (expecting at least 3),
    // then use these to override the default values
    if (args.length >= 3) {
      staffConfigFile = args[0];
      initialFunding = Integer.parseInt(args[1]);
      yearsToRun = Integer.parseInt(args[2]);
    }
    // Create a new PrintStream object for the output file
    try (PrintStream fileOut = new PrintStream(new FileOutputStream(outputFileName))) {
      // Redirect the standard output (System.out) to the fileOut PrintStream
      System.setOut(fileOut);

      // create a new University object with the specified initial funding
      University university = new University(initialFunding);
      // initializes an ArrayList to hold the market of available staff
      ArrayList<Staff> staffMarket = new ArrayList<>();

      // reads the staff configuration from the specified file
      // this file contains information about potential staff members to be hired
      try {
        readStaffConfiguration(staffConfigFile, staffMarket);
      } catch (IOException e) {
        // if there's an error reading the file, it prints the error message
        System.out.println("Error reading staff configuration: " + e.getMessage());
        return;
      }
      // creates a new instance of the simulation with the university and staff market
      EcsSim simulation = new EcsSim(university, staffMarket);
      // starts the simulation and run it for the specified number of years
      simulation.simulate(yearsToRun);

    } catch (FileNotFoundException e) {
      // prints the output file error message (only if file is not outputting)
      System.err.println("Error creating output file: " + e.getMessage());
    }

    // create a new University object with the specified initial funding
    University university = new University(initialFunding);
    // initializes an ArrayList to hold the market of available staff
    ArrayList<Staff> staffMarket = new ArrayList<>();

    // reads the staff configuration from the specified file
    // this file contains information about potential staff members to be hired
    try {
      readStaffConfiguration(staffConfigFile, staffMarket);
    } catch (IOException e) {
      // if there's an error reading the file, it prints the error message
      System.out.println("Error reading staff configuration: " + e.getMessage());
      return;
    }

    // creates a new instance of the simulation with the university and staff market
    EcsSim simulation = new EcsSim(university, staffMarket);

    // starts the simulation and run it for the specified number of years
    simulation.simulate(yearsToRun);
  }

  /*
  - reads the staff configuration from a specified file and populates a list with Staff objects
  - filePath: the path of the file containing the staff configuration
  - staffMarket: the list to which the read Staff objects will be added
  - IOException: an I/O error occurs while reading the file
  */
  public static void readStaffConfiguration(String filePath, List<Staff> staffMarket)
      throws IOException {

    // creates a File object that points to the file
    File file = new File(filePath);
    // checks if the file exists

    if (!file.exists()) {
      // if it doesn't, print the file path and return
      System.out.println("File not found: " + file.getAbsolutePath());
      return;
    }

    // opens the file for reading using a BufferedReader
    BufferedReader br = new BufferedReader(new FileReader(file));

    String line;
    // reads the file line by line
    while ((line = br.readLine()) != null) {
      // splits each line into parts using a space followed by a "(" as the delimiter
      String[] parts = line.split("\\s+\\(");

      // checks if the line is formatted into two parts
      if (parts.length == 2) {
        // extracts the staff member's skill
        String name = parts[0].trim();

        int skill = Integer.parseInt(parts[1].replace(")", "").trim()); // Remove ")" and trim

        // add a new Staff object to the staffMarket list with the extracted name and skill
        staffMarket.add(new Staff(name, skill));
      }
    }
    // closes the BufferReader
    br.close();
  }
}
