
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

/* SPARQL Endpoint に対するクエリ例
 * 注）Proxyの設定が必要な環境で実行するときは，実行時のJVMのオプションとして
 *      -DproxySet=true -DproxyHost=wwwproxy.osakac.ac.jp -DproxyPort=8080
 *     を追加する，
 *     Eclipseの場合「実行の構成＞引数」で設定可能
 * /
 */

public class rist1 {

	static public void main(String[] args) throws FileNotFoundException{
		
		//入力ファイル指定
		File file = new File("input/words.txt");
		
		//ファイルの読み込み用のReaderの設定
		BufferedReader br = new BufferedReader(	new InputStreamReader(new FileInputStream(file)));
		
		
		
		try {
			while(br.ready()) {
				String line = br.readLine(); //ファイルを1行ずつ読み込む
				System.out.println(line);

				
				
			//クエリの作成
			String queryStr = "PREFIX dbpj: <http://ja.dbpedia.org/resource/>\r\n"
					+ "PREFIX dbp-owl: <http://dbpedia.org/ontology/>PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n"
					+"SELECT DISTINCT ?p WHERE { <http://ja.dbpedia.org/resource/"+line+"> ?p ?o . } ";
			System.out.println(queryStr);
			Query query = QueryFactory.create(queryStr);

			
			

      
			 // Remote execution.
			try{
				
				QueryExecution qexec = QueryExecutionFactory.sparqlService("http://ja.dbpedia.org/sparql"	, query) ;
			    ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;

			    //出力用のファイルの作成
			  FileOutputStream out ;
			  out= new FileOutputStream("output/rist1-output.csv");

				// クエリの実行.
			    ResultSet rs = qexec.execSelect();
			 

			    // 結果の出力　※以下のどれか「１つ」を選ぶ（複数選ぶと，2つ目以降の結果が「空」になる）
			 	//ResultSetFormatter.out(System.out, rs, query);		//表形式で，標準出力に
			 	//ResultSetFormatter.out(out, rs, query); 			//表形式で，ファイルに
			 	//ResultSetFormatter.outputAsCSV(System.out, rs);	//CSV形式で，標準出力に
			 	ResultSetFormatter.outputAsCSV(out, rs);			//CSV形式で，ファイルに

			 	
			 	qexec.close();
			 	out.close();
			 	
			} catch (Exception e) {
			    e.printStackTrace();
			}
				}
				
			br.close();
			
		 	
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
}
}