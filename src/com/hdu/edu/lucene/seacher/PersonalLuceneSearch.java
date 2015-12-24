package com.hdu.edu.lucene.seacher;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import com.hdu.edu.bean.ChatRecordBean;
import com.hdu.edu.bean.QuesAnsBean;

public class PersonalLuceneSearch extends NormalLuceneSearch {
    
    
	public PersonalLuceneSearch() {
		try {

			IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(ConstantsFile.PERSONAL_INDEX_FILE_PATH)));

			searcher = new IndexSearcher(indexReader);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ChatRecordBean readytoAnswer(String question,ChatRecordBean chatrecordBean) {
		String msg ="对不起，您的问题太深奥了，我暂时不能理解";
		try {
			PersonalLuceneSearch luceneSearch = new PersonalLuceneSearch();
			TopDocs topDocs = null;
			topDocs = luceneSearch.search(question);
			int flag = 0;
			for (int i = 0; i < topDocs.scoreDocs.length; i++) {
				System.out.println(topDocs.scoreDocs[i].score);
				if(topDocs.scoreDocs[i].score>0.1){
					flag=1;
					break;
				}
			}
			if(flag==1){
				luceneSearch.printAnswer(1,topDocs,chatrecordBean);
			}else{
			    chatrecordBean.setResponse_msg(msg);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return chatrecordBean;
	}

//	@Override
//	public ChatRecordBean printAnswer(TopDocs topDocs) {
//		// TODO Auto-generated method stub
//		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//			//目前只输出一个结果
//			for (int i = 0; i < 1; i++) {
//				try {
//					Document document = searcher.doc(scoreDocs[i].doc);
//					chatrecordBean.setResponse_msg(document.get("answer"));
//					chatrecordBean.setCategory(document.get("category"));
//					chatrecordBean.setCategory(document.get("question"));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			return chatrecordBean;
//		}
}
	
	


