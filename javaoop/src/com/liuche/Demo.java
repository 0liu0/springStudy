package com.liuche;

public class Demo{
    public static void main(String[] args){
        Student student1=new Student();
        Nurse nurse1=new Nurse();
        //向上转型，父类引用指向子类对象
        Person person1=student1;
        person1.say();		//输出：我是学生
        Person person2=nurse1;
        person2.say();		//输出：我是护士
        //向下转型
        Student student2=(Student)person1;
        student2.say();		//输出：我是学生
        //向下转型需要注意的是不能把原来是护士的张三转成学生   例如：
//        Student student3=(Student)person2;
//        student3.say();		//此处会报错

        //向上转型比较高级的用法
        Student student4=new Student();
        say(student4);		//输出：我是学生
    }
    public static void say(Person person){
        person.say();
    }
}
abstract class Person{
    public abstract void say();
}
class Student extends Person{
    @Override
    public void say(){
        System.out.println("我是学生");
    }
}
class Nurse extends Person{
    @Override
    public void say(){
        System.out.println("我是护士");
    }
}





