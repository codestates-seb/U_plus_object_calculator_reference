public class Validator {

    // 입력한 연산자가 올바른 연산자인지 검사한다.
    private static boolean checkIsValidOperator(String operatorInput) {

        // 입력한 연산자가 하나가 아닌 경우 false를 리턴한다.
        if (operatorInput.length() > 1) return false;

        // 올바른 연산자를 정의하는 char 배열 선언 및 초기화
        char[] operators = new char[] {
                '+', '-', '*', '/', '%'
        };

        // operators를 순회하며 입력한 연산자가 operators에 존재하는 연산자인지 확인한다.
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
    private static boolean checkIsDouble(String str) {

        // .을 기준으로 str을 나누어 배열에 저장한다.
        // 예 : "1.2" -> { "1", "2" }
        // 예 : "1" -> { "1" }
        String[] splited = str.split("[.]");

        // split된 배열의 길이가 2라면 == .이 하나만 존재했다면 실수이므로 true를 리턴한다.
        return splited.length == 2;
    }

    // 입력한 숫자가 유효한 숫자인지 검사한다.
    private static boolean checkIsValidNumber(String str) {

        /*
         * 타당한 숫자의 기준
         * 1. 소수점은 하나여야 한다. -> splited.length <= 2
         * 2. 정수인 경우(splited.length == 1), 모두 숫자로 이루어져 있어야 한다.
         * 3. 소수인 경우(splited.length == 2), 소수점 앞과 뒤는 모두 숫자로 이루어져 있어야 한다.
         * */

        String[] splited = str.split("[.]");

        return splited.length <= 2 &&
                (splited.length == 1 && checkIsNumber(splited[0])) ||
                (splited.length == 2 && checkIsNumber(splited[0]) && checkIsNumber(splited[1]));
    }

    // 입력한 숫자가 숫자로만 이루어져 있는지 검사한다.
    private static boolean checkIsNumber(String str) {

        // str을 순회하면서
        for (int i = 0; i < str.length(); i++) {

            // i번째 문자가 아라비아 숫자가 아니라면 false를 리턴한다.
            if (!Character.isDigit(str.charAt(i))) return false;
        }

        // 반복문을 순회하는 동안 false를 리턴하지 않았다면 == 모든 철자가 아라비아 숫자라면 true를 리턴한다.
        return true;
    }
}
