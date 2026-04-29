// WITHOUT SYNCHRONIZED

class Spotify {
	public void playSongs(int n,String type) {
		for(int i=1;i<=n;i++) {
			System.out.println(type+i+" played");
		}
	}
}
class MyList1 implements Runnable {
	int n;
	String type;
	Spotify spotify;
	public MyList1(int n, String type, Spotify spotify) {
		this.n = n;
		this.type = type;
		this.spotify = spotify;
	}
	@Override
	public void run() {
		spotify.playSongs(n, type);
	}
}
class MyList2 implements Runnable {
	int n;
	String type;
	Spotify spotify;
	public MyList2(int n, String type, Spotify spotify) {
		this.n = n;
		this.type = type;
		this.spotify = spotify;
	}
	@Override
	public void run() {
		spotify.playSongs(n, type);
	}
}

public class Without_Sync_Methos {
    public static void main(String[] args) {
    	Spotify spotify = new Spotify();
    	MyList1 m1 = new MyList1(4, "Sad song ", spotify);
    	MyList2 m2 = new MyList2(4, "Love song ", spotify);
    	
    	Thread t1 = new Thread(m1);
    	Thread t2 = new Thread(m2);
    	t1.start();
    	t2.start();
    	
    
    }
}







// for Synchronized  make plainsongs method as synchronized