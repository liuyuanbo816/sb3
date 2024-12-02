package kafka3.basic;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;

import java.util.Properties;

public class KfkAdminClient {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.110.201:9092");

        try (AdminClient adminClient = AdminClient.create(properties)) {
            System.out.println(adminClient);
        }
    }
}
