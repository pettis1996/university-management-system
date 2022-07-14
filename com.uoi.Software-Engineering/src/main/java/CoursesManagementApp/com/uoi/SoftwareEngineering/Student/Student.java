package CoursesManagementApp.com.uoi.SoftwareEngineering.Student;

import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.*;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student.StudentAvgGradeValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Student.StudentUsernameValidation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*@StudentUsernameValidation
@AmValidation
@EmailValidation
@PasswordValidation*/
@Entity
@Data
@NoArgsConstructor
/*@YearValidation
@SemesterValidation
@StudentAvgGradeValidation*/
@Table(name="student")
public class Student{

    /*private List<StudentRegistration> myRegistrations;*/
    @Id
    @Column(unique = true)
    private int am;
    @OneToMany(mappedBy="student")
    private List<StudentRegistration> myRegistrations=new ArrayList<StudentRegistration>();
    /*@JsonIgnore
    private Map<String,List<String>> HistoryPerExamPeriod;
    @JsonIgnore
    private Map<String,List<String>> HistoryWithAllAttempts;
*/
    @Column
    private String name;

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Column(unique=true)
    private String userName;

    @Column
    private String passWord;


    @Column(unique = true)
    private String email;

    @Column
    private double avgGrade;
    @Column
    private int year;

    public int getCoursePassed() {
        return coursePassed;
    }

    public void setCoursePassed(int coursePassed) {
        this.coursePassed = coursePassed;
    }

    @Column
    private int coursePassed=0;

    @Column
    private int semester;

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    @Transient
    private String check="-";


 /*   public Map<String, List<String>> getHistoryPerExamPeriod() {
        return HistoryPerExamPeriod;
    }

    public void setHistoryPerExamPeriod(Map<String, List<String>> historyPerExamPeriod) {
        HistoryPerExamPeriod = historyPerExamPeriod;
    }

    public Map<String, List<String>> getHistoryWithAllAttempts() {
        return HistoryWithAllAttempts;
    }

    public void setHistoryWithAllAttempts(Map<String, List<String>> historyWithAllAttempts) {
        HistoryWithAllAttempts = historyWithAllAttempts;
    }*/

    public void addRegistrationToStudent(StudentRegistration registration){
        if(getAm()==registration.getAm()) {
            for(StudentRegistration reg:myRegistrations){
                if (reg.getRegistrationId()==registration.getRegistrationId()){
                    removeRegistrationFromStudent(reg);
                }
            }
            myRegistrations.add(registration);
        }
        else {
            throw new IllegalArgumentException("Registration must have same AM with Student");
        }
    }

    public void addManyRegistrationsToStudent(List<StudentRegistration> registrations) {
        for (StudentRegistration registration : registrations) {
            if (getAm() == registration.getAm()) {
                myRegistrations.add(registration);
            } else {
                throw new IllegalArgumentException("Registrations must have same AM with Student");
            }
        }
    }

    public void removeRegistrationFromStudent(StudentRegistration registration){
        if(getAm()==registration.getAm()) {
            myRegistrations.remove(registration);
        }
        else {
            throw new IllegalArgumentException("Registration must have same AM with Student.You can't remove undeclared registrations");
        }
    }

    public void removeManyRegistrationsFromStudent(List<StudentRegistration> registrations) {
        for (StudentRegistration registration : registrations) {
            if (getAm() == registration.getAm() && myRegistrations.contains(registration)) {
                myRegistrations.remove(registration);
            } else {
                throw new IllegalArgumentException("Registrations must have same AM with Student.You can't remove undeclared registrations");
            }
        }
    }

    public boolean isEmailValid(String email,String username) {
        if (email.split("@")[0].equals(username)&&email.split("@")[1].equals("uoi.gr")) {
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
    public Student(String name,String username,String password,String email,int am,int year,int semester,double avgGrade){
        if(name==null || name.equals("") ){
            throw new IllegalArgumentException("Check again the name it's empty or null");
        }
        if(username==null || username.equals("")||!username.startsWith("cs0")){
            throw new IllegalArgumentException("Check again the username it's empty or null");
        }
        if(password==null || password.equals("") || password.length()<8||checkPassword(password)==false){
            throw new IllegalArgumentException("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character");
        }
        if(!email.startsWith("cs0")||!username.startsWith("cs0")||isEmailValid(email,username)==false){
            throw new IllegalArgumentException("Email and username should start with cs0");
        }
        if(year<1){
            throw new IllegalArgumentException("yearOfStudies should be greater than or equal to one");
        }
        if(semester>year*2||semester<(year*2)-1){
            throw new IllegalArgumentException("Semester is not valid.Check it according to your yearOfStudies");
        }
        if(avgGrade<5 ||avgGrade>10){
            throw new IllegalArgumentException("AvgGrade should be between 5 and 10");
        }
        this.name=name;
        this.userName=username;
        this.passWord=password;
        this.email=email;
        this.am=am;
        this.year=year;
        this.semester=semester;
        this.avgGrade=avgGrade;
        this.coursePassed=0;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }


    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String password) {
        this.passWord = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }



    public List<StudentRegistration> getMyRegistrations() {
        return myRegistrations;
    }
    public void setMyRegistrations(List<StudentRegistration> myRegistrations) {
        this.myRegistrations = myRegistrations;
    }

    public int getAm() {
        return am;
    }

    public void setAm(int am) {
        this.am = am;
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
}
