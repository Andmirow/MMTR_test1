package com.MMTR.old;

import com.MMTR.Transleter;
import com.MMTR.old.FactoryTranslater;
import com.MMTR.old.MyChekedException;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите путь к словарю");
        String plase = scanner.nextLine();
        System.out.println("введите название словаря");
        String nameDataBase = scanner.nextLine();
        System.out.println("по каким правилам вы хотите с ним работать? введите 1 если с латинским словарем; 2 если с числовым словарем");
        Transleter tr = null;
        try {
            tr = FactoryTranslater.getTranslater(scanner.nextInt(),plase,nameDataBase);
            scanner.nextLine();
            boolean work = true;
            while (work){
                System.out.println("Команды: \n" +
                        "настроитьСловарь \n" +
                        "выбратьВсе \n" +
                        "найти \n" +
                        "удалить \n" +
                        "добавить \n"+
                        "выход");
                String comand = scanner.nextLine();
                if (comand.equals("выбратьВсе")){
                    tr.selectAll();
                }else if (comand.equals("найти")){
                    System.out.println("введите слово которое хотите найти");
                    try{
                        System.out.println(tr.find(scanner.nextLine()));
                    }catch (MyChekedException e){
                        System.out.println("не верный формат слова для словаря");
                    }
                }else if (comand.equals("удалить")){
                    System.out.println("введите слово которое хотите удалить");
                    boolean res = tr.delite(scanner.nextLine());
                    if (res){
                        System.out.println("слово успешно удалено");
                    }else
                        System.out.println("не удалось удалить слово");
                }else if (comand.equals("добавить")){
                    try{
                        System.out.println("введите слово по словарю");
                        String first = scanner.nextLine();
                        System.out.println("введите перевод");
                        String second = scanner.nextLine();
                        tr.Add(first,second);
                    }catch (MyChekedException e){
                        System.out.println("не верный формат слова для словаря");
                    }
                }else if (comand.equals("выход")){
                    work = false;
                }else if (comand.equals("настроитьСловарь")){
                    System.out.println("введите путь к словарю");
                    plase = scanner.nextLine();
                    System.out.println("введите название словаря");
                    nameDataBase = scanner.nextLine();
                    tr.setTransleter(plase,nameDataBase);
                } else
                    System.out.println("не правильная команда");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (MyChekedException throwables) {
            throwables.printStackTrace();
        } finally {
            tr.disconnect();
        }



    }
}
