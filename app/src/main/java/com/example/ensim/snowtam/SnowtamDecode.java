package com.example.ensim.snowtam;

import android.util.Log;

public class SnowtamDecode {

    String SnowtamCode;
    //String SnowtamDecode="";



    public static String DecodeSnowtam(String SnowtamCode){
        //this.SnowtamCode=SnowtamCode;
        //this.SnowtamCode="(SNOWTAM 0402\\nA) ENBO\\nB) 112";
        String SnowtamDecode="";

        SnowtamDecode+=SnowtamCode.substring(SnowtamCode.indexOf("A) "),SnowtamCode.indexOf(") ")-1);
        Log.d("Snowtam Decode",SnowtamDecode);

        SnowtamDecode+="\n";




        return SnowtamDecode;
    }
}
