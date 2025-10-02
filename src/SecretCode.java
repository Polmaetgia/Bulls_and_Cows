import java.util.ArrayList;
import java.util.Random;

public class SecretCode extends Code {

    public String combination() {
        StringBuilder alphabetAndNumbers = new StringBuilder(36);
        for (char c = '0'; c <= '9'; c++) {
            alphabetAndNumbers.append(c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            alphabetAndNumbers.append(c);
        }
        return alphabetAndNumbers.toString();
    }

    public String combination(int countOfSymbols) {
        String alphabetAndNumbers = combination();
        StringBuilder combination = new StringBuilder(countOfSymbols);
        for (int i = 0; i < alphabetAndNumbers.length(); i++) {
            if (i < countOfSymbols) {
                combination.append(alphabetAndNumbers.charAt(i));
            } else {
                break;
            }
        }
        return combination.toString();
    }

    protected String combination(int length, int countOfSymbols) {
        Random random = new Random();
        ArrayList<Character> secretCode = new ArrayList<>();
        StringBuilder sb = new StringBuilder(length);
        if (countOfSymbols > length) {
            String combination = combination(countOfSymbols);
            while (secretCode.size() < length) {
                char randomNumber = combination.charAt(random.nextInt(combination.length()));
                if (!secretCode.contains(randomNumber)) {
                    secretCode.add(randomNumber);
                }
            }
            for (char number : secretCode) {
                sb.append(number);
            }
        }
        return sb.toString();
    }

}
