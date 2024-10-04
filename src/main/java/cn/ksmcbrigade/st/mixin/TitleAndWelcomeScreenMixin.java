package cn.ksmcbrigade.st.mixin;

import cn.ksmcbrigade.st.StartupTone;
import net.minecraft.client.gui.screens.AccessibilityOnboardingScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.sound.sampled.*;
import java.io.IOException;

@Mixin({TitleScreen.class, AccessibilityOnboardingScreen.class})
public abstract class TitleAndWelcomeScreenMixin {

    @Inject(method = "init",at = @At("HEAD"))
    public void init(CallbackInfo ci) throws IOException {
        if(!StartupTone.sounded){
            if(!StartupTone.init) StartupTone.init();

            try {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(StartupTone.path);
                Clip clip = AudioSystem.getClip();

                clip.open(inputStream);
                clip.start();

                StartupTone.sounded = true;
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                StartupTone.LOGGER.info("Error in play the sound: {}",StartupTone.path,e);
            }
        }
    }
}
