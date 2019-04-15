package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AnalyzerUtilsTest {

    @Test
    public void testDisplayToken() throws Exception {
        String txt = "Adventure,Arm Around,Camping,Car,Car Trunk,Carefree,Casual Clothing,Cheerful,Couple,Day,Expressing Positivity,Forest,Grass,Green,Happiness,Healthy Lifestyle,Heterosexual Couple,Journey,Leisure Activity,Loving,Nature,Non-Urban Scene,Outdoors,Playful,Playing,Recreational Pursuit,Romance,Smiling,Summer,Tent,Togetherness,Toothy Smile,Transportation,Travel,Tree,Vacations,Weekend Activities,Young Couple,People,20s,Adults Only,Young Adult,Asian and Indian Ethnicities,Asian Ethnicity,Chinese Ethnicity,Oriental Ethnicity,Females,Males,Men,Women,Young Men,Young Women,Two People,Asia,Beijing,China,East Asia,Color Image,Front View,Horizontal,Looking At Camera,Photography,Portrait,Waist Up";
        Analyzer a3 = new SimpleAnalyzer();
//        Analyzer a4 = new WhitespaceAnalyzer(Version.LUCENE_4_9);
        Analyzer ik = new IKAnalyzer();
        Analyzer ikSmart = new IKAnalyzer();
        Analyzer ii = initAnalyzer();


//        AnalyzerUtils.displayToken(txt, a1);
//        AnalyzerUtils.displayToken(txt, a2);
//        AnalyzerUtils.displayToken(txt, a3);
//        AnalyzerUtils.displayToken(txt, a4);
        AnalyzerUtils.displayToken(txt, ik);
        AnalyzerUtils.displayToken(txt, ikSmart);
        AnalyzerUtils.displayToken(txt, ii);

    }

    private static Analyzer initAnalyzer() {
        Settings settings = Settings.builder()
                .put("path.home", "C:/tools/Elastic/elasticsearch-6.6.2")
                .put("path.conf", "C:/tools/Elastic/elasticsearch-6.6.2")
                .put("use_smart", "true")
                .put("enable_lowercase", "false")
                .put("enable_remote_dict", "false")
                .build();

        Path configPath = Paths.get("D:/tools/JetBrains/projects/bluejean-boots/third-project/elasticsearch-analysis-ik/");
        Environment env = new Environment(settings, configPath);

        //构建IK分词器，使用smart分词模式
        boolean useSmart = true;

        Configuration configuration = new Configuration(env, settings).setUseSmart(useSmart);

        Analyzer analyzer = new IKAnalyzer(configuration);


        return analyzer;
    }

    @Test
    public void testDisplayAllTokenInfo() throws Exception {
        String txt = "Adventure,Arm Around,Camping,Car,Car Trunk,Carefree,Casual Clothing,Cheerful,Couple,Day,Expressing Positivity,Forest,Grass,Green,Happiness,Healthy Lifestyle,Heterosexual Couple,Journey,Leisure Activity,Loving,Nature,Non-Urban Scene,Outdoors,Playful,Playing,Recreational Pursuit,Romance,Smiling,Summer,Tent,Togetherness,Toothy Smile,Transportation,Travel,Tree,Vacations,Weekend Activities,Young Couple,People,20s,Adults Only,Young Adult,Asian and Indian Ethnicities,Asian Ethnicity,Chinese Ethnicity,Oriental Ethnicity,Females,Males,Men,Women,Young Men,Young Women,Two People,Asia,Beijing,China,East Asia,Color Image,Front View,Horizontal,Looking At Camera,Photography,Portrait,Waist Up";
        Analyzer a1 = new StandardAnalyzer();
//        Analyzer a2 = new StopAnalyzer(Version.LUCENE_4_9);
//        Analyzer a3 = new SimpleAnalyzer(Version.LUCENE_4_9);
//        Analyzer a4 = new WhitespaceAnalyzer(Version.LUCENE_4_9);
        AnalyzerUtils.displayAllTokenInfo(txt, a1);
    }
}