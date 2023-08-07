package battle;

public class Poke {
	private String name;
	private char type;
	private int hp;
	private int atk;
	private int def;
	private int cond = 1;
	private Tech[] tech = new Tech[4]; //：techクラスを作って、そこに配列で4つ技を入れる予定

	public Tech getTech(int num) { // わざ使用時などに呼び出す用getter
		return this.tech[num];
	}

	public char getType() { // わざ使用時などに呼び出す用getter
		return this.type;
	}
	public int getDef() {
		return this.def;
	}
	public int getCond() { // 状態呼び出し 0:瀕死 1:通常 2:存在しない
		return this.cond;
	}


	public Poke() {
		this.cond = 2;
	}

	public Poke(String name, int hp, int atk, int def, int tech1, int tech2, int tech3, int tech4) { // 最初に個体を生成するときに使う(コンストラクタ)
		sta(name); //staメソッドでタイプなどステータスの代入
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.tech[0] = new Tech(tech1);
		this.tech[1] = new Tech(tech2);
		this.tech[2] = new Tech(tech3);
		this.tech[3] = new Tech(tech4);

	}

	public String getName() { // nameのgetter

		return this.name;

	}

	public int getHp() { // 手持ちのHPを取得する(別メソッドの記述と組み合わせて何番目の手持ち(どのPoke)か指定)
		return this.hp;
	}

	public void damHp(int num) { // 被ダメをHPから引く
		this.hp -= num;
	}

	public int damageCulc(Tech atkTech, Poke def) { // 自身が攻撃する場合のダメージ計算メソッド
	// 補助技や「守る」など実装予定ないので引数これで
	// 単純に「HP - 技の威力*(攻撃/防御)*タイプ相性/10 = ダメージ」でいいかな
		double type = Poke.typeJudge(atkTech.getType(), def.getType());
		if (type == 1) {
			type = 0.5;
		} else if (type == 2) {
			type = 1;
		} else if (type == 3) {
			type = 2;
		}

		int damage = 0;
		double damageD;

		if (type != 0) {
		damageD = atkTech.getPower() * (this.atk / def.getDef()) * type / 5;
		damage = (int)damageD;
		def.damHp(damage);
		}

		return damage;

	}

	public void sta(String name) { //初期ステータス代入メソッド

		switch (name) { // 名前で判定して各種ステータスを代入・順番は名前順にすること(今はタイプだけ)

		case "ウインディ":
			this.type = '炎';

			break;

		case "エーフィ":
			this.type = '超';

			break;

		case "カビゴン":
			this.type = 'ノ';

			break;

		case "カメックス":
			this.type = '水';

			break;

		case "サイドン":
			this.type = '地';

			break;

		case "ナッシー":
			this.type = '草';

			break;

		case "ピカチュウ":
			this.type = '電';

			break;

		case "ピジョット":
			this.type = '飛';

			break;

		case "フシギバナ":
			this.type = '草';

			break;

		case "フーディン":
			this.type = '超';

			break;

		case "リザードン":
			this.type = '炎';

			break;

		}

	}

