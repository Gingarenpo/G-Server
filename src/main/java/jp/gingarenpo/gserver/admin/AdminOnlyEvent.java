package jp.gingarenpo.gserver.admin;


import jp.gingarenpo.gserver.GServer;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * 管理者のみ実行できるイベントや、管理者関連を呼び出したりするイベントを格納しています。
 * 管理者権限を持つ人のみ使用することが可能です。
 */
public class AdminOnlyEvent implements Listener {
	
	/**
	 * 「!!!!9844!!!!」と1行目に書かれた看板をクリックしたとき
	 * 管理人を呼び出す
	 * @param e
	 */
	@EventHandler
	public void onCallAdmin(PlayerInteractEvent e) {
		if (e.getClickedBlock() == null || e.getClickedBlock().getType() != Material.OAK_SIGN) return;
		Sign s = (Sign) e.getClickedBlock().getState(); // 看板です
		if (!s.getLine(0).equals("!!!!9844!!!!")) return; // 書かれていることが違う
		// 管理人を呼び出す
		e.getPlayer().chat("管理人を呼び出しています。管理人がいれば反応があるはずです。");
		GServer.logger.info( e.getPlayer().getDisplayName() + "さんからのお呼び出しです！");
	}
	
}
