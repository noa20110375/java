import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
//ShootingGame이 JFrame을 상속 받음
public class ShootingGame extends JFrame{
//이미지 띄우기
	//적의 움직임을 그릴  때 깜빡임 없애기 위해 
//	더블 버퍼링을 할거임 미리 구현하기 위해 선언
	private Image bufferImage;
	private Graphics screenGraphic;
//	생성자 호출. getImage() 가 이미지 정보를 불러옴. 
	private Image mainScreen = new ImageIcon(C:\JAVA\NekoNyanNyan\src\images\main_screen.jpg).getImage();

	
	//	생성자
	public ShootingGame() {
//		타이틀 지정
		setTitle("Shooting Game");
//		테두리 없애기
		setUndecorated(true);
//  창,제목,크기 크기 여부 설정
//	가로 1280 세로 720
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
//		창의 크기를 조절 못하게 하기.
		setResizable(false);
//		 창을 화면의 가운데 띄우기
		setLocationRelativeTo(null);
//  윈도우창 종료시 프로세스까지 깔끔하게 닫기 가능
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		창을 화면에 나타낼지 설정
		setVisible(true);
//	배치관리자를 지정하지 않는다는 의미. 절대 위치로 컴포넌트들을 나타냄. 
//		위에서 다루었던 setSize와 setLocation 을 사용.
		setLayout(null);
		
	}
//	Main 배경
	public void paint(Graphics g) {
		bufferImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = bufferImage.getGraphics();  
		screenDraw(screenGraphic);
//		x ,y, observer
		g.drawImage(bufferImage,0,0,null);
	}
//	screenDraw에서는 필요요소를 그릴 예정
	public void screenDraw(Graphics g) {
		g.drawImage(mainScreen,0,0,null);
		this.repaint();
	}
}
