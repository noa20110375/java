package NekoNyanNyan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	private int delay = 20;
	private long pretime;
	private int cnt;
	private int score;

	private Image player = new ImageIcon("src/images/nekogray.png").getImage();

	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int playerSpeed = 100;
	private int playerHp = 30;

	private boolean up, down, left, right, shooting;
	private boolean isOver;

	// 배열
	private ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>();
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	private ArrayList<EnemyAttack> enemyAttackList = new ArrayList<EnemyAttack>();
	//보스
	private ArrayList<EnemyBoss> enemyBossList = new ArrayList<EnemyBoss>();
	private PlayerAttack playerAttack;
	private Enemy enemy;
	private EnemyAttack enemyAttack;

	private Audio backgroundMusic;
	private Audio hitSound;

	@Override
	public void run() {
		// 배경음악 , 공격 당했을 때 효과음
		backgroundMusic = new Audio("src/audio/gameBGM.wav", true);
		hitSound = new Audio("src/audio/hitSound.wav", false);

		reset();
		while (true) {

			while (!isOver) {
				// 현재 시간
				pretime = System.currentTimeMillis();
				if (System.currentTimeMillis() - pretime < delay) {
					// Key 프로세스 따로 나눌 거임 플레이어 적 따로
					try {
						Thread.sleep(delay - System.currentTimeMillis() + pretime);
						keyProcess();
						playerAttackProcess();
						enemyAppearProcess();
						enemyMoveProcess();
						enemyAttackProcess();
						cnt++;

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 다시시작
	public void reset() {
		isOver = false;
		cnt = 0;
		score = 0;
		playerX = 10;
		// 플레이어 위치?
		playerY = (Main.SCREEN_HEIGHT - playerHeight) / 2;
		// 음악 재생
		backgroundMusic.start();
		// reset을 하는 리스트 배열
		playerAttackList.clear();
		enemyList.clear();
		enemyAttackList.clear();
	}

	// 키프로세스
	private void keyProcess() {
		if (up && playerY - playerSpeed > 0)
			playerY -= playerSpeed;
		if (down && playerY + playerHeight + playerSpeed < Main.SCREEN_HEIGHT)
			playerY += playerSpeed;
		if (left && playerX - playerSpeed > 0)
			playerX -= playerSpeed;
		if (shooting && cnt % 15 == 0) {
			playerAttack = new PlayerAttack(playerX + 222, playerY + 25);
			playerAttackList.add(playerAttack);

		}
	}

	private void playerAttackProcess() {

		// 플레이어 공격시 적의 hp 떨어지고 플레이어 공격이 사라짐
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.fire();

			for (int j = 0; j < enemyList.size(); j++) {
				enemy = enemyList.get(j);
				if (playerAttack.x > enemy.x && playerAttack.x < enemy.x + enemy.width && playerAttack.y > enemy.y
						&& playerAttack.y < enemy.y + enemy.height) {
					enemy.hp -= playerAttack.attack;
					playerAttackList.remove(playerAttack);

				} // 만약에 적의 hp가 0이하 이면 피격음 실행 적을 제거. 스코어가 1000오름.
				if (enemy.hp <= 0) {
					hitSound.start();
					enemyList.remove(enemy);
					score += 1000;
				}
			}

		}

	}

	// 적 출현
	private void enemyAppearProcess() {
		if (cnt % 80 == 0) {
			enemy = new Enemy(1120, (int) (Math.random() * 621));
			enemyList.add(enemy);
		}
	}

	// 적 움직임
	private void enemyMoveProcess() {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			enemy.move();

		}
	}

	// 적 공격
	private void enemyAttackProcess() {
		if (cnt % 50 == 0) {
			enemyAttack = new EnemyAttack(enemy.x - 79, enemy.y + 35);
			enemyAttackList.add(enemyAttack);
		}
		for (int i = 0; i < enemyAttackList.size(); i++) {
			enemyAttack = enemyAttackList.get(i);
			enemyAttack.fire();
			if (enemyAttack.x > playerX & enemyAttack.x < playerX + playerWidth && enemyAttack.y > playerY
					&& enemyAttack.y < playerY + playerHeight) {
				hitSound.start();
				playerHp -= enemyAttack.attack;
				enemyAttackList.remove(enemyAttack);
				if (playerHp <= 0)
					isOver = true;

			}
		}
	}

	public void gameDraw(Graphics g) {
		playerDraw(g);
		enemyDraw(g);
		infoDraw(g);
	}


	public void infoDraw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("SCORE:" + score, 40, 80);
		if (isOver) {
			g.setColor(Color.BLACK);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 80));
			g.drawString("Press R to restart", 295, 380);
		}
	}

	public void playerDraw(Graphics g) {
		g.drawImage(player, playerX, playerY, null);
		
		g.fillRect(playerX - 1, playerY - 40, playerHp * 6, 20);
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
		}
	}

	public void enemyDraw(Graphics g) {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			g.drawImage(enemy.image, enemy.x, enemy.y, null);
			g.setColor(Color.GREEN);
			g.fillRect(enemy.x + 1, enemy.y - 40, enemy.hp * 15, 20);
		}
		for (int i = 0; i < enemyAttackList.size(); i++) {
			enemyAttack = enemyAttackList.get(i);
			g.drawImage(enemyAttack.image, enemyAttack.x, enemyAttack.y, null);
		}
	}

	public boolean isOver() {
		return isOver;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setShooting(boolean shooting) {
	        this.shooting = shooting;
   }

}