

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * WikidataのNT形式のダンプファイルから，
 * 　-SubClassOf（<http://www.wikidata.org/prop/direct/P279>）
 * 　-instanceOf（<http://www.wikidata.org/prop/direct/P31>）
 * 　-label（ja日本語，en日本語）
 * を抽出
 * */

public class wikidatatool {

	public static void main(String args[]) {
		long i=0;

		try {
//			File f = new File("F:/Wikidata_full_201013/latest-truthy.nt");
			File f = new File("E:\\wikidata/latest-truthy.nt");
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(f),"UTF-8"));
			String line="";
//			File saveFile = new File("E:/wikidata/Q3918-ins2.nt");//""+f.getName().replaceAll(".tsv", "")+".ttl");
//			File saveFile = new File("E:/wikidata/Q3918-ins.nt");//""+f.getName().replaceAll(".tsv", "")+".ttl");
			//File saveJPL = new File("E:/KG/Jp-labels.nt");//""+f.getName().replaceAll(".tsv", "")+".ttl");
			File saveENL = new File("E:/KG/En-labels.nt");
			File saveWD = new File("E:/KG/WD-triple.nt");
			
			File saveJPL = new File("E:\\wikidata/ja_label.nt");
//			FileOutputStream out;
//			out = new FileOutputStream(saveFile);			
//			OutputStreamWriter ow = new OutputStreamWriter(out, "UTF-8");
			BufferedWriter bwJPL = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveJPL), "UTF-8"));
//			BufferedWriter bwENL = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveENL), "UTF-8"));
//			BufferedWriter bwWD = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveWD), "UTF-8"));

			//long i=0;
			int c=0;

			//ファイルを1行ずつ読み込んで処理する
			//10:45 -> 12:11 約1時間半かかった
			while(br.ready()) {
				line = br.readLine();
				String[] data = line.split(" ");
			if(data.length>2) {
					if(data[1].equals("<http://www.wikidata.org/prop/direct/P31>")) {
						if(data[2].equals("<http://www.wikidata.org/entity/Q12136>")) {
						bwJPL.write(line+"\n");
						c++;
					}
				}
			}

				/*if(line.contains("<http://www.wikidata.org/prop/direct/P279>")==true){
					bw.write(line+"\n");
					c++;
				}
				else if(line.contains("<http://www.wikidata.org/prop/direct/P31>")==true){
					bw.write(line+"\n");
					c++;
				}
				else */
	/*			if(line.contains("<http://www.w3.org/2000/01/rdf-schema#label>")==true){
				//if(line.contains("rdf-schema#label>")==true){
					if(line.endsWith("@ja .")) {
						bwJPL.write(line+"\n");
						c++;
					}
					else if(line.endsWith("@en .")) {
						bwENL.write(line+"\n");
						c++;
					}
				}
				else {
					String[] data = line.split(" ");
					if(data[0].startsWith("<http://www.wikidata.org/")
							&& data[2].startsWith("<http://www.wikidata.org/")) {
						bwWD.write(line+"\n");
						c++;
					}
				}
				*/
				//System.out.println(line);


				i++;
				if(i % 100000 == 0) {
					System.out.println(c+" / "+i);
					bwJPL.write(line+"\n");
				}
//				if(i == 10000000 ) {
//					break;
//				}
			}
			
			br.close();
			bwJPL.close();
//			bwENL.close();
//			bwWD.close();
			
		}
		catch(IOException e) {
			System.out.println("ERROR at line:"+i);
			System.out.println(e.toString());
		}
	}

}