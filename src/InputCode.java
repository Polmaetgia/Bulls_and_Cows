import java.util.Scanner;

public class InputCode extends Code{
    public String enterInputCode(String secretCode) {
        Scanner scanner = new Scanner(System.in);
        String code = scanner.next();
        while (true) {
            if (code.length() != secretCode.length()) {
                System.out.println("Code length is invalid!");
                code = scanner.next();
            } else {
                break;
            }
        }
        return code;
    }

}
