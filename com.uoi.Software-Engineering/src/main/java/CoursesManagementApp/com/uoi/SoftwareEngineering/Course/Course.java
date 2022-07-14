package CoursesManagementApp.com.uoi.SoftwareEngineering.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Course.CourseYearValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Course.ExamPercentageValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Course.ProjectPercentageValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.CourseIdValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.SemesterValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Repository;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="course")

public class Course {
	@Id
	private int courseId;
	@Column(unique = true)
	private String courseName;

	@Column(nullable = true)
	private double examPercentage;
	@Column
	private boolean hasProject;
	@JsonIgnore
	private int idOfInstructor;

	@Column(nullable = true)
	private double projectPercentage;
	@Column(unique = true)
	private String syllabus;
	@Column
	private int year;
	@Column
	private int semester;
	@Column
	private String instructorLogin;

	public double getEcts() {
		return ects;
	}

	public void setEcts(double ects) {
		this.ects = ects;
	}

	@Column
	private double ects;

	@Transient
	@OneToMany(mappedBy = "course")
	private List<StudentRegistration> registrations = new ArrayList<StudentRegistration>();
	@ManyToOne(cascade = CascadeType.ALL)
	private Instructor instructor;

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	@Column
	private int hours;


	public boolean addStudentRegistration(StudentRegistration registration) {
		registrations.add(registration);
		if (registrations.contains(registration)) {
			return true;
		}
		return false;
	}

	public List<StudentRegistration> addManyStudentRegistrations(List<StudentRegistration> registrations) {
		for (StudentRegistration registration : registrations) {
			this.registrations.add(registration);
		}
		return registrations;
	}

	public void removeStudentRegistration(StudentRegistration registration) {
		registrations.remove(registration);
	}

	public List<StudentRegistration> removeManyStudentRegistrations(List<StudentRegistration> registrations) {
		for (StudentRegistration student : registrations) {
			registrations.remove(student);
		}
		return registrations;
	}

	public boolean checkUniqueStudentRegistrationsPerCourse() {
		for (int i = 0; i < registrations.size(); i++) {
			for (int j = i + 1; j < registrations.size(); j++) {
				if (registrations.get(i).getAm() == registrations.get(j).getAm()) {
					return false;
				}
			}
		}
		return true;
	}

	public Course() {
	}

	public Course(int courseId, String courseName,String syllabus,int hours,int year,int semester,String instructorLogin, int idOfInstructor,double ects, double examPercentage, double projectPercentage) {
		if (courseId <= 0) {
			throw new IllegalArgumentException("Check again courseId,it must be a positive integer");
		}
		if (courseName.equals("") || courseName == null) {
			throw new IllegalArgumentException("Check again courseName,it must not be empty");
		}
		if (syllabus == null || syllabus.equals("")) {
			throw new IllegalArgumentException("Check again the syllabus,it's empty");
		}
		if (hours<1) {
			throw new IllegalArgumentException(("Check again hours,it must  not be less than one"));
		}
		if (year < 1 || year > 5) {
			throw new IllegalArgumentException(("Check again the year,it must  not be less than one and more than 5"));
		}
		if (semester < 2 * year - 1 || semester > 2 * year) {
			throw new IllegalArgumentException(("Check again the semester,it is not valid"));
		}
		if (instructorLogin == null || instructorLogin.equals("")) {
			throw new IllegalArgumentException("Check again the instructorLogin,it's empty");
		}
		if (examPercentage <= 0 || examPercentage > 1.0) {
			throw new IllegalArgumentException("Check again the examPercentage,it's not valid");
		}
		if (projectPercentage >= 1.0 || projectPercentage < 0) {
			throw new IllegalArgumentException("Check again the projectPercentage,it's not valid");
		}

		this.courseId = courseId;
		this.courseName = courseName;
		this.syllabus = syllabus;
		this.year = year;
		this.semester = semester;
		this.instructorLogin = instructorLogin;
		this.idOfInstructor = idOfInstructor;
		this.examPercentage = examPercentage;
		this.projectPercentage = projectPercentage;
		this.ects=ects;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getInstructorLogin() {
		return instructorLogin;
	}

	public boolean getHasProject() {
		return hasProject;
	}

	public void setHasProject(boolean hasProject) {
		this.hasProject = hasProject;
	}

	public void setInstructorLogin(String instructorLogin) {
		this.instructorLogin = instructorLogin;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}


	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public List<StudentRegistration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<StudentRegistration> registrations) {
		this.registrations = registrations;
	}


	public double getExamPercentage() {
		return examPercentage;
	}

	public void setExamPercentage(double examPercentage) {
		this.examPercentage = examPercentage;
	}

	public double getProjectPercentage() {
		return projectPercentage;
	}

	public void setProjectPercentage(double projectPercentage) {
		this.projectPercentage = projectPercentage;
	}

	public int getInstructorId() {
		return idOfInstructor;
	}

	public void setInstructorId(int instructorId) {
		this.idOfInstructor = instructorId;
	}
}