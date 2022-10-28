package NekoNyanNyan;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;


//ShootingGame이 JFrame을 상속 받음
public class ShootingGame1 extends JFrame{
//이미지 띄우기
	//적의 움직임을 그릴  때 깜빡임 없애기 위해 
//	더블 버퍼링을 할거임 미리 구현하기 위해 선언
	private Image bufferImage;
	private Graphics screenGraphic;
//	생성자 호출. getImage() 가 이미지 정보를 불러옴. 
	//main 이미지
	private Image mainScreen = new ImageIcon("src/images/main_screen.jpg").getImage();
//	loading 이미지
	private Image loadingScreen = new ImageIcon("src/images/main_screen.jpg").getImage();
//gamescreen 이미지
	private Image gameScreen = new ImageIcon("src/images/main_screen.jpg").getImage();
//	boolean 타입으로 선언
	private boolean isMainScreen,isLoadingScreen,isGameScreen; 
	
	private Game game = new Game();
	//배경음악 선언
	private Audio backgroundMusic;
	
	
	//	생성자
	public ShootingGame1() {
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
	//what??
		init();
	}
	//초기 메인 화면
	private void init() {
//		메인 이미지만 불러옴.
		isMainScreen = true;
		isLoadingScreen = false;
		isGameScreen = false;
		// true 일때 실행
	backgroundMusic = new Audio("src/audio/menuBGM.wav", true);
	backgroundMusic.start();
	
	 addKeyListener(new KeyListener());
	}

	private void gameStart() {
		//Loading 이미지 불러옴
		isMainScreen = false;
		isLoadingScreen = true;
		
		Timer loadingTimer = new Timer();
		TimerTask loadingTask = new TimerTask() {
			
		
		@Override
		public void run() {
			backgroundMusic.stop();
			isLoadingScreen = false;
			isGameScreen = false;
			game.start();
		}
		};
		loadingTimer.schedule(loadingTask,3000);
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
		if(isMainScreen) {
		g.drawImage(mainScreen,0,0,null);
		}
		if(isLoadingScreen) {
			g.drawImage(loadingScreen,0,0,null);
			}
		if(isGameScreen) {
			g.drawImage(gameScreen,0,0,null);
		game.gameDraw(g);	
		}
		this.repaint();
	}



//키 눌렀을 때
class keyListener extends KeyAdapter{
	public void keypressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		//상
		case KeyEvent.VK_W:
			game.setUp(true);
			break;
		//하
	    case KeyEvent.VK_S:
		 game.setDown(true);
		break;
//좌
	case KeyEvent.VK_A:
		game.setLeft(true);
		break;
//우
	case KeyEvent.VK_D:
		game.setRight(true);
		break;
//다시시작
	  case KeyEvent.VK_R:
	       if (game.isOver()) game.reset();
           break;
           //공격
       case KeyEvent.VK_SPACE:
           game.setShooting(true);
           break;
//           게임시작
       case KeyEvent.VK_ENTER:
           if (isMainScreen) gameStart();
           break;
       case KeyEvent.VK_ESCAPE:
           System.exit(0);
           break;

		
	}
}     ///키 땠을 때
	 public void keyReleased(KeyEvent e) {
         switch (e.getKeyCode()) {
             case KeyEvent.VK_W:
                 game.setUp(false);
                 break;
             case KeyEvent.VK_S:
                 game.setDown(false);
                 break;
             case KeyEvent.VK_A:
                 game.setLeft(false);
                 break;
             case KeyEvent.VK_D:
                 game.setRight(false);
                 break;
             case KeyEvent.VK_SPACE:
                 game.setShooting(false);
                 break;
         }
        }
    }
}
