package game.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import game.logic.User;

/**
 * Klasse liest und sortiert die Daten aus dem File ab
 * 
 * @author 유형욱
 * @version 0.5 Beta
 *
 */
public class LoadData implements ILoadData{

	private ArrayList<User> list = new ArrayList<>();

	/**
	 * 
	 * @Return int 점수
	 */
	public Comparator<User> scoreNo = new Comparator<User>() {

		public int compare(User s1, User s2) {

			int rollno1 = s1.getScore();
			int rollno2 = s2.getScore();

			return rollno2 - rollno1;
		}
	};

	/**
	 * 점수 리스트로 정렬
	 * @return list
	 */
	@Override
	public ArrayList<User> load() {

		try (BufferedReader br = Files.newBufferedReader(Paths.get("Highscore.txt"))) {

			String currentLine = br.readLine();
			while (currentLine != null) {
				String[] temp = currentLine.split(" ");
				String name = temp[0];
				int score = Integer.valueOf(temp[1]);
				list.add(new User(name, score));
				currentLine = br.readLine();
			}

			Collections.sort(list, scoreNo);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}