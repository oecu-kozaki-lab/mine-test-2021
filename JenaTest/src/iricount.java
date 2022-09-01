import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;



/* SPARQL Endpoint に対するクエリ例
 * 注）Proxyの設定が必要な環境で実行するときは，実行時のJVMのオプションとして
 *      -DproxySet=true -DproxyHost=wwwproxy.osakac.ac.jp -DproxyPort=8080
 *     を追加する，
 *     Eclipseの場合「実行の構成＞引数」で設定可能
 * /
 */

public class iricount{

	static public void main(String[] args) throws FileNotFoundException{
		//入力ファイル指定
				File file = new File("input/iri.txt");
				
				//ファイルの読み込み用のReaderの設定
				BufferedReader br = new BufferedReader(	new InputStreamReader(new FileInputStream(file)));
				ArrayList<String> ListAll=new ArrayList<String>();
				try {
					while(br.ready()) {
				
					String line = br.readLine();
					
					ListAll.add(line);
					}
					br.close();
					System.out.println(ListAll);
				
					
						
						//FileOutputStream out1 = new FileOutputStream("output/outiricount.csv");
						//OutputStreamWriter out1w = new OutputStreamWriter(out1);
						
						
						FileOutputStream out2 = new FileOutputStream("output/outiricount.csv");
						OutputStreamWriter out2w = new OutputStreamWriter(out2);
					
						for(String uri: ListAll) {
							System.out.print("\t" + uri);
							System.out.println();
						out2w.write(uri);
						out2w.write(",");
						
						Map<String, Long> counts =  ListAll.stream()
							    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
							System.out.println(counts);
							
				}
						out2w.close();
						}
				catch (IOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				} 
				
			
				
				
			
			


				
		}
	
	}

	
		
	
