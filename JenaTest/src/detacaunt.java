import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class detacaunt {
	static public void main (String[] args) throws FileNotFoundException{
	//入力ファイル指定
	//File file = new File("output/outbyouki1-wikidatadben.txt");//Oのデータ
	//File file = new File("input/DBpediakarawikidata.txt");DBpediaからWikidataへのリンク用
	//File file = new File("input/DBpedia_itiran.txt");//DBpediaの疾患一覧
	//File file = new File("input/DBpedia_jyuufuku.txt");//DBpedia重複確認用
	File file = new File("input/DBpedia_ID_Wikidata_ID.txt");//DBのWikidataのリンクからwikidataの疾患一覧重複確認用(ID利用)
	//File file = new File("input/Wikidata_DB_jyuufuku_ver2.txt");//wikidataからWikipediaへのリンクとDBの疾患一覧重複確認用
	//File file = new File("input/Wikidata_en_jyuufuku.txt");//wikidata重複確認用
	//File file = new File("input/DB_wikidata_jyuufuku_ver2.txt");//DBのWikidataのリンクからwikidataの疾患一覧重複確認用
	//File file = new File("output/outbyouki1-wikidatadbenPdata.txt");
	//File file = new File("output/outbyouki1-wikidatadbenPOdata.txt");
	//File file = new File("input/sougolink.txt");
	//ファイルの読み込み用のReaderの設定ou
	BufferedReader br = new BufferedReader(	new InputStreamReader(new FileInputStream(file)));
	ArrayList<String> ListAll=new ArrayList<String>();
		try {
			while(br.ready()) {
			
				String line = br.readLine();
		
				ListAll.add(line);
			}
				br.close();
				//System.out.println(ListAll);
	
		
			
			//FileOutputStream out1 = new FileOutputStream("output/outiricount.csv");
			//OutputStreamWriter out1w = new OutputStreamWriter(out1);
			
				Map<String, Long> counts =  ListAll.stream()
						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
				System.out.println(counts);
				
				//FileOutputStream out2 = new FileOutputStream("output/outcountwikidatadbenPOdata.csv");
				//OutputStreamWriter out2w = new OutputStreamWriter(out2);
			//	for(String uri: ListAll) {
				//	System.out.print("\t" + uri);
				//System.out.println();
			//	out2w.write(uri);
			//	out2w.write(",");
			
				
			//	}
			//out2w.close();
			}
	catch (IOException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
		}
	}
}
