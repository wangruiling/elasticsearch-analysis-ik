package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AnalyzerUtilsTest {
    String txt = "Adventure,Arm Around,,Non-Urban Scene,Car,20s,Car Trunk";
//    String txt = "Adventure,Arm Around,Camping,Car,Car Trunk,Carefree,Casual Clothing,Cheerful,Couple,Day,Expressing Positivity,Forest,Grass,Green,Happiness,Healthy Lifestyle,Heterosexual Couple,Journey,Leisure Activity,Loving,Nature,Non-Urban Scene,Outdoors,Playful,Playing,Recreational Pursuit,Romance,Smiling,Summer,Tent,Togetherness,Toothy Smile,Transportation,Travel,Tree,Vacations,Weekend Activities,Young Couple,People,20s,Adults Only,Young Adult,Asian and Indian Ethnicities,Asian Ethnicity,Chinese Ethnicity,Oriental Ethnicity,Females,Males,Men,Women,Young Men,Young Women,Two People,Asia,Beijing,China,East Asia,Color Image,Front View,Horizontal,Looking At Camera,Photography,Portrait,Waist Up";
    String cjkTxt = "中国驻洛杉矶领事馆遭亚裔男子枪击 嫌犯已自首";

    @Test
    public void testIKDisplayToken() throws Exception {
        Analyzer ik = new IKAnalyzer();
        Analyzer ikSmart = new IKAnalyzer();
        Analyzer ii = initAnalyzer();

        AnalyzerUtils.displayToken(txt, ik);
        AnalyzerUtils.displayToken(txt, ikSmart);
        AnalyzerUtils.displayToken(txt, ii);

    }

    private static Analyzer initAnalyzer() {
        String ES_HOME = "C:/tools/elastic/elasticsearch-7.5.2";
        Settings settings = Settings.builder()
                .put("path.home", ES_HOME)
//                .put("path.conf", "C:/tools/Elastic/elasticsearch-7.0.0")
                .put("use_smart", "true")
                .put("enable_lowercase", "false")
                .put("enable_remote_dict", "false")
                .build();

        Path configPath = Paths.get(ES_HOME).resolve("config");
        Environment env = new Environment(settings, configPath);

        //构建IK分词器，使用smart分词模式
        boolean useSmart = true;

        Configuration configuration = new Configuration(env, settings).setUseSmart(useSmart);

        Analyzer analyzer = new IKAnalyzer(configuration);

        return analyzer;
    }

    @Test
    public void testStandardAnalyzer() throws Exception {
        Analyzer analyzer = new StandardAnalyzer();
        AnalyzerUtils.displayAllTokenInfo(txt, analyzer);
//        System.out.println();
//        AnalyzerUtils.displayToken(txt, analyzer);
    }

    @Test
    public void testSimpleAnalyzer() throws Exception {
        Analyzer analyzer = new SimpleAnalyzer();
        String txt2 = "fast wi fi network has down";
        AnalyzerUtils.displayAllTokenInfo(txt2, analyzer);
    }

    @Test
    public void testWhitespaceAnalyzer() throws Exception {
        Analyzer analyzer = new WhitespaceAnalyzer();
        AnalyzerUtils.displayAllTokenInfo(txt, analyzer);
    }

    @Test
    public void testKeywordAnalyzer() throws Exception {
        Analyzer analyzer = new KeywordAnalyzer();
        AnalyzerUtils.displayAllTokenInfo(txt, analyzer);
    }

    @Test
    public void testCJKAnalyzer() throws Exception {
        Analyzer analyzer = new CJKAnalyzer();

        AnalyzerUtils.displayAllTokenInfo(cjkTxt, analyzer);
    }
}
