import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class MostRepetedValue {

	static void countRepeated() {
		HashMap<String, Integer> wordmap = new HashMap<String, Integer>();
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("C:\\sample.txt"));

			String line = br.readLine();
			while (line != null) {

				String words[] = line.toLowerCase().split(" ");

				for (String word : words) {
					if (wordmap.containsKey(word)) {
						wordmap.put(word, wordmap.get(word) + 1);
					} else {
						wordmap.put(word, 1);
					}
				}

				line = br.readLine();

			}

			int count = 0;
			String mostRepeatedWord = null;
			Set<Entry<String, Integer>> st = wordmap.entrySet();

			for (Entry<String, Integer> entry : st) {

				if (entry.getValue() > count) {

					mostRepeatedWord = entry.getKey();
					count = entry.getValue();

				}

			}

	System.out.println(mostRepeatedWord+" "+count);
		} catch (Exception e) {

		}

	}

	public static void main(String args[]) {
		countRepeated();

	}

}
