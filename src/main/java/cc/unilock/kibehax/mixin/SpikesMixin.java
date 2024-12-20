package cc.unilock.kibehax.mixin;

import cc.unilock.kibehax.KibeHax;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.lucaargolo.kibe.blocks.miscellaneous.Spikes;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Spikes.class)
public class SpikesMixin {
	@Redirect(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/entity/FakePlayer;get(Lnet/minecraft/server/world/ServerWorld;)Lnet/fabricmc/fabric/api/entity/FakePlayer;"))
	private FakePlayer onEntityCollision$get(ServerWorld world, @Local(argsOnly = true) BlockPos pos) {
		FakePlayer fake = FakePlayer.get(world, KibeHax.PROFILE);
		fake.setPosition(pos.toCenterPos());
		return null;
	}
}
