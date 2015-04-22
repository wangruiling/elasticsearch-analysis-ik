package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.junit.Test;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class AnalyzerUtilsTest {

    @Test
    public void testDisplayToken() throws Exception {
        String txt = "Adventure,Arm Around,Camping,Car,Car Trunk,Carefree,Casual Clothing,Cheerful,Couple,Day,Expressing Positivity,Forest,Grass,Green,Happiness,Healthy Lifestyle,Heterosexual Couple,Journey,Leisure Activity,Loving,Nature,Non-Urban Scene,Outdoors,Playful,Playing,Recreational Pursuit,Romance,Smiling,Summer,Tent,Togetherness,Toothy Smile,Transportation,Travel,Tree,Vacations,Weekend Activities,Young Couple,People,20s,Adults Only,Young Adult,Asian and Indian Ethnicities,Asian Ethnicity,Chinese Ethnicity,Oriental Ethnicity,Females,Males,Men,Women,Young Men,Young Women,Two People,Asia,Beijing,China,East Asia,Color Image,Front View,Horizontal,Looking At Camera,Photography,Portrait,Waist Up";
//        Analyzer a1 = new StandardAnalyzer(Version.LUCENE_4_9);
//        Analyzer a2 = new StopAnalyzer(Version.LUCENE_4_9);
        Analyzer a3 = new SimpleAnalyzer(Version.LUCENE_4_9);
//        Analyzer a4 = new WhitespaceAnalyzer(Version.LUCENE_4_9);
        Analyzer ik = new IKAnalyzer();
        Analyzer ikSmart = new IKAnalyzer(true);

        Settings settings = ImmutableSettings.builder()
                .put("cluster.name", "elasticsearch_stg")
                .put("use_smart", "true")
                //探测集群中机器状态
                .put("client.transport.sniff", true).build();
        Settings indexSettings = ImmutableSettings.builder().build();
        Settings envSettings = ImmutableSettings.builder()
                .put("path.home", "E:/Lucene")
                .build();
        Environment env = new Environment(envSettings);
        Dictionary.initial(new Configuration(env));
        Analyzer ii = new IKAnalyzer(indexSettings, settings, env);


//        AnalyzerUtils.displayToken(txt, a1);
//        AnalyzerUtils.displayToken(txt, a2);
//        AnalyzerUtils.displayToken(txt, a3);
//        AnalyzerUtils.displayToken(txt, a4);
        AnalyzerUtils.displayToken(txt, ik);
        AnalyzerUtils.displayToken(txt, ikSmart);
        AnalyzerUtils.displayToken(txt, ii);

    }

    @Test
    public void testDisplayAllTokenInfo() throws Exception {
        String txt = "Adventure,Arm Around,Camping,Car,Car Trunk,Carefree,Casual Clothing,Cheerful,Couple,Day,Expressing Positivity,Forest,Grass,Green,Happiness,Healthy Lifestyle,Heterosexual Couple,Journey,Leisure Activity,Loving,Nature,Non-Urban Scene,Outdoors,Playful,Playing,Recreational Pursuit,Romance,Smiling,Summer,Tent,Togetherness,Toothy Smile,Transportation,Travel,Tree,Vacations,Weekend Activities,Young Couple,People,20s,Adults Only,Young Adult,Asian and Indian Ethnicities,Asian Ethnicity,Chinese Ethnicity,Oriental Ethnicity,Females,Males,Men,Women,Young Men,Young Women,Two People,Asia,Beijing,China,East Asia,Color Image,Front View,Horizontal,Looking At Camera,Photography,Portrait,Waist Up";
        Analyzer a1 = new StandardAnalyzer(Version.LUCENE_4_9);
//        Analyzer a2 = new StopAnalyzer(Version.LUCENE_4_9);
//        Analyzer a3 = new SimpleAnalyzer(Version.LUCENE_4_9);
//        Analyzer a4 = new WhitespaceAnalyzer(Version.LUCENE_4_9);
        AnalyzerUtils.displayAllTokenInfo(txt, a1);
    }
}