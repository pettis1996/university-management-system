package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;

public class StandardDeviation extends TemplateStatisticStrategy {

    @Override
    public void doActualCalculation() {
        setCalculationResult(getStats().getStandardDeviation());
    }
    public StandardDeviation(){
        super("StandardDeviation");
    }
}
