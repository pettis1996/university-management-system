package CoursesManagementApp.com.uoi.SoftwareEngineering.Course;


import CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics.Kurtosis;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics.StatisticFactory;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics.StatisticStrategy;
import CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics.TemplateStatisticStrategy;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistration;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationRepository;
import CoursesManagementApp.com.uoi.SoftwareEngineering.StudentRegistration.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CourseServiceImpl  implements CourseService{
    private List<StatisticStrategy> statCalculationStrategies;

    @Autowired
    private CourseRepository courseRepo;

    private Course course;
    @Autowired
    private StudentRegistrationService registrationService;

    private List<StudentRegistration> registrationsList;

    @Autowired
    private StudentRegistrationRepository registrationRepository;

    private List<StatisticStrategy> StatCalculationStrategies= new ArrayList<StatisticStrategy>();


    public CourseServiceImpl(){}
    public Map<String,Double> getCourseStatistics(Course course){
        Map<String,Double> courseStatistics=new HashMap<String,Double>();
            for(StatisticStrategy strategy:getStatCalculationStrategies()){
                courseStatistics.put(strategy.getStrategyName(),strategy.calculateStatistic(registrationService,course));
        }
        return courseStatistics;
    }


    public List<Course> findCourseByInstructorLogin(String LoginName){
        return courseRepo.findByInstructorLogin(LoginName);
    }
    public void delete(int CourseId){
        /*Course deletedCourse=courseRepo.findById(CourseId).orElse(null);
        courseRepo.delete(deletedCourse);
        System.out.println("Course with ID: "+ CourseId +" has been deleted");*/
        courseRepo.deleteByCourseId(CourseId);
    }
    public void save(Course course){
        courseRepo.save(course);
    }
    public void update(Course course){
        courseRepo.save(course);

    }

    @Override
    public Course findCourseById(int courseId) {
        return courseRepo.findByCourseId(courseId);
    }

    public  void deleteStudentRegistrationsFromCourse(int courseId, StudentRegistration registration){
        findCourseById(courseId).removeStudentRegistration(registration);
        registrationService.delete(registration.getRegistrationId());
    }
    public void updatecourseWeights(String instructorLogin, int courseId,double projectWeight,double examWeight){
        for (Course course : findCourseByInstructorLogin(instructorLogin)) {
            if (course.getCourseId() == courseId) {
                course.setProjectPercentage(projectWeight);
                course.setExamPercentage(examWeight);
                update(course);
            }
        }
    }
    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    public List<StatisticStrategy> getStatCalculationStrategies(){
        return statCalculationStrategies;
    }
    public void setStatCalculationStrategies(List<StatisticStrategy> statisticStrategyList) {
        this.statCalculationStrategies=statisticStrategyList;
    }
    public Map<String,Double> calculateCourseStatistics(int courseId){
        Map<String,Double> statistics=new HashMap<String,Double>();
        StatisticFactory factory=new StatisticFactory();
        String[] statisticNames={"Max","Mean","Median","Min","Percentiles","StandardDeviation","Variance"};
        for( String name:statisticNames){
            TemplateStatisticStrategy strategy=factory.create(name);
            statistics.put(name,strategy.calculateStatistic(registrationService,findCourseById(courseId)));
        }
        return statistics;
    }
    public boolean addRegistrationToCourse(int regId,int courseId){
         return findCourseById(courseId).addStudentRegistration(registrationRepository.findByRegId(regId));
    }
    public List<StudentRegistration> findRegistrationsInCourse( int courseId){
        return registrationRepository.findByCourseId(courseId);

        }

}