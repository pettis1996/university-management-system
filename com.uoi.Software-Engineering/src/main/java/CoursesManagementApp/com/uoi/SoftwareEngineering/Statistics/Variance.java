package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;

public class Variance extends TemplateStatisticStrategy {
    @Override
    public void doActualCalculation() {
        setCalculationResult(getStats().getVariance());
    }
    public Variance(){
        super("Variance");
    }
}
