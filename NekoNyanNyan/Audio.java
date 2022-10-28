package NekoNyanNyan;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
  private Clip clip;
  private File audioFile;
//  오디오 장치 오디오 입력 스트림
  private AudioInputStream audioInputStream;
//  무한반복 
  private boolean isLoop;

//오디오 제목과 무한반복.
public Audio(String pathName,boolean isLoop) {
//	예외 처리 
	try {
		
		clip = AudioSystem.getClip();
		audioFile = new File(pathName);
		audioInputStream = AudioSystem.getAudioInputStream(audioFile);
		clip.open(audioInputStream);
	}catch(LineUnavailableException e){
		e.printStackTrace();	
		}catch(IOException e) {
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
}

// 시작시 isLoop이면  무한반복
	public void start() {
		clip.setFramePosition(0);
		clip.start();
		if(isLoop)clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
//	종료시 끔.
	public void stop() {
		clip.stop();
	}
}
