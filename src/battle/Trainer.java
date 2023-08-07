package battle;

public abstract class Trainer {
	protected String name;
	protected Poke[] temochi = new Poke[6];
	protected Poke temoNow; // 現在の手持ち先頭(戦闘に出ている)のポケモン
	protected String property; // トレーナー属性名

	public String getName() {
		return this.name;
	}

	public String getProperty() {
		return this.property;
	}

	public Trainer() {
	}

	public Poke getTemochi(int num) { // 手持ちのnum匹目の情報(Poke変数)を取得する
		return this.temochi[num];
	}

	public Poke getTemoNow() { // 手持ちのnum匹目の情報(Poke変数)を取得する
		return this.temoNow;
	}

	public void attack(Trainer atk, Trainer def) { // 戦うメソッド(Trainer変数で指定してみた 多態性)
		Poke atkPoke = atk.temoNow;
		Poke defPoke = def.temoNow;

		int techNum = new java.util.Random().nextInt(4); // テストでランダムな技を繰り出すようにしています

		if (atk instanceof Opponent) { // 自分か相手かで表示セリフが変わるため分岐
			System.out.println("▽相手の" + atkPoke.getName() + "の" + atkPoke.getTech(techNum).getName() + "！");
		} else {
			System.out.println("▽" + atkPoke.getName() + "の" + atkPoke.getTech(techNum).getName() + "！");
		}

		int typeJudge = typeMsg(atkPoke.getTech(techNum).getType(), defPoke.getType(), defPoke);
		int damage = atkPoke.damageCulc(atkPoke.getTech(techNum), defPoke); // ダメージ計算メソッド

		if (typeJudge == 0) { // 効果がない場合は被ダメメッセージを表示しない

		} else {
			if (atk instanceof Opponent) { // 自分か相手かで表示セリフが変わるため分岐
				System.out.println(defPoke.getName() + "は" + damage + "のダメージを受けた！");
			} else {
				System.out.println(defPoke.getName() + "に" + damage + "のダメージを与えた！");
			}

		}

	}


	public abstract void sendOut(Trainer man, Trainer me); // 瀕死時の交代メソッド

	public int typeMsg(char atkType, char defType, Poke def) { // タイプ相性判定してメッセージが出るメソッド

		int typeJudge = Poke.typeJudge(atkType, defType);
		if (typeJudge == 0) {
			System.out.println(def.getName() + "にはこうかがないようだ・・・");
		} else if (typeJudge == 2) {
			// 何も表示しない
		} else if (typeJudge == 3) {
			System.out.println("こうかはばつぐんだ！");
		} else if (typeJudge == 1) {
			System.out.println("こうかはいまひとつのようだ");
		}
		return typeJudge;
	}

	public Trainer(String name) { // コンストラクタはここ
		//名前(name)でどのトレーナーか判定して、手持ちの情報などを格納する

		switch (name) {
		case "レッド":
			this.name = name;
			this.property = "ポケモントレーナー";
			// Pokeインスタンス引数→("名前", HP, 攻撃, 防御, 所持技4個)

			// 手持ち6体をPokeインスタンスで生成し、temochi配列に格納する
			temochi[0] = new Poke("ピカチュウ", 140, 130, 120, 1, 2, 3, 4);
			temochi[1] = new Poke("エーフィ", 140, 130, 120, 1, 2, 3, 4);
			temochi[2] = new Poke("カビゴン", 140, 130, 120, 1, 2, 3, 4);
			temochi[3] = new Poke("フシギバナ", 140, 130, 120, 1, 2, 3, 4);
			temochi[4] = new Poke("リザードン", 140, 130, 120, 1, 2, 3, 4);
			temochi[5] = new Poke("カメックス", 140, 130, 110, 1, 2, 3, 4);
			break;

		case "グリーン":
			this.name = name;
			this.property = "ライバル";
			temochi[0] = new Poke("ピジョット", 140, 120, 110, 1, 2, 3, 4);
			temochi[1] = new Poke("フーディン", 140, 130, 110, 1, 2, 3, 4);
			temochi[2] = new Poke("サイドン", 140, 120, 110, 1, 2, 3, 4);
			temochi[3] = new Poke("ウインディ", 100, 120, 110, 1, 2, 3, 4);
			temochi[4] = new Poke("ナッシー", 140, 130, 110, 1, 2, 3, 4);
			temochi[5] = new Poke("カメックス", 140, 120, 110, 1, 2, 3, 4);
			break;
		// 生成した手持ち6体を配列に格納する
		}

		temoNow = temochi[0]; // 初期値として0番目のポケモンを手持ち先頭に配置

	}

}

// Poke[] (=temochi[])  → 変数Pokeが0,1,2,3...と順番に格納されている配列(Poke型配列)