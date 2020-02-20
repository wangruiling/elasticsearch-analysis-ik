package org.wltea.analyzer.cfg;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.junit.jupiter.api.Test;


class ConfigurationTest {

    @Test
    void testConfiguration() {
        List<String> ext_dic_main_list = new ArrayList<>();
        ext_dic_main_list.add("#dicName$extra#dicPath$extra_test.dic#isRemote$false");
        ext_dic_main_list.add("#dicName$extra2#dicPath$http://localhost:80/extra_test2.dic#isRemote$true");

        Settings settings =  Settings.builder()
                .put("path.home", "C:/tools/elastic/elasticsearch-7.5.2")
                .put("use_smart", true)
                .put("enable_lowercase", false)
                .put("enable_remote_dict", false)
                .putList("ext_dic_main", ext_dic_main_list)
                .build();

        Path configPath = Paths.get("C:/tools/elastic/elasticsearch-7.5.2/config");
        Environment env = new Environment(settings, configPath);

        Configuration configuration = new Configuration(env, settings).setUseSmart(true);

//        List<DicFile> dicFiles = configuration.getDicFiles();
//        System.out.println(dicFiles.size());
//        dicFiles.forEach(dicFile -> {
//            System.out.println("dicName=" + dicFile.getDicName() + ";dicPath=" + dicFile.getAbsolutePath() + "\\" + dicFile.getDicPath());
//        });
    }
}
