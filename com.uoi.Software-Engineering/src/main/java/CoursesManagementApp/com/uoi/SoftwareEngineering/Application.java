package CoursesManagementApp.com.uoi.SoftwareEngineering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.access.SecurityConfig;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

	/*@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(1,"Instructor"));
			userService.saveRole(new Role(2,"Student"));
			userService.saveRole(new Role(3,"secreariat"));

			userService.saveUser(new User(1,"User803","User803","myy8032022",new ArrayList<Role>()));
			userService.saveUser(new User(2,"Kostas Orfanoudis","cs03303","3303password",new ArrayList<Role>()));
			userService.saveUser(new User(3,"Paris Pettis","cs03136","3136password",new ArrayList<Role>()));

			userService.addRoleToUser("User803","Instructor");
			userService.addRoleToUser("cs03303","Student");
			userService.addRoleToUser("cs03136","Student");

		};
	}
*/
}
