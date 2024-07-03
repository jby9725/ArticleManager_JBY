package org.koreait;

import java.util.HashMap;
import java.util.Map;

public class CommendParser {

    private String functionName;
    private String actionMethod;
    private Map<String, String> params;
    private String errMsg = "";

    @Override
    public String toString() {
        return "Cp{" +
                "actionMethod='" + actionMethod + '\'' +
                ", params=" + params +
                '}';
    }

    public CommendParser(String cmd) {

        // parsing
        String[] cmdBits = cmd.split(" ", 3);
        functionName = cmdBits[0];
        actionMethod = cmdBits[1];
        params = new HashMap<>();

        String[] paramBits;

        if (cmdBits.length == 2) { // article add / member add 와 같이 뒤에 옵션이 안 붙는다.
            return;
        }

        try { // 옵션들이 여러개일때 처리하자.
            paramBits = cmdBits[2].split(" ");

            for (String paramStr : paramBits) {
                String[] paramStrBits = paramStr.split(" ", 2);


                String key = paramStrBits[0];

                // id=@@ 와 같이 id가 잘 들어가는지. (오타가 안나는지)
                if (!key.equals("id")) {
                    System.out.println("오타 있음(id)");
                    errMsg = "오타 있음(id)";
                }
                String value = paramStrBits[1];
                params.put(key, value);

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("명령어가 제대로 입력되지 않았습니다. 확인해주세요.");
            return;
        }
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getActionMethod() {
        return actionMethod;
    }

    public String getParams(String paramName) {
        return params.get(paramName);
    }

    public String getErrMsg() {
        return errMsg;
    }

}
