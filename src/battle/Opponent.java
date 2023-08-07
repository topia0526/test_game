package battle;

public class Opponent extends Trainer {

	public void start() {
		System.out.println(this.name + "は" + this.temoNow.getName() + "を繰り出した！");
	}

	public int ai(Trainer atk, Trainer def) { // 戦闘時どのコマンドを選択するか判定するAIメソッド

		int result = 0; // 交代するかどうか結果を返す用
		int max = 0; //インデックス代入用変数

		if (Poke.typeJudge(def.temoNow.getType(), atk.temoNow.getType()) == 3) {
			// 受け時ばつぐんの場合は交代検討する
			int[] compatibility = new int[6]; //相性判定の数値を入れる用変数

			for (int i = 1; i < 6; i++) { // 各手持ちの相性判定(6回)
				compatibility[0] = Poke.typeJudge(this.temochi[0].getType(), def.temoNow.getType());
				compatibility[i] = Poke.typeJudge(this.temochi[i].getType(), def.temoNow.getType());

				if (compatibility[max] < compatibility[i]) {// 前の手持ちと比較して相性が良ければ、そのインデックスを代入する
					if (this.temochi[max].getCond() != 0) { // ただし交代先候補が瀕死の場合は交代しない
						max = i;
					}
				}
			}
		} else {
			result = 7; // 交代を検討しない場合7を返す
		}

		if (temoNow != temochi[max] && result != 7) { //交代要員の該当がある場合は交代する(commandBで)
			result = max; // 交代する場合交代先インデックスを返す
		} else {
			result = 7; // 交代しない場合7を返す
		}

		return result;

	}


	public void action(Trainer atk, Trainer def, int max) { // AIの結果を元にコマンドを選択する
		if (max != 7) {
			if (this.temoNow != temochi[max]) {
				System.out.println(this.name + "は" + this.temoNow.getName() + "をひっこめた！");
				this.temoNow = temochi[max]; // 新しい手持ちに交代
				System.out.println(this.name + "は" + this.temoNow.getName() + "を繰り出した！");
			}
		} else {
			attack(atk, def);
		}

	}


	public void sendOut(Trainer man, Trainer me) { // 瀕死時の交代メソッド
		System.out.println(man.temoNow.getName() + "は たおれた！");
		// 交代ポケモン選出AI
		System.out.println(man.getName() + "は" + man.temoNow.getName() + "を くりだした！");
	}
	public Opponent(String name) {
		super(name); // Trainerクラスからコンストラクタ呼び出し
	}

}
