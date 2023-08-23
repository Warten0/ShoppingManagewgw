import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Customer extends User {

    ArrayList<String> customerName = new ArrayList<>();//用户名
    ArrayList<String> cusPassword = new ArrayList<>();//客户密码
    ArrayList<String> customerSignInTime = new ArrayList<>();//用户注册时间
    String name1;

    //初始化一个客户
    public void addCustomer0() {
        customerName.add("yun1923");//初始化一个用户
        cusPassword.add("Ynu123456");//第一位用户的密码
        PersonalInformation person = new PersonalInformation();
    }

    //登录
    public void cuSignIn() {
        this.addCustomer0();
        System.out.println("请输入您的用户名：");
        Scanner nm = new Scanner(System.in);
        name1 = nm.next();
        if (customerName.contains(name1)) {
            System.out.println("请输入您的密码：");//密码连续输入错误5次就锁定账户。
            Scanner ps = new Scanner(System.in);
            String passWord1 = ps.next();
            if (cusPassword.contains(passWord1)) {
                System.out.println("登陆成功！");
                TABtoCustomer trend = new TABtoCustomer();
                trend.chooseAfterSignIn();
            } else {
                System.err.println("密码错误！");
                System.out.println("~~~~~~~~~~~~");
                System.out.println("\t1--重新输入");
                System.out.println("\t2---忘记密码");
                Scanner ipt = new Scanner(System.in);
                int input = ipt.nextInt();
                switch (input) {
                    //重新输入 //连续输入错误5次就锁定账户
                    case 1:
                        int count = 1;
                        while (true) {
                            int times = 5 - count;
                            if (count < 5) {
                                System.out.println("第" + count + "次输入的密码不正确，您还有" + times + "次机会");
                                System.out.println("请输入您的密码：");
                                Scanner ps1 = new Scanner(System.in);
                                String passWord2 = ps1.next();
                                if (cusPassword.contains(passWord2)) {
                                    System.out.println("登陆成功！");
                                    TABtoCustomer trend = new TABtoCustomer();
                                    trend.chooseAfterSignIn();
                                }
                                count++;
                            }
                            if (count == 5) {
                                System.err.println("您5次输入的密码都不正确，账号即将被锁定！");
                                System.exit(0);
                            }
                        }
                        //忘记密码
                    case 2:
                        this.setPassWord();
                        break;

                }
            }
        } else {
            System.err.println("用户名输入错误！请重新输入。");
            cuSignIn();//返回上一级
        }
    }

    //密码管理
    public String setPassWord() {
        PassWordmg passWordmg = new PassWordmg();
        passWordmg.setNewPassword();
        System.out.println("是否查看新密码：\n\t1--是\n\t0--否");
        Scanner choose = new Scanner(System.in);
        int choose1 = choose.nextInt();
        switch (choose1) {
            case 1:
                System.out.println(cusPassword);
                setPassWord();//返回上一级
                break;
            case 0:
                this.cuSignIn();
        }

        return setPassWord();
    }

    //用户注册
    public void customerRegister() {
        this.addCustomer0();
        System.out.println("请输入您的用户名：");//用户名长度不少于5个字符；
        Scanner nm1 = new Scanner(System.in);
        String customersName = nm1.next();
        if (customerName.contains(customersName)) {
            System.err.println("该名称已有小伙伴使用啦，请换一个吧。");
            customerRegister();
        } else {
            //要控制后面注册用户名储存的不与之冲突
            Date signInDate = new Date();
            customerSignInTime.add("2021-07-20-10-10-10");//初始化用户的注册时间，用于测试与占据数组的0号地址
            //Date转String
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-hh-hh");
            String str = sdf.format(signInDate);
            System.out.println(str);
            customerSignInTime.add(str);//将用户注册时间转换为字符串存入数组
            System.out.println("您于" + signInDate + "注册了账号");
            customerName.add(customersName);
            System.out.println("请设置您的登录密码：");//密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合。
            System.err.println("密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合。");
            Scanner cusp1 = new Scanner(System.in);
            String cusPassword1 = cusp1.next();
            //要控制后面注册用户的密码储存地址不冲突
            cusPassword.add(cusPassword1);
            //if()判断
            //用户个人信息填写
            PersonalInformation personalInformation = new PersonalInformation();
            personalInformation.bindMobilePhoneNumber();
            personalInformation.setCustomerID();
            personalInformation.bindEmail();
            this.cuSignIn();
        }
    }

    //购物车管理
    //可能会出现功能重复的现象
    public void shoppingMg() {
        ShoppingMg shoppingMg = new ShoppingMg();
        System.out.println("请选择对购物车的操作！");
        System.out.println("\t1---添加商品至购物车");
        System.out.println("\t2---删除购物车中的商品");
        System.out.println("\t3---自动清理");
        Scanner chs = new Scanner(System.in);
        int choose = chs.nextInt();
        switch (choose) {
            case 1:
                shoppingMg.addGoodsToShopCar();
                break;
            case 2:
                shoppingMg.deleteGoodsFromShopCar();
                break;
            case 3:
                shoppingMg.modifyGoodsOfShopCar();
                break;
        }

    }

    //支付
    public void pay() {
        Pay paying = new Pay();

        paying.payFor();
    }

    //购物历史
    public void shoppingHs() {
        ShoppingHs history = new ShoppingHs();
        history.getShoppingHs();

    }
}
