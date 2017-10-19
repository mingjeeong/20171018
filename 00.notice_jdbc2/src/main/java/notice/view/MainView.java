package notice.view;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import notice.controller.Controller;
import notice.model.dto.NoticeDto;

public class MainView {
	private Scanner s = new Scanner(System.in);
	private String id, pw;
	private String no;
	String title,content;
	int num;
	private ApplicationContext context;
	private Controller controller;
	
	
	public String login() {
		//DI객체 생성
		context = new ClassPathXmlApplicationContext("jdbc-beans-config.xml");
		controller = context.getBean(Controller.class);
		
		
		System.out.println("-----로그인 입력하시오-----");
		System.out.println("아이디 :");
		id=s.nextLine();
		System.out.println("비밀번호 :");
		pw=s.nextLine();
		
		if(controller.loginAction(id, pw)!=null) {
			return "로그인 성공";
		}else {
			return "로그인 실패";
		}
	}
	public void notice(String result) {
			
		Controller controller = context.getBean(Controller.class);
		if(result.equals("로그인 성공")) {
			System.out.println(id+"님 환영합니다.");
			
			menu();
				
		}else {
			System.out.println("일치하는 정보가 없습니다.");
		}
	}
		
	public void menu() {
		System.out.println("--------게시판-------");
		showList();//공지사항 리스트 출력
		System.out.println("--------메뉴--------");
		System.out.println("1.공지보기");
		System.out.println("2.공지등록");
		System.out.println("3.공지수정");
		System.out.println("4.공지삭제");
		System.out.println("5.로그아웃");
		System.out.println("6.종료");
		System.out.println("---이용할 서비스를 선택하시오.---");
		no=s.nextLine();
		
		switch(no) {
		case "1":
			showNotice();
			break;
		case "2":
			insertNotice();
			break;
		case "3":
			System.out.println("------공지글 수정------");
			System.out.println("제목 :");
			title = s.nextLine();
			System.out.println("내용 :");
			content = s.nextLine();
			
		//	controller.updateAction(dto);
		
			break;
		case "4":
			System.out.println("삭제");
			break;
		case "5":
			id=null;
			pw=null;
			System.out.println("성공적으로 로그아웃 되었습니다.");
			login();
			break;
		case "6":
			System.out.println("프로그램을 종료합니다.이용해주셔서 감사합니다.");
			System.exit(0);
			break;
		}
	}
	/**
	 * 공지 리스트 보기
	 */
	private void showList() {
		controller = context.getBean(Controller.class);
		ArrayList<NoticeDto> list = controller.listAction();
		for(int i=0 ; i<list.size() ; i++) {
			System.out.println(list.get(i));
		}
	}
	
	/**
	 * 공지 내용 보기
	 */
	private void showNotice() {
		controller = context.getBean(Controller.class);
		
		System.out.println("---글번호 입력---");
		num = Integer.parseInt(s.nextLine());
		NoticeDto dto = controller.viewAction(num);
		System.out.println(dto+","+dto.getContent());
		menu();
	}
	/**
	 * 공지등록
	 */
	private void insertNotice() {
		System.out.println("-----공지글 등록-----");
		System.out.println("제목 :");
		title = s.nextLine();
		System.out.println("내용 :");
		content = s.nextLine();
		controller.saveAction(id, title, content);
		menu();
	}
		
		

		

	public static void main(String[] args) {

		MainView main = new MainView();
		String result=main.login();
		main.notice(result);
		
		
	}

}
