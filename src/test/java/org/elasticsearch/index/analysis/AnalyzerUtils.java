package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * 分词查看器,通过TokenStream显示分词的详细信息
 * TokenStream用于访问token(词汇,单词,最小的索引单位),可以看做token的迭代器
 * https://www.xttblog.com/?p=1828
 * https://www.cnblogs.com/tele-share/p/9194849.html
 * @author wangrl
 * @Date: 2019-09-02 16:48
 */
public class AnalyzerUtils {
    public static void displayToken(TokenStream stream) {
        try {
            //创建一个属性，这个属性会添加流中，随着这个TokenStream增加
            //CharTermAttribute-保存相应词汇
            CharTermAttribute cta = stream.getAttribute(CharTermAttribute.class);

            //在调用incrementToken()开始消费token之前需要重置stream到一个干净的状态
            stream.reset();
            while (stream.incrementToken()) {
                System.out.print("[" + cta.toString() + "]");
            }
            System.out.println();

            //在调用 incrementToken() 结束迭代之后，调用 end() 和 close() 方法，其中 end() 可以唤醒当前 TokenStream 的处理器去做一些收尾工作，
            //close() 可以关闭 TokenStream 和 Analyzer 去释放在分析过程中使用的资源。
            stream.end();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayAllTokenInfo(TokenStream stream) {
        try {
            //位置增量的属性，存储语汇单元之间的距离
            PositionIncrementAttribute pia = stream.addAttribute(PositionIncrementAttribute.class);

            //每个语汇单元的位置偏移量
            OffsetAttribute oa = stream.addAttribute(OffsetAttribute.class);

            //存储每一个语汇单元的信息（分词单元信息）
            CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);

            //使用的分词器的类型信息
            TypeAttribute ta = stream.addAttribute(TypeAttribute.class);

            //在调用incrementToken()开始消费token之前需要重置stream到一个干净的状态
            stream.reset();
            while (stream.incrementToken()) {
                System.out.print(pia.getPositionIncrement() + ":");
                System.out.print(cta + "[" + oa.startOffset() + "-" + oa.endOffset() + "]-->" + ta.type() + "\n");
            }

            //在调用 incrementToken() 结束迭代之后，调用 end() 和 close() 方法，其中 end() 可以唤醒当前 TokenStream 的处理器去做一些收尾工作，
            //close() 可以关闭 TokenStream 和 Analyzer 去释放在分析过程中使用的资源。
            stream.end();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayToken(String str, Analyzer a) {
        System.out.println(a.getClass());
        TokenStream stream = a.tokenStream("content", new StringReader(str));
        displayToken(stream);
    }

    public static void displayAllTokenInfo(String str, Analyzer a) {
        TokenStream stream = a.tokenStream("content", new StringReader(str));

        displayAllTokenInfo(stream);
    }
}
