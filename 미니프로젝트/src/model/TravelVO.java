package model;

public class TravelVO {
	private String theme;
	private String locationName;
	private String storeName;
	private String address;
	private String description;
	private String recommendMenu;

	public TravelVO() {
	}

	public TravelVO(String theme, String locationName, String storeName, String address, String description,
			String recommendMenu) {
		this.theme = theme;
		this.locationName = locationName;
		this.storeName = storeName;
		this.address = address;
		this.description = description;
		this.recommendMenu = recommendMenu;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecommendMenu() {
		return recommendMenu;
	}

	public void setRecommendMenu(String recommendMenu) {
		this.recommendMenu = recommendMenu;
	}
}
