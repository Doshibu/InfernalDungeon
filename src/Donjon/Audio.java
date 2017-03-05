package Donjon;

import java.io.*;
import javax.sound.sampled.*;

/**
 * Joue un son.
 * @author Quentin VIGNAUD
 *
 */
public class Audio extends Thread {
	private AudioInputStream audioInputStream = null;
	private SourceDataLine line;
	private String fileName;
	private boolean boucle;
	private boolean lecture = true;
	/**
	 * 
	 * @param fileName Fichier son à jouer (format wave).
	 * @param boucle Indique s'il est à jouer en boucle.
	 */
	public Audio(String fileName, boolean boucle) {
		this.fileName = fileName;
		this.setBoucle(boucle);
	}

	public void run() {
		File fichier = new File(fileName);
		do {
			/*try {
				AudioFileFormat format = AudioSystem
						.getAudioFileFormat(fichier);
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}*/

			try {
				audioInputStream = AudioSystem.getAudioInputStream(fichier);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class,
					audioFormat);

			try {
				line = (SourceDataLine) AudioSystem.getLine(info);

			} catch (LineUnavailableException e) {
				e.printStackTrace();
				return;
			}

			try {
				line.open(audioFormat);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
				return;
			}
			line.start();

			try {
				byte bytes[] = new byte[1024];
				int bytesRead = 0;
				while ((bytesRead = audioInputStream.read(bytes, 0,
						bytes.length)) != -1 && lecture) {
					line.write(bytes, 0, bytesRead);
				}

			} catch (IOException io) {
				io.printStackTrace();
				return;
			}
			if (!lecture) return;
		} while (boucle);
		return;
	}
	
	public boolean lectureEnCours() {
		return lecture;
	}
	
	public void arret() {
		lecture = false;
	}

	public String getFileName() {
		return fileName;
	}

	public boolean isBoucle() {
		return boucle;
	}

	public void setBoucle(boolean boucle) {
		this.boucle = boucle;
	}
}
