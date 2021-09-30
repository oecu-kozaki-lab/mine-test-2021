import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

public class rist3 {
	static public void main(String[] args) throws FileNotFoundException{
		
}
	static public void query() {
		//入力ファイル指定
		File file = new File("input/words.txt");
		
		//ファイルの読み込み用のReaderの設定
		BufferedReader br = new BufferedReader(	new InputStreamReader(new FileInputStream(file)));
		
		
while(br.ready()) {
	String line = br.readLine(); //ファイルを1行ずつ読み込む
	System.out.println(line);
		String queryStr = "PREFIX dbpj: <http://ja.dbpedia.org/resource/>\r\n"
				+ "PREFIX dbp-owl: <http://dbpedia.org/ontology/>PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n"
				+"SELECT DISTINCT ?p WHERE { <http://ja.dbpedia.org/resource/"+line+"> ?p ?o . } ";
		System.out.println(queryStr);
		Query query = QueryFactory.create(queryStr);
	}
}