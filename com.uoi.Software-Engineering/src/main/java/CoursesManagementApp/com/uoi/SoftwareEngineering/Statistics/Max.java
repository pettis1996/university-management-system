package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;

public class Max extends TemplateStatisticStrategy {
    @Override
    public void doActualCalculation() {
        setCalculationResult(getStats().getMax());
    }

    public Max(){
        super("Max");
    }

}
