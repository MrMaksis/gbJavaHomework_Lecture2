package Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Homeworks {

    public static void main(String[] args) {

        Task_1();
        Task_2();
    }

    /**
     * Task 1-2
     */
    static void Task_1(){
        String sqlQuery = "SELECT * FROM students WHERE";

        Map<String, Object> parseJson = parseJson(readFileToString("data/Parametrs.json"));
        StringBuilder strBuffer = new StringBuilder(sqlQuery);

        int i = 0;
        for (String key : parseJson.keySet()) {
            Object value = parseJson.get(key);
            
            if(i == 0) strBuffer.append(" " + key.replaceAll("\"", "") + " = " + value + " END ");
            else if (i <= 4) strBuffer.append(key.replaceAll("\"", "") + " = " + value);
            else if (i < 4)strBuffer.append(key.replaceAll("\"", "") + " = " + value + " END ");

            i++;
        }

        System.out.println(strBuffer);
    }
    static void Task_2(){
        int[] arr = {5, 2, 8, 12, 3};
        bubbleSort(arr);
    }

    /**
    * Task functions
    */
    public static String readFileToString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                contentBuilder.append(line);
                contentBuilder.append(System.lineSeparator());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

    //Запись в файл
    public static void writeLog(int[] arr) {
        try {
            FileWriter writer = new FileWriter("data/log.txt", true);
            for (int num : arr) {
                writer.write(num + " ");
            }
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Возвращает список отпарсенного json файла
    public static Map<String, Object> parseJson(String json) {
        Map<String, Object> result = new HashMap<>();
        json = json.trim();

        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
            String[] keyValuePairs = json.split(",");

            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                result.put(key, parseValue(value));
            }
        }

        return result;
    }

    public static Map<String, Object> parseJsonMap(String json) {
        Map<String, Object> result = new HashMap<>();
        json = json.trim();

        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
            String[] keyValuePairs = json.split(",");

            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                result.put(key, parseValue(value));
            }
        }

        return result;
    }

    //Распаковка значений json файла
    private static Object parseValue(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }

        if (value.equalsIgnoreCase("true")) {
            return true;
        }

        if (value.equalsIgnoreCase("false")) {
            return false;
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            // Ignore
        }

        return null;
    }

    //Сортировки пузырьком числового массива
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Обмен элементов
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // Запись в лог-файл
                    writeLog(arr);
                    swapped = true;
                }
            }
            // Если не было обмена элементов во внутреннем цикле, то массив уже отсортирован
            if (!swapped) {
                break;
            }
        }
    }

}
