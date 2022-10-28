package NekoNyanNyan;

import java.awt.Image;

import javax.swing.ImageIcon;
//player 패키지 만들어서 Player 에서 상속하는 방식으로
//쪼개기 추가할 거 다른동작...
public class PlayerAttack {
	//Attack 어택 이미지 넣기
 Image image =new ImageIcon("src/images/missile.png").getImage();
 int x,y;
 int width = image.getWidth(null);
 int height = image.getHeight(null);
 int attack =5;
 
 public PlayerAttack(int x, int y) {
	 this.x =x;
	 this.y =y;
 }
 
	 public void fire() {
		 this.x += 15;
	 }
}
