package jp.gingarenpo.gserver;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.Set;
import java.util.logging.Logger;

/**
 * メインクラス
 * このクラスにイベントを追記するのはやめましょう。イベントごとに適切に管理されたワールドから選択します。
 */
public final class GServer extends JavaPlugin {
    
    public Logger logger = getLogger();
    
    /**
     * プラグイン開始時
     */
    @Override
    public void onEnable() {
        logger.info("Add Event Listener...");
        addEvents();
        logger.info("Event add completed.");

    }
    
    /**
     * プラグイン終了時
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    private void addEvents() {
        // パッケージ配下のクラス（jp.gingarenpo.gserver以下）を参照してリスナーが存在するイベントをサーバーにフックする
        // 当メソッドは使い方がよくわからない場合は書き換えずそのまま使うべし
        PluginManager pm = this.getServer().getPluginManager();
        
        // パッケージの中身を全部ゲット
        Reflections r = new Reflections("jp.gingarenpo.gserver");
        Set<Class<? extends Listener>> classes = r.getSubTypesOf(Listener.class);
        for (Class c: classes) {
            logger.info(c.getName());
        }
    }
}
