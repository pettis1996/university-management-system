package CoursesManagementApp.com.uoi.SoftwareEngineering.Statistics;

public class StatisticFactory {

    public static TemplateStatisticStrategy create (String strategyName) {
        if (strategyName.equals("Kurtosis")) {
            return new Kurtosis();
        } else if (strategyName.equals("Max")) {
            return new Max();
        } else if (strategyName.equals("Mean")) {
            return new Mean();
        } else if (strategyName.equals("Median")) {
            return new Median();
        } else if (strategyName.equals("Min")) {
            return new Min();
        } else if (strategyName.equals("Percentiles")) {
            return new Percentiles();
        } else if (strategyName.equals("Skewness")) {
            return new Skewness();
        } else if (strategyName.equals("StandardDeviation")) {
            return new StandardDeviation();
        } else if (strategyName.equals("Variance")) {
            return new Variance();
        }
        return null;
    }
}
