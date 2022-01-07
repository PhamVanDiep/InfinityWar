package button;

public enum SHIP {
	
	BLUE("res/ship/blue_ship.png", "res/life/blue_life.png"),
	RED("res/ship/red_ship.png", "res/life/red_life.png"),
	ORANGE("res/ship/orange_ship.png", "res/life/orange_life.png"),
	GREEN("res/ship/green_ship.png", "res/life/green_life.png");
	
	private String urlShip;
	private String urlLife;
	
	private SHIP(String url, String urlLife) {
		urlShip = url;
		this.urlLife = urlLife;
	}
	
	public String getURLShip() {
		return urlShip;
	}
	
	public String getUrlLife() {
		return urlLife;
	}
}
