package org.wltea.analyzer.lucene;

import org.apache.lucene.analysis.util.CharTokenizer;

/**
 * 蓝牛仔自定义英文短语分词格式：以英文逗号","，作为分词标志，保留短语中的（"空格", "'", "-"）
 * @author wangrl
 * @Date: 2019-09-04 15:44
 */
public class CommaTokenizer extends CharTokenizer {

    @Override
    protected boolean isTokenChar(int c) {
        //蓝牛仔自定义英文短语分词格式：以英文逗号","，作为分词标志，保留短语中的（"空格", "'", "-"）
        if (44 == c) {
            return false;
        } else {
            return true;
        }
    }
}
