package pageObjects;


public class testInventoryObject {
String title;
String desc;
String price;
public testInventoryObject() {

}

public testInventoryObject(String title, String desc, String price) {
	super();
	this.title = title;
	this.desc = desc;
	this.price = price;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}

}
