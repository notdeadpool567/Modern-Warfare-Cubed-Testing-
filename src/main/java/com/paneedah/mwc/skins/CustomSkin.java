package com.paneedah.mwc.skins;

import com.paneedah.mwc.utils.ModReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.io.File;

import static com.paneedah.mwc.proxies.ClientProxy.mc;

public class CustomSkin {

    private ResourceLocation resourceLocation;

    public CustomSkin(String name, File file) {
        try {
            DynamicTexture dynamicTexture = new DynamicTexture(ImageIO.read(file));
            resourceLocation = new ResourceLocation(ModReference.id, "customskin_" + name.toLowerCase());
            Minecraft.getMinecraft().getTextureManager().loadTexture(resourceLocation, dynamicTexture);
            mc.getTextureManager().bindTexture(resourceLocation);
        } catch (Exception e) { e.printStackTrace();}
    }

    public ResourceLocation getResourceLocation() {
        return this.resourceLocation;
    }

    public static ResourceLocation getCustomSkinResource(String skinName) {
        File image = new File("./config/mwc/skins/"+skinName+".png");
        if (!image.exists())
            return null;

        if (!GunSkins.customSkins.containsKey(skinName))
            GunSkins.customSkins.put(skinName, new CustomSkin(skinName, image));

        return GunSkins.customSkins.get(skinName).resourceLocation;
    }
}
