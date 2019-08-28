package org.wltea.analyzer.lucene.synonym;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.synonym.SynonymGraphFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.apache.lucene.analysis.util.FilesystemResourceLoader;
import org.apache.lucene.util.Version;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 原文链接：https://blog.csdn.net/winnerspring/article/details/37521101
 *
 * @author wangrl
 * @Date: 2019-08-27 16:31
 */
public class SynonymsTest {
    private static void displayTokens(TokenStream ts) throws IOException {
        CharTermAttribute termAttr = ts.addAttribute(CharTermAttribute.class);
        OffsetAttribute offsetAttribute = ts.addAttribute(OffsetAttribute.class);
        ts.reset();
        while (ts.incrementToken()) {
            String token = termAttr.toString();
            System.out.print(offsetAttribute.startOffset() + "-" + offsetAttribute.endOffset() + "[" + token + "] ");
        }
        System.out.println();
        ts.end();
        ts.close();
    }

    public static void main(String[] args) throws Exception {
        //String testInput = "其实 i似 好人";
        String testInput = "三劫散仙是一个菜鸟";
        Version ver = Version.LUCENE_8_1_0;
        Map<String, String> filterArgs = new HashMap<String, String>();
        filterArgs.put("luceneMatchVersion", ver.toString());
        filterArgs.put("synonyms", "C:/tools/Elastic/ik/config/synonym.txt");
        //filterArgs.put("expand", "true");
        SynonymGraphFilterFactory factory = new SynonymGraphFilterFactory(filterArgs);
        factory.inform(new FilesystemResourceLoader(Paths.get("C:/tools/Elastic/ik/config"), new ClasspathResourceLoader(Thread.currentThread().getContextClassLoader())));
        Analyzer ikAnalyzer = initAnalyzer();
        TokenStream ts = factory.create(ikAnalyzer.tokenStream("someField", testInput));
        displayTokens(ts);
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
}
