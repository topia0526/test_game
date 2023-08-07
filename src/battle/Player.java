package battle;

public class Player extends Trainer {


	public void start() {
		System.out.println("いけっ！" + temoNow.getName() + "！");
	}

	public Player(String name) {
		super(name); // Trainerクラスからコンストラクタ呼び出し
	}


	public void sendOut(Trainer man, Trainer me) { // 瀕死時の交代メソッド

	}

}
