package jp.gingarenpo.gserver;

import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.logging.Logger;

/**
 * メインクラス
 * このクラスにイベントを追記するのはやめましょう。イベントごとに適切に管理されたワールドから選択します。
 */
public final class GServer extends JavaPlugin {
    
    public static Logger logger;
    public static Server server;
    
    /**
     * プラグイン開始時
     */
    @Override
    public void onEnable() {
        logger = getLogger();
        server = getServer();
        logger.info("Add Event Listener...");
        // Todo: 例外発生時の処理
        try {
            addEvents();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        logger.info("Event add completed.");

    }
    
    /**
     * プラグイン終了時
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    private void addEvents() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // パッケージ配下のクラス（jp.gingarenpo.gserver以下）を参照してリスナーが存在するイベントをサーバーにフックする
        // 当メソッドは使い方がよくわからない場合は書き換えずそのまま使うべし
        PluginManager pm = this.getServer().getPluginManager();
        
        // パッケージの中身を全部ゲット
        Reflections r = new Reflections("jp.gingarenpo.gserver");
        Set<Class<? extends Listener>> classes = r.getSubTypesOf(Listener.class);
        for (Class<? extends Listener> c: classes) {
            pm.registerEvents(c.getConstructor().newInstance(), this);
            logger.info("Add Event Listener '"+c.getSimpleName()+"'.");
        }
    }
}
