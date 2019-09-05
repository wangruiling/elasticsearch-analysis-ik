package org.wltea.analyzer.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerUtils;
import org.junit.jupiter.api.Test;

/**
 * Created by wangrl on 2019-9-5.
 */
class CommaAnalyzerTest {
    @Test
    public void displayToken() {
        String txt = "Adventure,Arm Around,Camping,Car,Car Trunk,Carefree,Casual Clothing,Cheerful,Couple,Day,Expressing Positivity,Forest,Grass,Green,Happiness,Healthy Lifestyle,Heterosexual Couple,Journey,Leisure Activity,Loving,Nature,Non-Urban Scene,Outdoors,Playful,Playing,Recreational Pursuit,Romance,Smiling,Summer,Tent,Togetherness,Toothy Smile,Transportation,Travel,Tree,Vacations,Weekend Activities,Young Couple,People,20s,Adults Only,Young Adult,Asian and Indian Ethnicities,Asian Ethnicity,Chinese Ethnicity,Oriental Ethnicity,Females,Males,Men,Women,Young Men,Young Women,Two People,Asia,Beijing,China,East Asia,Color Image,Front View,Horizontal,Looking At Camera,Photography,Portrait,Waist Up";

        Analyzer analyzer1 = new CommaAnalyzer();

        AnalyzerUtils.displayToken(txt, analyzer1);
        System.out.println("------------------------------------");

    }
}