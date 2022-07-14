/*package CoursesManagementApp.com.uoi.SoftwareEngineering.User;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Instructor.Instructor;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat.Secretariat;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Student.Student;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Entity
@Data
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.INTEGER)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "role")

@JsonSubTypes({@JsonSubTypes.Type(value = Instructor.class, name = "Instructor"),

        @JsonSubTypes.Type(value = Student.class, name = "Student"),

        @JsonSubTypes.Type(value = Secretariat.class, name = "Secretariat")

})
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public  abstract int getRole();

    public void setRole(int role) {
        this.role = role;
    }



    private int role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;



    public void addCourseToInstructor(Course course,Instructor instructor){
        if(course.getInstructorLogin().equals(getUsername())) {
            instructor.getMyCourses().add(course);
        }
        else{
            throw new IllegalArgumentException("The course's instructorLogin should be equal with instructor's username");
        }
    }
    public void removeCourseFromInstructor(Course course,Instructor instructor){
        if(course.getInstructorLogin().equals(getUsername())) {
            instructor.getMyCourses().remove(course);
        }
        else{
            throw new IllegalArgumentException("Instructor doesn't teach this course you are going to remove");
        }
    }

    public boolean isEmailValid(String email,String username) {
        if (email.split("@")[0].equals(username)&&email.split("@")[1].equals("uoi.gr")) {
            return true;
        }
        return false;
    }

    private static boolean checkPassword(String str) {
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



    public User(String name,String username,String password,int role,String email){
            if(name==null || name.equals("") ){
                throw new IllegalArgumentException("Check again the name it's empty or null");
            }
            if(username==null || username.equals("")){
                throw new IllegalArgumentException("Check again the username it's empty or null");
            }
            if(password==null || password.equals("") || password.length()<8||checkPassword(password)==false){
                throw new IllegalArgumentException("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character");
            }
            if(role!=0 && role!=1 && role!=2){
                throw new IllegalArgumentException("Check again the role,only Student,Secretariat and Instructor are allowed");
            }
            if(email==null || email.equals("")||isEmailValid(email,username)==false){
                throw new IllegalArgumentException("Emails must be at the form of username@uoi.gr");
            }


            this.name=name;
            this.username=username;
            this.password=password;
            this.role=role;
            this.email=email;

    }
}*/