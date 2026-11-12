package com.techflow.ws.sys.domain.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleUtil {
    public static String mercosulChars="ABCDEFGHIJ";
    public static String mercosulRegex="^[A-Z]{3}[0-9]{1}[A-J0-9]{1}[0-9]{2}$";

    public static Boolean isMercosulPlate(String plate) {
        if(plate == null) return false;
        Pattern pattern = Pattern.compile(mercosulRegex);
        Matcher matcher = pattern.matcher(plate.trim().toUpperCase());
        return matcher.matches();
    }
    
    public static List<String> validPlates(List<String> plateList) {
        List<String> plates = new ArrayList<>();

        if(plateList != null && plateList.size() > 0)  {
            for (String plate : plateList) {
                plate = plate.trim().toUpperCase();
                if(plate.length() == 7) {
                    if(!plates.contains(plate))
                        plates.add(plate);
                }
            }
        }
        return plates;
    }

    public static String mercosulPlateString(String plate) {
        try {
            List<String> plates = mercosulPlate(plate);
            StringBuilder sb = new StringBuilder();
            String sep = "";
            for(String p : plates) {
                sb.append(sep).append("'").append(p).append("'");
                sep = ", ";
            }
            return sb.toString();
        }
        catch(Exception ex) {
            return "";
        }        
    }

    public static List<String> mercosulPlate(String plate) {
        List<String> plates = new ArrayList<>();
        plate = plate.trim().toUpperCase();
        if(plate.length() == 7) {
            plates.add(plate);
            char mercosulDigit = plate.charAt(4);
            if(Character.isDigit(mercosulDigit)) {
                char mercosulChar = (char) (mercosulDigit - '0' + 'A');
                plates.add(plate.substring(0, 4) + mercosulChar + plate.substring(5));
            }
            else {
                char mercosulChar = (char) (mercosulDigit - 'A' + '0');
                plates.add(plate.substring(0,4) + mercosulChar + plate.substring(5));
            }
        }
        return plates;
    }
}
