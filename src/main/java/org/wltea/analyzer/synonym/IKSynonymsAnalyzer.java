package org.wltea.analyzer.synonym;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.lucene.IKTokenizer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * 可以加载同义词库的Lucene 专用IK分词器
 * http://qindongliang.iteye.com/blog/1922742
 * @author: wangrl
 * @Date: 2014-09-23 17:20
 */
public class IKSynonymsAnalyzer extends Analyzer{
    private Configuration configuration;

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        //原文链接：https://blog.csdn.net/winnerspring/article/details/37567739

        Tokenizer token = new IKTokenizer(configuration);
        Map<String, String> paramsMap = new HashMap<>(2);
        paramsMap.put("luceneMatchVersion", "LUCENE_43");
        paramsMap.put("synonyms", "C:\\同义词\\synonyms.txt");
        SynonymFilterFactory factory=new SynonymFilterFactory(paramsMap);
        //FilesystemResourceLoader loader = new FilesystemResourceLoader();
        try {
            //factory.inform(loader);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new TokenStreamComponents(token, factory.create(token));
    }


    //@Override
    //protected TokenStreamComponents createComponents(String fieldName) {
    //    Version ver = Version.LUCENE_8_1_0;
    //    Map<String, String> filterArgs = new HashMap<String, String>();
    //    filterArgs.put("luceneMatchVersion", ver.toString());
    //    filterArgs.put("synonyms", "C:/tools/Elastic/ik/config/synonym.txt");
    //    //filterArgs.put("expand", "true");
    //
    //
    //    TokenStream tokenStream = null;
    //    IKAnalyzer ikAnalyzer = new IKAnalyzer();
    //    try {
    //        SynonymGraphFilterFactory factory = new SynonymGraphFilterFactory(filterArgs);
    //        factory.inform(new FilesystemResourceLoader(Paths.get("C:/tools/Elastic/ik/config"), new ClasspathResourceLoader(Thread.currentThread().getContextClassLoader())));
    //
    //        factory.create()
    //        tokenStream = factory.create(ikAnalyzer.tokenStream(fieldName, reader));
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //    return new TokenStreamComponents(tokenizer, tokenStream);
    //}
}
