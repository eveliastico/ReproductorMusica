package Modelo;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Hermann Cazares
 */
public class Sonido {

    private static Clip clip;
    private static long posicionPausa;

    public void reproducirSonido(String rutaCancion) {
        detenerSonido();

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(rutaCancion).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.setMicrosecondPosition(0);
            clip.start();
            clip.drain();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void detenerSonido() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.setMicrosecondPosition(0);
        }
    }

    public void pausar() {
        if (clip != null && clip.isRunning()) {
            posicionPausa = clip.getMicrosecondPosition();
            clip.stop();
        }
    }

    public void reanudar() {
        if (clip != null && !clip.isRunning()) {
            clip.setMicrosecondPosition(posicionPausa); 
            clip.start();
        }
    }
}
