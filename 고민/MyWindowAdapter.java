import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MyWindowAdapter extends WindowAdapter
{
	// �����츦 �ݱ� ���� �ΰ� Ŭ����. ���� �ݴ� ������
	// setVisible(false);
	// dispose();
	// System.exit(0);
	// �̻� �� �������� �̷������.
	//
	MyWindowAdapter(){
	}
	public void windowClosing(WindowEvent e) {
		Window wnd = e.getWindow();
		wnd.setVisible(false);
		wnd.dispose();
		System.exit(0);
	}
}
