package org.zmz.sb3.newfeatures.test.reflect;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReflectTest1 {

    private static final Pattern linePattern = Pattern.compile("_(\\w)");

    @Test
    public void t1() throws JsonProcessingException {
        MetaDataset metaDataset = new MetaDataset();

        Map<String, Object> m = BeanUtil.beanToMap(metaDataset);

        System.out.println("m size: " + m.size());

        String json = "{\"s_operation\":\"重庆公司本部\",\"s_install_path\":\"/\",\"professional_code\":\"1001\",\"s_reply_code\":\"17C0MM0002CQ001\",\"s_operation_department\":\"人员虚拟部门\",\"s_storage_mode\":\"1\",\"i_status\":1,\"s_inventory_leader_phone\":\"15320584301\",\"s_host_ip\":\"127.0.0.1\",\"s_support_to_other\":\"向其他系统提供数据\",\"s_background_db_account\":\"0\",\"s_contractor_manufacturer\":\"重庆湘缘物资有限公司\",\"s_inventory\":\"重庆公司本部\",\"s_main_system_name\":\"BCQFD3\",\"s_management_leader\":\"lj\",\"s_contractor\":\"重庆公司本部\",\"s_system_ip\":\"127.0.0.1\",\"s_meta_code\":\"134!@!重庆分舵3\",\"s_group_data\":\"入集团大数据平台的数据\",\"s_system_introduce\":\"重庆分舵重庆分舵重庆分舵重庆分舵重庆分舵\",\"s_online_date\":\"2024-03-20 17:26:20\",\"s_app_account\":\"0\",\"s_create_staff_name\":\"admin\",\"s_system_import_data\":\"系统重要数据资产\",\"s_operation_leader_phone\":\"15320584301\",\"s_function_type\":\"1\",\"s_meta_version\":\"v20240322.150107\",\"s_operation_leader\":\"张馨\",\"s_inventory_leader_email\":\"15320584301@189.cn\",\"s_system_type\":\"1\",\"s_contractor_department\":\"人员虚拟部门\",\"s_user\":\"全市分公司\",\"s_external_data_type\":\"外部数据种类\",\"s_sub_system_name\":\"重庆分舵3\",\"s_system_create_data\":\"本系统生成数据\",\"s_startup_parameter\":\"--debug\",\"i_modify_status\":3,\"d_update_time\":\"2024-03-21 21:01:32\",\"s_contractor_leader_email\":\"15320584301@189.cn\",\"s_update_staff_name\":\"admin\",\"s_sensitive_words\":\"0\",\"s_reply_project_name\":\"17C0MM0002CQ\",\"s_external_data\":\"0\",\"s_inventory_leader\":\"张馨\",\"s_templet_code\":\"134\",\"s_inventory_department\":\"人员虚拟部门\",\"i_update_staff_id\":\"1\",\"s_major_sensitive_words\":\"id\",\"s_underline_date\":\"Invalid date\",\"i_create_staff_id\":\"116623\",\"s_operation_leader_email\":\"15320584301@189.cn\",\"s_port\":\"22\",\"s_province_data\":\"入省大数据平台的数据\",\"s_management_department\":\"-\",\"s_management_leader_email\":\"0027024016@iwhalecloud.com\",\"s_management_leader_phone\":\"13617089880\",\"s_contractor_leader\":\"张馨\",\"s_application\":\"重庆分舵\",\"s_user_department\":\"全市部门\",\"s_reply_syetem_name\":\"系统工程批复信息\",\"s_log_path\":\"/\",\"s_organization_level\":\"组织层次\",\"s_management\":\"-\",\"s_system_code\":\"CQ-CQBB-ZQFD-0098\",\"s_cannula_staff_platform\":\"0\",\"s_contractor_leader_phone\":\"15320584301\",\"s_busi_manage_department\":\"业务管理单位\",\"s_other_collect_data\":\"其他系统采集数据\",\"grading_filing_system_name\":\"定级备案系统名称\",\"s_system_abbreviation\":\"CQFD3\",\"d_create_time\":\"2024-03-21 21:01:33\",\"s_province\":\"重庆分舵\",\"s_collect_id\":\"134!@!重庆分舵3\"}";
        Map<String, String> map = new ObjectMapper().readValue(json, new TypeReference<>() {
        });
        System.out.println("map size: " + map.size());

        int i = 0;
        for (String s : map.keySet()) {
            // 避免状态脏数据覆盖
            if (m.containsKey(lineToHump(s)) && m.get(lineToHump(s)) != null) {
                continue;
            }
            m.put(lineToHump(s), i + "");
        }

        System.out.println("最终 m size : " + m.size());
        m.keySet().forEach(System.out::println);
    }

    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuilder sb = new StringBuilder(512);
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    @Test
    public void t2() throws IOException {
        List<String> list1 = Files.readAllLines(Path.of("D:\\worksp\\ideaProjects\\sb3\\sb3-new-features\\src\\test\\java\\org\\zmz\\sb3\\newfeatures\\test\\reflect\\z1.txt"));
        System.out.println("list1 size : " + list1.size());
        List<String> list2 = Files.readAllLines(Path.of("D:\\worksp\\ideaProjects\\sb3\\sb3-new-features\\src\\test\\java\\org\\zmz\\sb3\\newfeatures\\test\\reflect\\z2.txt"))
                .stream()
                .map(s -> s.split(":")[0])
                .collect(Collectors.toList());
        System.out.println("list2 size : " + list2.size());

        list2.removeAll(list1);

        System.out.println(list2);
    }
}
