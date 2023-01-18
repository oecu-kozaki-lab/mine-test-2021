import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class sampel1 {
	static public void main(String[] args) throws FileNotFoundException{

		//クエリの作成
		String queryStr = "PREFIX wdt: <http://www.wikidata.org/prop/direct/>"
				+ "PREFIX wd: <http://www.wikidata.org/entity/>"
				+ "PREFIX wikibase: <http://wikiba.se/ontology#>"
				+ "PREFIX bd: <http://www.bigdata.com/rdf#>"
				+ "SELECT distinct ?s ?sLabel ?p \r\n"
				+ "WHERE \r\n"
				+ "{\r\n"
				+ "  ?s ?p \"D003424\".\r\n"
				+ "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". }\r\n"
				+ "}";
		Query query = QueryFactory.create(queryStr);

		 // Remote execution.
        try{
        	QueryExecution qexec = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql"	, query) ;
            ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;

            //出力用のファイルの作成
	        FileOutputStream out;
			out = new FileOutputStream("output/SPARQL-output1.txt");

			// クエリの実行.
	        ResultSet rs = qexec.execSelect();

	        // 結果の出力　※以下のどれか「１つ」を選ぶ（複数選ぶと，2つ目以降の結果が「空」になる）
	     	ResultSetFormatter.out(System.out, rs, query);		//表形式で，標準出力に
	     	//ResultSetFormatter.out(out, rs, query); 			//表形式で，ファイルに
	     	//ResultSetFormatter.outputAsCSV(System.out, rs);	//CSV形式で，標準出力に
	     	//ResultSetFormatter.outputAsCSV(out, rs);			//CSV形式で，ファイルに

	     	out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



	}
}
