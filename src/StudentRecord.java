import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/***********************************************************************
 * 
 * @author Don Charest Date 03/30/17, Due: 02/28/17 CS342-B1 Spring 2017
 *         Homework Assignment 4 StudentRecord Class, Version 2
 * 
 ***********************************************************************
 */

public class StudentRecord
{
	// Key = ID, Value = Student Object
	static HashMap<String, Student> hmap = new HashMap<String, Student>();
	// ArrayList of Student Objects
	static ArrayList<Student> students = new ArrayList<Student>();

	
	public static void main(String[] args)
	{
		RunMenu();
	} // End Method: main()
	
	/**
	 * Method to create a new student object
	 * @param id - Student object String (unique) 
	 * @param name - Student object String
	 */
	public static void addStudent(String id, String name) 
	{
		// Create new Student object 
		Student student = new Student(id, name);
		// Add new student object to the ArrayList
		students.add(student);
		// Add new Student object to the hashMap
		hmap.put(id, student);
	} // End Method: AddStudent()	
	
	/**
	 * Method to print a students cumulative GPA
	 * @param iD3 - Student object String (unique) 
	 */
	public static void printGPA(String iD3)
	{
		Student student = StudentRecord.hmap.get(iD3);
		// Get total grade points
		double totalGradePoints = 0.0;
		for (int i = 0; i < student.gradePoints.size(); i++)
		{
			totalGradePoints += student.gradePoints.get(i);
		}
		// Get total credits
		double totalCredits = 0.0;
		for (int j = 0; j < student.credits.size(); j++)
		{
			totalCredits += student.credits.get(j);
		}
		// Calculate cumulative GPA
		double cumulativeGPA = (totalGradePoints / totalCredits);
		System.out.printf("Cumulative GPA: %.2f\n", cumulativeGPA);
		System.out.println();
	}
	
	/**
	 * Method: RunMenu - runs through the user menu options.
	 */
	public static void RunMenu()
	{
		boolean running = true;
		while (running)
		{
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Choose an option:\n"

					+ " \n1.  Add a new student record"
					+ " \n2.  Add a single course record to an existing student record"
					+ " \n3.  Delete a single course record from an existing student record"
					+ " \n4.  Print a complete student record (including students' cumalitive GPA)" + " \n5.  Exit"
					+ "	\n ");

			String input = keyboard.nextLine();
			switch (input)
			{
			case "1": // Add a new student (ID & Name)
				System.out.println("Enter Student ID: ");
				String iD = keyboard.nextLine().toUpperCase();
				//if (DoesExist(iD))
				if (hmap.containsKey(iD))
				{
					System.out.println("Student ID entered alreary taken. Please choose a unique ID\n");
				} else
				{
					System.out.println("Enter Student Name: ");
					String name = keyboard.nextLine().toUpperCase();
					addStudent(iD, name);
				}
				break;

			case "2": // Add a new course record (Course name, Credits, Grade)
				System.out.println("Enter Student ID: ");
				String iD1 = keyboard.nextLine().toUpperCase();

				if (hmap.containsKey(iD1))
				{
					Student student = hmap.get(iD1);
					
					System.out.println("Enter course name: ");
					String courseName = keyboard.nextLine().toUpperCase();
					// Add course name to ArrayList
					student.courseName.add(courseName);	

					System.out.println("Enter course credits (i.e. \"1\", \"2\", \"3\", \"4\"): ");
					int credits = keyboard.nextInt();

					System.out.println("Enter course grade (i.e. \"A\", \"B\", \"C\", \"D\" or \"F\"): ");
					String grade = keyboard.next().toUpperCase();

					// Convert letter grade to points.
					int g = 0;
					if (grade.equals("A"))
					{
						g = 4;
					} else
						if (grade.equals("B"))
						{
							g = 3;
						} else
							if (grade.equals("C"))
							{
								g = 2;
							} else
								if (grade.equals("D"))
								{
									g = 1;
								} else
									if (grade.equals("F"))
									{
										g = 0;
									}
					// Calculate grade points for course
					int gradePoints = credits * g;
					// Add course grade point to ArrayList
					student.gradePoints.add(gradePoints);
					// Add course credits to ArrayList
					student.credits.add(credits);
					// Add new CourseRecord
					Student.addCourse(student, courseName, credits, grade);

					break;
				} else
				{
					System.out.print("Student ID not found\n\n");
				}
				break;

			case "3": // Delete an existing course record
				System.out.println("Enter Student ID: ");
				String iD2 = keyboard.nextLine().toUpperCase();

				if (hmap.containsKey(iD2))
				{
					System.out.println("Enter course name: ");
					String courseName = keyboard.nextLine().toUpperCase();
					// Remove course records from ArrayLists to Re-Calculate GPA
					Student student = hmap.get(iD2);
					int arrayListPos = student.courseName.indexOf(courseName);
					student.courseName.remove(arrayListPos);
					student.gradePoints.remove(arrayListPos);
					student.credits.remove(arrayListPos);
					// Remove course record from linkedList
					Student.removeCourse(student, courseName);
					
				break;
			} else
			{
				System.out.print("Student ID not found\n\n");
			}
			break;

			case "4": // Display student record (specified by ID)
				System.out.println("Enter Student ID: ");
				String iD3 = keyboard.next().toUpperCase();

				if (hmap.containsKey(iD3))
				{	// return hashMap Value - the Student object
					Student student = hmap.get(iD3);
					String name = student.getName();
					// Print Student ID
					System.out.println("\nID: " + iD3); 
					// Print Student Name
					System.out.println("NAME: " + name + "\n"); 
					// Print Course Record
					Student.printList(iD3, student);
					// Print Cumulative GPA
					printGPA(iD3);
					
				} else
				{
					System.out.print("Student ID not found\n\n");
				}
				break;

			case "5": // Exit program
				System.out.println("Bye\n");
				keyboard.close(); // Close Scanner class
				System.exit(0); // Exit program
				break;

			// Invalid user entry --> Valid responses are between "1" & "9".
			default:
				System.out.println("Please choose a valid menu option\n");
				break;
			}
		}
	}// End Method: RunMenu()
	

}