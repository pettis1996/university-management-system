package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;

import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;

public interface StatisticStrategy {
    public double calculateStatistic(StudentRegistrationService registrationService,Course course);

    public String getStrategyName();
}
