package util;

import java.io.IOException;

public class ExecutablePlayer {
	public static void foo() {
		Runtime rt = Runtime.getRuntime();
		Process pc = null;
		try {
			// 외부 프로세스 실행
			pc = rt.exec("D:\\A_TeachingMaterial\\4.MiddleProject\\workspace\\NuiNuiClient\\MusicPlayer.exe");
			System.out.println("MusicPlayer Excute!!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 명령어 종료시 까지 대기
			try {
				pc.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 명령어 종료시 하위 프로세스 제거
			pc.destroy();
		}
	}
}
