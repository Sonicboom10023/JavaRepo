package com.example.dronecs420;

interface Visitor {
    // Used to return the sum of items price values.
    public int visit1(ItemsClass iclass);
    // Used to return the sum of items cur_price values.
    public int visit2(ItemsClass iclass);

    // Used to return the ItemContainer price value.
    public int visit1(ItemContainer icontainer);
    // Used to return the ItemContainer cur_price value.
    public int visit2(ItemContainer icontainer);
}
