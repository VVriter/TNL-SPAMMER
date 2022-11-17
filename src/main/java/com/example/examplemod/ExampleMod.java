package com.example.examplemod;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.Logger;
import java.util.Random;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";
    private static Logger logger;

    public boolean enebled = false;
    public double tickDelay = 15000;

    public String[] frazs = {
            ">А у нас тут халява thnlshop.xyz - okdbs",
            ">Самые не дорогие ресурсы discord.gg/2bee - dsja js",
            ">Мы роздаем киты discord.gg/2bee - asdas",
            ">Более 16 бустов и 1300 человек discord.gg/2bee - asdas",
            ">Ввойди в историю dsc.gg/tnls - unxgwl",
            ">Самый старый клан орг ру - discord.gg/2bee",
            ">10 приглашений - 1 кит dsc.gg/tnls - kadajsk",
            ">f3dot только тут лол discord.gg/2bee - sjdsoss",
            ">У нас покупаI-0т все магазы которые есть на орг ру tnlshop.xyz sdasda",
            ">Hameha and Lemoliam в нашем клане dsc.gg/tnls",
            ">Давай бегом к нам и без выебонов discord.gg/2bee mnztelx",
            ">Заебали топеры? Kyпu кuт и розеби их! discord.gg/2bee orhx",
            ">Все время скамят ноунеймы? Тебе к нам dsc.gg/tnls",
            ">Хватит скакать на хую у рандомных шопов - discord.gg/2bee",
            ">Мы - ТНЛ, мы - доверие discord.gg/2bee slgjcid"
    };
    public Timer timer = new Timer();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(new ExampleMod());
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent e) {
        if (e.getMessage().startsWith(".start")) {
            e.setCanceled(true);
            enebled = true;
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(ChatFormatting.AQUA+"Включил спамер!"));
        }
        if (e.getMessage().startsWith(".end")) {
            e.setCanceled(true);
            enebled = false;
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(ChatFormatting.AQUA+"Включил спамер!"));
        }
        if (e.getMessage().startsWith(".delay")) {
            e.setCanceled(true);
            String[] args = e.getMessage().split(" ");
            try {
                tickDelay = Double.parseDouble(args[1]);
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(ChatFormatting.AQUA+"Поставил тиковый дилей на " + args[1]));
                timer.reset();
            } catch (Exception ex) {
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(ChatFormatting.AQUA+"Неправильно указаные аргументы."));
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (Minecraft.getMinecraft().world == null && Minecraft.getMinecraft().player == null) return;
        if (!enebled) return;
        if (timer.hasPassedMs(tickDelay)) {
            Minecraft.getMinecraft().player.sendChatMessage(frazs[randomValue(0, frazs.length)]);
            timer.reset();
        }
    }

    public static int randomValue(int min, int max){
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }

}
