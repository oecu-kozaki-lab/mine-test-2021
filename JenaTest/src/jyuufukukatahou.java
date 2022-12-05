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

	public class jyuufukukatahou {
		static public void main (String[] args) throws FileNotFoundException{
		//入力ファイル指定
		//File DBfile = new File("input2/PrimaryTopic_jyuufukunasi.txt");//PrimaryTopic重複してるもののみ
		//File DBfile = new File("input2/PrimaryTopic_.txt");//PrimaryTopic
		//File DBfile = new File("input/WD_wikipedia_link_.txt");//WDからwikipediaへのリンク_整理版
		//File DBfile = new File("input2/WD_wikip_DB.txt");//PrimaryTopic重複してるもののみ
		//File DBfile = new File("input2/WD_wikip_DB.txt");//WD→wikip→DB
		//File DBfile = new File("input2/WD_itiran.txt");//WDの疾患一覧
		//File DBfile = new File("input2/DB_topic_WD.txt");//DB→Topic→WD
		//File DBfile = new File("input2/DB_itiran_.txt");//DBのtopic_付き
		//File DBfile = new File("input2/WD_wikip_DB_.txt");//WD→wikip→DB_付き
		File DBfile = new File("input2/DB.txt");//DBPの疾患一覧
		//入力ファイル指定2
		//File WDfile = new File("input/WD_wikipedia_link_.txt");//WDからwikipediaへのリンク_整理版
		//File WDfile = new File("input/PrimaryTopic_.txt");//PrimaryTopic
		//File WDfile = new File("input2/DB_topic_WD.txt");//DB→Topic→WD
		//File WDfile = new File("input2/DB_itiran.txt");//DBのTopic
		//File WDfile = new File("input2/WD_wikip_DB.txt");//WD→wikip→DB
		//File WDfile = new File("input2/wikipedia.txt");//wikipリンク
		//File WDfile = new File("input2/DB_topic.txt");//wikipリンク
		//File WDfile = new File("input2/sougo.txt");//wikipリンク
		File WDfile = new File("input2/DB_itiran-.txt");//DBのtopic_付き
		
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
				//FileWriter file = new FileWriter("output2/PrimaryTopic_2.csv");//PrimaryTopicどちらに含まれているか
		            
	            //FileWriter file = new FileWriter("output2/PrimaryTopic_2.2.2.csv");//PrimaryTopicどちらに含まれているか
	            //FileWriter file = new FileWriter("output2/sougolink.csv");//相互リンク
	            //FileWriter file = new FileWriter("output2/sougo.csv");//WDの疾患一覧とTopic
	            //FileWriter file = new FileWriter("output2/sougolink2.csv");//相互リンク
	            //FileWriter file = new FileWriter("output2/sougotopic.csv");//WDのwikipediaとTopic
	            //FileWriter file = new FileWriter("output2/sougotopic2.csv");//WDのwikipediaとTopic
	            //FileWriter file = new FileWriter("output2/sougoWD.csv");//WDとの相互
				FileWriter file = new FileWriter("output2/DB-topic.csv");//WDとの相互
	            //FileWriter file = new FileWriter("output2/sougoDB.csv");//WDとの相互
	            // PrintWriterクラスのオブジェクトを生成する
	            PrintWriter pw = new PrintWriter(new BufferedWriter(file));
				while(DBdata.ready()) {

					String line = DBdata.readLine();

//				String[] searchTargets = {line2};

				      if (ListAll.contains(line)) {
				        System.out.println(line+"\t"+ ":1");
				        pw.println(line+"\t"+ ":1");
				      } else {
				        System.out.println(line+"\t"+ ":0");
				        pw.println(line+"\t"+ ":0");
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

