package com.hdu.edu.lucene.seacher;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.hdu.edu.bean.ChatRecordBean;

/**
 * 查找索引 Created with IntelliJ IDEA. User: xuronghua Date: 15-12-10 Time: 下午1:38 To change this template use File |
 * Settings | File Templates.
 */
public class NormalLuceneSearch
{
    // 声明一个IndexSearcher对象
    protected IndexSearcher searcher = null;
    
    // 声明一个Query对象
    private Query query = null;
    
    private String questionfield = "question";
    
    // 构造函数
    public NormalLuceneSearch()
    {
        try
        {
            
            IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(ConstantsFile.INDEX_FILE_PATH)));
            
            searcher = new IndexSearcher(indexReader);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    // 返回查询结果
    public TopDocs search(String keyWord)
    {
        
        System.out.println("正在检索关键字：" + keyWord);
        try
        {
            
            Analyzer analyzer = new IKAnalyzer(true);
            
            QueryParser queryParser = new QueryParser(Version.LUCENE_47, questionfield, analyzer);
            // 将关键字包装成Query对象
            query = queryParser.parse(keyWord);
            TopDocs results = searcher.search(query, 5 * 2);
            
            for (int i = 0; i < results.scoreDocs.length; i++)
            {
                System.out.println(results.scoreDocs[i].score);
            }
            
            return results;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public ChatRecordBean readytoAnswer(String question, ChatRecordBean chatrecordBean)
    {
        
        try
        {
            
            NormalLuceneSearch luceneSearch = new NormalLuceneSearch();
            
            int number = 0;
            
            TopDocs topDocs = null;
            topDocs = luceneSearch.search(question);
            
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            
            if (scoreDocs.length == 0 || scoreDocs[0].score < 0.35)
            {
                luceneSearch.printAnswer(0, topDocs, chatrecordBean);
                
                return chatrecordBean;
            }
            // 匹配度最高的匹配分数大于等于1----高度匹配，只返回一个结果
            if (scoreDocs[0].score >= 1)
            {
                // scoreDocs.length == 1 因为有可能只返回一条数据 第一条和第二条分数差大于0.3，只返回第一条
                if (scoreDocs.length == 1 || (scoreDocs[0].score - scoreDocs[1].score) > 0.3)
                {
                    luceneSearch.printAnswer(1, topDocs, chatrecordBean);
                }
                else
                {// 头两条分数差值小于0.3，返回头两条对应的问题，即没有找到确切答案
                    
                    for (int i = 0; i < scoreDocs.length; i++)
                    {
                        if (scoreDocs[i].score >= 1)
                        {
                            number++;
                        }
                        else
                        {
                            break;
                        }
                        if (i == 5)
                        {
                            break;
                        }
                    }
                    luceneSearch.printAnswer(number, topDocs, chatrecordBean);
                }
                return chatrecordBean;
            }
            
            for (int i = 0; i < scoreDocs.length; i++)
            {
                if (scoreDocs[i].score >= 0.35)
                {
                    number++;
                }
                else
                {
                    break;
                }
                if (i == 5)
                {
                    break;
                }
                luceneSearch.printAnswer(number, topDocs, chatrecordBean);
                
            }
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return chatrecordBean;
    }
    
    public ChatRecordBean printAnswer(int number, TopDocs topDocs, ChatRecordBean chatrecordBean)
    {
        // TODO Auto-generated method stub
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        Document document = null;
        String responseMsg = "您可能想问的是不是：";
        // 目前只输出一个结果
        if (number == 1)
        {
            
            try
            {
                document = searcher.doc(scoreDocs[0].doc);
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            chatrecordBean.setResponse_msg(document.get("answer"));
            chatrecordBean.setCategory(document.get("category"));
            Date chat_date = new Date(System.currentTimeMillis());
            chatrecordBean.setChat_date(chat_date);
        }
        else if (number == 0)
        {
            String msg = "对不起，您的问题太深奥了，我暂时不能理解";
            chatrecordBean.setResponse_msg(msg);
            chatrecordBean.setCategory("未解决");
            Date chat_date = new Date(System.currentTimeMillis());
            chatrecordBean.setChat_date(chat_date);
        }
        else
        {
            for (int i = 0; i < number; i++)
            {
                try
                {
                    document = searcher.doc(scoreDocs[i].doc);
                    responseMsg += " " + (i + 1) + "." + document.get("question");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            chatrecordBean.setResponse_msg(responseMsg);
            chatrecordBean.setCategory("复杂提问");
            Date chat_date = new Date(System.currentTimeMillis());
            chatrecordBean.setChat_date(chat_date);
            
        }
        
        return chatrecordBean;
    }
    
}
