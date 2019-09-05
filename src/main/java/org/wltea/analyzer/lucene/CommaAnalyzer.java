package org.wltea.analyzer.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenFilter;

/**
 * 蓝牛仔自定义英文短语分词器
 * 蓝牛仔自定义英文短语分词格式：以英文逗号","，作为分词标志，保留短语中的（"空格", "'", "-"）
 * @author wangrl
 * @Date: 2019-09-05 17:06
 */
public class CommaAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        CommaTokenizer tokenizer = new CommaTokenizer();

        TokenFilter tokenFilter = new LowerCaseFilter(tokenizer);
        return new TokenStreamComponents(tokenizer, tokenFilter);
    }
}
