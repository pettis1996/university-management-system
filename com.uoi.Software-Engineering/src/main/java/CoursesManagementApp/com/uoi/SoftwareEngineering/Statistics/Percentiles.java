package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;

public class Percentiles extends TemplateStatisticStrategy{
    private int param=0;
    @Override
    public void doActualCalculation() {
        if (param==0) {
            setCalculationResult(getStats().getPercentile(50));
        }
        else{
            setCalculationResult(getStats().getPercentile(param));
        }
    }
    public Percentiles(int param){
        super("Percentiles");
        this.param=param;
    }
    public Percentiles(){
        super("Percentiles");
    }
}
