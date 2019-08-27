package org.wltea.analyzer.sample;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangrl
 * @Date: 2019-08-27 14:51
 */
public class DicTest {
    /**
     * 功能描述: <br>
     * 创建IK分词器使用的热更新词典
     * @since: 1.0.0
     * @author: wangrl
     * @create: 2019-8-27 15:18
     */
    @Test
    public void createDic() throws IOException {
        List<String> words = new ArrayList<>(4);
        words.add("蓝牛仔");
        words.add("安徽省");
        words.add("一家三口");
        words.add("麻省理工学院");
        Path dicFilePath = Paths.get("/ossfs/static/html/extra_main.dic");
        Files.write(dicFilePath, words);
    }
}
