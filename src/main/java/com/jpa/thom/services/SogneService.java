package com.jpa.thom.services;

import com.jpa.thom.models.Smittetryk;
import com.jpa.thom.models.Sogn;
import com.jpa.thom.repositoires.SognRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Service
public class SogneService {

    @Autowired
    SognRepository sognRepository;

    //Logikken for checked (lukket/ikke lukket) opdateres her
    public Iterable<Sogn> sognsWithCheckedValueUpdated(Iterable<Sogn> sogns){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        //I dag som Instant
        Date todayDate = java.util.Calendar.getInstance().getTime();
        Instant toDayInstant = todayDate.toInstant();

        for (Sogn s: sogns  ) {
            Instant dbInstant = s.getNedlukDato().toInstant();

            if(dbInstant.isBefore(toDayInstant) && s.isChecked()==false){
                System.out.println("1");
                s.setChecked(true);
            }
            else if (dbInstant.isBefore(toDayInstant) && s.isChecked()==true){
                System.out.println("2");
                s.setChecked(true);
            }
            else if ((dbInstant.isAfter(toDayInstant) || dbInstant.equals(toDayInstant)) && s.isChecked()==false){
                System.out.println("3");
                s.setChecked(false);
            }
            else if ((dbInstant.isAfter(toDayInstant) || dbInstant.equals(toDayInstant)) && s.isChecked()==true){
                System.out.println("4");
                s.setChecked(true);
            }
            //updater database
            sognRepository.save(s);
        }
        return sogns;
    }

    //udregner smittetryk
    public ArrayList<Smittetryk> KommuneOgSmittetryk(){
        ArrayList<Smittetryk> tempList = new ArrayList<>();
        ArrayList<Smittetryk> returList = new ArrayList<>();

        //Populere temp list med [kommuneId, kommunenavn,antalHitsPåKommuneNavn, smittetryk]
        for (Sogn sogne: sognRepository.findAll()) {
            tempList.add(new Smittetryk(sogne.getKommune().getId(),sogne.getKommune().getNavn(),0,sogne.getSmittetryk(),0));
            System.out.println("print: id " + sogne.getKommune().getId()+" Kommunenavn "+ sogne.getKommune().getNavn() + " Smittetryk " + sogne.getSmittetryk());
        }

        double tempSmitteTryk=0;
        String tempKommuneNavn="";
        int tempCount=0;
        for (int i = 0; i < tempList.size(); i++) {
            Long idKommune = tempList.get(i).getKommuneId();
            tempKommuneNavn = tempList.get(i).getKommuneNavn();
            double smitteTrykSum = tempList.get(i).getSumSmitteTryk();

            //ingen træf på kommuneNavn,
            // indsætter: kommuneId, kommuneNavn, count ++1, ++sumSmitteTryk, sumSmitteTryk/count
//            if (!returList.contains(tempKommuneNavn)){
            if (getIndexByProperty(tempKommuneNavn,returList)==-1){
                returList.add(new Smittetryk(idKommune,tempKommuneNavn,1,smitteTrykSum,smitteTrykSum/1));
            }else if(getIndexByProperty(tempKommuneNavn,returList)>-1){ //hvis træf
                //skal fange index
                int tempIndex = getIndexByProperty(tempKommuneNavn,tempList);

                //udregner count
                tempCount = returList.get(tempIndex).getCountKommuner()+1;

                //opdatere count
                returList.get(tempIndex).setCountKommuner(tempCount);

                //udregner smitteTrykSum
                tempSmitteTryk = returList.get(tempIndex).getSumSmitteTryk()+smitteTrykSum;

                //opdatere smitteTrykSum
                returList.get(tempIndex).setSumSmitteTryk(tempSmitteTryk);

                //udregner smitteTryksGennemsnit
                returList.get(tempIndex).setAvgSmitteTryk(tempSmitteTryk/tempCount);
            }else {
                System.out.println("Noget gik galt");
            }//if

            tempSmitteTryk=0;
            tempKommuneNavn="";
            tempCount=0;

        }

        return returList;
    }//metode

    //hjælpemetode, finder index hvor properties meacther
    private int getIndexByProperty(String yourString, ArrayList<Smittetryk> smittetryksList) {
        for (int i = 0; i < smittetryksList.size(); i++) {
            if (smittetryksList !=null && smittetryksList.get(i).getKommuneNavn().equals(yourString)) {
                return i;
            }
        }
        return -1;// not there is list
    }
}
