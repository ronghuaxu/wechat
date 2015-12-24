package com.hdu.edu.lucene.seacher;

import java.io.File;
import java.util.Date;

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

/**
 * 查找索引 Created with IntelliJ IDEA. User: xuronghua Date: 15-12-10 Time: 下午1:38 To
 * change this template use File | Settings | File Templates.
 */
public class NormalLuceneSearch {
	// 声明一个IndexSearcher对象
	protected IndexSearcher searcher = null;
	// 声明一个Query对象
	private Query query = null;
	private String questionfield = "question";

	// 构造函数
	public NormalLuceneSearch() {
		try {
			
			IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(ConstantsFile.INDEX_FILE_PATH)));
			
			searcher = new IndexSearcher(indexReader);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 返回查询结果
	public TopDocs search(String keyWord) {
		System.out.println("正在检索关键字：" + keyWord);
		try {
			// Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_47);
			Analyzer analyzer = new IKAnalyzer();
			
			QueryParser queryParser = new QueryParser(Version.LUCENE_47, questionfield, analyzer);
			// 将关键字包装成Query对象
			query = queryParser.parse(keyWord);
			Date start = new Date();
			TopDocs results = searcher.search(query, 5 * 2);
			
			for (int i = 0; i < results.scoreDocs.length; i++) {
				System.out.println(results.scoreDocs[i].score);
			}
			
			Date end = new Date();
			
			System.out.println("检索完成用时:" + (end.getTime() - start.getTime()) + "毫秒");
			
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 打印结果
	public void printResult(TopDocs topDocs) {
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		if (scoreDocs.length == 0) {
			System.out.println("Sorry,没有找到您想要的结果");
		} else {
			for (int i = 0; i < scoreDocs.length; i++) {
				try {
					Document document = searcher.doc(scoreDocs[i].doc);
					// System.out.println("这是第" + (i + 1) + "个检索到的结果，文件名为：" +
					// document.get("path"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("==================================");
	}

	public String readytoAnswer(String question) {

		String msg = "对不起，您的问题太深奥了，我暂时不能理解";

		try {
			NormalLuceneSearch luceneSearch = new NormalLuceneSearch();
			TopDocs topDocs = null;
			topDocs = luceneSearch.search(question);
			return luceneSearch.printAnswer(topDocs);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return msg;
	}

	// 打印结果
	public String printAnswer(TopDocs topDocs) {
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		String msg = "对不起，您的问题太深奥了，我暂时不能理解";
		if (scoreDocs.length == 0) {
			return msg;
		} else {
			for (int i = 0; i < 1; i++) {
				try {
					Document document = searcher.doc(scoreDocs[i].doc);
					msg = document.get("answer");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return msg;
		}
	}

}
