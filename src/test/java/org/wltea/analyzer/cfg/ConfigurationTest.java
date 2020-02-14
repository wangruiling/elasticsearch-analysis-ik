package org.wltea.analyzer.cfg;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.dic.DicFile;


class ConfigurationTest {

    @Test
    void test() {
        Settings settings =  Settings.builder()
                .put("path.home", "C:/tools/elastic/elasticsearch-7.5.2")
                .put("use_smart", true)
                .put("enable_lowercase", false)
                .put("enable_remote_dict", false)
                .putList("ext_dic_main", Arrays.asList("#dicName$extra#dicPath$extra_test.dic#isRemote$false"))
                .build();

        Path configPath = Paths.get("C:/tools/elastic/elasticsearch-7.5.2/config");
        Environment env = new Environment(settings, configPath);

        Configuration configuration = new Configuration(env, settings).setUseSmart(true);

        List<DicFile> dicFiles = configuration.getDicFiles();
        System.out.println(dicFiles.size());
        dicFiles.forEach(dicFile -> {
            System.out.println(dicFile.getDicName() + ":" + dicFile.getDicPath());
        });
    }
}
