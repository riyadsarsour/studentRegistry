package studentregistry;
import java.io.Serializable;

class Student implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private so need to set getter/setter methods
    private String studentName = null;
    private Integer studentID = 0;
    private String[] subjects = null;

    // default constructor
    public Student(){}

    // params Constructor
    public Student(String studentName, Integer studentID, String[] subjects){
        super();
        this.studentName = studentName;
        this.studentID = studentID;
        this.subjects = subjects;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        StringBuilder subjectLister = new StringBuilder();
        for(String subject : this.subjects){
            subjectLister.append(subject + "\n");
        }

        return "Student Name: " + this.studentName + "\nStudent ID: " + this.studentID + "\n List Subject Enrollment:\n" + subjectLister ;
    }
}
