package com.example.dronecs420;

import java.util.ArrayList;
import java.util.Iterator;
public class ItemContainer extends ItemsClass implements Visitable {

    ArrayList<ItemsClass> containerList = new ArrayList<ItemsClass>();
    private String parent;
    private String name;
    private int price;
    private double Lx;
    private double Ly;
    private int length;
    private int width;
    private int height;

    // price field
    private int cur_price;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        ItemContainer other = (ItemContainer) obj;
        if (name == other.name)
            return true;
        return false;
    }

    private final boolean container = true;

    public ItemContainer(String cparent, String cname, int cprice, double cx, double cy, int clength, int cwidth, int cheight) {
    	this.parent = cparent;
        this.name = cname;
        this.price = cprice;
        this.Lx = cx;
        this.Ly = cy;
        this.length = clength;
        this.width = cwidth;
        this.height = cheight;
        this.cur_price = cur_price;
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
    
    public void setParent(String parent) {
        this.parent = parent;
    }
    
    public String getParent() {
        return parent;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    // this function deletes the container
    public void deleteContainer(ItemContainer icontainer) {
        //remove icontainer from containerlist
        if (!icontainer.equals(this)){
            this.containerList.remove(icontainer);
        }
        //hopefully delete self
        else if (icontainer.equals(this)){
            this.containerList = null;
        }
        else{
            //do nothing
        }
    }

    public ItemsClass searchItems(String searchname, ItemContainer container){
        //create string buffer
        String buffer = "";
        this.containerList = container.containerList;
        //iterate through containerList
        for (int i = 0; i < containerList.size(); i++){
            //checks if item is itemContainer type, then iterates into it
            if (containerList.get(i).checkType()){
                searchItems(searchname, (ItemContainer) containerList.get(i));
            }
            else{
                buffer = containerList.get(i).getName();
                if (buffer.contains(searchname)){
                    return containerList.get(i);
                }
            }
        }
        if (this.checkType()){
            System.out.println("Item not found in ItemContainer");
        }
        else{
            System.out.println("Item not found");
        }
        return null;
    }
    public void addItemContainer(ItemContainer icontainer){
        this.containerList.add(icontainer);
    }
    public void addItem(ItemsClass item){
        this.containerList.add(item);
    }

    @Override
    public int getCur_price() {
        return cur_price;
    }

    @Override
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