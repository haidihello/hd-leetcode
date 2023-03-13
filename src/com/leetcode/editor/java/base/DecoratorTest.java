package com.leetcode.editor.java.base;

/**
 * 我们去咖啡馆点咖啡的时候，通常会点具体的咖啡单品，然后再点一些咖啡调料。
 * 咖啡种类（具体类）：Espresso、ShortBlack、LongBlack、Decaf
 * 调料（装饰类）：Milk、Soy、Chocolate
 */
public class DecoratorTest {
    public static void main(String[] args) {
        // 来一杯意式咖啡
        Drink drink = new Espresso();
        System.out.println("cost:" + drink.cost() + ",decorator:" + drink.getDescription());
        // 添加巧克力
//        drink = new Chocolate(drink);
        System.out.println("cost:" + drink.cost() + ",decorator:" + drink.getDescription());
        // 添加牛奶
        drink = new Milk(drink);
        System.out.println("cost:" + drink.cost() + ",decorator:" + drink.getDescription());
    }
}

class Decorator extends Drink {

    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public float cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "+" + drink.getDescription();
    }
}

/**
 * 添加巧克力
 */
class Chocolate extends Decorator {


    public Chocolate(Drink drink) {
        super(drink);
        super.setDescription("Chocolate");
        super.setPrice(3.0f);
    }
}

/**
 * 加牛奶
 */
class Milk extends Decorator {
    public Milk(Drink drink) {
        super(drink);
        super.setDescription("Milk");
        super.setPrice(2.0f);
    }

}

class Espresso extends Coffee {
    public Espresso() {
        super.setDescription("Espresso");
        super.setPrice(5.0f);
    }
}

class Decaf extends Coffee {
    public Decaf() {
        super.setDescription("Decaf");
        super.setPrice(3.0f);
    }
}

class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}

abstract class Drink {
    // 描述
    private String description;
    // 价格
    private float price = 0f;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description + "+" + this.getPrice();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public abstract float cost();
}
