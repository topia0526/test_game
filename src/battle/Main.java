package battle;

public class Main {

	public static void main(String[] args) {
		//

		// 戦闘開始メソッド
		Player me = new Player("レッド");
		Opponent man = new Opponent("グリーン"); // 戦闘トレーナーインスタンス(固有)を生成

		System.out.println(man.getProperty() + "の" + man.getName() + "がしょうぶをしかけてきた！");

		// ポケモンを表に出す
		man.start();
		me.start();
		int turn; // ターン数管理変数

		// 戦闘開始
		for (int a = 0; a < 5; a++) {
			turn = a + 1;
			//戦闘コマンド選択(とりあえずたたかう/交代/逃げるで実装)
			int ai = man.ai(man, me);// 相手のコマンド選択(まだ行動はしない)
			System.out.println("---------------------------------");
			System.out.println("【" + turn + "ターン目】");

			String barA = String.format("【自分】%-6s 残りHP:%3d  ", me.temoNow.getName(), me.temoNow.getHp());
			System.out.print(barA);

			for (int i = 0; i < 6; i++) {
				if (me.temochi[i].getCond() == 0) {
					System.out.print("×");
				} else if (me.temochi[i].getCond() == 1) {
					System.out.print("〇");
				} else if (me.temochi[i].getCond() == 2) {
					System.out.print("‐");
				}
			}
			System.out.print("\n");

			String barB = String.format("【相手】%-6s 残りHP:%3d  ", man.temoNow.getName(), man.temoNow.getHp());
			System.out.print(barB);

			for (int i = 0; i < 6; i++) {
				if (man.temochi[i].getCond() == 0) {
					System.out.print("×");
				} else if (man.temochi[i].getCond() == 1) {
					System.out.print("〇");
				} else if (man.temochi[i].getCond() == 2) {
					System.out.print("‐");
				}
			}
			System.out.print("\n");

			System.out.println("▽どうする？ 【1:たたかう】 【2:交代】 【3:逃げる】>");
			int commandInput = new java.util.Scanner(System.in).nextInt(); // コマンド入力

			if (ai != 7) { // 交代時(7以外)は必ず相手に先制する
				man.action(man, me, ai);
			}

			switch (commandInput) {
			case 1:
				me.attack(me, man);
				break;
			case 2:
				System.out.println("交代changeメソッド");
				//me.change();
				break;
			case 3:
				System.out.println("逃げるrunメソッド");
				//me.run();
				break;
			}

			if (ai == 7) {
				if (man.temoNow.getCond() == 0) {
					// man.sendOut(); 瀕死の時は攻撃せずに次のターンの準備。
					// 交代をしつつプレイヤー側の交代も提案するメソッドを作る
				} else {
					man.action(man, me, ai);
				}
			}

			// 戦闘の流れ
			// こちらの攻撃！
			// ダメージ計算
			//相手体力が残っているか判定
			// 相手の攻撃！
			// ダメージ計算
			//コマンド選択メソッドへ戻る

		}


		/* タイプ相性表正しく作れてるかテスト
		for (int i = 0; i < 18; i++) {
			for (int j = 0; j < 18; j++) {
				if (Poke.typeJudgeTest(i,j) == 0) {
					System.out.print("×");
				} else if (Poke.typeJudgeTest(i,j) == 1) {
					System.out.print("△");
				} else if (Poke.typeJudgeTest(i,j) == 2) {
					System.out.print("…");
				} else if(Poke.typeJudgeTest(i,j)==3)System.out.print("〇");{
					}
				if(j==17) {
					System.out.print("\n");
					}
				}
			}


		………………………………△×……△…
		…△△…〇〇……………〇△…△…〇…
		…〇△…△………〇………〇…△………
		……〇△△………×〇…………△………
		…△〇…△……△〇△…△〇…△…△…
		…△△…〇△……〇〇…………〇…△…
		〇…………〇…△…△△△〇×…〇〇△
		…………〇……△△………△△……×〇
		…〇…〇△……〇…×…△〇………〇…
		………△〇…〇…………〇△………△…
		………………〇〇……△…………×△…
		…△……〇…△△…△〇……△…〇△△
		…〇………〇△…△〇…〇…………△…
		×………………………〇……〇…△……
		……………………………………〇…△×
		………………△………〇……〇…△…△
		…△△△…〇………………〇………△〇
		…△…………〇△………………〇〇△…
		*/

	}

}

// getter setter の呼び出し方 ： クラスをインスタンス生成(任意変数名)→"任意変数名.getName()"でnameを呼び出せる。
