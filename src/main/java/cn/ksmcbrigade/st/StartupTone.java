package cn.ksmcbrigade.st;

import net.minecraftforge.fml.common.Mod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Mod("st")
public class StartupTone {

    public static Logger LOGGER = LogManager.getLogger();

    public static File path = new File("config/st-sound.wav");

    public static boolean sounded = false;
    public static boolean init = false;

    public static void init() throws IOException {
        if(!path.exists()){
            FileUtils.writeByteArrayToFile(path, IOUtils.toByteArray(Objects.requireNonNull(StartupTone.class.getResourceAsStream("/assets/st/sound.wav"))));
        }
        LOGGER.info("Startup Tone mod loaded.");
        init = true;
    }
}
