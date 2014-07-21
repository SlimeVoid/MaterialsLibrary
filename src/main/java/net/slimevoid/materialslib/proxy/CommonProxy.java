package net.slimevoid.materialslib.proxy;

import java.io.File;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.minecraftforge.common.MinecraftForge;
import net.slimevoid.library.ICommonProxy;
import net.slimevoid.materialslib.network.event.ClientConnectToServer;

public class CommonProxy implements ICommonProxy {

	@Override
	public void preInit() {

	}

    @Override
    public void init() {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) MinecraftForge.EVENT_BUS.register(new ClientConnectToServer());
    }

    @Override
    public void postInit() {

    }

    @Override
    public String getMinecraftDir() {
        return null;
    }

    @Override
    public void registerConfigurationProperties(File configFile) {

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public void registerTickHandlers() {

    }

    @Override
    public void registerRenderInformation() {

    }

    @Override
    public void registerEventHandlers() {

    }

    @Override
    public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {

    }

    @Override
    public boolean isClient(World world) {
        return false;
    }






}
