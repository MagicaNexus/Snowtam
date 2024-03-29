package Models.SnowtamModels;

import java.util.ArrayList;

public class SnowtamDecode {

    String SnowtamCode;

    ArrayList<String> listLettres=new ArrayList<String>();


    public String getLastUpdate(String Snowtam){
        String lastUpdate="";

        if(Snowtam.contains("CREATED:")){
            String[] temp=Snowtam.split("CREATED:");
            temp=SupEnter(temp[1]).split("SOURCE");
            lastUpdate=temp[0];
        }


        return lastUpdate;
    }

    public String DecodeSnowtam(String SnowtamCode, String nameAirport){
        SnowtamCode=SupEnter(SnowtamCode);

        if(!SnowtamCode.contains("Pas de snowtam disponible pour cet aéroport.")&&SnowtamCode.contains("A)")&&!SnowtamCode.contains("No snowtam for this airport.")) {

            String SnowtamDecode = "";
            for (int i = 0; i < 20; i++) {
                listLettres.add("");
            }

            int IndiceDerniereLettre = 0;

            String s = SnowtamCode;

            String[] split = null;
            if(s.contains("A)")) {
                split = s.split("A\\)");
                //String poubelle = split[0];
                listLettres.set(IndiceDerniereLettre, split[1]);
                IndiceDerniereLettre = 0;
            }
            //si B)
            if (split[1].contains("B)")) {
                split = split[1].split("B\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 1;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            //si C)
            if (split[1].contains("C)")) {
                split = split[1].split("C\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 2;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            //...
            if (split[1].contains("D)")) {
                split = split[1].split("D\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 3;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("E)")) {
                split = split[1].split("E\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 4;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("F)")) {
                split = split[1].split("F\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 5;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("G)")) {
                split = split[1].split("G\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 6;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("H)")) {
                split = split[1].split("H\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 7;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("I)")) {
                split = split[1].split("I\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 8;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("J)")) {
                split = split[1].split("J\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 9;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("K)")) {
                split = split[1].split("K\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 10;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("L)")) {
                split = split[1].split("L\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 11;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("M)")) {
                split = split[1].split("M\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 12;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("N)")) {
                split = split[1].split("N\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 13;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("O)")) {
                split = split[1].split("O\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 14;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("P)")) {
                split = split[1].split("P\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 15;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("Q)")) {
                split = split[1].split("Q\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 16;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("R)")) {
                split = split[1].split("R\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 17;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("S)")) {
                split = split[1].split("S\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 18;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            if (split[1].contains("T)")) {
                split = split[1].split("T\\)");
                listLettres.set(IndiceDerniereLettre, split[0]);
                IndiceDerniereLettre = 19;
                listLettres.set(IndiceDerniereLettre, split[1]);
            }
            else {
                this.DecodeSnowtam(split[1],nameAirport);
            }


            //on decode le snowtam pour chaque lettre une a une
            String temp = "";


            //On decode A
            if (listLettres.get(0) != "") {
                SnowtamDecode += "A) Airport city : " + nameAirport + " - " + listLettres.get(0);
                SnowtamDecode += "\n";
            }
            //On decode B
            if (listLettres.get(1) != "") {
                temp = SupEspace(listLettres.get(1));
                String day = temp.substring(2, 4);
                SnowtamDecode += "B) Last update : " + day + " ";
                String month = temp.substring(0, 2);
                month = getMonth(Integer.valueOf(month));
                SnowtamDecode += month + " at ";
                String hour = temp.substring(4, 6);
                String minutes = temp.substring(6, 8);
                SnowtamDecode += hour + "H" + minutes + " UTC";
                SnowtamDecode += "\n";
            }

            //On decode C
            if (listLettres.get(2) != "") {
                SnowtamDecode += "C) Runway : " + listLettres.get(2);
                SnowtamDecode += "\n";
            }

            //On decode D
            if (listLettres.get(3) != "") {
                SnowtamDecode += "D) Cleared runway lenght  " + listLettres.get(3) + "M";
                SnowtamDecode += "\n";
            }

            //On decode E
            if (listLettres.get(4) != "") {
                SnowtamDecode += "E) Cleared runway width " + listLettres.get(4) + "M";
                if (listLettres.get(4).contains("L")) {
                    SnowtamDecode += " left";
                } else if (listLettres.get(4).contains("R")) {
                    SnowtamDecode += " right";
                }
                SnowtamDecode += "\n";
            }

            //On decode F
            if (listLettres.get(5) != "") {
                String[] conds = listLettres.get(5).split("/");
                SnowtamDecode += "F) Threshold: ";
                SnowtamDecode += getConditions(conds[0]);
                SnowtamDecode += " / Mid runway: ";
                SnowtamDecode += getConditions(conds[1]);
                SnowtamDecode += " / Roll out: ";
                SnowtamDecode += getConditions(conds[2]);
                SnowtamDecode += "\n";
            }

            //On decode G
            if (listLettres.get(6) != "") {
                String[] conds = SupEspace(listLettres.get(6)).split("/");
                SnowtamDecode += "G) Threshold: ";
                SnowtamDecode += conds[0];
                SnowtamDecode += "mm / Mid runway: ";
                SnowtamDecode += conds[1];
                SnowtamDecode += "mm / Roll out: ";
                SnowtamDecode += conds[2] + "mm";
                SnowtamDecode += "\n";
            }

            //On decode H
            if (listLettres.get(7) != "") {
                String[] conds = listLettres.get(7).split("/");
                SnowtamDecode += "H) Braking action Threshold: ";
                SnowtamDecode += getCoefFrottement(conds[0]);
                SnowtamDecode += " / Mid runway: ";
                SnowtamDecode += getCoefFrottement(conds[1]);
                if (conds[2].contains(" ")&&!conds[2].contains(")")) {
                    String[] x = conds[2].split(" ");
                    SnowtamDecode += " / Roll out: ";
                    SnowtamDecode += getCoefFrottement(x[0]);
                    SnowtamDecode += "\nInstrument: " + x[1];
                }
                else if(conds[2].length()>0){
                    SnowtamDecode += " / Roll out: ";
                    SnowtamDecode += getCoefFrottement(conds[2].substring(0,1));
                }
                SnowtamDecode += "\n";
            }

            //On decode J
            if (listLettres.get(9) != "") {
                String[] conds = listLettres.get(9).split("/");
                SnowtamDecode += "J) Critical Snow Bank : ";
                SnowtamDecode += conds[0] + "cm / ";
                if (conds[1].contains("L") && !conds[1].contains("LR")) {
                    conds = conds[1].split("L");
                    SnowtamDecode += conds[0] + "m left of Runway";
                } else if (conds[1].contains("R") && !conds[1].contains("LR")) {
                    conds = conds[1].split("R");
                    SnowtamDecode += conds[0] + "m right of Runway";
                } else if (conds[1].contains("LR")) {
                    conds = conds[1].split("LR");
                    SnowtamDecode += conds[0] + "m right and left of Runway";
                }
                SnowtamDecode += "\n";
            }

            //On decode K
            if (listLettres.get(10) != "") {
                if (listLettres.get(10).contains("YES")) {
                    String[] conds = listLettres.get(10).split("S ");
                    SnowtamDecode += "K) Lights obscured: YES ";
                    if (conds[1].contains("L") && !conds[1].contains("LR")) {
                        SnowtamDecode += "left of Runway";
                    } else if (conds[1].contains("R") && !conds[1].contains("LR")) {
                        SnowtamDecode += "left of Runway";
                    } else if (conds[1].contains("LR")) {
                        SnowtamDecode += "Right and left of Runway";
                    }
                } else if (listLettres.get(10).contains("NO")) {
                    String[] conds = listLettres.get(10).split("O ");
                    SnowtamDecode += "K) Lights obscured: NO ";
                    if (conds[1].contains("L") && !conds[1].contains("LR")) {
                        SnowtamDecode += "LEFT of Runway";
                    } else if (conds[1].contains("R") && !conds[1].contains("LR")) {
                        SnowtamDecode += "Right of Runway";
                    } else if (conds[1].contains("LR")) {
                        SnowtamDecode += "RIGHT and LEFT of Runway";
                    }
                }
                SnowtamDecode += "\n";
            }


            //On decode L
            if (listLettres.get(11) != "") {
                if (listLettres.get(11).contains("/")) {
                    String[] longs = listLettres.get(11).split("/");
                    SnowtamDecode += "L) Further clearance " + longs[0] + "m / " + SupEspace(longs[1]) + "m";
                } else if (listLettres.get(11).contains("TOTAL")) {
                    SnowtamDecode += "L) Further clearance total";
                }
                SnowtamDecode += "\n";
            }

            //On decode M
            if (listLettres.get(12) != "") {
                SnowtamDecode += "M) Anticipated time of completion " + SupEspace(listLettres.get(12)) + " UTC";
                SnowtamDecode += "\n";
            }

            //On decode N
            if (listLettres.get(13) != "") {
                //A implementer
                SnowtamDecode += "N) "+listLettres.get(13);
                SnowtamDecode += "\n";
            }

            //On decode P
            if (listLettres.get(15) != "") {
                if (listLettres.get(15).contains("YES")) {
                    String[] conds = listLettres.get(15).split("S");
                    SnowtamDecode += "P) Snow banks : YES SPACE " + conds[1] + "m";
                }
                SnowtamDecode += "\n";
            }

            //On decode R
            if (listLettres.get(17) != "") {
                //A implementer
                SnowtamDecode += "R) "+listLettres.get(17);
                SnowtamDecode += "\n";
            }

            //On decode S
            if (listLettres.get(18) != "") {
                temp = SupEspace(listLettres.get(18));
                String day = temp.substring(2, 4);
                SnowtamDecode += "S) Next observation " + day + " ";
                String month = temp.substring(0, 2);
                month = getMonth(Integer.valueOf(month));
                SnowtamDecode += month + " at ";
                String hour = temp.substring(4, 6);
                String minutes = temp.substring(6, 8);
                SnowtamDecode += hour + "H" + minutes + "UTC";
                SnowtamDecode += "\n";
            }

            //On decode T
            if (listLettres.get(19) != "") {
                String tmp = listLettres.get(19).replaceAll("CONTAMINATION", " Contamination : ");
                tmp = tmp.replaceAll("RWY", "runway");
                tmp = tmp.replaceAll("OBSERVATION", "\nobservation : ");
                tmp = tmp.replaceAll("\\.", ".\n");
                tmp = tmp.replaceAll("100", "51–100%");
                tmp = tmp.replaceAll("50", "26–50%");
                tmp = tmp.replaceAll("25", "11–25%");
                // tmp=tmp.replaceAll("10","less than 10%");
                tmp = tmp.replaceAll("PERCENT", "");
                SnowtamDecode += "T)" + tmp;
                SnowtamDecode += "\n";
            }

            String[] str=SnowtamDecode.split("CREATED");
            SnowtamDecode=str[0];
            return SnowtamDecode;
        }
        else{
            return SnowtamCode;
        }

    }

    private String getConditions(String nbr) {
        String cond="";
        nbr=SupEspace(nbr);
        if(!nbr.contains("NIL")) {
            int nb=Integer.valueOf(nbr);
            switch (nb) {
                case 0:
                    cond = "CLEAR AND DRY";
                    break;
                case 1:
                    cond = "DAMP";
                    break;
                case 2:
                    cond = "WET or WATER PATCHES";
                    break;
                case 3:
                    cond = "RIME OR FROST COVERED";
                    break;
                case 4:
                    cond = "DRY SNOW";
                    break;
                case 5:
                    cond = "WET SNOW";
                    break;
                case 6:
                    cond = "SLUSH";
                    break;
                case 7:
                    cond = "ICE";
                    break;
                case 8:
                    cond = "COMPACTED OR ROLLED SNOW";
                    break;
                case 9:
                    cond = "FROZEN RUTS OR RIDGES";
                    break;
            }
        }
        else{
            cond=nbr;
        }


        return cond;
    }


    private String getCoefFrottement(String nbr) {
        String coef="";
        nbr=SupEspace(nbr);
        if(!nbr.contains("NIL")) {
            int nb=Integer.valueOf(nbr);
            if(nb>40 || nb==5){
                coef="GOOD";
            }
            else if((nb>=36 && nb<=39) || nb==4){
                coef="MEDIUM TO GOOD";
            }
            else if((nb>=30 && nb<=35) || nb==3){
                coef="MEDIUM";
            }
            else if((nb>=26 && nb<=29) || nb==2){
                coef="MEDIUM TO POOR";
            }
            else if(nb<=25 || nb==1){
                coef="POOR";
            }
            else{
                coef=nbr;
            }
        }
        else{
            coef=nbr;
        }


        return coef;
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

    String  SupEspace(String string){
        if(string.contains(" ")) {
            string=string.replace(" ", "");
        }
        return string;
    }

    String getMonth(int nb){
        String m="ND";
        switch(nb) {
            case 1:
                m="JANUARY";
                break;
            case 2:
                m="FEBRUARY";
                break;
            case 3:
                m="MARCH";
                break;
            case 4:
                m="APRIL";
                break;
            case 5:
                m="MAY";
                break;
            case 6:
                m="JUNE";
                break;
            case 7:
                m="JULY";
                break;
            case 8:
                m="AUGUST";
                break;
            case 9:
                m="SEPTEMBER";
                break;
            case 10:
                m="OCTOBER";
                break;
            case 11:
                m="NOVEMBER";
                break;
            case 12:
                m="DECEMBER";
                break;

        }
        return m;
    }
}
