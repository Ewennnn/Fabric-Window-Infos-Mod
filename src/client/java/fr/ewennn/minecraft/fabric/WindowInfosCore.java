package fr.ewennn.minecraft.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class WindowInfosCore implements ClientModInitializer {

	private static WindowInfosCore instance;

	public static WindowInfosCore getInstance() {
		return instance;
	}

	private static void setInstance(WindowInfosCore instance) {
		WindowInfosCore.instance = instance;
	}

	private String baseTitle;
	private String username;
	private @Nullable String server;

	@Override
	public void onInitializeClient() {
		setInstance(this);
		this.baseTitle = "Minecraft " + SharedConstants.getGameVersion().getName();
		this.username = MinecraftClient.getInstance().getSession().getUsername();
	}

	/**
	 * Permet de modifier l'adresse du serveur sur lequel le client est connecté.
	 * @param serverIP Adresse du serveur. Null si le client n'est pas connecté.
	 */
	public void updateServer(@Nullable String serverIP) {
		this.server = serverIP;
		this.updateWindowTitle();
	}

	/**
	 * Permet de mettre à jour automatiquement le nom de la fenêtre du jeu.
	 */
	public void updateWindowTitle() {
		MinecraftClient.getInstance().getWindow().setTitle(this.getTitle());
	}

	/**
	 * Permet de récupérer le titre formaté de la fenêtre.
	 * @return Titre formaté de la fenêtre du jeu.
	 */
	public String getTitle() {
		StringBuilder builder = new StringBuilder(this.baseTitle);
		if (Objects.nonNull(this.username)) {
			builder.append(" | ")
					.append(this.username);
		}

		if (Objects.nonNull(this.server)) {
			builder.append(" | ")
					.append(this.server);
		}

		return builder.toString();
	}
}