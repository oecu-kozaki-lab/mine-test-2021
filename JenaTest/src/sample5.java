import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class sample5 {
	static public void main(String[] args) throws FileNotFoundException{

		//クエリの作成
		String queryStr = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n"
				+ "PREFIX meshv: <http://id.nlm.nih.gov/mesh/vocab#>\r\n"
				+ "PREFIX mesh: <http://id.nlm.nih.gov/mesh/>\r\n"
				+ "PREFIX mesh2015: <http://id.nlm.nih.gov/mesh/2015/>\r\n"
				+ "PREFIX mesh2016: <http://id.nlm.nih.gov/mesh/2016/>\r\n"
				+ "PREFIX mesh2017: <http://id.nlm.nih.gov/mesh/2017/>\r\n"
				+ "\r\n"
				+ "SELECT DISTINCT ?p  ?oLabel\r\n"
				+ "FROM <http://id.nlm.nih.gov/mesh>\r\n"
				+ "WHERE {\r\n"
				+ "\r\n"
				+ "  mesh:D007938 ?p ?o.\r\n"
				+ "  ?o rdfs:label ?oLabel\r\n"
				+ "  \r\n"
				+ "}\r\n"
				+ "ORDER BY ?p";
		Query query = QueryFactory.create(queryStr);

		 // Remote execution.
        try{
        	QueryExecution qexec = QueryExecutionFactory.sparqlService("http://id.nlm.nih.gov/mesh/sparql"	, query) ;
            ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;

            //出力用のファイルの作成
	        FileOutputStream out;
			out = new FileOutputStream("output/SPARQL-output5.txt");

			// クエリの実行.
	        ResultSet rs = qexec.execSelect();
	       
	        // 結果の出力　※以下のどれか「１つ」を選ぶ（複数選ぶと，2つ目以降の結果が「空」になる）
	     	ResultSetFormatter.out(System.out, rs, query);		//表形式で，標準出力に
	     	//ResultSetFormatter.out(out, rs, query); 			//表形式で，ファイルに
	     	//ResultSetFormatter.outputAsCSV(System.out, rs);	//CSV形式で，標準出力に
	     	//ResultSetFormatter.outputAsCSV(out, rs);			//CSV形式で，ファイルに
	     	
	     	out.close();
	     	 qexec.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



	}
}
