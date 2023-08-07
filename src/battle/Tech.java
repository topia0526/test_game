package battle;

public class Tech {

	String name;
	char type;
	int power;



	public String getName() {
		return this.name;
	}
	public int getPower() {
		return this.power;
	}
	public char getType() {
		return this.type;
	}



	//わざ情報を記述するクラス
	public Tech(int num) {

		// 技情報を番号で受け取り、詳細情報を格納する（技名,技タイプ,威力）
		switch(num) {
		case 1:
			this.name = "10まんボルト";
			this.type = '電';
			this.power = 70;
			break;
		case 2:
			this.name = "かみなり";
			this.type = '電';
			this.power = 70;
			break;
		case 3:
			this.name = "でんこうせっか";
			this.type = 'ノ';
			this.power = 70;
			break;
		case 4:
			this.name = "たたきつける";
			this.type = 'ノ';
			this.power = 70;
			break;
		case 5:
			this.name = "10万ボルト";
			this.type = '電';
			this.power = 70;
			break;
		case 6:
			this.name = "10万ボルト";
			this.type = '電';
			this.power = 70;
			break;
		case 7:
			this.name = "10万ボルト";
			this.type = '電';
			this.power = 70;
			break;
		case 8:
			this.name = "10万ボルト";
			this.type = '電';
			this.power = 70;
			break;


	}

	}


}
