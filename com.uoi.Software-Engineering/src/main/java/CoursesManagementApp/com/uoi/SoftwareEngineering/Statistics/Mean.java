package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;

public class Mean extends TemplateStatisticStrategy{

    public Mean(){
        super("Mean");
    }

    @Override
    public void doActualCalculation() {
        setCalculationResult(getStats().getMean());
    }
}
