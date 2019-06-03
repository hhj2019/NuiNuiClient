package api;

import java.util.Random;

/**
 * 랜덤으로 패스워드 생성
 * @author Hyungwook
 *
 */
public class RandomCodeGenerator {
	
	public static String generate() {
		StringBuffer sb = new StringBuffer();
		Random rnd = new Random();

		do {

			char rnd_char = (char) (rnd.nextInt(26) + 65);
			sb.append(rnd_char);

		} while (sb.length() < 8);

		String code = sb.toString();
		return code;
	}
}
