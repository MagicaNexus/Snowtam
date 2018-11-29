package com.example.ensim.snowtam;

import android.util.Log;

public class SnowtamDecode {

    String SnowtamCode;
    //String SnowtamDecode="";



    public String DecodeSnowtam(String SnowtamCode, String nameAirport){
        //this.SnowtamCode=SnowtamCode;
        //this.SnowtamCode="(SNOWTAM 0402\\nA) ENBO\\nB) 112";
        String SnowtamDecode="";
        String temp="";


        SnowtamCode=SupEnter(SnowtamCode);


        if(SnowtamCode.contains("A)")){
            SnowtamDecode+=SnowtamCode.substring(SnowtamCode.indexOf("A) "),SnowtamCode.indexOf("B) "))+" - "+nameAirport;
            SnowtamDecode+="\n";
        }

        if(SnowtamCode.contains("B)")){
            temp=SnowtamCode.substring(SnowtamCode.indexOf("B) "),SnowtamCode.indexOf("B) ")+11);
            String day=temp.substring(5,7);
            SnowtamDecode+="B) "+day+" ";
            String month=temp.substring(3,5);
            switch(Integer.valueOf(month)) {
                case 1:
                    month="JANUARY";
                    break;
                case 2:
                    month="FEBRUARY";
                    break;
                case 3:
                    month="MARCH";
                    break;
                case 4:
                    month="APRIL";
                    break;
                case 5:
                    month="MAY";
                    break;
                case 6:
                    month="JUNE";
                    break;
                case 7:
                    month="JULY";
                    break;
                case 8:
                    month="AUGUST";
                    break;
                case 9:
                    month="SEPTEMBER";
                    break;
                case 10:
                    month="OCTOBER";
                    break;
                case 11:
                    month="NOVEMBER";
                    break;
                case 12:
                    month="DECEMBER";
                    break;

            }
            SnowtamDecode+=month+" AT ";
            String hour=temp.substring(7,9);
            String minutes=temp.substring(9,11);
            SnowtamDecode+=hour+"h"+minutes+"UTC";
            SnowtamDecode+="\n";
        }

        if(SnowtamCode.contains("C)")){
            SnowtamDecode+="C) RUNWAY "+SnowtamCode.substring(SnowtamCode.indexOf("C) ")+3,SnowtamCode.indexOf("C) ")+5);
            SnowtamDecode+="\n";
        }

        if(SnowtamCode.contains("D)")){
            SnowtamDecode+="D) CLEARED RUNWAY LENGTH  "+SnowtamCode.substring(SnowtamCode.indexOf("D) ")+3,SnowtamCode.indexOf("D) ")+6);
            SnowtamDecode+="\n";
        }


        Log.d("Snowtam Decode",SnowtamDecode);


        return SnowtamDecode;
    }



    String  SupEnter(String string){
        if(string.contains("\\n")) {
            string=string.replace("\\n", "");
        }
        if(string.contains("\n")) {
            string=string.replace("\n", "");
        }
        return string;
    }
}
