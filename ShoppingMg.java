import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingMg extends Goods {
    //客户的购物车
    ArrayList<String> ownGoods = new ArrayList<>();//购物车中的商品名称
    ArrayList<Integer> ownGoodsCount = new ArrayList<>();//购物车中每种商品的数量

    //增加商品至购物车
    public void addGoodsToShopCar() {
        super.addGoods0();
        ownGoods.add("可口可乐");
        ownGoodsCount.add(2);
        TABtoCustomer tab=new TABtoCustomer();
        System.out.println("\t1--商品名添加\n\t2--商品编号添加");
        //用户选择
        Scanner choose = new Scanner(System.in);
        int add = choose.nextInt();
        //用户输入
        switch (add) {
            case 1:
                System.out.println("请输入你要购买的商品名称:");
                Scanner buy = new Scanner(System.in);
                String buyThings = buy.next();
                if (goodsName.contains(buyThings)) {
                    ownGoods.add(buyThings);
                    int address4 = goodsName.indexOf(buyThings);
                    System.out.println("您购买的商品还剩余：");
                    System.out.println(goodsCount.get(address4));
                    System.out.println("请选择需要购买商品的数量");
                    Scanner num = new Scanner(System.in);
                    int number = num.nextInt();
                    ownGoodsCount.add(number);
                    //从商品库中减去被购买商品的数量
                    goodsCount.set(address4, goodsCount.get(address4) - number);
                    System.out.println("商品已成功添加至购物车，快去为宝贝买单吧！");
                    System.out.println("商品名："+ownGoods);
                    System.out.println("购买量："+ownGoodsCount);
                    System.out.println("\t1--继续\n\t2--返回");
                    Scanner trend=new Scanner(System.in);
                    int trends=trend.nextInt();
                    switch (trends){
                        case 1:
                            addGoodsToShopCar();//返回上一级
                            break;
                        case 2:
                            tab.chooseAfterSignIn();
                            break;
                    }


                } else {
                    System.out.println("暂时还未找到您想要的商品");//判断商品是否存在
                    addGoodsToShopCar();//返回上一级
                    break;
                }
            case 2://使用商品名进行添加后期完善时加入


        }
    }

    //把商品从购物车中清除
    public void deleteGoodsFromShopCar() {
        //先初始化添加第一位用户的购物车-->用于测试
        ownGoods.add("可口可乐");
        ownGoodsCount.add(2);
        //先展示购物车中的内容
        System.out.println("商品：" + ownGoods);
        System.out.println("数量：" + ownGoodsCount);
        try {
            System.err.println("请确认是否继续商品删除操作");
            System.out.println("\t1--继续\n\t2--退出");
            Scanner chs = new Scanner(System.in);
            int choose4 = chs.nextInt();
            switch (choose4) {
                case 1:
                    System.out.println("请输入需要删除的商品名：");
                    Scanner del1=new Scanner(System.in);
                    String delGoods=del1.next();
                    if(ownGoods.contains(delGoods)){
                        System.out.println("请选择删除方式：");
                        System.out.println("\t4--清除该商品的全部数量\n\t5--选择性清除");
                        Scanner del=new Scanner(System.in);
                        int delete=del.nextInt();
                        int address5=ownGoods.indexOf(delGoods);//找出商品在购物车中的位置
                        int address6=goodsName.indexOf(delGoods);//找出商品在商品库中的位置
                        switch (delete){
                            case 4:
                                ownGoods.remove(address5);
                                ownGoodsCount.remove(address5);
                                //把商品放回商品库
                                goodsCount.set(address6,goodsCount.get(address6)+ownGoodsCount.get(address5));
                                break;//后期加入还原点
                            case 5:
                                System.out.println("请输入需要删除的商品数量：");
                                Scanner deleteNumber=new Scanner(System.in);
                                int deleteCount=deleteNumber.nextInt();
                                if(deleteCount>ownGoodsCount.get(address5)||deleteCount<=0){
                                    System.out.println("请重新选择");
                                }else {
                                    ownGoodsCount.set(address5,ownGoodsCount.get(address5)-deleteCount);
                                    System.out.println("删除成功");
                                    //把商品放回商品库
                                    goodsCount.set(address6,goodsCount.get(address6)+deleteCount);
                                }
                        }
                    }else {
                        System.err.println("购物车中没有此商品！");
                        System.err.println("请重新选择！");
                        deleteGoodsFromShopCar();
                    }
                    break;
                case 2:
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    //修改购物车中的商品//修改商品数量，当数量小于或等于0，则将该商品从购物车中清除。
    public void modifyGoodsOfShopCar() {
       if(ownGoodsCount.contains(0)){
           int goodsAddress=ownGoodsCount.indexOf(0);//判断商品数等于0的商品在购物车中的位置
           ownGoods.remove(goodsAddress);
       }
    }
}
