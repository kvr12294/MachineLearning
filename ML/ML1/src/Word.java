
public class Word {

	String name;
	int yes;
	int no;
	
	Word(){
		yes=0;
		no=0;
	}
	Word(String name,int yes,int no)
	{
		this.name=name;
		this.yes=yes;
		this.no=no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYes() {
		return yes;
	}

	public void setYes(int yes) {
		this.yes = yes;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	
}
