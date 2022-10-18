import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class WordCounter {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> termMap = new HashMap<String, Integer>();

        while(scanner.hasNext()) {
            String term = scanner.next();
            // 単語の出現回数を1増やす
            int count = 1;
            if(termMap.containsKey(term)){
                count += termMap.get(term);
            }
            termMap.put(term, count);
        }

        // 単語数の表示 (ソートはせず)
        Iterator<String> iterator = termMap.keySet().iterator();
	while(iterator.hasNext()) {
	    String key = iterator.next();
	    Integer value = termMap.get(key);
	    System.out.println(key + ": " + value);
	}
    }
}