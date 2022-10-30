import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class GameScreen extends Canvas
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//���� ������ ���� ��� ���ϴ� Ŭ����
	//������ ȭ�� ��¿����� ���ӿ��� �������� ���̴� �������� ���� ���� ������ �ʴ� ���� ����.
	W_Shooting_frame main;
	int cnt, gamecnt;

	//ȭ�� �׸���� ����
	int x,y;//�÷��̾� ĳ������ ��ǥ

	Image dblbuff;//������۸��� �����
	Graphics gc;//������۸��� �׷��� ���ؽ�Ʈ

	Image bg,bg_f, bgFlip;//���ȭ�� //2013-10
	Image cloud[]=new Image[5];//����
	Image title, title_key;//Ÿ��Ʋȭ��

	Image chr[]=new Image[9];//�÷��̾� ĳ����
	Image enemy[]=new Image[5];//�� ĳ����
	Image bullet[]=new Image[5];//�Ѿ�
	Image explo;//����
	Image item[]=new Image[3];//������
	
	Image num;//2013-10
	Image uiUp;//2013-10

	Image _start;//���۷ΰ�
	Image _over;//���ӿ����ΰ�
	Image shield;//�ǵ�

	Font font;

	int v[]={-2,-1,0,1,2,1,0,-1};
	int v2[]={-8,-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,7,6,5,4,3,2,1,0,-1,-2,-3,-4,-5,-6,-7};
	int step=0;

	boolean boss=false;//�������� �߰�

	GameScreen(W_Shooting_frame main){
		this.main=main;
		font=new Font("Default",Font.PLAIN,9);
	}

	public void paint(Graphics g){
		if(gc==null) {
			dblbuff=createImage(main.gScreenWidth,main.gScreenHeight);//���� ���۸��� ������ũ�� ���� ����. ���� paint �Լ� ������ �� ��� �Ѵ�. �׷��� ������ null�� ��ȯ�ȴ�.
			if(dblbuff==null) System.out.println("������ũ�� ���� ���� ����");
			else gc=dblbuff.getGraphics();//������ũ�� ���ۿ� �׸��� ���� �׷��� ���ؽ�Ʈ ȹ��
			return;
		}
		update(g);
	}
	public void update(Graphics g){//ȭ�� ���ڰŸ��� ���̱� ����, paint���� ȭ���� �ٷ� ��ȭ���� �ʰ� update �޼ҵ带 ȣ���ϰ� �Ѵ�.
		cnt=main.cnt;
		gamecnt=main.gamecnt;
		if(gc==null) return;
		dblpaint();//������ũ�� ���ۿ� �׸���
		g.drawImage(dblbuff,0,0,this);//������ũ�� ���۸� ����ȭ�鿡 �׸���.
	}
	public void dblpaint(){
		//���� �׸��� ������ �� �Լ����� ��� ���Ѵ�.
		switch(main.status){
		case 0://Ÿ��Ʋȭ��
			Draw_TITLE();
			gc.setColor(new Color(0));
			gc.drawString("Education ver.", 10,40);
			break;
		case 1://���� ��ŸƮ
			Draw_BG();
			Draw_MY();
			Draw_BG2();
			drawImageAnc(_start, 0,270, 3);
			break;
		case 2://����ȭ��
		case 4://�Ͻ�����
			Draw_BG();
			Draw_MY();
			Draw_ENEMY();
			Draw_BULLET();
			Draw_EFFECT();
			Draw_ITEM();
			Draw_BG2();
			Draw_UI();
			break;
		case 3://���ӿ���
			Draw_BG();
			Draw_ENEMY();
			Draw_BULLET();
			Draw_BG2();
			drawImageAnc(_over, 320,240, 4);
			Draw_UI();
			break;
		default:
			break;
		}
	}

	public void Draw_TITLE(){
		gc.drawImage(title,0,0,this);
		if(cnt%20<10) gc.drawImage(title_key, 320-201,370, this);
	}
	public void Draw_BG(){
		int i;
		
		//������ �����̱� ���� �߰� �ڵ�
		int step1 = (cnt%2560)/2;//2013-10
		step1=step1>=640?step1-1280:step1;//2013-10
		int step2 = step1>=0?640-step1:-(640+step1);//2013-10
		gc.drawImage(bg,0-step1,0,this);//2013-10
		gc.drawImage(bgFlip,step2,0,this);//2013-10
		
		for(i=0;i<12;i++) gc.drawImage(cloud[3], i*64-((cnt/1)%128), 370, this);//2013-10 : ������ �����̰� �Ǹ鼭 �� ���� ���� ������ �̵� �ӵ��� ���� ����
		for(i=-1;i<14;i++) gc.drawImage(cloud[2], i*64-(cnt%128)*2, 395, this);
	}
	public void Draw_BG2(){
		int i;
		step=(cnt%(bg_f.getWidth(this)/main.scrspeed))*main.scrspeed;
		gc.drawImage(bg_f,0-step,540-bg_f.getHeight(this)+v[(cnt/20)%8]*2,this);
		//System.out.println("���"+step);
		if(step>=bg_f.getWidth(this)-main.gScreenWidth) {
			gc.drawImage(bg_f,0-step+bg_f.getWidth(this),540-bg_f.getHeight(this)+v[(cnt/20)%8]*2,this);
		}
		for(i=-1;i<14;i++) gc.drawImage(cloud[0], i*128-(cnt%128)*8, 435, this);
	}
	public void Draw_MY(){
		int myx,myy;
		myx=main.myx/100;
		myy=main.myy/100;
		switch(main.mymode){
		case 0://����
		case 1://�����̸鼭 ����
			if(main.cnt%20<10) drawImageAnc(chr[2+(main.cnt/5)%2], myx, myy, 4);
			break;
		case 2://���÷���
			if(main.myimg==6) drawImageAnc(chr[main.myimg+(main.cnt/3)%2], myx, myy, 4);
			else if(main.myimg!=8) drawImageAnc(chr[main.myimg+(main.cnt/5)%2], myx, myy, 4);
			else if(main.myimg==8) drawImageAnc(chr[main.myimg], myx, myy, 4);
			break;
		case 3://������
			if(main.cnt%6<3) drawImageAnc(chr[8], myx, myy, 4);
			break;
		}
		if(main.myshield>2) drawImageAnc(shield, (int)(Math.sin(Math.toRadians((cnt%72)*5))*16+myx), (int)(Math.cos(Math.toRadians((cnt%72)*5))*16+myy), (main.cnt/6%7)*64,0, 64,64, 4);//�ǵ� �������� 3 �̻�
		else if(main.myshield>0&&main.cnt%4<2) drawImageAnc(shield, (int)(Math.sin(Math.toRadians((cnt%72)*5))*16+myx), (int)(Math.cos(Math.toRadians((cnt%72)*5))*16+myy), (main.cnt/6%7)*64,0, 64,64, 4);//�ǵ� �������� 1, 2�� ����
	}
	public void Draw_ENEMY(){
		int i;
		Enemy buff;
		for(i=0;i<main.enemies.size();i++){
			buff=(Enemy)(main.enemies.elementAt(i));
			switch(buff.img){
			case 0:
				drawImageAnc(enemy[0], buff.dis.x, buff.dis.y, ((cnt/8)%7)*36,0, 36,36, 4);
				break;
			case 1:
				drawImageAnc(enemy[1], buff.dis.x, buff.dis.y, 4);//���� ���
				break;
			case 2://��ġ �׿����
				switch(buff.mode){
				case 0://���ʿ��� ���������� ����
				case 1://���ڸ����� ��� ����
				case 2://�Ʒ��� �̵�
				case 3://���� �̵�
					drawImageAnc(enemy[2], buff.dis.x, buff.dis.y, 0,0,36,50,4);
					break;
				case 5://�ڷ� ���� ����
					drawImageAnc(enemy[2], buff.dis.x, buff.dis.y, 72,0,36,50,4);
					break;
				case 4://�����ؼ� ���� ������ ���а� �������� �Ѿ� �߻�
					drawImageAnc(enemy[2], buff.dis.x, buff.dis.y, 36,0,36,50,4);
					break;
				}
			default:
				break;
			}
		}
	}
	public void Draw_BULLET(){
		Bullet buff;
		int i;
		for(i=0;i<main.bullets.size();i++){
			buff=(Bullet)(main.bullets.elementAt(i));
			switch(buff.img_num){
			case 0:
			case 1:
			case 2:
			case 3:
				drawImageAnc(bullet[buff.img_num], buff.dis.x-6,buff.dis.y-6, 4);
				break;
			}
		}
	}
	public void Draw_EFFECT(){
		int i;
		Effect buff;
		for(i=0;i<main.effects.size();i++){
			buff=(Effect)(main.effects.elementAt(i));
			drawImageAnc(explo, buff.dis.x, buff.dis.y, ((16-buff.cnt)%4)*64,((16-buff.cnt)/4)*64,64,64, 4);
		}
	}
	public void Draw_ITEM(){
		int i;
		Item buff;
		for(i=0;i<main.items.size();i++){
			buff=(Item)(main.items.elementAt(i));
			drawImageAnc(item[buff.kind], buff.dis.x, buff.dis.y, ((main.cnt/4)%7)*36,0, 36,36, 4);
		}
	}
	public void Draw_UI(){
		//String str1="SCORE "+main.score+"   LIFE "+main.mylife+"   SPEED "+main.myspeed+"  LEVEL "+(main.level+1);//2013-10
		String str2="[1] Speed down   [2] Speed up   [3] Pause";
//		gc.setColor(new Color(0));
//		gc.drawString(str1, 9,40);
//		gc.drawString(str1, 11,40);
//		gc.drawString(str1, 10,39);
//		gc.drawString(str1, 10,41);
//		gc.setColor(new Color(0xffffff));
//		gc.drawString(str1, 10,40);

		gc.setColor(new Color(0));
		gc.drawString(str2, 9,main.gScreenHeight-10);
		gc.drawString(str2, 11,main.gScreenHeight-10);
		gc.drawString(str2, 10,main.gScreenHeight-11);
		gc.drawString(str2, 10,main.gScreenHeight-9);
		gc.setColor(new Color(0xffffff));
		gc.drawString(str2, 10,main.gScreenHeight-10);
		
		//�׷����� UI�� ���� �߰� �ڵ�
		gc.drawImage(uiUp, 16, 25, this);//2013-10
		
		drawImageNum(num, 320, 40, main.score, 8);//2013-10
		drawImageNum(num, 52, 40, main.mylife, 2);//2013-10
		drawImageNum(num, 576, 40, main.level, 2);//2013-10
		
	}
	
	//2013-10
	public void drawImageNum(Image img, int x, int y, int value, int digit){
		//���ڸ� �̹����� ��ȯ�� �����ش�
		
		int width = img.getWidth(this)/10;
		int height = img.getHeight(this);
		
		String valueStr = String.valueOf(value);
		if(valueStr.length()<digit)
			valueStr = "0000000000".substring(0, digit-valueStr.length()) + valueStr;
		int _xx = x-valueStr.length()*width/2;
		for(int i=0;i<valueStr.length();i++)
			drawImageAnc(num, _xx+i*width, y, (valueStr.charAt(i)-'0')*width, 0, width,height, 0);

	}
	//2013-10
	
	public void drawImageAnc(Image img, int x, int y, int anc){
		//��Ŀ���� ������ �̹��� ��� ��ġ�� �����Ѵ�.
		//��) anc==0 : �»���� ����, anc==4 : �̹��� �߾��� ����
		int imgw, imgh;
		imgw=img.getWidth(this);
		imgh=img.getHeight(this);
		x=x-(anc%3)*(imgw/2);
		y=y-(anc/3)*(imgh/2);
		
		gc.drawImage(img, x,y, this);
	}
	public void drawImageAnc(Image img, int x, int y, int sx,int sy, int wd,int ht, int anc){//sx,sy���� wd,ht��ŭ Ŭ�����ؼ� �׸���.
		if(x<0) {
			wd+=x;
			sx-=x;
			x=0;
		}
		if(y<0) {
			ht+=y;
			sy-=y;
			y=0;
		}
		if(wd<0||ht<0) return;
		x=x-(anc%3)*(wd/2);
		y=y-(anc/3)*(ht/2);
		gc.setClip(x, y, wd, ht);
		gc.drawImage(img, x-sx, y-sy, this);
		gc.setClip(0,0, main.gScreenWidth+10,main.gScreenHeight+30);
	}


}