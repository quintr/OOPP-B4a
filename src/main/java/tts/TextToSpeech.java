package tts;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import GUI.UI;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javax.sound.sampled.AudioInputStream;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.modules.synthesis.Voice;
import marytts.signalproc.effects.AudioEffect;
import marytts.signalproc.effects.AudioEffects;
import org.apache.commons.lang.ObjectUtils;


/**
 * @author GOXR3PLUS
 *
 */
public class TextToSpeech {

	private AudioPlayer tts;
	private MaryInterface marytts;
	private static Service<Void> backGroundThread;
	
	/**
	 * Constructor
	 */
	public TextToSpeech() {
		try {
			marytts = new LocalMaryInterface();
			
		} catch (MaryConfigurationException ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	//----------------------GENERAL METHODS---------------------------------------------------//
	
	/**
	 * Transform text to speech
	 *  @param daemon
	 *            <br>
	 *            <b>True</b> The thread that will start the text to speech Player will be a daemon Thread <br>
	 *            <b>False</b> The thread that will start the text to speech Player will be a normal non daemon Thread
	 * @param join
	 *            <br>
	 *            <b>True</b> The current Thread calling this method will wait(blocked) until the Thread which is playing the Speech finish <br>
	 * @param text
 *            The text that will be transformed to speech
	 */
	public void speak(String text) {
		boolean playtts = getplaytts();
		// Stop the previous player
		stopSpeaking();
		marytts.setVoice("dfki-poppy-hsmm");
		backGroundThread = new Service<Void>() {
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						if(playtts) {
							try (AudioInputStream audio = marytts.generateAudio(text)) {

								// Player is a thread(threads can only run one time) so it can be
								// used has to be initiated every time
								tts = new AudioPlayer();
								tts.setAudio(audio);
								tts.setGain(2);
								tts.setDaemon(false);
								tts.start();


							} catch (SynthesisException ex) {
								Logger.getLogger(getClass().getName()).log(Level.WARNING, "Error saying phrase.", ex);
							} catch (IOException ex) {
								Logger.getLogger(getClass().getName()).log(Level.WARNING, "IO Exception", ex);
							}
						}
						else {
							stopSpeaking();
						}
						return null;
						}


				};
			}


		};

		backGroundThread.start();


	}
	
	/**
	 * Stop the MaryTTS from Speaking
	 */
	public void stopSpeaking() {
		// Stop the previous player
		if (tts != null)
			tts.cancel();
	}

	//----------------------GETTERS---------------------------------------------------//
	private boolean getplaytts(){
		try {
			return UI.state.user.getBoolPreference("useTTS");
		}
		catch (NullPointerException ex){
			return true;
		}
	}
	/**
	 * Available voices in String representation
	 * 
	 * @return The available voices for MaryTTS
	 */
	public Collection<Voice> getAvailableVoices() {
		return Voice.getAvailableVoices();
	}
	
	/**
	 * @return the marytts
	 */
	public MaryInterface getMarytts() {
		return marytts;
	}
	
	/**
	 * Return a list of available audio effects for MaryTTS
	 * 
	 * @return
	 */
	public List<AudioEffect> getAudioEffects() {
		return StreamSupport.stream(AudioEffects.getEffects().spliterator(), false).collect(Collectors.toList());
	}
	
	//----------------------SETTERS---------------------------------------------------//
	
	/**
	 * Change the default voice of the MaryTTS
	 * 
	 * @param voice
	 */
	public void setVoice(String voice) {
		marytts.setVoice(voice);
	}
	
}
