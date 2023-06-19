package Test;

/*
1) Калькулятор позволяет выполнять операции сложения строк, вычитывания строк из строк,
умножения строк на число и деления строк на число: "a" + "b", "a" - "b", "a" * b, "a" / b.
Данные передаются в одну версию(посмотрите пример)! Решения, входящие в каждую строку,
число и арифмитическая операция передаются с соответствующей строкой расчета неверными.
2) Значения строк передаваемых в выражении выделяются двойными кавычками.
3) Результатом сложения двух строк, является строка состоящая из переданных.
4) Результатом деления строки на число n, является строка в n раз короче исходной (см. пример).
5) Результатом умножения строки на число n является строка, которая в переданной строке повторяется ровно n раз.
6) Результатом вычитания строки из строки является строка, в которой удалена переданная подстрока или сама
исходная строка, если она не входила в вычитаемую строку.
7) Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. И строки длинной не более 10 символов
8) Калькулятор умеет работать только с целыми числами.
9) Первым аргументом выражения, подаваемого на вход, должна быть строка, при вводе пользователем выражения
вроде 3 + "hello", калькулятор должен выбросить исключение и прекратить свою работу.
10) При вводе пользователем неподходящих чисел, строк или неподдерживаемых операций
(например, деление строки на строку) приложение выбрасывает остатки и завершает свою работу.
 */

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String addition(String input) { // Операция сложения строк
        String[] result = input.split("\\+");
        return result[0] + result[1];
    }

    public static String subtraction(String input) { // Операция вычитания строк
        String[] result = input.split("\\-");
        return result[0].replaceAll(result[1], "");
    }

    public static String multiplication(String input) { // Операция умножения строк
        String[] result = input.split("\\*");

        try {
            if (((Integer.parseInt(result[1])) < 10) && ((Integer.parseInt(result[1])) > 0)) {
                return result[0].repeat(Integer.parseInt(result[1]));
            } else System.out.println("Второе значение может быть только числом в диапозоне [1;10]");
        } catch (Exception Error) {
            System.out.println("Умножение возможно только на число в диапозоне [1;10]");
            System.exit(0);
        }
        return "";
    }

    public static String division(String input) { // Операция деления строк
        String[] result = input.split("\\/");

        try {
            if (((Integer.parseInt(result[1])) < 10) && ((Integer.parseInt(result[1])) > 0)) {
                int res = result[0].length()/Integer.parseInt(result[1]);
                return result[0].substring(0, res);
            } else System.out.println("Второе значение может быть только числом в диапозоне [1;10]");
        } catch (Exception Error) {
            System.out.println("Деление возможно только на число в диапозоне [1;10]");
            System.exit(0);
        }
        return "";
    }

    public static void main(String[] args) {

        System.out.println("Введите строки в формате: a+b, a-b, a*b, a/b");
        Input expression = new Input(); // Ввод данных Пользователем

        String expressionNew = expression.input.replaceAll("[\"]", "");

        Pattern addition = Pattern.compile("\\+");
        Matcher Addition = addition.matcher(expression.input);
        Pattern subtraction = Pattern.compile("\\-");
        Matcher Subtraction = subtraction.matcher(expression.input);
        Pattern multiplication = Pattern.compile("\\*");
        Matcher Multiplication = multiplication.matcher(expression.input);
        Pattern division = Pattern.compile("\\/");
        Matcher Division = division.matcher(expression.input);

        String[] res = expression.input.split("\\+|\\-|\\/|\\*");
        String error1 = res[0];
        String error2 = res[1];

        if (error1.length() < 10 && error2.length() < 10) { // Условие - Длина строки не более 10 символом
            Pattern number = Pattern.compile("[1-9|0]");
            Matcher Number = number.matcher(error1);

            if (Number.find() != true) { // Условие - Первое значение не может быть числом
                if (Addition.find() == true) // Операция сложения
                    System.out.println(addition(expressionNew)); // Результат

                else if (Subtraction.find() == true)  // Операция вычитания
                    System.out.println(subtraction(expressionNew)); // Результат

                else if (Multiplication.find() == true) // Операция умножения
                    System.out.println(multiplication(expressionNew)); // Результат

                else if (Division.find() == true) // Операция деления
                    System.out.print(division(expressionNew)); // Результат

            } else System.out.println("Первое значение не может быть числом");

        } else System.out.println("Длина строки одного значения не должна превышать более 10 символов");
    }
}

class Input { // Конструктор ввода данных пользователем
    Scanner in = new Scanner(System.in);
    String input;

    Input() {
        input = in.nextLine();
    }
}
