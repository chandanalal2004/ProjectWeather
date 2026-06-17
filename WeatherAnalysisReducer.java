import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WeatherAnalysisReducer extends Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        double totalPrecip = 0;
        double totalAvgTemp = 0;
        double highestTemp = Double.MIN_VALUE;
        double lowestTemp = Double.MAX_VALUE;
        int count = 0;

        for (Text value : values) {
            String[] parts = value.toString().split(",");

            double precip = Double.parseDouble(parts[0]);
            double avg = Double.parseDouble(parts[1]);
            double max = Double.parseDouble(parts[2]);
            double min = Double.parseDouble(parts[3]);

            totalPrecip += precip;
            totalAvgTemp += avg;
            if (max > highestTemp) highestTemp = max;
            if (min < lowestTemp) lowestTemp = min;
            count++;
        }

        context.write(key, new Text(
                "Average_Precipitation=" + (totalPrecip / count) +
                ", Average_Temperature=" + (totalAvgTemp / count) +
                ", Highest_Temperature=" + highestTemp +
                ", Lowest_Temperature=" + lowestTemp));
    }
}
