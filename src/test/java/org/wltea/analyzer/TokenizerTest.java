package org.wltea.analyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TokenizerTest {

    @Test
    public void main() throws IOException {
        String ES_HOME = "C:/tools/elastic/elasticsearch-7.5.2";
        List<String> ext_dic_list = new ArrayList<>(4);
//        ext_dic_list.add("#dicName$extra-test2#dicPath$extra_test2.dic#isRemote$false");
        ext_dic_list.add("#dicName$extra-remote#dicPath$http://localhost:80/main.dic#isRemote$true");
        ext_dic_list.add("#dicName$extra-test#dicPath$extra_test.dic#isRemote$false");

        Settings settings = Settings.builder()
                .put("path.home", ES_HOME)
                .put("use_smart", true)
                .put("enable_lowercase", false)
//                .put("enable_remote_dict", false)
                .putList("ext_dic_main", ext_dic_list)
                .build();

        Path configPath = Paths.get(ES_HOME).resolve("config");
        Environment env = new Environment(settings, configPath);

        Configuration configuration = new Configuration(env, settings).setUseSmart(true);

        IKAnalyzer ik = new IKAnalyzer(configuration);


        String t = "IK分词器Lucene Analyzer接口实现类 关于国际民生银行 我是中国人";
//        String t = "分词器";
        TokenStream tokenStream = ik.tokenStream("", new StringReader(t));
        tokenStream.reset();
        CharTermAttribute termAtt = tokenStream.addAttribute(CharTermAttribute.class);
        while (tokenStream.incrementToken()) {
            System.out.println(termAtt);
        }
        tokenStream.end();
        tokenStream.close();
    }
}
