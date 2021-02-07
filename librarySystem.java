package library;

import java.util.ArrayList;
import java.util.Scanner;

class book{
    public String name;   //书名
    public String num;    //书编号
    public String variety;  //书籍种类
    public int borrowDay; //借阅天数
    public boolean flag;  //是否在库中
    book(){}
    book(String na, String nu, String va){
        name = na;
        num = nu;
        variety = va;
        borrowDay = 0;  //借阅初始为0
        flag = false;   //默认在库中
    }
};

class person{
    public String name;
    public int age;
    public int borrowNum;
    ArrayList<book> bookList = new ArrayList<>();
    person(){}
    person(String n,int a){
        name = n;
        age = a;
    }
    void borrow(book b){}
    void returnBook(book b){}
}

class student extends person{
    String ID;  //学号
    student(String n,int a,String id){
        super(n,a);
        ID = id;
        borrowNum = 10;  //学生可以同时最多借10本书
    }
    void borrow(book b){
        if(borrowNum == 0){
            System.out.println("借书数量已满，无法借书");
            return;
        }
        if(b.flag == true){
            System.out.println("该书已经被借走了");
        }
        else{
            b.borrowDay = 30; //学生可以借30天
            b.flag = true;    //标记这本书已经被借走了
            borrowNum--;
            bookList.add(b);  //添加到已借书籍列表中
            System.out.println("借书成功");
        }
    }
    public void returnBook(book b){
        //还书
        b.flag = false;
        b.borrowDay = 0;
        borrowNum++;
        bookList.remove(b);
    }


}

class teacher extends person{
    String title;  //职位
    teacher(String n,int a,String t){
        super(n,a);
        title = t;
        borrowNum = 20; //老师可以同时最多借20本书
    }
    public void borrow(book b){
        if(borrowNum == 0){
            System.out.println("借书数量已满，无法借书");
            return;
        }
        if(b.flag == true){
            System.out.println("该书已经被借走了");
        }
        else{
            b.borrowDay = 60; //教师可以借60天
            b.flag = true;    //标记这本书已经被借走了
            borrowNum--;
            bookList.add(b);
            System.out.println("借书成功");
        }
    }
    public void returnBook(book b){
        //还书
        b.flag = false;
        b.borrowDay = 0;
        borrowNum++;
        bookList.remove(b);
    }
}

public class sys {
    ArrayList<person> readerList = new ArrayList<>();
    ArrayList<book> bookList = new ArrayList<>();
    //添加读者
    public void addReader(){
        System.out.println("请输入你要录入的类型选项(1.学生 2.教师)");
        Scanner in = new Scanner(System.in);
        int type = in.nextInt();
        String name;
        int age;
        System.out.println("请输入姓名");
        name = in.next();
        System.out.println("请输入年龄");
        age = in.nextInt();
        switch (type){
            case 1:
                String ID;
                System.out.println("请输入学号");
                ID = in.next();
                readerList.add(new student(name,age,ID));
                System.out.println("添加学生成功");
                break;
            case 2:
                String title;
                System.out.println("请输入职称");
                title = in.next();
                readerList.add(new teacher(name,age,title));
                System.out.println("添加教师成功");
                break;
            default:
                System.out.println("请输入1-2");
                break;
        }
    }
    //删除读者
    public void delReader(){
        System.out.println("请输入要删除的读者姓名");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        for(int i = 0; i < readerList.size(); i++){
            if(readerList.get(i).name.equals(name)){
                //如果找到 则删除
                readerList.remove(i);
                System.out.println("删除成功");
                return;
            }
        }
        System.out.println("未找到该读者");
    }
    //添加书籍
    public void addBook(){
        String name, num, variety;
        Scanner in = new Scanner(System.in);
        System.out.println("请输入书籍名");
        name = in.next();
        System.out.println("请输入书籍编号");
        num = in.next();
        System.out.println("请输入书籍种类");
        variety = in.next();
        bookList.add(new book(name, num, variety));
        System.out.println("书籍入库成功");
    }
    //借阅书籍
    public void borrowBook(){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入借书人名字");
        String readerName = in.next();
        person p = null;
        for(int i = 0; i < readerList.size(); i++){
            if(readerList.get(i).name.equals(readerName)){
                p = readerList.get(i);
                break;
            }
        }
        if(p == null){
            System.out.println("该借书人不存在");
            return;
        }
        System.out.println("请输入你要借阅的书籍名字");
        String bookName = in.next();
        book b = null;
        for(int i=0;i<bookList.size();i++){
            if(bookList.get(i).name.equals(bookName)){
                b = bookList.get(i);
                break;
            }
        }
        if(b == null){
            System.out.println("不存在该书籍");
            return;
        }
        //如果都存在 则借这本书
        p.borrow(b);
    }
    //归还书籍
    public void retrunBook(){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入还书人名字");
        String readerName = in.next();
        person p = null;
        for(int i = 0; i < readerList.size(); i++){
            if(readerList.get(i).name.equals(readerName)){
                p = readerList.get(i);
                break;
            }
        }
        if(p == null){
            System.out.println("该借书人不存在");
            return;
        }
        System.out.println("请输入你要还的书籍名字");
        String bookName = in.next();
        book b = null;
        for(int i = 0; i < p.bookList.size(); i++){
            if(p.bookList.get(i).name.equals(bookName)){
                b = p.bookList.get(i);
                break;
            }
        }
        if(b == null){
            System.out.println("你未借这本书");
            return;
        }
        //如果都存在 则还这本书
        p.returnBook(b);
        System.out.println("还书成功");
    }
    //查阅书籍
    public void searchBook(){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入查询的书籍名字");
        String name = in.next();
        for(int i = 0; i < bookList.size(); i++){
            if(name.equals(bookList.get(i).name)){
                System.out.println("------------------");
                System.out.println("书名: " + bookList.get(i).name);
                System.out.println("编号: " + bookList.get(i).num);
                System.out.println("种类: " + bookList.get(i).variety);
                System.out.println("是否借出: " + bookList.get(i).flag);
                System.out.println("------------------");
                return;
            }
        }
        System.out.println("未找到该书籍");
    }
    //删除书籍
    public void delBook(){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入需要删除的书籍名字");
        String name = in.next();
        for(int i=0;i<bookList.size();i++){
            if(name.equals(bookList.get(i).name)){
                bookList.remove(i);
                System.out.println("删除成功");
                return;
            }
        }
        System.out.println("不存在该书籍");
    }
    public static void main (String args[]){
        sys s = new sys();
        while(true){
            System.out.println("--------------------------------");
            System.out.println("      图书馆管理系统          ");
            System.out.println("--------------------------------");
            System.out.println(" 功能选项：                       ");
            System.out.println("   1.读者录入         2.删除读者   ");
            System.out.println("   3.添加书籍         4.借阅书籍   ");
            System.out.println("   5.归还书籍         6.查询书籍   ");
            System.out.println("   7.删除书籍         8.退出系统   ");
            System.out.println("---------------------------------");
            System.out.print( "请输入选项(1-7): ");
            int choice;
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
            switch (choice){
                case 1:
                    s.addReader();
                    break;
                case 2:
                    s.delReader();
                    break;
                case 3:
                    s.addBook();
                    break;
                case 4:
                    s.borrowBook();
                    break;
                case 5:
                    s.retrunBook();
                    break;
                case 6:
                    s.searchBook();
                    break;
                case 7:
                    s.delBook();
                    break;
                case 8:
                	return;
            }
        }

    }
}
