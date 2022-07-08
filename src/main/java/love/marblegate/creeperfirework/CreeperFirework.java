package love.marblegate.creeperfirework;

import love.marblegate.creeperfirework.misc.Configuration;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class CreeperFirework implements ModInitializer {

    @Override
    public void onInitialize(ModContainer mod) {
        AutoConfig.register(Configuration.class, JanksonConfigSerializer::new);
    }
}
