package org.wltea.analyzer.synonym;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.util.CharsRef;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.lucene.IKTokenizer;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * 可以加载同义词库的Lucene 专用IK分词器
 * http://qindongliang.iteye.com/blog/1922742
 * @author: wangrl
 * @Date: 2014-09-23 17:20
 */
public class IKSynonymsAnalyzer extends Analyzer{
    private Configuration configuration;

    public IKSynonymsAnalyzer() {
        super();
    }

    public IKSynonymsAnalyzer(Configuration configuration) {
        super();
        this.configuration = configuration;
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {

        Tokenizer tokenizer = new IKTokenizer(configuration);

        SynonymMap.Builder synonymsBuilder = new SynonymMap.Builder();
        CharsRef input = new CharsRef("一家五口");
        CharsRef output = new CharsRef("爷爷,奶奶,五个人");
        synonymsBuilder.add(input, output, true);

        SynonymMap synonyms = null;
        try {
            synonyms = synonymsBuilder.build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SynonymGraphFilter synonymGraphFilter = new SynonymGraphFilter(tokenizer, synonyms, false);
        return new TokenStreamComponents(tokenizer, synonymGraphFilter);
    }

    //@Override
    //protected TokenStreamComponents createComponents(String fieldName) {
    //    //原文链接：https://blog.csdn.net/winnerspring/article/details/37567739
    //
    //    Tokenizer tokenizer = new IKTokenizer(configuration);
    //    Map<String, String> paramsMap = new HashMap<>(2);
    //    paramsMap.put("synonyms", "synonym.txt");
    //    //paramsMap.put("analyzer", "");
    //    //paramsMap.put("tokenizerFactory", "");
    //
    //    SynonymGraphFilterFactory factory = new SynonymGraphFilterFactory(paramsMap);
    //    try {
    //        Path baseDirectory = Paths.get("C:/tools/Elastic/ik/config/");
    //        factory.inform(new FilesystemResourceLoader(baseDirectory, new ClasspathResourceLoader(Thread.currentThread().getContextClassLoader())));
    //        //factory.inform(new ClasspathResourceLoader(Thread.currentThread().getContextClassLoader()));
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //    return new TokenStreamComponents(tokenizer, factory.create(tokenizer));
    //}

}
