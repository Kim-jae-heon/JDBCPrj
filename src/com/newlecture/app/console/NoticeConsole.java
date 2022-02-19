package com.newlecture.app.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;

public class NoticeConsole {

	private NoticeService service;
	private int page;
	private String searchField;
	private String searchWord;
	//private int count; //console전역에서 현제 몇 번째 페이지인지 관리해야하기에 count로 관리하는 것.
	
	public NoticeConsole() {

		service = new NoticeService();
		page = 1;
		searchField = "TITLE";
		searchWord = "";
	}
	
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		List<Notice> list = service.getList(page, searchField, searchWord);
		int count = service.getCount();
		int lastPage = count/5;
		lastPage = count%5 == 0? lastPage:lastPage+1;
		
		System.out.println("───────────────────────────────────────────────────────────────────────────");
		System.out.printf("<공지사항> 총%d 게시글\n", count);
		System.out.println("───────────────────────────────────────────────────────────────────────────");
		
		for(Notice n : list) {
			System.out.printf("%d.%s/ %s / %s\n",
								n.getId(), 
								n.getTitle(),
								n.getWriterId(), 
								n.getRegDate());
		}
		System.out.println("───────────────────────────────────────────────────────────────────────────");
		System.out.printf("%d/%dpages\n", page,lastPage);
	}

	public int inputNoticeMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.검색 / 6.종료\n>");
		String menu_ = scan.nextLine(); //nextLine으로 한 이유? nextInt는 a를 입력했을 경우 받아들이지 못하는 문제, 
		//enter를 입력했을 경우 버퍼의 문제 때문에 많은 제약이 있다. nextLine으로 한 뒤, 
		int menu = Integer.parseInt(menu_);
		//menu에서 정수로 치환하는 작업이 필요하다.
		
		return menu;
	}

	public void movePrevList() {
		if(page == 1) {
			System.out.println("==============================");
			System.out.println("이전 페이지가 없습니다");
			System.out.println("==============================");
			return;
		}
		page--;
	}

	public void moveNextList() throws ClassNotFoundException, SQLException {
		int count = service.getCount();
		int lastPage = count/5;
		lastPage = count%5 == 0? lastPage:lastPage+1;
		
		if(page == lastPage) {
			
			System.out.println("==============================");
			System.out.println("다음 페이지가 없습니다");
			System.out.println("==============================");
			return;
		}

		page++;
	}

	public void inputSearchWord() {
		Scanner scan = new Scanner(System.in); //멤버변수에 두어야할 것과 지역변수에 두어야할 것을 명확하게 구분하기.
		System.out.println("검색 범주(title/content/writerId)중에 하나를 입력하세요");
		System.out.print(">");
		searchField = scan.nextLine();
		
		System.out.print("검색어>");
		searchWord = scan.nextLine();
	}

	
}
