package org.wltea.analyzer.synonym;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.synonym.SynonymGraphFilterFactory;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.apache.lucene.analysis.util.FilesystemResourceLoader;
import org.apache.lucene.util.Version;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.analysis.AnalyzerUtils;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.lucene.IKAnalyzer;


class IKSynonymsAnalyzerTest {
    @Test
    public void testSynonyms() {
        String text = "domain name system is fragile";
        Analyzer analyzer = new IKSynonymsAnalyzer();
        AnalyzerUtils.displayToken(text, analyzer);
    }

    @Test
    public void testSynonyms2() {
//        String text = "215|中国|华北|北京市|北京市|联通 这是一个人的4S店，这是有15-2岁婴儿的一家五口";
        String text = "domain name system is fragile";
        IKSynonymsAnalyzer analyzer = new IKSynonymsAnalyzer(initConfiguration());

        AnalyzerUtils.displayToken(text, analyzer);
    }

    public static void main(String[] args) throws Exception {
        //String testInput = "其实 i似 好人";
        String testInput = "this is a tom";
        Version ver = Version.LUCENE_8_1_0;
        Map<String, String> filterArgs = new HashMap<String, String>();
        filterArgs.put("luceneMatchVersion", ver.toString());
        filterArgs.put("synonyms", "C:/tools/Elastic/ik/config/synonym.txt");
        //filterArgs.put("expand", "true");
        SynonymGraphFilterFactory factory = new SynonymGraphFilterFactory(filterArgs);
        factory.inform(new FilesystemResourceLoader(Paths.get("C:/tools/Elastic/ik/config"), new ClasspathResourceLoader(Thread.currentThread().getContextClassLoader())));
        Analyzer ikAnalyzer = initAnalyzer();
        TokenStream ts = factory.create(ikAnalyzer.tokenStream("someField", testInput));
        AnalyzerUtils.displayToken(ts);
    }

    private static Analyzer initAnalyzer() {
        Settings settings = Settings.builder()
                .put("path.home", "C:/tools/Elastic/elasticsearch-7.3.1")
                .put("path.conf", "C:/tools/Elastic/elasticsearch-7.3.1")
                .put("use_smart", "true")
                .put("enable_lowercase", "false")
                .put("enable_remote_dict", "false")
                .build();

        Path configPath = Paths.get("C:/tools/Elastic/elasticsearch-7.3.1/config");
        Environment env = new Environment(settings, configPath);

        //构建IK分词器，使用smart分词模式
        boolean useSmart = true;

        Configuration configuration = new Configuration(env, settings).setUseSmart(useSmart);

        return new IKAnalyzer(configuration);
    }

    private static Configuration initConfiguration() {
        Settings settings = Settings.builder()
                .put("path.home", "C:/tools/Elastic/elasticsearch-7.3.1")
                .put("path.conf", "C:/tools/Elastic/elasticsearch-7.3.1")
                .put("use_smart", "true")
                .put("enable_lowercase", "false")
                .put("enable_remote_dict", "false")
                .build();

        Path configPath = Paths.get("C:/tools/Elastic/elasticsearch-7.3.1/config");
        Environment env = new Environment(settings, configPath);

        //构建IK分词器，使用smart分词模式
        boolean useSmart = true;

        Configuration configuration = new Configuration(env, settings).setUseSmart(useSmart);

        return configuration;
    }
}
