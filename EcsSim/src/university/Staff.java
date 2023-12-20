package university;

public class Staff {
  // name of staff member
  public String name;
  // skill level of staff member
  int skill;
  // number of years that the staff has been teaching
  public int yearsOfTeaching;
  // stamina of the staff member
  public int stamina;

  // constructor for Staff
  // initializes the staff member's name, skill level, years of teaching, and stamina

  public Staff(String name, int skill) {
    this.name = name;
    this.skill = skill;
    this.yearsOfTeaching = 0; // years of teaching starts with 0 as default
    this.stamina = 100; // the stamina of the staff starts at 100
  }

  // string representation of the staff member name.
  public String toString() {
    return name; // returns the name instead of memory locations ..
  }

  /*
  - teaching session for a given number of students for each staff
  - reputation is calculated based on skill and number of students
  - skill increases by 1 each time if it is below 100.
  - stamina decreases based on the number of students and current skill level.*/
  public int instruct(int numberOfStudents) {
    int reputation = (100 * skill) / (100 + numberOfStudents);
    if (skill < 100) {
      skill += 1;
    }
    double staminaDecrease = Math.ceil((double) numberOfStudents / (20 + skill)) * 20;
    stamina -= (int) staminaDecrease;
    if (stamina <= 0) {
      stamina = 0;
    }
    return reputation;
  }

  // replenishes the stamina of the staff member by 20 points, up to a maximum of 100
  public void replenishStamina() {
    stamina += 20;
    if (stamina > 100) {
      stamina = 100;
    }
  }

  // increases the years of teaching by 1

  public void increaseYearsOfTeaching() {
    yearsOfTeaching += 1;
  }

  // returns the skill level of the staff member
  public int getSkill() {
    return skill;
  }

  // returns the current stamina of the staff member

  public int getStamina() {
    return stamina;
  }

  // returns name of the staff member

  public String getName() {
    return name;
  }

  // returns the years that the staff has been teaching

  public int getYearsOfTeaching() {
    return yearsOfTeaching;
  }
}
