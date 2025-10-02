public class Code {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public char[] codeArray(String code) {
        char[] codeArray = new char[code.length()];
        for (int i = 0; i < code.length(); i++) {
            codeArray[i] = code.charAt(i);
        }
        return codeArray;
    }
}
