import java.util.Scanner;

public class Calculator {

    Scanner scanner = new Scanner(System.in);
    String input; // 입력 값을 할당할 변수

    String left = "";     // 좌항 숫자
    String operator = ""; // 연산자
    String right = "";    // 우항 숫자
    String result;        // 출력 화면에 보일 값

    public void run() {

        boolean hasDouble = false; // 좌항 및 우항 중 실수 존재 여부
        boolean isReady = false;   // 좌항, 연산자, 우항이 모두 입력되었는지의 여부

        while (true) {

            // 좌항, 연산자, 우항이 모두 입력되었다면,
            if (isReady) {
                result = calculate(left, right, operator, hasDouble); // result에 연산 결과를 할당하고,
                left = result;   // 연속 연산이 가능하도록 left에 연산 결과를 할당한다.
                operator = "";   // 연산이 끝났으므로 초기화한다.
                right = "";      // 연산이 끝났으므로 초기화한다.
                hasDouble = Validator.checkIsDouble(left); // 연속 연산에 필요한 left가 실수인지 다시 확인한다.
                isReady = false; // 연산이 끝났으므로 초기화한다.
            }
            else {
                result = left + operator + right;
            }

            // 화면을 출력한다.
            printDisplay(result);

            // 입력을 받는다.
            input = scanner.nextLine();

            // 커맨드에 대한 분기를 먼저 처리한다.
            switch (input) {

                // 입력값이 off인 경우, 프로그램을 종료한다.
                case "off":
                    System.exit(0);

                    // 입력값이 ac인 경우, 좌항, 연산자, 우항, hasDouble을 초기화한다.
                case "ac":
                    left = "";
                    operator = "";
                    right = "";
                    hasDouble = false;
                    continue;

                    // 입력값이 =인 경우, isReady를 true로 할당하고 다음 반복으로 넘어가 연산 결과가 출력될 수 있도록 한다.
                case "=":
                    isReady = true;
                    continue;
            }

            // 좌항, 우항 중 하나라도 실수라면 hasDouble에 true가 할당된다.
            hasDouble = hasDouble || Validator.checkIsDouble(input);

            // 숫자인 경우
            if (Character.isDigit(input.charAt(0)) || input.charAt(0) == '.') {

                // 0을 생략하고 소수를 입력한 경우, 0을 붙여준다.
                if (input.indexOf(".") == 0) {
                    input = "0" + input;
                }

                // 올바른 형식의 숫자인지 검사한다.
                if (!Validator.checkIsValidNumber(input)) {
                    System.out.println("올바른 피연산자를 입력하세요");
                    continue;
                }

                // 연산자 입력 전이라면 좌항에 입력값을 할당하고, 연산자가 입력되었다면 우항에 입력값을 할당한다.
                if (operator.length() == 0) left = input;
                else right = input;
            }

            // 숫자가 아닌 경우 -> 연산자인 경우
            else {

                // 올바른 연산자인지 검사하고, 올바른 연산자라면 operator에 할당한다.
                if (!Validator.checkIsValidOperator(input)) {
                    System.out.println("올바른 연산자를 입력해주세요.");
                }
                else operator = input;
            }
        }
    }

    // 화면을 출력한다.
    private static void printDisplay(String result) {

        System.out.println("-".repeat(23)); // "-"를 23번 반복한 문자열을 생성하여 출력한다.
        // result가 빈 문자열인 경우, 즉 연산이 이루어지지 않은 경우 0을 출력하고, 그렇지 않은 경우 result를 그대로 출력한다.
        System.out.printf("| %19s |\n", result.equals("") ? "0" : result);
        System.out.println("-".repeat(23));
        System.out.println("|   +      -      *   |");
        System.out.println("|   /      %     ac   |");
        System.out.println("|   1      2      3   |");
        System.out.println("|   4      5      6   |");
        System.out.println("|   7      8      9   |");
        System.out.println("|   0      .      =   |");
        System.out.println("-".repeat(23));
        System.out.println("종료 : off");
    }

    // 좌항, 연산자, 우항 및 좌우항 중 실수가 존재하는지의 여부를 입력받아 적절한 연산 메서드를 호출한다.
    private static String calculate(String left, String right, String operator, boolean hasDouble) {

        /*
         * return 좌우항 중 실수가 하나라도 있다면 ?
         *          좌우항 모두를 실수로 변환하여 연산을 수행하고, 문자열로 만들어라 :
         *          좌우항 모두를 정수로 변환하여 연산을 수행하고, 문자열로 만들어라;
         * */

        return hasDouble ?
                calculate(Double.parseDouble(left), Double.parseDouble(right), operator) + "" :
                calculate(Integer.parseInt(left), Integer.parseInt(right), operator) + "";
    }

    // 좌우항 모두 정수일 때 호출되어, operator에 해당하는 연산을 수행하여 리턴한다.
    private static int calculate(int left, int right, String operator) {
        switch (operator) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/": return left / right;
            case "%": return left % right;
            default: return -1;
        }
    }

    // 좌우항 모두 실수일 때 호출되어, operator에 해당하는 연산을 수행하여 리턴한다.
    private static double calculate(double left, double right, String operator) {
        switch (operator) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/": return left / right;
            case "%": return left % right;
            default: return -1;
        }
    }
}
