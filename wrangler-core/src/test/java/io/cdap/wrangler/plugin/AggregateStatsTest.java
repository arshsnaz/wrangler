package io.cdap.wrangler.plugin;

import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.executor.ExecutorContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AggregateStatsTest {

    @Test
    public void testAggregateStatsDirective() throws Exception {
        // Create sample input rows
        Row row1 = new Row("size", "1MB").add("time", "1s");
        Row row2 = new Row("size", "512KB").add("time", "500ms");

        List<Row> inputRows = Arrays.asList(row1, row2);

        // Initialize directive
        AggregateStats directive = new AggregateStats();
        directive.initialize(new DummyArguments());

        // Execute aggregation
        List<Row> result = directive.execute(inputRows, new DummyContext());

        // Validate output
        Row output = result.get(0);
        double sizeMB = (Double) output.getValue("total_size_mb");
        double timeSec = (Double) output.getValue("total_time_sec");

        Assert.assertEquals(1.5, sizeMB, 0.001);  // 1MB + 0.5MB
        Assert.assertEquals(1.5, timeSec, 0.001); // 1s + 0.5s
    }

    // DummyArguments and DummyContext simulate directive usage
    static class DummyArguments implements io.cdap.wrangler.api.Arguments {

        @Override
        public Object value(String name) {
            switch (name) {
                case "sizeCol":
                    return () -> "size";
                case "timeCol":
                    return () -> "time";
                case "outSizeCol":
                    return () -> "total_size_mb";
                case "outTimeCol":
                    return () -> "total_time_sec";
                default:
                    throw new IllegalArgumentException("Unknown argument: " + name);
            }
        }
    }

    static class DummyContext implements ExecutorContext {
    }
}
