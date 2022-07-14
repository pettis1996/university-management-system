package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;

public class Min extends TemplateStatisticStrategy{

    @Override
    public void doActualCalculation() {
        setCalculationResult(getStats().getMin());
    }

    public Min(){
        super("Min");
    }
}
