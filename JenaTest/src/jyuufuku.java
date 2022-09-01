import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;



/* SPARQL Endpoint に対するクエリ例
 * 注）Proxyの設定が必要な環境で実行するときは，実行時のJVMのオプションとして
 *      -DproxySet=true -DproxyHost=wwwproxy.osakac.ac.jp -DproxyPort=8080
 *     を追加する，
 *     Eclipseの場合「実行の構成＞引数」で設定可能
 * /
 */

public class jyuufuku {

	static public void main(String[] args) throws FileNotFoundException{
		
		//入力ファイル指定
		File file = new File("input/wikidatadben.txt");
		
		//ファイルの読み込み用のReaderの設定
		BufferedReader br = new BufferedReader(	new InputStreamReader(new FileInputStream(file)));
		
		//各疾患ごとにもつPのリスト
		ArrayList<ArrayList<String>> ListDip = new ArrayList<ArrayList<String>>();
		
		//全疾患のプロパティを持つリスト
		ArrayList<String> ListAll=new ArrayList<String>();
		
		  //出力用のファイルの作成
		  FileOutputStream out= new FileOutputStream("input/wikidatadben.txt");
		  
		
		  
		try {
			
			while(br.ready()) {
				String line = br.readLine(); //ファイルを1行ずつ読み込む
				System.out.println(line);
				
		
				
			//クエリの作成
			//dbpedia jp
			String queryStr = "PREFIX dbp: <http://dbpedia.org/property/>\n"
					+ "PREFIX dbo: <http://dbpedia.org/ontology/>\n"
					+ "PREFIX dbp-owl: <http://dbpedia.org/ontology/>\n"
					+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
					+ "SELECT DISTINCT  ?p WHERE { <http://dbpedia.org/resource/"+line+"> ?p ?o.\n"
					+"}";
			Query query = QueryFactory.create(queryStr);
			
			 // Remote execution.
			try{
			
				
				QueryExecution qexec = QueryExecutionFactory.sparqlService("http://kg.hozo.jp/agraph/dbbdanp/"	, query) ;
			    ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
			  
				// クエリの実行.
			    ResultSet rs = qexec.execSelect();
			    
				//リストDip用に作成
			    ArrayList<String> ListDip1=new ArrayList<String>();
			    ListDip.add(ListDip1);
			    
			    //各疾患ごとに…
				while(rs.hasNext()){
					
					QuerySolution qs = rs.next();
				
						RDFNode node = qs.get("p");
						
						//各疾患がもつPのリスト
						ListDip1.add(node.toString());
						
						System.out.println(node.toString());
						
					

					   Map<String, Long> counts =  ListAll.stream()
							    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
							System.out.println(counts);
					
				}
				  PrintWriter p = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
		              new FileOutputStream("output/jyuufukuPdataDBen.csv", false),"UTF-8")));

				qexec.close();
				} catch (Exception e) {
			    e.printStackTrace();
				
				}
			}
				
			br.close();
			out.close();
			
			
			 
		}
		catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			}
	}

	
		
	}
	