public class Code {
    private String code;

    protected String getCode() {
        return code;
    }

    protected void setCode(String code) {
        this.code = code;
    }

    protected char[] codeArray(String code) {
        char[] codeArray = new char[code.length()];
        for (int i = 0; i < code.length(); i++) {
            codeArray[i] = code.charAt(i);
        }
        return codeArray;
    }
}
