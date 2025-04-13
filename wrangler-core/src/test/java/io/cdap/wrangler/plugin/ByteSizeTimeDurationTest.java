package io.cdap.wrangler.plugin;

import org.junit.Assert;
import org.junit.Test;

import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;

public class ByteSizeTimeDurationTest {

    @Test
    public void testByteSizeParsing() {
        Assert.assertEquals(1024, new ByteSize("1KB").getBytes());
        Assert.assertEquals(1048576, new ByteSize("1MB").getBytes());
        Assert.assertEquals(1572864, new ByteSize("1.5MB").getBytes());
    }

    @Test
    public void testTimeDurationParsing() {
        Assert.assertEquals(150, new TimeDuration("150ms").getMilliseconds());
        Assert.assertEquals(2000, new TimeDuration("2s").getMilliseconds());
        Assert.assertEquals(90000, new TimeDuration("1.5min").getMilliseconds());
    }
}
