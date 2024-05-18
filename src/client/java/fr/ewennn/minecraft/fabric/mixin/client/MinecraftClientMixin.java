package fr.ewennn.minecraft.fabric.mixin.client;

import fr.ewennn.minecraft.fabric.WindowInfosCore;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

	/**
	 * Modification du résultat au moment du chargement du nom de la fenêtre du jeu.
	 * @param info CallbackInfo permettant de contrôler le retour des méthodes avec sorties.
	 */
	@Inject(at = @At("RETURN"), method = "getWindowTitle", cancellable = true)
	private void getWindowTitle(CallbackInfoReturnable<String> info) {
		info.setReturnValue(WindowInfosCore.getInstance().getTitle());
	}

	/**
	 * Modification du nom de la fenêtre du jeu lorsque le client quitte un monde.
	 */
	@Inject(at = @At("RETURN"), method = "onDisconnected")
	private void onDisconnect(CallbackInfo ci) {
		WindowInfosCore.getInstance().updateServer(null);
	}
}