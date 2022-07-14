package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;


import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.Course;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseService;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Course.CourseRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public  abstract class TemplateStatisticStrategy implements StatisticStrategy {
    private DescriptiveStatistics stats=new DescriptiveStatistics();
    private String strategyName;
    private Map<Integer,Double> grades=new HashMap<Integer,Double>();
    private Course course;
    private double calculationResult;
    @Autowired
    private StudentRegistrationService registrationService;
    private List<StudentRegistration> registrations;
    private CourseRepository courseRepo;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentRegistrationRepository registrationRepo;


    public double calculateStatistic(StudentRegistrationService registrationService,Course course) {

        prepareDataSet(registrationService,course);
        doActualCalculation();
        return calculationResult;
    }


    public List<StudentRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<StudentRegistration> registrations) {
        this.registrations = registrations;
    }


    protected void prepareDataSet(StudentRegistrationService registrationService,Course course) {
        for(StudentRegistration registration: registrationService.findRegistrationByCourse(course.getCourseId())){
            stats.addValue((float) ((registration.getExamGrade() *course.getExamPercentage()) + (registration.getProjectGrade()*course.getProjectPercentage())));
        }
    }

    public abstract void doActualCalculation();


    public double getCalculationResult() {
        return calculationResult;
    }

    public void setCalculationResult(double calculationResult) {
        this.calculationResult = calculationResult;
    }

    public TemplateStatisticStrategy() {
    }
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public DescriptiveStatistics getStats() {
        return stats;
    }

    public void setStats(DescriptiveStatistics stats) {
        this.stats = stats;
    }
    public Map<Integer, Double> getGrades() {
        return grades;
    }

    public void setGrades(Map<Integer, Double> grades) {
        this.grades = grades;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }


    public TemplateStatisticStrategy(String strategyName){
        this.strategyName=strategyName;
    }
}