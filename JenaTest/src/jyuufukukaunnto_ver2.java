import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class jyuufukukaunnto_ver2 {
	static public void main (String[] args) throws FileNotFoundException{
	//入力ファイル指定
	File DBfile = new File("input/DBpedia_ID_Wikidata_ID.txt");//DBのWikidataのリンクID
	//File DBfile = new File("input/DBpedia_ID_Wikidata_ID _test.txt");//DBのWikidataのリンクIDテスト用
	//入力ファイル指定2
	File WDfile = new File("input/WikidataID_en.txt");//Wikidatの疾患一覧のID
	//File WDfile = new File("input/WikidataID_en_test.txt");//Wikidatの疾患一覧のIDテスト用
	//ファイルの読み込み用のReaderの設定ou
	BufferedReader DBdata = new BufferedReader(	new InputStreamReader(new FileInputStream(DBfile)));
	//ファイルの読み込み用のReaderの設定ou
	BufferedReader WDdata = new BufferedReader(new InputStreamReader(new FileInputStream(WDfile)));

	//リスト作成
	ArrayList<String> ListAll=new ArrayList<String>();//WikidataのID
    //ArrayList<String> ListAll2=new ArrayList<String>();

	 //出力用のファイルの作成
	 //File file = new File("output/jyuusukukaunnto_ver2.csv");
	  //FileWriter filewriter = new FileWriter(file);

	 
	//Wikidata のIDリストの読み込み
    try {
    	while(WDdata.ready()) {
			String line = WDdata.readLine();
			ListAll.add(line);
		}
		WDdata.close();
		}
	catch (IOException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
		}


	//DBpediaの疾患のリンク先がWikidataの疾患リストに含まれるかチェック
		try {
			  // FileWriterクラスのオブジェクトを生成する
            FileWriter file = new FileWriter("output/jyuusukukaunnto_ver2.csv");
            //FileWriter file = new FileWriter("output/jyuusukukaunnto_ver2test.csv");//テスト用
            // PrintWriterクラスのオブジェクトを生成する
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));
			while(DBdata.ready()) {

				String line = DBdata.readLine();

//			String[] searchTargets = {line2};

			      if (ListAll.contains(line)) {
			        System.out.println(line+"\t"+ ":1");
			        pw.println(line+ ":1");
			      } else {
			        System.out.println(line+"\t"+ ":0");
			        pw.println(line+ ":0");
			      }
			}
				DBdata.close();
				pw.close();

		}
	catch (IOException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
		}


	}
}