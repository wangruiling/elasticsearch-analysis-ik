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
        String txt = "Adventure,Arm Around,Camping,Car,Car Trunk,Casual Clothing,Non-Urban Scene,20s,Adults Only,Young Adult,Asian and Indian Ethnicities";

        Analyzer analyzer1 = new CommaAnalyzer();

        AnalyzerUtils.displayToken(txt, analyzer1);
        System.out.println("------------------------------------");
    }
}
