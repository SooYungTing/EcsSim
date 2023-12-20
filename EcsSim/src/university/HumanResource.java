package university;

import java.util.*;

public class HumanResource {
  // HashMap to store staff members and their respective salaries
  HashMap<Staff, Float> staffSalary;

  /*
  - constructor for HumanResource
  - initializes the HashMap.*/
  public HumanResource() {
    staffSalary = new HashMap<>();
  }

  /*
  - adds a staff member to the HashMap with a randomly calculated salary
  - salary is calculated based on the staff member's skill level and a random factor between 9.5% and 10.5% */
  public void addStaff(Staff staff) {
    float mMin = (float) (9.5 / 100);
    float mMax = (float) (10.5 / 100);
    Random random = new Random();
    float salary;
    // calculate salary based on skill and a random factor within the range
    salary = ((random.nextFloat() * (mMax - mMin)) + mMax) * staff.getSkill();
    staffSalary.put(staff, salary);
  }

  // returns an iterator over the set of staff members
  public Iterator<Staff> getStaff() {
    return staffSalary.keySet().iterator();
  }

  // calculates and returns the total salary expense for all staff members in the list
  public float getTotalSalary() {
    float totalSalary = 0.0F;
    for (Float salary : staffSalary.values()) {
      totalSalary += salary;
    }
    return totalSalary;
  }
}
