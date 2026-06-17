import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DataCleaningMapper extends Mapper<Object, Text, Text, Text> {

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();

        if (line.startsWith("\"Data.Precipitation\"")) {
            return;
        }

        String[] fields = line.split(",", -1);

        if (fields.length >= 12) {
            context.write(new Text(""), new Text(line));
        }
    }
}