	public static int typeJudge(char typeA, char typeB) { //タイプをcharからintに変換するメソッド
		//typeA = 攻撃側、typeB = 防御側
		char type = '0'; // for文回す用変数
		int typeNum = 0;
		int typeANum = 0; // typeA,Bはchar型なのでint型には代入できないためint型変数を用意する
		int typeBNum = 0;

		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				type = typeA;
			} else {
				type = typeB;
			}
			switch (type) { //配列表に入れて計算するため、char型タイプ名(攻撃)を数字に変換
			case 'ノ':
				typeNum = 0;
				break;
			case '炎':
				typeNum = 1;
				break;
			case '水':
				typeNum = 2;
				break;
			case '電':
				typeNum = 3;
				break;
			case '草':
				typeNum = 4;
				break;
			case '氷':
				typeNum = 5;
				break;
			case '格':
				typeNum = 6;
				break;
			case '毒':
				typeNum = 7;
				break;
			case '地':
				typeNum = 8;
				break;
			case '飛':
				typeNum = 9;
				break;
			case '超':
				typeNum = 10;
				break;
			case '虫':
				typeNum = 11;
				break;
			case '岩':
				typeNum = 12;
				break;
			case '霊':
				typeNum = 13;
				break;
			case '竜':
				typeNum = 14;
				break;
			case '悪':
				typeNum = 15;
				break;
			case '鋼':
				typeNum = 16;
				break;
			case '妖':
				typeNum = 17;
				break;
			default:
				System.out.println("タイプ表記が間違っています");
			}
			if (i == 0) {
				typeANum = typeNum;
			} else {
				typeBNum = typeNum;
			}

		}

		int[][] attack = new int[18][18];

		// 効果なし:0 いまひとつ:1 通常:2 ばつぐん:3

		// 0:ノーマル攻
		attack[0][0] = 2;
		attack[0][1] = 2;
		attack[0][2] = 2;
		attack[0][3] = 2;
		attack[0][4] = 2;
		attack[0][5] = 2;
		attack[0][6] = 2;
		attack[0][7] = 2;
		attack[0][8] = 2;
		attack[0][9] = 2;
		attack[0][10] = 2;
		attack[0][11] = 2;
		attack[0][12] = 1;
		attack[0][13] = 0;
		attack[0][14] = 2;
		attack[0][15] = 2;
		attack[0][16] = 1;
		attack[0][17] = 2;

		// 1:ほのお攻
		attack[1][0] = 2;
		attack[1][1] = 1;
		attack[1][2] = 1;
		attack[1][3] = 2;
		attack[1][4] = 3;
		attack[1][5] = 3;
		attack[1][6] = 2;
		attack[1][7] = 2;
		attack[1][8] = 2;
		attack[1][9] = 2;
		attack[1][10] = 2;
		attack[1][11] = 3;
		attack[1][12] = 1;
		attack[1][13] = 2;
		attack[1][14] = 1;
		attack[1][15] = 2;
		attack[1][16] = 3;
		attack[1][17] = 2;

		// 2:みず攻

		attack[2][0] = 2;
		attack[2][1] = 3;
		attack[2][2] = 1;
		attack[2][3] = 2;
		attack[2][4] = 1;
		attack[2][5] = 2;
		attack[2][6] = 2;
		attack[2][7] = 2;
		attack[2][8] = 3;
		attack[2][9] = 2;
		attack[2][10] = 2;
		attack[2][11] = 2;
		attack[2][12] = 3;
		attack[2][13] = 2;
		attack[2][14] = 1;
		attack[2][15] = 2;
		attack[2][16] = 2;
		attack[2][17] = 2;

		// 3:でんき攻

		attack[3][0] = 2;
		attack[3][1] = 2;
		attack[3][2] = 3;
		attack[3][3] = 1;
		attack[3][4] = 1;
		attack[3][5] = 2;
		attack[3][6] = 2;
		attack[3][7] = 2;
		attack[3][8] = 0;
		attack[3][9] = 3;
		attack[3][10] = 2;
		attack[3][11] = 2;
		attack[3][12] = 2;
		attack[3][13] = 2;
		attack[3][14] = 1;
		attack[3][15] = 2;
		attack[3][16] = 2;
		attack[3][17] = 2;

		// 4:くさ攻 ここから編集する

		attack[4][0] = 2;
		attack[4][1] = 1;
		attack[4][2] = 3;
		attack[4][3] = 2;
		attack[4][4] = 1;
		attack[4][5] = 2;
		attack[4][6] = 2;
		attack[4][7] = 1;
		attack[4][8] = 3;
		attack[4][9] = 1;
		attack[4][10] = 2;
		attack[4][11] = 1;
		attack[4][12] = 3;
		attack[4][13] = 2;
		attack[4][14] = 1;
		attack[4][15] = 2;
		attack[4][16] = 1;
		attack[4][17] = 2;

		// 5:こおり攻

		attack[5][0]  = 2;
		attack[5][1]  = 1;
		attack[5][2]  = 1;
		attack[5][3]  = 2;
		attack[5][4]  = 3;
		attack[5][5]  = 1;
		attack[5][6]  = 2;
		attack[5][7]  = 2;
		attack[5][8]  = 3;
		attack[5][9]  = 3;
		attack[5][10]  = 2;
		attack[5][11]  = 2;
		attack[5][12]  = 2;
		attack[5][13]  = 2;
		attack[5][14]  = 3;
		attack[5][15]  = 2;
		attack[5][16]  = 1;
		attack[5][17]  = 2;

		// 6:かくとう攻

		attack[6][0]  = 3;
		attack[6][1]  = 2;
		attack[6][2]  = 2;
		attack[6][3]  = 2;
		attack[6][4]  = 2;
		attack[6][5]  = 3;
		attack[6][6]  = 2;
		attack[6][7]  = 1;
		attack[6][8]  = 2;
		attack[6][9]  = 1;
		attack[6][10]  = 1;
		attack[6][11]  = 1;
		attack[6][12]  = 3;
		attack[6][13]  = 0;
		attack[6][14]  = 2;
		attack[6][15]  = 3;
		attack[6][16]  = 3;
		attack[6][17]  = 1;


		// 7:どく攻

		attack[7][0]  = 2;
		attack[7][1]  = 2;
		attack[7][2]  = 2;
		attack[7][3]  = 2;
		attack[7][4]  = 3;
		attack[7][5]  = 2;
		attack[7][6]  = 2;
		attack[7][7]  = 1;
		attack[7][8]  = 1;
		attack[7][9]  = 2;
		attack[7][10]  = 2;
		attack[7][11]  = 2;
		attack[7][12]  = 1;
		attack[7][13]  = 1;
		attack[7][14]  = 2;
		attack[7][15]  = 2;
		attack[7][16]  = 0;
		attack[7][17]  = 3;


		// 8:じめん攻

		attack[8][0]  = 2;
		attack[8][1]  = 3;
		attack[8][2]  = 2;
		attack[8][3]  = 3;
		attack[8][4]  = 1;
		attack[8][5]  = 2;
		attack[8][6]  = 2;
		attack[8][7]  = 3;
		attack[8][8]  = 2;
		attack[8][9]  = 0;
		attack[8][10]  = 2;
		attack[8][11]  = 1;
		attack[8][12]  = 3;
		attack[8][13]  = 2;
		attack[8][14]  = 2;
		attack[8][15]  = 2;
		attack[8][16]  = 3;
		attack[8][17]  = 2;


		// 9:ひこう攻

		attack[9][0]  = 2;
		attack[9][1]  = 2;
		attack[9][2]  = 2;
		attack[9][3]  = 1;
		attack[9][4]  = 3;
		attack[9][5]  = 2;
		attack[9][6]  = 3;
		attack[9][7]  = 2;
		attack[9][8]  = 2;
		attack[9][9]  = 2;
		attack[9][10]  = 2;
		attack[9][11]  = 3;
		attack[9][12]  = 1;
		attack[9][13]  = 2;
		attack[9][14]  = 2;
		attack[9][15]  = 2;
		attack[9][16]  = 1;
		attack[9][17]  = 2;


		// 10:エスパー攻

		attack[10][0]  = 2;
		attack[10][1]  = 2;
		attack[10][2]  = 2;
		attack[10][3]  = 2;
		attack[10][4]  = 2;
		attack[10][5]  = 2;
		attack[10][6]  = 3;
		attack[10][7]  = 3;
		attack[10][8]  = 2;
		attack[10][9]  = 2;
		attack[10][10]  = 1;
		attack[10][11]  = 2;
		attack[10][12]  = 2;
		attack[10][13]  = 2;
		attack[10][14]  = 2;
		attack[10][15]  = 0;
		attack[10][16]  = 1;
		attack[10][17]  = 2;


		// 11:むし攻

		attack[11][0]  = 2;
		attack[11][1]  = 1;
		attack[11][2]  = 2;
		attack[11][3]  = 2;
		attack[11][4]  = 3;
		attack[11][5]  = 2;
		attack[11][6]  = 1;
		attack[11][7]  = 1;
		attack[11][8]  = 2;
		attack[11][9]  = 1;
		attack[11][10]  = 3;
		attack[11][11]  = 2;
		attack[11][12]  = 2;
		attack[11][13]  = 1;
		attack[11][14]  = 2;
		attack[11][15]  = 3;
		attack[11][16]  = 1;
		attack[11][17]  = 1;


		// 12:いわ攻

		attack[12][0]  = 2;
		attack[12][1]  = 3;
		attack[12][2]  = 2;
		attack[12][3]  = 2;
		attack[12][4]  = 2;
		attack[12][5]  = 3;
		attack[12][6]  = 1;
		attack[12][7]  = 2;
		attack[12][8]  = 1;
		attack[12][9]  = 3;
		attack[12][10]  = 2;
		attack[12][11]  = 3;
		attack[12][12]  = 2;
		attack[12][13]  = 2;
		attack[12][14]  = 2;
		attack[12][15]  = 2;
		attack[12][16]  = 1;
		attack[12][17]  = 2;


		// 13:ゴースト攻

		attack[13][0]  = 0;
		attack[13][1]  = 2;
		attack[13][2]  = 2;
		attack[13][3]  = 2;
		attack[13][4]  = 2;
		attack[13][5]  = 2;
		attack[13][6]  = 2;
		attack[13][7]  = 2;
		attack[13][8]  = 2;
		attack[13][9]  = 2;
		attack[13][10]  = 3;
		attack[13][11]  = 2;
		attack[13][12]  = 2;
		attack[13][13]  = 3;
		attack[13][14]  = 2;
		attack[13][15]  = 1;
		attack[13][16]  = 2;
		attack[13][17]  = 2;


		// 14:ドラゴン攻

		attack[14][0]  = 2;
		attack[14][1]  = 2;
		attack[14][2]  = 2;
		attack[14][3]  = 2;
		attack[14][4]  = 2;
		attack[14][5]  = 2;
		attack[14][6]  = 2;
		attack[14][7]  = 2;
		attack[14][8]  = 2;
		attack[14][9]  = 2;
		attack[14][10]  = 2;
		attack[14][11]  = 2;
		attack[14][12]  = 2;
		attack[14][13]  = 2;
		attack[14][14]  = 3;
		attack[14][15]  = 2;
		attack[14][16]  = 1;
		attack[14][17]  = 0;

		// 15:あく攻

		attack[15][0]  = 2;
		attack[15][1]  = 2;
		attack[15][2]  = 2;
		attack[15][3]  = 2;
		attack[15][4]  = 2;
		attack[15][5]  = 2;
		attack[15][6]  = 1;
		attack[15][7]  = 2;
		attack[15][8]  = 2;
		attack[15][9]  = 2;
		attack[15][10]  = 3;
		attack[15][11]  = 2;
		attack[15][12]  = 2;
		attack[15][13]  = 3;
		attack[15][14]  = 2;
		attack[15][15]  = 1;
		attack[15][16]  = 2;
		attack[15][17]  = 1;


		// 16:はがね攻

		attack[16][0]  = 2;
		attack[16][1]  = 1;
		attack[16][2]  = 1;
		attack[16][3]  = 1;
		attack[16][4]  = 2;
		attack[16][5]  = 3;
		attack[16][6]  = 2;
		attack[16][7]  = 2;
		attack[16][8]  = 2;
		attack[16][9]  = 2;
		attack[16][10]  = 2;
		attack[16][11]  = 2;
		attack[16][12]  = 3;
		attack[16][13]  = 2;
		attack[16][14]  = 2;
		attack[16][15]  = 2;
		attack[16][16]  = 1;
		attack[16][17]  = 3;


		// 17:フェアリー攻

		attack[17][0]  = 2;
		attack[17][1]  = 1;
		attack[17][2]  = 2;
		attack[17][3]  = 2;
		attack[17][4]  = 2;
		attack[17][5]  = 2;
		attack[17][6]  = 3;
		attack[17][7]  = 1;
		attack[17][8]  = 2;
		attack[17][9]  = 2;
		attack[17][10]  = 2;
		attack[17][11]  = 2;
		attack[17][12]  = 2;
		attack[17][13]  = 2;
		attack[17][14]  = 3;
		attack[17][15]  = 3;
		attack[17][16]  = 1;
		attack[17][17]  = 2;


		//行(縦)：攻撃
		//列(横)：防御

		/*
		ノ0
		炎1
		水2
		電3
		草4
		氷5
		格6
		毒7
		地8
		飛9
		超10
		虫11
		岩12
		霊13
		竜14
		悪15
		鋼16
		妖17
		*/

		int typeJudge = attack[typeANum][typeBNum];

		return typeJudge;

		// int damage = power * attack[typeA][typeB];

	}


	// タイプ出力テスト用
	public static int typeJudgeTest(int typeANum, int typeBNum) {
	 //タイプをcharからintに変換するメソッド
	//typeA = 攻撃側、typeB = 防御側

	int[][] attack = new int[18][18];

	// 効果なし:0 いまひとつ:1 通常:2 ばつぐん:3

	// 0:ノーマル攻
	attack[0][0] = 2;
	attack[0][1] = 2;
	attack[0][2] = 2;
	attack[0][3] = 2;
	attack[0][4] = 2;
	attack[0][5] = 2;
	attack[0][6] = 2;
	attack[0][7] = 2;
	attack[0][8] = 2;
	attack[0][9] = 2;
	attack[0][10] = 2;
	attack[0][11] = 2;
	attack[0][12] = 1;
	attack[0][13] = 0;
	attack[0][14] = 2;
	attack[0][15] = 2;
	attack[0][16] = 1;
	attack[0][17] = 2;

	// 1:ほのお攻
	attack[1][0] = 2;
	attack[1][1] = 1;
	attack[1][2] = 1;
	attack[1][3] = 2;
	attack[1][4] = 3;
	attack[1][5] = 3;
	attack[1][6] = 2;
	attack[1][7] = 2;
	attack[1][8] = 2;
	attack[1][9] = 2;
	attack[1][10] = 2;
	attack[1][11] = 3;
	attack[1][12] = 1;
	attack[1][13] = 2;
	attack[1][14] = 1;
	attack[1][15] = 2;
	attack[1][16] = 3;
	attack[1][17] = 2;

	// 2:みず攻

	attack[2][0] = 2;
	attack[2][1] = 3;
	attack[2][2] = 1;
	attack[2][3] = 2;
	attack[2][4] = 1;
	attack[2][5] = 2;
	attack[2][6] = 2;
	attack[2][7] = 2;
	attack[2][8] = 3;
	attack[2][9] = 2;
	attack[2][10] = 2;
	attack[2][11] = 2;
	attack[2][12] = 3;
	attack[2][13] = 2;
	attack[2][14] = 1;
	attack[2][15] = 2;
	attack[2][16] = 2;
	attack[2][17] = 2;

	// 3:でんき攻

	attack[3][0] = 2;
	attack[3][1] = 2;
	attack[3][2] = 3;
	attack[3][3] = 1;
	attack[3][4] = 1;
	attack[3][5] = 2;
	attack[3][6] = 2;
	attack[3][7] = 2;
	attack[3][8] = 0;
	attack[3][9] = 3;
	attack[3][10] = 2;
	attack[3][11] = 2;
	attack[3][12] = 2;
	attack[3][13] = 2;
	attack[3][14] = 1;
	attack[3][15] = 2;
	attack[3][16] = 2;
	attack[3][17] = 2;

	// 4:くさ攻 ここから編集する

	attack[4][0] = 2;
	attack[4][1] = 1;
	attack[4][2] = 3;
	attack[4][3] = 2;
	attack[4][4] = 1;
	attack[4][5] = 2;
	attack[4][6] = 2;
	attack[4][7] = 1;
	attack[4][8] = 3;
	attack[4][9] = 1;
	attack[4][10] = 2;
	attack[4][11] = 1;
	attack[4][12] = 3;
	attack[4][13] = 2;
	attack[4][14] = 1;
	attack[4][15] = 2;
	attack[4][16] = 1;
	attack[4][17] = 2;

	// 5:こおり攻

	attack[5][0]  = 2;
	attack[5][1]  = 1;
	attack[5][2]  = 1;
	attack[5][3]  = 2;
	attack[5][4]  = 3;
	attack[5][5]  = 1;
	attack[5][6]  = 2;
	attack[5][7]  = 2;
	attack[5][8]  = 3;
	attack[5][9]  = 3;
	attack[5][10]  = 2;
	attack[5][11]  = 2;
	attack[5][12]  = 2;
	attack[5][13]  = 2;
	attack[5][14]  = 3;
	attack[5][15]  = 2;
	attack[5][16]  = 1;
	attack[5][17]  = 2;

	// 6:かくとう攻

	attack[6][0]  = 3;
	attack[6][1]  = 2;
	attack[6][2]  = 2;
	attack[6][3]  = 2;
	attack[6][4]  = 2;
	attack[6][5]  = 3;
	attack[6][6]  = 2;
	attack[6][7]  = 1;
	attack[6][8]  = 2;
	attack[6][9]  = 1;
	attack[6][10]  = 1;
	attack[6][11]  = 1;
	attack[6][12]  = 3;
	attack[6][13]  = 0;
	attack[6][14]  = 2;
	attack[6][15]  = 3;
	attack[6][16]  = 3;
	attack[6][17]  = 1;


	// 7:どく攻

	attack[7][0]  = 2;
	attack[7][1]  = 2;
	attack[7][2]  = 2;
	attack[7][3]  = 2;
	attack[7][4]  = 3;
	attack[7][5]  = 2;
	attack[7][6]  = 2;
	attack[7][7]  = 1;
	attack[7][8]  = 1;
	attack[7][9]  = 2;
	attack[7][10]  = 2;
	attack[7][11]  = 2;
	attack[7][12]  = 1;
	attack[7][13]  = 1;
	attack[7][14]  = 2;
	attack[7][15]  = 2;
	attack[7][16]  = 0;
	attack[7][17]  = 3;


	// 8:じめん攻

	attack[8][0]  = 2;
	attack[8][1]  = 3;
	attack[8][2]  = 2;
	attack[8][3]  = 3;
	attack[8][4]  = 1;
	attack[8][5]  = 2;
	attack[8][6]  = 2;
	attack[8][7]  = 3;
	attack[8][8]  = 2;
	attack[8][9]  = 0;
	attack[8][10]  = 2;
	attack[8][11]  = 1;
	attack[8][12]  = 3;
	attack[8][13]  = 2;
	attack[8][14]  = 2;
	attack[8][15]  = 2;
	attack[8][16]  = 3;
	attack[8][17]  = 2;


	// 9:ひこう攻

	attack[9][0]  = 2;
	attack[9][1]  = 2;
	attack[9][2]  = 2;
	attack[9][3]  = 1;
	attack[9][4]  = 3;
	attack[9][5]  = 2;
	attack[9][6]  = 3;
	attack[9][7]  = 2;
	attack[9][8]  = 2;
	attack[9][9]  = 2;
	attack[9][10]  = 2;
	attack[9][11]  = 3;
	attack[9][12]  = 1;
	attack[9][13]  = 2;
	attack[9][14]  = 2;
	attack[9][15]  = 2;
	attack[9][16]  = 1;
	attack[9][17]  = 2;


	// 10:エスパー攻

	attack[10][0]  = 2;
	attack[10][1]  = 2;
	attack[10][2]  = 2;
	attack[10][3]  = 2;
	attack[10][4]  = 2;
	attack[10][5]  = 2;
	attack[10][6]  = 3;
	attack[10][7]  = 3;
	attack[10][8]  = 2;
	attack[10][9]  = 2;
	attack[10][10]  = 1;
	attack[10][11]  = 2;
	attack[10][12]  = 2;
	attack[10][13]  = 2;
	attack[10][14]  = 2;
	attack[10][15]  = 0;
	attack[10][16]  = 1;
	attack[10][17]  = 2;


	// 11:むし攻

	attack[11][0]  = 2;
	attack[11][1]  = 1;
	attack[11][2]  = 2;
	attack[11][3]  = 2;
	attack[11][4]  = 3;
	attack[11][5]  = 2;
	attack[11][6]  = 1;
	attack[11][7]  = 1;
	attack[11][8]  = 2;
	attack[11][9]  = 1;
	attack[11][10]  = 3;
	attack[11][11]  = 2;
	attack[11][12]  = 2;
	attack[11][13]  = 1;
	attack[11][14]  = 2;
	attack[11][15]  = 3;
	attack[11][16]  = 1;
	attack[11][17]  = 1;


	// 12:いわ攻

	attack[12][0]  = 2;
	attack[12][1]  = 3;
	attack[12][2]  = 2;
	attack[12][3]  = 2;
	attack[12][4]  = 2;
	attack[12][5]  = 3;
	attack[12][6]  = 1;
	attack[12][7]  = 2;
	attack[12][8]  = 1;
	attack[12][9]  = 3;
	attack[12][10]  = 2;
	attack[12][11]  = 3;
	attack[12][12]  = 2;
	attack[12][13]  = 2;
	attack[12][14]  = 2;
	attack[12][15]  = 2;
	attack[12][16]  = 1;
	attack[12][17]  = 2;


	// 13:ゴースト攻

	attack[13][0]  = 0;
	attack[13][1]  = 2;
	attack[13][2]  = 2;
	attack[13][3]  = 2;
	attack[13][4]  = 2;
	attack[13][5]  = 2;
	attack[13][6]  = 2;
	attack[13][7]  = 2;
	attack[13][8]  = 2;
	attack[13][9]  = 2;
	attack[13][10]  = 3;
	attack[13][11]  = 2;
	attack[13][12]  = 2;
	attack[13][13]  = 3;
	attack[13][14]  = 2;
	attack[13][15]  = 1;
	attack[13][16]  = 2;
	attack[13][17]  = 2;


	// 14:ドラゴン攻

	attack[14][0]  = 2;
	attack[14][1]  = 2;
	attack[14][2]  = 2;
	attack[14][3]  = 2;
	attack[14][4]  = 2;
	attack[14][5]  = 2;
	attack[14][6]  = 2;
	attack[14][7]  = 2;
	attack[14][8]  = 2;
	attack[14][9]  = 2;
	attack[14][10]  = 2;
	attack[14][11]  = 2;
	attack[14][12]  = 2;
	attack[14][13]  = 2;
	attack[14][14]  = 3;
	attack[14][15]  = 2;
	attack[14][16]  = 1;
	attack[14][17]  = 0;

	// 15:あく攻

	attack[15][0]  = 2;
	attack[15][1]  = 2;
	attack[15][2]  = 2;
	attack[15][3]  = 2;
	attack[15][4]  = 2;
	attack[15][5]  = 2;
	attack[15][6]  = 1;
	attack[15][7]  = 2;
	attack[15][8]  = 2;
	attack[15][9]  = 2;
	attack[15][10]  = 3;
	attack[15][11]  = 2;
	attack[15][12]  = 2;
	attack[15][13]  = 3;
	attack[15][14]  = 2;
	attack[15][15]  = 1;
	attack[15][16]  = 2;
	attack[15][17]  = 1;


	// 16:はがね攻

	attack[16][0]  = 2;
	attack[16][1]  = 1;
	attack[16][2]  = 1;
	attack[16][3]  = 1;
	attack[16][4]  = 2;
	attack[16][5]  = 3;
	attack[16][6]  = 2;
	attack[16][7]  = 2;
	attack[16][8]  = 2;
	attack[16][9]  = 2;
	attack[16][10]  = 2;
	attack[16][11]  = 2;
	attack[16][12]  = 3;
	attack[16][13]  = 2;
	attack[16][14]  = 2;
	attack[16][15]  = 2;
	attack[16][16]  = 1;
	attack[16][17]  = 3;


	// 17:フェアリー攻

	attack[17][0]  = 2;
	attack[17][1]  = 1;
	attack[17][2]  = 2;
	attack[17][3]  = 2;
	attack[17][4]  = 2;
	attack[17][5]  = 2;
	attack[17][6]  = 3;
	attack[17][7]  = 1;
	attack[17][8]  = 2;
	attack[17][9]  = 2;
	attack[17][10]  = 2;
	attack[17][11]  = 2;
	attack[17][12]  = 2;
	attack[17][13]  = 2;
	attack[17][14]  = 3;
	attack[17][15]  = 3;
	attack[17][16]  = 1;
	attack[17][17]  = 2;


	//行(縦)：攻撃
	//列(横)：防御

	/*
	ノ0
	炎1
	水2
	電3
	草4
	氷5
	格6
	毒7
	地8
	飛9
	超10
	虫11
	岩12
	霊13
	竜14
	悪15
	鋼16
	妖17
	*/

	int typeJudge = attack[typeANum][typeBNum];

	return typeJudge;

	// int damage = power * attack[typeA][typeB];
	}

	}
