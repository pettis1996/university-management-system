package CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.EmailValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Instructor.InstructorUsernameValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.PasswordValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Entity
@NoArgsConstructor
@Table(name="instructor")

public class Instructor{
    @Id
    private int id;

    @Column(unique = true)
    private String userName;

    @Column
    private String name;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column
    private String position;

    @Column
    private String passWord;

    @Column(unique = true)
    private String email;

    @Column
    @OneToMany(mappedBy = "instructor")
    @Autowired
    private List<Course> myCourses= new ArrayList<Course>();

    public String getMyCoursesString() {
        return myCoursesString;
    }

    public void setMyCoursesString(String myCoursesString) {
        this.myCoursesString = myCoursesString;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }



    @Transient
    private String check="-";

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int couresId) {
        this.courseId = couresId;
    }

    @Transient
    private int courseId;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Transient
    private String stat;

    private String myCoursesString="courses: ";

    public Instructor(int id,String name,String username,String password,String email,String position) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Check again the name it's empty or null");
        }
        if (username == null || username.equals("")|| !username.startsWith("cse")) {
            throw new IllegalArgumentException("Check again the username it's empty or null");
        }
        if (password == null || password.equals("") || password.length() < 8 || checkPassword(password) == false) {
            throw new IllegalArgumentException("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character");
        }
        if (email == null || email.equals("") ||!username.startsWith("cse") || !email.startsWith("cse")|| isEmailValid(email,username)==false) {
                throw new IllegalArgumentException("Instructor's email and username should start with cse");
            }
        this.name=name;
        this.userName=username;
        this.passWord=password;
        this.email=email;
        this.id=id;
        this.position=position;
        }

        public boolean isEmailValid (String email, String username){
            if (email.split("@")[0].equals(username) && email.split("@")[1].equals("uoi.gr")) {
                return true;
            }
            return false;
        }
        public static boolean checkPassword (String str){
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(str);
            boolean isStringContainsSpecialCharacter = matcher.find();
            char ch;
            boolean capitalFlag = false;
            boolean lowerCaseFlag = false;
            boolean numberFlag = false;
            for (int i = 0; i < str.length(); i++) {
                ch = str.charAt(i);
                if (Character.isDigit(ch)) {
                    numberFlag = true;
                } else if (Character.isUpperCase(ch)) {
                    capitalFlag = true;
                } else if (Character.isLowerCase(ch)) {
                    lowerCaseFlag = true;
                }
                if (numberFlag && capitalFlag && lowerCaseFlag && isStringContainsSpecialCharacter)
                    return true;
            }
            return false;
        }
    public void addCourseToInstructor(Course course){
        if(course.getInstructorLogin().equals(userName)) {
            myCourses.add(course);
            myCoursesString+=course.getCourseName()+", ";
            setMyCoursesString(myCoursesString);
        }
        else{
            throw new IllegalArgumentException("The course's instructorLogin should be equal with instructor's username");
        }
    }
    public void removeCourseFromInstructor(Course course){
        if(course.getInstructorId()==id) {
            myCourses.remove(course);
            setMyCoursesString("courses:");
            for (Course c : getMyCourses()) {
                myCoursesString += c.getCourseName() +  ", " ;
            }
            setMyCoursesString(myCoursesString);
        }
        else{
            throw new IllegalArgumentException("Instructor doesn't teach this course you are going to remove");
        }
    }
    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}