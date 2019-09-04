package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.UnicodeWhitespaceAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.ClassicAnalyzer;
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
    public void displayToken() {
        String txt = "Adventure,Arm Around,Camping,Car,Car Trunk,Carefree,Casual Clothing,Cheerful,Couple,Day,Expressing Positivity,Forest,Grass,Green,Happiness,Healthy Lifestyle,Heterosexual Couple,Journey,Leisure Activity,Loving,Nature,Non-Urban Scene,Outdoors,Playful,Playing,Recreational Pursuit,Romance,Smiling,Summer,Tent,Togetherness,Toothy Smile,Transportation,Travel,Tree,Vacations,Weekend Activities,Young Couple,People,20s,Adults Only,Young Adult,Asian and Indian Ethnicities,Asian Ethnicity,Chinese Ethnicity,Oriental Ethnicity,Females,Males,Men,Women,Young Men,Young Women,Two People,Asia,Beijing,China,East Asia,Color Image,Front View,Horizontal,Looking At Camera,Photography,Portrait,Waist Up";

        Analyzer analyzer1 = new StandardAnalyzer();
        Analyzer analyzer2 = new EnglishAnalyzer();
        Analyzer analyzer3 = new KeywordAnalyzer();
        Analyzer analyzer4 = new WhitespaceAnalyzer();
        Analyzer analyzer5 = new UnicodeWhitespaceAnalyzer();
        Analyzer analyzer6 = new ClassicAnalyzer();

        AnalyzerUtils.displayToken(txt, analyzer1);
        System.out.println("------------------------------------");
        AnalyzerUtils.displayToken(txt, analyzer2);
        System.out.println("------------------------------------");
        AnalyzerUtils.displayToken(txt, analyzer3);
        System.out.println("------------------------------------");
        AnalyzerUtils.displayToken(txt, analyzer4);
        System.out.println("------------------------------------");
        AnalyzerUtils.displayToken(txt, analyzer5);
        System.out.println("------------------------------------");
        AnalyzerUtils.displayToken(txt, analyzer6);
    }

    @Test
    public void displayAllTokenInfo() {
        String txt = "Adventure,Arm Around,Camping,Car,Car Trunk,Carefree,Casual Clothing,Cheerful,Couple,Day,Expressing Positivity,Forest,Grass,Green,Happiness,Healthy Lifestyle,Heterosexual Couple,Journey,Leisure Activity,Loving,Nature,Non-Urban Scene,Outdoors,Playful,Playing,Recreational Pursuit,Romance,Smiling,Summer,Tent,Togetherness,Toothy Smile,Transportation,Travel,Tree,Vacations,Weekend Activities,Young Couple,People,20s,Adults Only,Young Adult,Asian and Indian Ethnicities,Asian Ethnicity,Chinese Ethnicity,Oriental Ethnicity,Females,Males,Men,Women,Young Men,Young Women,Two People,Asia,Beijing,China,East Asia,Color Image,Front View,Horizontal,Looking At Camera,Photography,Portrait,Waist Up";

        Analyzer analyzer = new StandardAnalyzer();
        TokenStream stream = analyzer.tokenStream("content", txt);
        AnalyzerUtils.displayAllTokenInfo(stream);
    }

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
//        AnalyzerUtils.displayToken(txt, ik);
//        AnalyzerUtils.displayToken(txt, ikSmart);
        AnalyzerUtils.displayToken(txt, ii);

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