import java.awt.Point;

public class Bullet
{
	// ���ӿ� �����ϴ� �Ѿ��� ó���ϱ� ���� Ŭ����
	// �޸� ȿ���� ���ؼ��� �Ѿ˿� ���� �ּ����� ������ ��� ���� ������, ó�� ������ ���� ������ ��ü ó�� ��ƾ�� �����Ѵ�.
	Point dis;//�Ѿ��� ǥ�� ��ǥ. ���� ��ǥ���� *100 �����̴�.
	Point pos;//�Ѿ��� ��� ��ǥ. ���� ��ǥ���� *100 �����̴�.
	Point _pos;//�Ѿ��� ���� ��ǥ
	int degree;//�Ѿ��� ���� ���� (����)
		//�Ѿ��� ���� ������ x, y ���������ε� ǥ�� �����ϴ�. ������ �� ��� ������ ź�� ������ ���������.
	int speed;//�Ѿ��� �̵� �ӵ�
	int img_num;//�Ѿ��� �̹��� ��ȣ
	int from;//�Ѿ��� ���� �߻��ߴ°�
	Bullet(int x, int y, int img_num, int from, int degree, int speed){
		pos=new Point(x,y);
		dis=new Point(x/100,y/100);
		_pos=new Point(x,y);
		this.img_num=img_num;
		this.from=from;
		this.degree=degree;
		this.speed=speed;
	}
	public void move(){
		_pos=pos;//���� ��ǥ ����
		pos.x-=(speed*Math.sin(Math.toRadians(degree))*100);
		pos.y-=(speed*Math.cos(Math.toRadians(degree))*100);
		dis.x=pos.x/100;
		dis.y=pos.y/100;
		//if(pos.x<0||pos.y>gScreenWidth*100||pos.y<0||pos.y>gScreenHeight*100) ebullet[i].pic=255;
	}
}