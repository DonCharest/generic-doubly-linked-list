import java.util.ArrayList;

// Student object Class
public class Student
	{
		public String iD;
		public String name;
		public GDList<Course> courses;
		public ArrayList<String> courseName;
		public ArrayList<Integer> gradePoints;
		public ArrayList<Integer> credits;
		
		// Student object constructor
		public Student(String iD, String name)
		{
			super();
			this.iD = iD;
			this.name = name;
			courses = new GDList<Course>();
			courseName = new ArrayList<String>();
			gradePoints = new ArrayList<Integer>();
			credits = new ArrayList<Integer>();
		}
		// Accessors & mutators
		public String getName()
		{
			return name;
		}
		public String getiD()
		{
			return iD;
		}
		public GDList<Course> getCourses()
		{
			return courses;
		}
		public void setCourses(GDList<Course> courses)
		{
			this.courses = courses;
		}
		/**
		 * Method to create a new course object record
		 * @param student - Student object
		 * @param courseName - String (name of course object)
		 * @param credits - Integer (credits for course object)
		 * @param grade - String (letter grade for course object)
		 */
		public static void addCourse(Student student, String courseName, int credits, String grade)
		{
			student.courses.addToTail(new Course(courseName, credits, grade));
		}
		/**
		 * Method to remove a course object record
		 * @param student - Student object
		 * @param courseName - String (name of course object)
		 */
		public static void removeCourse(Student student, String courseName)
		{
			Student.Course course = student.courses.findCourseNode(courseName);
			student.courses.deleteNode(course);
		}
		/**
		 * Method to print a students course record
		 * @param id - Student object String (unique) 
		 * @param student - Student object
		 */
		public static void printList(String id, Student student)
		{
			student.courses.printList(id, student);
		}

		// Course object
		public static class Course
		{ // class variables
			String courseName;
			int credits; // 1, 2, 3, or 4
			String grade; // valid value is A, B, C, D, or F
			// constructor
			public Course(String courseName, int credits, String grade)
			{
				super();
				this.courseName = courseName;
				this.credits = credits;
				this.grade = grade;
			}
			// Accessors & mutators
			public String getCourseName()
			{
				return courseName;
			}
			public int getCredits()
			{
				return credits;
			}
			public String getGrade()
			{
				return grade;
			}
			public void setCourseName(String courseName)
			{
				this.courseName = courseName;
			}
			public void setCredits(int numberOfCredits)
			{
				this.credits = numberOfCredits;
			}
			public void setGrade(String grade)
			{
				this.grade = grade;
			}
			
			 /**
		     * Method to print: Name, credits, and letter grade of the course
		     */
		    public void displayCourse()
		    {
		    	System.out.println("CourseName: " + courseName + "\nCredits: " + credits + "\nGrade: " + grade);
		    }
		} // End Inner Class: Course
	}