package de.maxhenkel.voicechat.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.resources.ResourceLocation;

public class ImageButton extends AbstractButton {

    protected Minecraft mc;
    protected ResourceLocation texture;
    protected PressAction onPress;
    protected TooltipSupplier tooltipSupplier;

    public ImageButton(int x, int y, ResourceLocation texture, PressAction onPress, TooltipSupplier tooltipSupplier) {
        super(x, y, 20, 20, "");
        mc = Minecraft.getInstance();
        this.texture = texture;
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
    }

    @Override
    public void onPress() {
        this.onPress.onPress(this);
    }

    protected void renderImage(int mouseX, int mouseY, float delta) {
        mc.getTextureManager().bind(texture);
        blit(x + 2, y + 2, 0, 0, 16, 16, 16, 16);
    }

    @Override
    public void renderButton(int mouseX, int mouseY, float delta) {
        super.renderButton(mouseX, mouseY, delta);
        renderImage(mouseX, mouseY, delta);

        if (isHovered()) {
            renderToolTip(mouseX, mouseY);
        }
    }

    public void renderToolTip(PoseStack matrices, int mouseX, int mouseY) {
        this.tooltipSupplier.onTooltip(this, matrices, mouseX, mouseY);
    }

    public interface TooltipSupplier {
        void onTooltip(ImageButton button, PoseStack matrices, int mouseX, int mouseY);
    }

    public interface PressAction {
        void onPress(ImageButton button);
    }

}
