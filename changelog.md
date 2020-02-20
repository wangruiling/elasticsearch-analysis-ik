elasticsearch-analysis-ik Changelog

- 修改文件：CharacterUtil.java 第76行添加代码

```java
/************** 2016/4/08 此处使用使用新的字符处理规则:特殊字符'-' By:wangrl start ****************/
|| input == '-'
/************** 2016/4/08 此处使用使用新的字符处理规则 By:wangrl end ****************/
```

- 配置IK分词的同义词过滤器
```
PUT my_index
{
    "settings": {
        "analysis": {
            "analyzer": {
                "ik_max_syno": {
                    "type": "custom",
                    "tokenizer": "ik_max_word",
                    "filter": [
                        "my_synonym"
                    ]
                },
                "ik_smart_syno": {
                    "type": "custom",
                    "tokenizer": "ik_smart",
                    "filter": [
                        "my_synonym"
                    ]
                }
            },
            "filter": {
                "my_synonym": {
                    "type": "synonym",
                    "synonyms_path": "analysis-ik/synonym.txt"
                }
            }
        }
    },
  "mappings": {
      "properties": {
        "title":{
          "type": "text",
          "analyzer": "ik_max_syno",
          "search_analyzer": "ik_smart_syno"
        }
      }
  }
}

POST my_index/_analyze
{
  "analyzer": "ik_smart_syno",
  "text":     "出租车"
}
```
