package com.example.dronecs420;

class ItemsVisitor implements Visitor {

    public ItemsVisitor(){}
    @Override
    public int visit1(ItemsClass iclass) {
        //implement method for calculating PPV of ItemsClass
        return iclass.getPrice();
    }

    @Override
    public int visit2(ItemsClass iclass) {
        //implement method for calculating CMV of ItemsClass
        return iclass.getCur_price();
    }

    @Override
    public int visit1(ItemContainer icontainer) {
        //implement method for calculating PPV of ItemContainer
        return icontainer.getPrice();
    }

    @Override
    public int visit2(ItemContainer icontainer) {
        //implement method for calculating CMV of ItemContainer
        return icontainer.getCur_price();
    }
}
