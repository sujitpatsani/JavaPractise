import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FindNoOfCharWordLinesInTestFile {

	BufferedReader reader = null;
	int charCount = 0;
	int wodCount = 0;
	int lineCount = 0;

	public void findLinesWordsChar() {

		try {
			reader = new BufferedReader(new FileReader("C:\\sample.txt"));
			String currentline = reader.readLine();

			while (currentline != null) {
				lineCount++;

				String word[] = currentline.split(" ");
				wodCount = wodCount + word.length;

				for (String string : word) {

					char cha[] = string.toCharArray();
					for (char c : cha) {

						charCount++;

					}

				}

				currentline = reader.readLine();

			}

			System.out.println(lineCount);
			System.out.println(wodCount);
			System.out.println(charCount);

		} catch (IOException exp) {

		}

	}

	public static void main(String[] args) {

		FindNoOfCharWordLinesInTestFile brc = new FindNoOfCharWordLinesInTestFile();

		brc.findLinesWordsChar();

	}

}
