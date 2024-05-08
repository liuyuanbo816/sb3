package bigdata.hadoop.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HDFSAPITest {

    private static final String HDFS_PATH = "hdfs://192.168.110.201:9010";
    private static final String HDFS_USER = "root";
    private static FileSystem fs;

    @BeforeAll
    public static void beforeAll() {
        Configuration cfg = new Configuration();
        cfg.set("dfs.replication", "1");
        cfg.set("fs.defaultFS", HDFS_PATH);
        cfg.set("hadoop.tmp.dir", "/home/zjc/bigdata/hadoop-3.3.6/tmp/hdp336");

        try {
            fs = FileSystem.get(new URI(HDFS_PATH), cfg, HDFS_USER);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void after() {
        fs = null;
    }

    @Test
    public void testCreateDir() throws IOException {
        fs.mkdirs(new Path("/home/zjc/bigdata/test/hdp-hdfs-api/t0"));
    }

}
