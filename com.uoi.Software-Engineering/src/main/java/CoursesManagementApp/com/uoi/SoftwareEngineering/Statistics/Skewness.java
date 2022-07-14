package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;

public class Skewness extends TemplateStatisticStrategy {

    public Skewness(){
        super("Skewness");
    }

    public void doActualCalculation(){
        setCalculationResult(getStats().getSkewness());
    }
}
