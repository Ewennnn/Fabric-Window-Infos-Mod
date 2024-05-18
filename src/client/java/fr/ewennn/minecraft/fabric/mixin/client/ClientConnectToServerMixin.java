package fr.ewennn.minecraft.fabric.mixin.client;

import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.network.packet.s2c.login.LoginSuccessS2CPacket;
import fr.ewennn.minecraft.fabric.WindowInfosCore;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ClientLoginNetworkHandler.class)
public abstract class ClientConnectToServerMixin {

    @Shadow @Final private @Nullable ServerInfo serverInfo;

    /**
     * Interception du packet informant que le client s'est connecté avec succès à un serveur.
     * Dans le cas d'un monde solo, le serveur est embarqué et le {@link ServerInfo} est null.
     * @param successS2CPacket Packet de succès de connection à un serveur.
     */
    @Inject(at = @At("RETURN"), method = "onSuccess")
    public void joinServer(LoginSuccessS2CPacket successS2CPacket, CallbackInfo ci) {
        WindowInfosCore.getInstance().updateServer(
                Objects.requireNonNullElse(
                        this.serverInfo,
                        new ServerInfo("Unknown", "Singleplayer", ServerInfo.ServerType.OTHER)
                ).address
        );
    }
}
