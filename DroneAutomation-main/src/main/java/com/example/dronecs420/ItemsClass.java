package com.example.dronecs420;

public class ItemsClass implements Visitable {
    private int price;
    private String parent;
    private String name;
    private double Lx;
    private double Ly;
    private int length;
    private int width;
    private int height;
    private int ini_price;
    private int cur_price;
    private final boolean container = false;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        ItemsClass other = (ItemsClass) obj;
        if (name == other.name)
            return true;
        return false;
    }

    public ItemsClass(String iparent, String iname, int iprice, double ix, double iy, int ilength, int iwidth, int iheight, int initial_price){
        this.parent = iparent;
    	this.name = iname;
        this.price = iprice;
        this.Lx = ix;
        this.Ly = iy;
        this.length = ilength;
        this.width = iwidth;
        this.height = iheight;
        this.ini_price = initial_price;
        this.cur_price = initial_price;
    }

    public ItemsClass() {
    }


    public void deleteItem(ItemsClass self){
        self = null;

    }

    public boolean checkType(){
        if (this.container){
            return true;
        }
        else {
            return false;
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getLy() {
        return Ly;
    }

    public void setLy(double ly) {
        Ly = ly;
    }

    public double getLx() {
        return Lx;
    }

    public void setLx(double lx) {
        Lx = lx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCur_price() {
        return cur_price;
    }

    public void setCur_price(int cur_price) {
        this.cur_price = cur_price;
    }

    @Override
    public int accept1(Visitor visitor) {
        return visitor.visit1(this);
    }

    @Override
    public int accept2(Visitor visitor) {
        return visitor.visit2(this);
    }
}
