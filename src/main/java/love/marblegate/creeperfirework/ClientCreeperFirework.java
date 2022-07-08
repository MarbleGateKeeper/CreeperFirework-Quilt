package love.marblegate.creeperfirework;

import love.marblegate.creeperfirework.misc.FireworkManufacturer;
import love.marblegate.creeperfirework.misc.NetworkUtil;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
//import org.quiltmc.loader.api.ModContainer;
//import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class ClientCreeperFirework implements ClientModInitializer {

    /*@Override
    public void onInitializeClient(ModContainer mod) {
    Why I'm not able to use org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer?
        Description: Initializing game

    Caused by: org.quiltmc.loader.api.LanguageAdapterException: Class love.marblegate.creeperfirework.ClientCreeperFirework cannot be cast to net.fabricmc.api.ClientModInitializer!
	    at org.quiltmc.loader.impl.util.DefaultLanguageAdapter.create(DefaultLanguageAdapter.java:64)
	    at org.quiltmc.loader.impl.entrypoint.EntrypointStorage$NewEntry.getOrCreate(EntrypointStorage.java:112)
	    at org.quiltmc.loader.impl.entrypoint.EntrypointStorage.lambda$getEntrypointContainers$1(EntrypointStorage.java:220)
	    ... 16 more
    }*/

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(NetworkUtil.THE_VERY_I_DONT_WANT_TO_USE_PACKET, (client, handler, buf, responseSender) -> {
            if (client.world != null) {
                BlockPos pos = buf.readBlockPos();
                boolean b = buf.readBoolean();
                client.world.addFireworkParticle(pos.getX(), pos.getY() + 0.5F, pos.getZ(), 0, 0, 0, FireworkManufacturer.generate(b));
                if (b) {
                    client.world.addFireworkParticle(pos.getX(), pos.getY() + 2.5F, pos.getZ(), 0, 0, 0, FireworkManufacturer.generateRandomSpecial());
                }
                if (b) {
                    client.world.playSound((PlayerEntity) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, SoundCategory.HOSTILE, 8.0F, 2.0F);
                } else {
                    client.world.playSound((PlayerEntity) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.HOSTILE, 8.0F, 2.0F);
                }
            }
        });
    }
}
