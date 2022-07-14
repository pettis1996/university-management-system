package CoursesManagementApp.com.uoi.SoftwareEngineering.Secretariat;

import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.EmailValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.PasswordValidation;
import CoursesManagementApp.com.uoi.SoftwareEngineering.ValidationConstraints.Secretariat.SecretariatUsernameValidation;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@NoArgsConstructor
@Table(name="secretariat")

public class Secretariat {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    private int id;

    @Column(unique=true)
    private String userName;

    @Column
    private String name;

    @Column
    private String passWord;

    @Column(unique = true)
    private String email;

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Column
    private String floor;

    @Column
    private String office;

    @Transient
    private String check="-";
    public Secretariat(String name,String username,String password,String email){
        if(name==null || name.equals("") ){
            throw new IllegalArgumentException("Check again the name it's empty or null");
        }
        if(username==null || username.equals("")){
            throw new IllegalArgumentException("Check again the username it's empty or null");
        }
        if(password==null || password.equals("") || password.length()<8||checkPassword(password)==false){
            throw new IllegalArgumentException("Password has minimum length 8 and must contain at least one Uppercase,one lowercase,one number and one special character");
        }
        if(!username.startsWith("css")){
            throw new IllegalArgumentException("Secretariat's username should start with css");
        }
        if(email==null || email.equals("")|| isEmailValid(email,username)==false){
            throw new IllegalArgumentException("Secretariat's email should be in the format username@uoi.gr");
        }


        this.name=name;
        this.userName=username;
        this.passWord=password;
        this.email=email;
    }
    public boolean isEmailValid(String email,String username) {
        if (email.split("@")[0].equals(username)&&email.split("@")[1].equals("uoi.gr")) {
            return true;
        }
        return false;
    }
    public boolean checkPassword(String str) {
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
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
