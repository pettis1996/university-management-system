package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;

public class Median extends TemplateStatisticStrategy {
    @Override
    public void doActualCalculation() {
        setCalculationResult(getStats().getPercentile(50));
    }
    public Median(){
        super("Median");
    }
}
