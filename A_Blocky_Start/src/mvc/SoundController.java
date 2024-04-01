package mvc;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class SoundController {

	public static final float MAX_VOLUME = 0; // dB
	public static final float MIN_VOLUME = -50; // dB
	
	public static Clip backgroundMusic;
	public static Clip buttonClick;
	
	private static FloatControl backgroundMusicVolume;
	private static FloatControl buttonClickVolume;
	
	/**
	 * Constructor.
	 */
	public SoundController() {
		try {
			AudioInputStream stream;
			
			// Load background music
			stream = AudioSystem.getAudioInputStream(new File("./snd/BadApple16Bit.wav"));
			backgroundMusic = AudioSystem.getClip();
			backgroundMusic.open(stream);
			backgroundMusicVolume = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
			backgroundMusicVolume.setValue(0);
			
			// Load button click
			stream = AudioSystem.getAudioInputStream(new File("./snd/ButtonClick.wav"));
			buttonClick = AudioSystem.getClip();
			buttonClick.open(stream);
			buttonClickVolume = (FloatControl) buttonClick.getControl(FloatControl.Type.MASTER_GAIN);
			buttonClickVolume.setValue(0);
			
		} catch (Exception e) {
			Main.errorLogController.addError(e);
			return;
		}
	}
	
	/**
	 * Updates the volume of all sound effects.
	 * @param percentage Percentage (0..100)
	 */
	public void updateVolume(float percentage) {
		try {
			// Check percentage
			if (percentage < 0) {
				percentage = 0;
			} else if (percentage > 100) {
				percentage = 100;
			}
			
			// Calculate dB
			float volume = MIN_VOLUME + (MAX_VOLUME - MIN_VOLUME) * (percentage / 100);
			
			backgroundMusicVolume.setValue(volume - 10); // too loud
			buttonClickVolume.setValue(volume);
			
		} catch (Exception e) {
			Main.errorLogController.addError(e);
			return;
		}
		
	}
	
	/**
	 * Play any sound clip.
	 * @param clip Clip
	 */
	public void playSound(Clip clip) {
		// Check if opened
		if (!clip.isOpen()) {
			return;
		}
		
		// (Re)-Play sound effect
		clip.setFramePosition(0);
		
		if (clip == backgroundMusic) {
			backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
		} else if (clip == buttonClick) {
			clip.loop(0);
		}
		
	}

}
