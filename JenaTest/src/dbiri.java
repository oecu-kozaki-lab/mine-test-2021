import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class dbiri {
	static public void main(String[] args) throws FileNotFoundException{

		//入力ファイル指定
				File file = new File("input/dbpediaen5.txt");
				
				//ファイルの読み込み用のReaderの設定
				BufferedReader br = new BufferedReader(	new InputStreamReader(new FileInputStream(file)));
				
		try {
			while(br.ready()) {
				String line = br.readLine(); //ファイルを1行ずつ読み込む
				System.out.println(line);

			//クエリの作成
			String queryStr = "PREFIX dbp: <http://dbpedia.org/property/>\n"
					+ "PREFIX dbo: <http://dbpedia.org/ontology/>\n"
					+ "PREFIX dbp-owl: <http://dbpedia.org/ontology/>\n"
					+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
					+ "SELECT DISTINCT ?p  ?o WHERE { <http://dbpedia.org/resource/"+line+"> ?p ?o.\n"
					+ "FILTER isIRI(?o)\n"
					+ " } ";
			//System.out.println(queryStr);
			Query query = QueryFactory.create(queryStr);

			 // Remote execution.
			try{
				QueryExecution qexec = QueryExecutionFactory.sparqlService("http://kg.hozo.jp/agraph/dedanpen/", query) ;
			    ((QueryEngineHTTP)qexec).addParam("timeout", "100000") ;

			    //出力用のファイルの作成
			    //FileOutputStream out;
				//out = new FileOutputStream("output/outiri1.txt");

				// クエリの実行.
			    ResultSet rs = qexec.execSelect();
			    
			    // FileWriterクラスのオブジェクトを生成する
	            FileWriter file1 = new FileWriter("output/out-iri1.txt");
	            // PrintWriterクラスのオブジェクトを生成する
	            PrintWriter pw = new PrintWriter(new BufferedWriter(file1));
			    while(rs.hasNext()) {
			    	QuerySolution qs=rs.next();
			    	System.out.println(qs);
			    	pw.println(qs);
			    }
			    pw.close();
			    // 結果の出力　※以下のどれか「１つ」を選ぶ（複数選ぶと，2つ目以降の結果が「空」になる）
			 	//ResultSetFormatter.out(System.out, rs, query);		//表形式で，標準出力に
			    //ResultSetFormatter.out(out, rs, query); 			//表形式で，ファイルに
			 	//ResultSetFormatter.outputAsCSV(System.out, rs);	//CSV形式で，標準出力に
			 	//ResultSetFormatter.outputAsCSV(out, rs);			//CSV形式で，ファイルに
			 	qexec.close();
			 	//out.close();
			
			} catch (Exception e) {
			    e.printStackTrace();
			}


			
}	br.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
}}
