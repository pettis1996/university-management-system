package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;

public class Kurtosis extends TemplateStatisticStrategy{
    @Override
    public void doActualCalculation() {
        setCalculationResult(getStats().getKurtosis());

    }
    public Kurtosis(){
        super("Kurtosis");
    }



}
