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

public class dbpediaen2{

	static public void main(String[] args) throws FileNotFoundException{
		
		//入力ファイル指定
		File file = new File("input/dbpediaen.txt");
		
		//ファイルの読み込み用のReaderの設定
		BufferedReader br = new BufferedReader(	new InputStreamReader(new FileInputStream(file)));
		
		//各疾患ごとにもつPのリスト
		ArrayList<ArrayList<String>> ListDip = new ArrayList<ArrayList<String>>();
		
		//全疾患のプロパティを持つリスト
		ArrayList<String> ListAll=new ArrayList<String>();
		
		  //出力用のファイルの作成
		  FileOutputStream out= new FileOutputStream("output/rist1-outputdb.csv");
		  
		
		  
		try {
			
			while(br.ready()) {
				String line = br.readLine(); //ファイルを1行ずつ読み込む
				System.out.println(line);
				
			//List<String> propList = new ArrayList<String>();
				//ArrayList[]<String> disPorp = new ArrayList<String>()[10];
				//String[][];
				
			//クエリの作成
			//dbpedia jp
				String queryStr = "PREFIX dbp: <http://dbpedia.org/property/>\n"
						+ "PREFIX dbo: <http://dbpedia.org/ontology/>\n"
						+ "PREFIX dbp-owl: <http://dbpedia.org/ontology/>\n"
						+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
						+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
						+"SELECT DISTINCT ?p WHERE { <http://dbpedia.org/resource/"+line+"> ?p ?o.}";
			//System.out.println(queryStr);
			Query query = QueryFactory.create(queryStr);
			
			 // Remote execution.
			try{
				//List<String> ListDip = new ArrayList<String>();
				//List<String> ListAll = new ArrayList<String>();
				
				QueryExecution qexec = QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql"	, query) ;
			    ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
			  
				// クエリの実行.
			    ResultSet rs = qexec.execSelect();
			    
				//リストDip用に作成
			    ArrayList<String> ListDip1=new ArrayList<String>();
			    ListDip.add(ListDip1);
			    
			    //各疾患ごとに…
				while(rs.hasNext()){
					
					QuerySolution qs = rs.next();
					//java.util.List<String> vars = rs.getResultVars();
					//for(int i=0; i<vars.size();i++){
						//RDFNode node = qs.get(vars.get(i));
						RDFNode node = qs.get("p");
						
						//各疾患がもつPのリスト
						ListDip1.add(node.toString());
						
						System.out.println(node.toString());
						
					
					//String prop = node.toString();
					
						//Pが含まれていないときに追加する
					//if(!ListAll.contains(node.toString())){
					   ListAll.add(node.toString());
					   
					//} 
				
					//disProp[i].add(prop);
				
					//}
				}
				  PrintWriter p = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
		              new FileOutputStream("output/rist1-outputdb.csv", false),"UTF-8")));
				  
				 /* int k,pk;
				
				  for( k=0; pk<ListAll;k++ ) {
					  ListAll.get((int) pk);
					  p.print(pk);
				  };*/
				 
				/*  while ((line = br.readLine()) != null) {
					  if (i == 0) {
					        ListAll.get("p");
					      }
					  else {
					   
					        }
					      }*/

					      
					     // i++;
				 
					    
				  
			    // 結果の出力　※以下のどれか「１つ」を選ぶ（複数選ぶと，2つ目以降の結果が「空」になる）
			 	//ResultSetFormatter.out(System.out, rs, query);		//表形式で，標準出力に
			 	//ResultSetFormatter.out(out, rs, query); 			//表形式で，ファイルに
			 	//ResultSetFormatter.outputAsCSV(System.out, rs);	//CSV形式で，標準出力に
			 	//ResultSetFormatter.outputAsCSV(out, rs);			//CSV形式で，ファイルに
					
				qexec.close();
				} catch (Exception e) {
			    e.printStackTrace();
				
				}
			}
				
			br.close();
			out.close();
			
			
			 
			//リスト内のデータ確認用
			//System.out.println(ListDip);
			//System.out.println(ListAll);
			//hashmap利用？
			
			//ArrayList<String> ListDip2=new ArrayList<String>();
			/*Map<String, Integer> map = new HashMap<>();
			for (String s : ListDip) {
			    Integer i = map.get(s);
			    map.put(s, i == null ? 1 : i + 1);
			}
			
		    //map stream利用？
		     * 
			List<String> deepList =new ArrayList<String>(ListDip);
			System.out.println("ディープコピーしたリスト");
            for (int i : deepList){System.out.println(i);}*/
			
			Map<String, Long> counts =  ListAll.stream()
				    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
				System.out.println(counts);
			
			/*	File file2 = new File("output/test2.txt");
				  FileWriter filewriter = new FileWriter(file2);
				  filewriter.println(counts);
			/*	  filewriter.close();
				
			/* int count = 0;
		        int size = ListDip.size();
		        for( int i = 0; i < size; i++ )
		        {
		            List<String> s = ListDip.get(i);
			    	
		            if( s.equals(ListAll) )
		                count++;
		        }
		        System.out.println(count);*/

		        
		/*	 for(int i = 1;  ; i++) {
		           
	             System.out.println(i + "番目の要素:" + ListDip.get(i-1)); 
	             i++;
	             if(ListDip.contains((i-1))) {
	            	 //ListDip.add((true));
	            	 System.out.println(true);
	             }
	             else{
	            	 System.out.println(false);
	            	 }
	             
	           
	        
			 }*/
			 
		}
		catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			}
	}

	
		
	}

