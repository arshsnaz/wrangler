package io.cdap.wrangler.plugin;

import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.executor.ExecutorContext;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.api.Directive;
import io.cdap.wrangler.api.parser.ColumnName;
import io.cdap.wrangler.api.parser.TokenType;
import io.cdap.wrangler.api.Arguments;
import io.cdap.wrangler.api.UsageDefinition;

import java.util.Collections;
import java.util.List;

public class AggregateStats implements Directive {

    private String sizeColumn;
    private String timeColumn;
    private String outputSizeColumn;
    private String outputTimeColumn;

    private long totalBytes = 0;
    private long totalMilliseconds = 0;

    @Override
    public UsageDefinition define() {
        return UsageDefinition.builder("aggregate-stats")
            .define("sizeCol", TokenType.COLUMN_NAME)
            .define("timeCol", TokenType.COLUMN_NAME)
            .define("outSizeCol", TokenType.COLUMN_NAME)
            .define("outTimeCol", TokenType.COLUMN_NAME)
            .build();
    }

    @Override
    public void initialize(Arguments arguments) {
        this.sizeColumn = ((ColumnName) arguments.value("sizeCol")).value();
        this.timeColumn = ((ColumnName) arguments.value("timeCol")).value();
        this.outputSizeColumn = ((ColumnName) arguments.value("outSizeCol")).value();
        this.outputTimeColumn = ((ColumnName) arguments.value("outTimeCol")).value();
    }

    @Override
    public List<Row> execute(List<Row> rows, ExecutorContext context) throws Exception {
        for (Row row : rows) {
            String byteSizeValue = row.getValue(sizeColumn).toString();
            String timeDurationValue = row.getValue(timeColumn).toString();

            totalBytes += new ByteSize(byteSizeValue).getBytes();
            totalMilliseconds += new TimeDuration(timeDurationValue).getMilliseconds();
        }

        Row result = new Row();
        result.add(outputSizeColumn, totalBytes / (1024.0 * 1024.0)); // Convert bytes to MB
        result.add(outputTimeColumn, totalMilliseconds / 1000.0);     // Convert ms to seconds

        return Collections.singletonList(result);
    }
}
