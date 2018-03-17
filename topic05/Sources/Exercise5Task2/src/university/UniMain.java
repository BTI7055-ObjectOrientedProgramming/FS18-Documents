package university;


public final class UniMain {

	private UniMain() {
	}

	private static final String UNIVERSITY_XML = "university.xml";

	public static void main(String[] args)  {

		// create some persons 
		Student student = new Student("Max", "max@bfh.ch", "B");
		Student student2 = new Student("Tom", "tom@bfh.ch", "c");
		Professor prof = new Professor("Mr. X", "prof.x@bfh.ch", "IT Security");
		Employee empl = new Employee("Erni Schmidt", "erni.schmidt@bfh.ch",
				"HR");

		// create uni, assigning staff and students
		University bfh = new University("bfh");

		bfh.addStaff(student);
		bfh.addStaff(prof);
		bfh.addStaff(empl);

		bfh.addStudent(student);
		bfh.addStudent(student2);

		// create Marhaller and write to system.out and file university.xml
        // @TODO
        
		// read the created XML file (unmarhall) and output the result
		// @TODO
	}
}
