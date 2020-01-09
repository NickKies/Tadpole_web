import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

public class ViewVO {
	// 문자열을 찾는 검색 메서드
	static void Searching(String search, ArrayList<SongVO> SL, HashSet<Integer> Key) {

		char[] atmp, btmp;
		int cnt = 0;
		atmp = search.toCharArray();
		for (int i = 0; i < SL.size(); i++) {
			// 이름에서 중 검색
			btmp = SL.get(i).getName().toCharArray();
			for (char a : atmp) {
				for (char b : btmp) {
					if (a == b&&a!=' ') {
						cnt++;
					}
				}
				if (cnt == 0)
					break;
			}
			if (cnt == atmp.length) {
				Key.add(i);
				
			}
			cnt = 0;
			// 아티스트에서 중 검색
			btmp = SL.get(i).getArtist().toCharArray();
			for (char a : atmp) {
				for (char b : btmp) {
					if (a == b&&a!=' ') {
						cnt++;
					}
				}
				if (cnt == 0)
					break;
			}
			if (cnt == atmp.length) {
				Key.add(i);
			}
			cnt = 0;
			// 앨범에서 중 검색
			btmp = SL.get(i).getAlbum().toCharArray();
			for (char a : atmp) {
				for (char b : btmp) {
					if (a == b&&a!=' ') {
						cnt++;
					}
				}
				if (cnt == 0)
					break;
			}
			if (cnt == atmp.length) {
				Key.add(i);
			}
			cnt = 0;

		}
	}

	// 콘솔창 알고리즘 메서드
	static int algorithm(Scanner sc, int num, ArrayList<SongVO> SL) {
		String search;
		int length = SL.size();
		if (num == 1) {
			System.out.println("---------------------------멜론 차트 순위------------------------------");
			System.out.println("-------------------------------------------------------------------");
			for (int i = 0; i < length; i++) {
				System.out.printf("%d위\t%s\t%s\t%s\n", SL.get(i).getRank(), SL.get(i).getName(), SL.get(i).getArtist(),
						SL.get(i).getAlbum());
			}
			System.out.println("-------------------------------------------------------------------");
			return 1;
		}

		else if (num == 2) {
			HashSet<Integer> resultKey = new HashSet<Integer>();
			System.out.println("------------------------- 검  색 ----------------------------");
			System.out.printf("노래/가수/앨범명을 입력하세요(아무거나 하나만): ");
			search = sc.nextLine();
			Searching(search, SL, resultKey);
			Iterator itr = resultKey.iterator();
			if (resultKey.size() == 0)
				System.out.println("\t검색 결과가 없습니다.\t");
			else {
				int i = 0;
				System.out.printf("총 %d 개의 검색 결과가 있습니다.\n",resultKey.size());
				while (itr.hasNext()) {
					i = (int) itr.next();
					System.out.printf("%d위\t%s\t%s\t%s\n", SL.get(i).getRank(), SL.get(i).getName(),
							SL.get(i).getArtist(), SL.get(i).getAlbum());
				}
			}
			System.out.println("-------------------------   끝  " + " ----------------------------");

			return 2;
		} else if(num==3)
			return -1;
			
		else
			System.out.println("1번 2번 3번 중에서 만 선택해 주세요");
		return 0;
	}

	// 메인 콘솔창
	static void view_in_console(ArrayList<SongVO> SL) {
		Scanner sc = new Scanner(System.in);
		String MainMenu = "--------------------------Melon---------------------------\n1. 음원 차트 확인\n2. 검색\n3. 종료\n-----------------------made by kyle-----------------------\n";
		int choice = 0;

		while (choice != -1) {

			System.out.println(MainMenu);
			System.out.printf("번호를 선택하세요: ");
			choice = Integer.parseInt(sc.nextLine());
			
			choice=algorithm(sc, choice, SL);
		}
	}
}
