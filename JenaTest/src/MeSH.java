import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class MeSH {
	
	
	static public void main(String[] args) throws FileNotFoundException{

		//開始タイムスタンプ（処理前の時刻）を取得
				long startTime = System.currentTimeMillis();
				//
				//時間計測をする処理内容
				LocalDateTime ldt = LocalDateTime.now();
				ZonedDateTime zdt = ldt.atZone(ZoneOffset.ofHours(+9));
				long epochMilli = zdt.toInstant().toEpochMilli();
				System.out.println("現在時刻 ： " + epochMilli);
		//入力ファイル指定
		File file = new File("input2/mesh.txt");
			
		//ファイルの読み込み用のReaderの設定
		BufferedReader br = new BufferedReader(	new InputStreamReader(new FileInputStream(file)));
	 
		// BufferedWriter writer=new BufferedWriter(new FileWriter("output.txt"));


		 try {BufferedWriter writer=new BufferedWriter(new FileWriter("output2/mesh.txt"));
			while(br.ready()) {
			 String line = br.readLine(); //ファイルを1行ずつ読み込む
			// String line2 = URLEncoder.encode(line, "UTF-8");
			 //System.out.println(line2);
			 
			//クエリの作成
			String queryStr ="PREFIX wdt: <http://www.wikidata.org/prop/direct/>"
					+ "PREFIX wd: <http://www.wikidata.org/entity/>"
					+ "PREFIX wikibase: <http://wikiba.se/ontology#>"
					+ "PREFIX bd: <http://www.bigdata.com/rdf#>" 
					+ "PREFIX schema: <http://schema.org/>"
					+ "SELECT distinct ?s ?sLabel ?ID"
					+ "	WHERE "
					+ "	{"
					+ "	  ?s wdt:P486 "+line+"."
					+ "	  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". }"
					+ "	}";
			Query query = QueryFactory.create(queryStr);

			 
			 // Remote execution.
			try{
				QueryExecution qexec = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql"	, query) ;
			    ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;


			
			    
				// クエリの実行.
			    ResultSet rs = qexec.execSelect();
			  
			    
		        	
		        		 while(rs.hasNext()) {
		        			
		 		        	QuerySolution qs = rs.next();
		 		        	org.apache.jena.rdf.model.Resource  res = qs.getResource("s");
		 		        	//org.apache.jena.rdf.model.Resource  res2 = qs.getResource("type");
		 		        	org.apache.jena.rdf.model.Literal  res3 = qs.getLiteral("sLabel");
		 		        	org.apache.jena.rdf.model.Literal  res4 = qs.getLiteral("ID");
		 		        	if(res!=null) {
		 		        	
		 		        		System.out.println(res+","+res3+","+res4);
		 		        			        		
		 		        		 writer.write(res+","+res3+","+res4);
				        		 writer.newLine();
		 		        	}
		        			 
		        }	
		        qexec.close();   
		      
			 }
			catch (Exception e) {
			    e.printStackTrace();
			    }
			 }
			
			
			br.close();
			  writer.close();
			
			
			long endTime = System.currentTimeMillis();
			//処理結果
			System.out.println("---取得結果--------------------");
			System.out.println("開始時刻 ： " + startTime + "ミリ秒");
			System.out.println("終了時刻 ： " + endTime + "ミリ秒");
			System.out.println("処理時間 ： " + (endTime - startTime) + "ミリ秒");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		 
	}
	}
            
		
	 	
	 
