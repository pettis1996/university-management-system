package CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.math3.analysis.function.Identity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Entity
@Table
@Data
public class StudentRegistration {
	@Id
	@NotNull
	@Column(name="regId")
	private int regId;

	public int getRegAm() {
		return regAm;
	}

	public void setRegAm(int regAm) {
		this.regAm = regAm;
	}

	@Column
	private int regAm;

	@Column
	private int courseId;

	@Column
	private String email;

	@Column
	private double examGrade;

	@Column
	private double projectGrade;

	@Column
	private double overall;

	@Column
	private String studentName;



	@Column
	private int yearOfRegistration;

	@Column
	private int semester;

	@Column
	private int yearOfStudies;

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}
	@Transient
	private  String check="-";


	@JsonIgnore

	@ManyToOne(cascade=CascadeType.ALL)
	private Student student;
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	private Course course;

	public void setProjectGrade(double projectGrade) {
		if(projectGrade<0.0||projectGrade>10.0){
			throw new IllegalArgumentException("projectGrade  should be between 0.0 and 10.0");
		}
		this.projectGrade = projectGrade;
	}

	public double getExamGrade() {
		return examGrade;
	}

	public void setExamGrade(double examGrade) {
		if(examGrade<0.0||examGrade>10.0){
			throw new IllegalArgumentException("examGrade  should be between 0.0 and 10.0");
		}
		this.examGrade = examGrade;
	}

	public StudentRegistration(int registrationId,int am,int courseId,String email,double examGrade,double projectGrade,String studentName,int yearOfStudies,int semester,int yearOfRegistration){
		if(registrationId<=0){
			throw new IllegalArgumentException("registrationId should be greater than zero");
		}
		if(studentName== null || studentName.equals("")){
			throw new IllegalArgumentException("studentName should not be empty");
		}
		if(am<=0){
			throw new IllegalArgumentException("am should be greater than zero");
		}
		String[] tokens=email.split("@");

		String[] tokensAm=email.split("cs0");
		if(email == null||email.equals("")||!(tokens[1].equals("uoi.gr"))){
			throw new IllegalArgumentException("Only emails of University of Ioannina are accepted");
		}
		if(email == null||email.equals("")||!(tokens[1].equals("uoi.gr"))||!(tokensAm[1].contains(am+""))){
			throw new IllegalArgumentException("Email must contain student's am");
		}
		if(yearOfStudies<1){
			throw new IllegalArgumentException("yearOfStudies should be greater than or equal to one");
		}
		if(semester>yearOfStudies*2||semester<(yearOfStudies*2)-1){
			throw new IllegalArgumentException("semester is not valid.Check it according to your yearOfStudies");
		}
		if(yearOfRegistration>2022|| yearOfRegistration<2013){
			throw new IllegalArgumentException("yearOfRegistration should be between 2013(year of foundation) and the current year");
		}
		if(courseId<=0){
			throw new IllegalArgumentException("courseId should be greater than or equal to one");
		}
		this.regId=registrationId;
		this.studentName=studentName;
		this.regAm=am;
		this.email=email;
		this.yearOfStudies=yearOfStudies;
		this.semester=semester;
		this.yearOfRegistration=yearOfRegistration;
		this.courseId=courseId;
		this.examGrade=examGrade;
		this.projectGrade=projectGrade;
	}
	public double getOverall() {
		return overall;
	}

	public void setOverall(float overall) {
		this.overall = overall;
	}


	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getAm() {
		return regAm;
	}

	public void setAm(int am) {
		this.regAm = am;
	}

	public int getYearOfRegistration() {
		return yearOfRegistration;
	}

	public void setYearOfRegistration(int yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getYearOfStudies() {
		return yearOfStudies;
	}

	public void setYearOfStudies(int yearOfStudies) {
		this.yearOfStudies = yearOfStudies;
	}

	public int getRegistrationId() {
		return regId;
	}

	public void setRegistrationId(int studentRegistrationId) {
		this.regId = studentRegistrationId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public StudentRegistration() {}

	public double getProjectGrade() {
		return projectGrade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEmailValid(String email,int am) {
		if (email.split("@")[0].equals("cs0"+am)&&email.split("@")[1].equals("uoi.gr")) {
			return true;
		}
		return false;
	}

	public  boolean checkPassword(String str) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(str);
		boolean isStringContainsSpecialCharacter = matcher.find();
		char ch;
		boolean capitalFlag = false;
		boolean lowerCaseFlag = false;
		boolean numberFlag = false;
		for(int i=0;i < str.length();i++) {
			ch = str.charAt(i);
			if( Character.isDigit(ch)) {
				numberFlag = true;
			}
			else if (Character.isUpperCase(ch)) {
				capitalFlag = true;
			} else if (Character.isLowerCase(ch)) {
				lowerCaseFlag = true;
			}
			if(numberFlag && capitalFlag && lowerCaseFlag && isStringContainsSpecialCharacter)
				return true;
		}
		return false;
	}
}
