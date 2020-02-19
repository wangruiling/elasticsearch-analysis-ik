package org.wltea.analyzer.dic;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.cfg.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @Test
    void loadAllDictFiles() {
        List<String> ext_dic_main_list = new ArrayList<>();
        ext_dic_main_list.add("#dicName$extra#dicPath$extra_test.dic#isRemote$false");
        ext_dic_main_list.add("#dicName$extra2#dicPath$extra_test2.dic#isRemote$false");

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

        // 初始化词典
        Dictionary.getSingleton().loadAllDictFiles(configuration.getDicFiles());
    }
}
