import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WeatherAnalysisMapper extends Mapper<Object, Text, Text, Text> {

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();

        String[] fields = line.split(",", -1);

        if (fields.length >= 13) {
            try {
                double precipitation = Double.parseDouble(fields[0].replace("\"", "").trim());

                double avgTemp = Double.parseDouble(fields[10].replace("\"", "").trim());
                double maxTemp = Double.parseDouble(fields[11].replace("\"", "").trim());
                double minTemp = Double.parseDouble(fields[12].replace("\"", "").trim());

                context.write(
                    new Text("WEATHER"),
                    new Text(precipitation + "," + avgTemp + "," + maxTemp + "," + minTemp)
                );

            } catch (Exception e) {
            }
        }
    }
}
