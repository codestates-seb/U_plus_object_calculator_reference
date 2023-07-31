public class Validator {

    // 입력한 연산자가 올바른 연산자인지 검사한다.
    public static boolean checkIsValidOperator(String operatorInput) {

        if (operatorInput.length() > 1) return false;

        char[] operators = new char[] {
                '+', '-', '*', '/', '%'
        };

        int i = 0;
        while (i < operators.length) {
            if (operators[i] == operatorInput.charAt(0)) {
                return true;
            }
            else i++;
        }
        return false;
    }

    // 입력한 숫자가 실수인지 확인한다.
    public static boolean checkIsDouble(String str) {

        String[] splited = str.split("[.]");
        return splited.length == 2;
    }

    // 입력한 숫자가 유효한 숫자인지 검사한다.
    public static boolean checkIsValidNumber(String str) {

        String[] splited = str.split("[.]");

        return splited.length <= 2 &&
                (splited.length == 1 && checkIsNumber(splited[0])) ||
                (splited.length == 2 && checkIsNumber(splited[0]) && checkIsNumber(splited[1]));
    }

    // 입력한 숫자가 숫자로만 이루어져 있는지 검사한다.
    private static boolean checkIsNumber(String str) {

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}
