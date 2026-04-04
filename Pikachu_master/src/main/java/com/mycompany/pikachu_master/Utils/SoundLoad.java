/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pikachu_master.Utils;
import javax.sound.sampled.*;
import java.io.File;
import java.net.URL;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 *
 * @author DELL
 */
public class SoundLoad {
    public static boolean isBgmOn = true;
    public static boolean isSfxOn = true;
    private static Clip bgmClip;
    private Clip transitionClip;
    
    // 1. NHẠC NỀN (BACKGROUND MUSIC)
    
    // Phát nhạc nền lặp vô tận
    public void playBGM(String resourcePath) {
        if(!isBgmOn) return;
        try {
            stopBGM();
            // Dùng URL thay vì File để đọc từ thư mục resources/source của Project
            URL url = getClass().getResource(resourcePath);
            if (url != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
                bgmClip = AudioSystem.getClip();
                bgmClip.open(audioInput);
                bgmClip.loop(Clip.LOOP_CONTINUOUSLY); 
                bgmClip.start();
                System.out.println("audio played !");
            } else {
                System.err.println("error: audio not found: " + resourcePath);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Dừng nhạc nền
    public void stopBGM() {
        if (bgmClip != null && bgmClip.isRunning()) {
            bgmClip.stop();
        }
    }
    
    // 2. NHẠC CHUYỂN MÀN HÌNH (TRANSITION SOUND)
    // Phát âm thanh khi đổi màn hình (Ví dụ: Từ StartScreen sang MainScreen)
    public void playTransitionSound(String resourcePath) {
        try {
            // Nếu đang có tiếng chuyển màn trước đó thì dừng lại
            if (transitionClip != null && transitionClip.isRunning()) {
                transitionClip.stop();
                transitionClip.close();
            }

            URL url = getClass().getResource(resourcePath);
            if (url != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
                transitionClip = AudioSystem.getClip();
                transitionClip.open(audioInput);
                transitionClip.start();
                
                // Tự động giải phóng sau khi phát xong
                transitionClip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        transitionClip.close();
                    }
                });
            } else {
                System.err.println("LỖI: Không tìm thấy nhạc chuyển màn tại: " + resourcePath);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // 3. NHẠC THAO TÁC (SOUND EFFECTS)
    

 // Phát âm thanh 1 lần (click chuột, ăn điểm...)
    public void playSoundEffect(String resourcePath) {
        if(!isSfxOn) return;
        try {
            URL url = getClass().getResource(resourcePath);
            if (url != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
                Clip effectClip = AudioSystem.getClip();
                effectClip.open(audioInput);
                effectClip.start();
                
                effectClip.addLineListener(new LineListener() {
                    @Override
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) {
                            effectClip.close();
                        }
                    }
                });
            } else {
                System.err.println("LỖI: Không tìm thấy file âm thanh tại: " + resourcePath);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
