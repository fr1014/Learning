package DataSturctures.hashtab;

import java.util.Scanner;

public class HashTabDemo {

    public static void main(String[] args) {
        //创建哈希表
        HashTap hashTap = new HashTap(7);
        String key = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("exit: 退出");
            key = sc.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id");
                    int id = sc.nextInt();
                    System.out.println("请输入name");
                    String name = sc.next();
                    Emp emp = new Emp(id, name);
                    hashTap.add(emp);
                    break;
                case "list":
                    System.out.println("所有雇员的信息");
                    hashTap.list();
                    break;
                case "exit":
                    System.out.println("退出程序");
                    sc.close();
                    System.exit(0);
                    break;
            }
        }
    }

}

//创建HashTab,管理多条链表
class HashTap {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTap(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        //分别初始化每一条链表头
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.getId());
        empLinkedLists[empLinkedListNo].add(emp);
    }

    //遍历所有的链表，遍历hashtable
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    public int hashFun(int id) {
        return id % size;
    }
}

//表示一个雇员
class Emp {
    private int id;
    private String name;
    private Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }
}

//创建EmpLinkedList,表示一个链表
class EmpLinkedList {
    //头指针，指向第一个Emp
    private Emp head; //默认null

    public void add(Emp emp) {
        //如果添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (curEmp.getNext() != null) {
            curEmp = curEmp.getNext();
        }
        curEmp.setNext(emp);
    }

    //遍历链表
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表为空！");
            return;
        }
        System.out.print("第" + (no + 1) + "条链表的信息: ");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id = %d\t name = %s\t", curEmp.getId(), curEmp.getName());
            curEmp = curEmp.getNext();
            if (curEmp == null) {
                break;
            }
        }
        System.out.println();
    }
}
