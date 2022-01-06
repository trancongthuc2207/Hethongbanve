package config;




import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class HashMK {
    public static String hashMK(String mk){
        String maHash = "";
        char[] chuoi = mk.toCharArray();
        Map<Character, Integer> myMap = new HashMap<>();
        
        //Key La ki tu
        //value La so lan xuat hien cua ki tu do 
        for(char i : chuoi){
            if(myMap.containsKey(i) == false){
                //Ki tu chua xuat hien 1 lan nao
                myMap.put(i, 1);
            }
            else{
                //xuat hien va tang bien value
                int soLanXuatHien = myMap.get(i);
                soLanXuatHien++;
                myMap.put(i,soLanXuatHien);
            }
        }
        for(Map.Entry entry : myMap.entrySet()){
            maHash += entry.getKey() + String.valueOf(entry.getValue());
            if(maHash.charAt(maHash.length() - 1) == '1'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '!');
                maHash += "&*#";
            }
            if(maHash.charAt(maHash.length() - 1) == '2'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '&');
                maHash += "@#$";
            }
            if(maHash.charAt(maHash.length() - 1) == '3'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '@');
                maHash += "$@@";
            }
            if(maHash.charAt(maHash.length() - 1) == '4'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '(');
                maHash += "@$";
            }
            if(maHash.charAt(maHash.length() - 1) == '5'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), ')');
                maHash += "($@";
            }
            if(maHash.charAt(maHash.length() - 1) == '6'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '^');
                maHash += "#$*";
            }
            if(maHash.charAt(maHash.length() - 1) == '7'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '#');
                maHash += "!#*";
            }
        }
        return maHash;
    }
}
