package com.google.mlkit.vision.demo.java.CameraCrossReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvHandler {
    public static String csvFile="csvsample.csv";

    private static String[] getFirstColumnFromCsvFile(String csvFile){
        ArrayList<String> columnList=new ArrayList<String>();
        String delimiter = ",";
        File file=new File(csvFile);
        //System.out.println(file.getAbsoluteFile());
        try {
            Scanner sc=new Scanner(file);
            sc.useDelimiter(delimiter);

            while(sc.hasNext()){
                //System.out.println(sc.next());
                columnList.add(sc.next());
                if(sc.hasNext())sc.nextLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return columnList.toArray(new String[0]);
    }

    public int findRowContainingIntValue(String[] columnArray,String value){
        for(int i=0;i<columnArray.length;i++){
            if(columnArray[i].equals(value)){
                return i;
            }
        }
        return 0;
    }

    public static String getValueInColumn(String csvFile,int row,int column){
        String result="";
        String delimiter = ",";
        try {
            Scanner sc=new Scanner(new File(csvFile));
            sc.useDelimiter(delimiter);
            for(int i=0;i<row;i++){
                sc.nextLine();
            }
            for(int i=0;i<column;i++){
                sc.next();
            }
            result=sc.next();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args){
        System.out.println("testing");
        String[] result = getFirstColumnFromCsvFile(csvFile);
        for(String entry:result){
            //System.out.println(entry);
        }

        String value=getValueInColumn(csvFile,4,2);
        //System.out.println(value);
    }

}
