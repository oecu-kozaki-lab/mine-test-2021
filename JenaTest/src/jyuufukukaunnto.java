import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class jyuufukukaunnto {
	static public void main (String[] args) throws FileNotFoundException{
	//入力ファイル指定
	//File file = new File("input/DBpedia_ID_Wikidata_ID.txt");//DBのWikidataのリンクID
	File file = new File("input/DBpedia_ID_Wikidata_ID _test.txt");//DBのWikidataのリンクID
	//入力ファイル指定2
	//File file1 = new File("input/WikidataID_en.txt");//Wikidatの疾患一覧のID
	File file1 = new File("input/WikidataID_en_test.txt");//Wikidatの疾患一覧のID
	//ファイルの読み込み用のReaderの設定ou
	BufferedReader br = new BufferedReader(	new InputStreamReader(new FileInputStream(file)));
	//ファイルの読み込み用のReaderの設定ou
	BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));

	//リスト作成
	ArrayList<String> ListAll=new ArrayList<String>();//WikidataのID
    //ArrayList<String> ListAll2=new ArrayList<String>();

	//Wikidata のIDリストの読み込み
    try {
    	while(br2.ready()) {
			String line = br2.readLine();
			ListAll.add(line);
		}
		br2.close();
		}
	catch (IOException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
		}


	//DBpediaの疾患のリンク先がWikidataの疾患リストに含まれるかチェック
		try {
			while(br.ready()) {

				String line = br.readLine();

//			String[] searchTargets = {line2};

			      if (ListAll.contains(line)) {
			        System.out.println(line+ ":1");
			      } else {
			        System.out.println(line+ ":0");
			      }
			}
				br.close();


		}
	catch (IOException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
		}


	}
}
