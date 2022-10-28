package NekoNyanNyan;

import java.awt.Image;

import javax.swing.ImageIcon;
//Enemy 패키지 만들어서 Enemy 에서 상속하는 방식으로
//쪼개기 추가할 거 다른동작... 마찬가지
public class Enemy {

//	적 이미지 넣기
	Image  image = new ImageIcon("src/images/nekogray.png").getImage();
//	정수형 변수 x,y선언
	int x,y;
	//이미지  가로,세로 값 초기화
	int width = image.getWidth(null);
	int height = image.getHeight(null);

	int hp =10;
//매개변수 x,y를 가진 생성자 생성
	public Enemy(int x ,int y) {
		this.x = x;
		this.y = y;
	}
	// 움직임 ㅎㅎ
	public void move() {
		this.x -=7;
	}
	}

