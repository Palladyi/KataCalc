package arabian;



import java.util.Scanner;

public class CalculatorArabian {
    public static void main(String[] args) {
        //2+3
        //V-VII
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", " /", " \\*"};
        Scanner scn = new Scanner(System.in);
        System.out.println("Input: ");
        String scnd = scn.nextLine();
        //Удаляем пробелы в выражении
        String exp = scnd.replaceAll(" ", "");
        //Определяем арифметическое действие:
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if (actionIndex == -1) {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
            return;
        }
        //"2-4".split("-")-> {"2", "4"}
        String[] data = exp.split(regexActions[actionIndex]);
        //System.out.println(data [0] + "&" + data [1]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)


        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                //если римские, то конвертируем их в арабские
                //X+V
                //x-10
                //v - 5
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            } else {
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            //выполняем с числами арифметическое действие
            if (((0 < a) && (a <= 10)) && ((0 < b) && (b <= 10))) {
                int result;
                switch (actions[actionIndex]) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    default:
                        result = a / b;
                        break;
                }
                //15->XV
                if (isRoman) {
                   if (result <= 0) {
                        //если числа были римские и меньше одного, то
                        System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                    } else
                       //если числа были римские, возвращаем результат в римском числе

                       System.out.println(converter.intToRoman(result));

                } else {
                    //если числа были арабские, возвращаем результат в арабском числе
                    System.out.println(result);
                }
            } else {
                System.out.println("Числа должны быть от 0 включая 10");
            }
        } else {
            System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");

        }
    }
}
