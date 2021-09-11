import java.util.*;
import java.io.*;



class Legesystem{


    Lenkeliste<Pasient> pasientliste=new Lenkeliste<>();
    Lenkeliste<Legemiddel> legemiddelliste=new Lenkeliste<>();
    SortertLenkeliste<Lege> legeliste=new SortertLenkeliste<>();
    Lenkeliste<Resept> reseptliste=new Lenkeliste<>();






    private ArrayList<String> linjer = new ArrayList<>();




  public void lesFraFil(String s) throws UlovligUtskrift {
    Scanner in=null;



    try {
      in=new Scanner(new File(s));
    }
    catch(FileNotFoundException e) {
      System.out.println("file not found");
    }


    while(in.hasNextLine()){
      linjer.add(in.nextLine());
   }
   int teller=1;

   for (int i=1;i<linjer.size() ;i++ ) {
     if (linjer.get(i).contains("# Legemidler")) {
       break;
     }
     String[] linjeSplit=linjer.get(i).split(",");
     String navn=linjeSplit[0];
     String fodselnr=linjeSplit[1];
     Pasient p=new Pasient(navn,fodselnr);
     pasientliste.leggTil(p);

     teller++;
   }

   for (int i=teller+1; i<linjer.size() ;i++ ) {
     if (linjer.get(i).contains("# Leger")){
       break;
     }
     String[] linjeSplit=linjer.get(i).split(",");
     if (linjeSplit.length==5) {
       String navn=linjeSplit[0];
       String type=linjeSplit[1];
    //   double pris=Double.parseDouble(linjeSplit[2]);
    //   double virkestoff=Double.parseDouble(linjeSplit[3]);
    //   int styrke=Integer.parseInt(linjeSplit[4]);
    // under er new shiz
      /* double pris=0;
       double virkestoff=0;
       int styrke=0;
       try {
         pris=Double.parseDouble(linjeSplit[2]);
       } catch(NumberFormatException nfe) {
         System.out.println("argument må være tall");
         return;
       }
       try {
         virkestoff=Double.parseDouble(linjeSplit[3]);
       } catch(NumberFormatException nfe) {
         System.out.println("argument må være tall");
         return;
       }
       try {
         styrke=Integer.parseInt(linjeSplit[4]);
       } catch(NumberFormatException nfe) {
         System.out.println("argument må være tall");
         return;
       }*/



       if (type.equals("narkotisk")) {
         double pris=Double.parseDouble(linjeSplit[2]);
         double virkestoff=Double.parseDouble(linjeSplit[3]);
         int styrke=Integer.parseInt(linjeSplit[4]);
         Narkotisk n=new Narkotisk(navn,pris,virkestoff,styrke);
         legemiddelliste.leggTil(n);
       }
       else if (type.equals("vanedannende")) {
         double pris=Double.parseDouble(linjeSplit[2]);
         double virkestoff=Double.parseDouble(linjeSplit[3]);
         int styrke=Integer.parseInt(linjeSplit[4]);
         Vanedannende v=new Vanedannende(navn,pris,virkestoff,styrke);
         legemiddelliste.leggTil(v);
       }

     }
     else if(linjeSplit.length==4 && linjeSplit[1].equals("vanlig")){
       String navn=linjeSplit[0];
       double pris=Double.parseDouble(linjeSplit[2]);
       double virkestoff=Double.parseDouble(linjeSplit[3]);
       Vanlig van=new Vanlig(navn,pris,virkestoff);
       legemiddelliste.leggTil(van);
     }


     teller++;
   }

   for (int i=teller+2;i<linjer.size() ;i++ ) {
     if (linjer.get(i).contains("# Resepter")) {
       break;
     }
     String[] linjeSplit=linjer.get(i).split(",");
     String navn=linjeSplit[0];
     int sjekk=Integer.parseInt(linjeSplit[1]);
     if (sjekk==0) {
       Lege l=new Lege(navn);
       legeliste.leggTil(l);
     }
     else {
       Spesialist spes=new Spesialist(navn,linjeSplit[1]);
       legeliste.leggTil(spes);
     }

     teller++;




   }

   for (int i=teller+3;i<linjer.size() ;i++) {




     String[] linjeSplit=linjer.get(i).split(",");
     int lmID=Integer.parseInt(linjeSplit[0]);
     Legemiddel lm=null;
    // Legemiddel lm=legemiddelliste.get(index);
    /*for (Legemiddel l :legemiddelliste ) {
      if (l.hentID()==lmID) {
        lm=l;
      }
      else {

      }

    }*/
    for (Legemiddel l :legemiddelliste ) {
      if (lmID<134 && l.hentID()==lmID) {
        lm=l;
      }
      else {

      }
    }



    /*
    try {


      for (Legemiddel l :legemiddelliste ) {
        if (l.hentID()==lmID) {
          lm=l;
        }

      }
    } catch(NullPointerException e) {
      System.out.println("lol");
    }*/


     String legenavn=linjeSplit[1];
     Lege lege=null;
     for (Lege l : legeliste ) {
       if (l.hentNavn().equals(legenavn)) {
         lege=l;
       }
     }
     int pID=Integer.parseInt(linjeSplit[2]);

     Pasient p=null;
     for (Pasient pas : pasientliste) {
       if (pas.hentID()==pID) {
         p=pas;
       }
     }

     String type=linjeSplit[3];


     if (linjeSplit.length==5 &&lm!=null) {
       int reit=Integer.parseInt(linjeSplit[4]);
       if (type.equals("hvit")) {
         try {
           HvitResept resept=lege.skrivHvitResept(lm, p, reit);
           reseptliste.leggTil(resept);
         } catch(Exception e) {
          // System.out.println(e);
         }

       }
       else if(type.equals("blaa")) {
         try {
           BlaaResept resept=lege.skrivBlaaResept(lm, p, reit);
           reseptliste.leggTil(resept);
         } catch(Exception e) {
          // System.out.println(e);
         }

       }
       else if(type.equals("millitaer")) {
         try {
           MilitaerResept resept=lege.skrivMilitaerResept(lm, p, reit);
           reseptliste.leggTil(resept);
         } catch(Exception e) {
          // System.out.println(e);
         }
       }


     }
     else {
      if (lm!=null) {


       try {
         PResept resept=lege.skrivPResept(lm, p);
         reseptliste.leggTil(resept);
       } catch(Exception e) {
        // System.out.println(e);
       }
     }

     }

     teller++;


   }






 }







 public void kommandoLoekke() throws UlovligUtskrift{

   int input=1;



   while (input!=0) {
     System.out.println("\nVELKOMMEN TIL PROGRAMMET! \n HER ER VALGMULIGHETENE:");
     System.out.println("tast 1 for å få oversikt");
     System.out.println("tast 2 for å legg til elementer");
     System.out.println("tast 3 for å bruke en resept");
     System.out.println("tast 4 for å skrive vise statistikk");
     System.out.println("tast 0 for å avslutte");

     Scanner in=new Scanner(System.in);
     System.out.println("valg: ");
     input=in.nextInt();


     if (input==1) {
       System.out.println("tast 1 for å skrive ut pasienter");
       System.out.println("tast 2 for å skrive ut leger");
       System.out.println("tast 3 for å skrive ut legemidler");
       System.out.println("tast 4 for å skrive ut resepter");
       int i1=in.nextInt();

       if (i1==1) {
         for (Pasient p : pasientliste) {
           System.out.println(p);

         }
       }

       if (i1==2) {
         for (Lege l : legeliste) {
           System.out.println(l);

         }
       }

       if (i1==3) {
         for (Legemiddel leg : legemiddelliste) {
           System.out.println(leg);

         }
       }

       if (i1==4) {
         for (Resept r : reseptliste) {
           System.out.println(r);

         }
       }




     }


     if (input==2) {
       System.out.println("tast 1 for å legge til en lege");
       System.out.println("tast 2 for å legge til en pasient");
       System.out.println("tast 3 for å legge til et legemiddel");
       System.out.println("tast 4 for å legge til en resept");
       int i2=in.nextInt();

       //legge til lege
       if (i2==1) {
         System.out.println("tast 1 for å legge til en vanlig lege");
         System.out.println("tast 2 for å legge til en Spesialist");
         int i22=in.nextInt();
         if (i22==1) {
           System.out.println("angi navn");
           String navn=in.next();
           Lege lege=new Lege(navn);
           legeliste.leggTil(lege);

         }
         else if (i22==2) {
           System.out.println("angi navn");
           String navn=in.next();
           System.out.println("angi kontrollID");
           String kid=in.next();
           Spesialist spes=new Spesialist(navn,kid);
           legeliste.leggTil(spes);
         }




       }


       //legge til pasient
       if (i2==2) {
         System.out.println("angi navn");
         String navn=in.next();
         System.out.println("angi fødselsnr");
         String fodselnr=in.next();
         Pasient pas=new Pasient(navn,fodselnr);
         pasientliste.leggTil(pas);
       }

       //legge til legemiddel
       if (i2==3) {
         System.out.println("tast 1 for å legge til et vanlig legemiddel");
         System.out.println("tast 2 for å legge til et vanedannende legemiddel");
         System.out.println("tast 3 for å legge til et narkotisk legemiddel");
         int i23=in.nextInt();
         if (i23==1) {
           System.out.println("angi navn");
           String navn=in.next();
           System.out.println("angi pris");
           double pris=in.nextDouble();
           System.out.println("angi virkestoff");
           double virkestoff=in.nextDouble();
           Vanlig vanlig=new Vanlig(navn,pris,virkestoff);
           legemiddelliste.leggTil(vanlig);
         }
         else if (i23==2) {
           System.out.println("angi navn");
           String navn=in.next();
           System.out.println("angi pris");
           double pris=in.nextDouble();
           System.out.println("angi virkestoff");
           double virkestoff=in.nextDouble();
           System.out.println("angi styrke");
           int styrke=in.nextInt();
           Vanedannende vane=new Vanedannende(navn,pris,virkestoff,styrke);
           legemiddelliste.leggTil(vane);
         }
         else if (i23==3) {
           System.out.println("angi navn");
           String navn=in.next();
           System.out.println("angi pris");
           double pris=in.nextDouble();
           System.out.println("angi virkestoff");
           double virkestoff=in.nextDouble();
           System.out.println("angi styrke");
           int styrke=in.nextInt();
           Narkotisk nar=new Narkotisk(navn,pris,virkestoff,styrke);
           legemiddelliste.leggTil(nar);
         }




       }
       //legger til resepter
       if (i2==4) {
         System.out.println("tast 1 for å legge til Hvit Resept");
         System.out.println("tast 2 for å legge til militaer Resept");
         System.out.println("tast 3 for å legge til P Resept");
         System.out.println("tast 4 for å legge til Blaa Resept");
         int i24=in.nextInt();
         if (i24==1) {
           for (Legemiddel l :legemiddelliste ) {
             System.out.println(l);
           }
           System.out.println("angi ID til legemiddel ");

            int lmID=in.nextInt();
            Legemiddel lm=null;

           for (Legemiddel l :legemiddelliste ) {
             if (l.hentID()==lmID) {
               lm=l;
             }
           }

           // velge leger (usikker på om dette er optimal løsning.. men det FUNKER)
           if (lm instanceof Narkotisk) {
             for (Lege le :legeliste ) {
               if (le instanceof Spesialist) {
                 System.out.println(le);
               }
              }
           }
           else {
             for (Lege l :legeliste ) {
               System.out.println(l);
             }
           }


           System.out.println("angi ID til lege ");

            int leInt=in.nextInt();
            Lege lege=null;

           for (Lege l :legeliste ) {
             if (l.hentID()==leInt) {
               lege=l;
             }
           }


           //velge pasienter

           for (Pasient p  :pasientliste ) {
             System.out.println(p);
           }
           System.out.println("angi ID til pasient");
           int pid=in.nextInt();
           Pasient pasient=null;

           for (Pasient pa :pasientliste ) {
             if (pa.hentID()==pid) {
               pasient=pa;
             }
           }

           //reit
           System.out.println("angi reit");
           int reit=in.nextInt();
           Resept res=lege.skrivHvitResept(lm, pasient,reit);
           reseptliste.leggTil(res);

         }

         if (i24==2) {
           for (Legemiddel l :legemiddelliste ) {
             System.out.println(l);
           }
           System.out.println("angi ID til legemiddel ");

            int lmID=in.nextInt();
            Legemiddel lm=null;

           for (Legemiddel l :legemiddelliste ) {
             if (l.hentID()==lmID) {
               lm=l;
             }
           }

           // velge leger (usikker på om dette er optimal løsning.. men det FUNKER)
           if (lm instanceof Narkotisk) {
             for (Lege le :legeliste ) {
               if (le instanceof Spesialist) {
                 System.out.println(le);
               }
              }
           }
           else {
             for (Lege l :legeliste ) {
               System.out.println(l);
             }
           }


           System.out.println("angi ID til lege ");

            int leInt=in.nextInt();
            Lege lege=null;

           for (Lege l :legeliste ) {
             if (l.hentID()==leInt) {
               lege=l;
             }
           }


           //velge pasienter

           for (Pasient p  :pasientliste ) {
             System.out.println(p);
           }
           System.out.println("angi ID til pasient");
           int pid=in.nextInt();
           Pasient pasient=null;

           for (Pasient pa :pasientliste ) {
             if (pa.hentID()==pid) {
               pasient=pa;
             }
           }

           //reit
           System.out.println("angi reit");
           int reit=in.nextInt();
           Resept res=lege.skrivMilitaerResept(lm, pasient,reit);
           reseptliste.leggTil(res);

         }


         if (i24==3) {
           for (Legemiddel l :legemiddelliste ) {
             System.out.println(l);
           }
           System.out.println("angi ID til legemiddel ");

            int lmID=in.nextInt();
            Legemiddel lm=null;

           for (Legemiddel l :legemiddelliste ) {
             if (l.hentID()==lmID) {
               lm=l;
             }
           }

           // velge leger (usikker på om dette er optimal løsning.. men det FUNKER)
           if (lm instanceof Narkotisk) {
             for (Lege le :legeliste ) {
               if (le instanceof Spesialist) {
                 System.out.println(le);
               }
              }
           }
           else {
             for (Lege l :legeliste ) {
               System.out.println(l);
             }
           }


           System.out.println("angi ID til lege ");

            int leInt=in.nextInt();
            Lege lege=null;

           for (Lege l :legeliste ) {
             if (l.hentID()==leInt) {
               lege=l;
             }
           }


           //velge pasienter

           for (Pasient p  :pasientliste ) {
             System.out.println(p);
           }
           System.out.println("angi ID til pasient");
           int pid=in.nextInt();
           Pasient pasient=null;

           for (Pasient pa :pasientliste ) {
             if (pa.hentID()==pid) {
               pasient=pa;
             }
           }



           Resept res=lege.skrivPResept(lm, pasient);
           reseptliste.leggTil(res);

         }


         if (i24==4) {
           for (Legemiddel l :legemiddelliste ) {
             System.out.println(l);
           }
           System.out.println("angi ID til legemiddel ");

            int lmID=in.nextInt();
            Legemiddel lm=null;

           for (Legemiddel l :legemiddelliste ) {
             if (l.hentID()==lmID) {
               lm=l;
             }
           }

           // velge leger (usikker på om dette er optimal løsning.. men det FUNKER)
           if (lm instanceof Narkotisk) {
             for (Lege le :legeliste ) {
               if (le instanceof Spesialist) {
                 System.out.println(le);
               }
              }
           }
           else {
             for (Lege l :legeliste ) {
               System.out.println(l);
             }
           }


           System.out.println("angi ID til lege ");

            int leInt=in.nextInt();
            Lege lege=null;

           for (Lege l :legeliste ) {
             if (l.hentID()==leInt) {
               lege=l;
             }
           }


           //velge pasienter

           for (Pasient p  :pasientliste ) {
             System.out.println(p);
           }
           System.out.println("angi ID til pasient");
           int pid=in.nextInt();
           Pasient pasient=null;

           for (Pasient pa :pasientliste ) {
             if (pa.hentID()==pid) {
               pasient=pa;
             }
           }

           //reit
           System.out.println("angi reit");
           int reit=in.nextInt();
           Resept res=lege.skrivBlaaResept(lm, pasient,reit);
           reseptliste.leggTil(res);

         }



       }





     }
     //bruke resept
     if (input==3) {
       for (Pasient p :pasientliste ) {
         System.out.println(p);
       }
       System.out.println("\nhvilken pasient vil du se resepter for? angi ID");

       int pid=in.nextInt();
       Pasient pasient=null;

       for (Pasient pa :pasientliste ) {
         if (pa.hentID()==pid) {
           pasient=pa;
         }
       }

       System.out.println("\nvalgt pasient: " + pasient+ "\n");
       pasient.skrivUtListe();
       System.out.println("hvilken resept vil du bruke? angi id");
       int rid=in.nextInt();
       Resept resept=null;

       for (Resept r : reseptliste ) {
         if (r.hentID()==rid) {
           resept=r;
         }
       }

       if (resept.hentReit()<=0) {
         System.out.println("resepten er oppbrukt");
       }
       else {
         resept.bruk();
       }

      System.out.println("brukte resept på"+ resept.hentLegemiddel()+ ". Gjenværende reit:"+ resept.hentReit());



     }


     if (input==4) {
       System.out.println("tast 1 for å vise totalt antall utskrevne resepter på vanedannende legemidler");
       System.out.println("tast 2 for å vise totalt antall utskrevne resepter på narkotiske legemidler");
       System.out.println("tast 3 for å vise statistikk om mulig misbruk av narkotika");


       int i4=in.nextInt();
       if (i4==1) {
         int teller=0;
         for (Resept r : reseptliste ) {
           if (r.hentLegemiddel() instanceof Vanedannende) {
             teller++;
           }
         }
         System.out.println("antall resepter på vanedannende legemidler: "+ teller);
       }

       if (i4==2) {
         int teller=0;
         for (Resept r : reseptliste ) {
           if (r.hentLegemiddel() instanceof Narkotisk) {
             teller++;
           }
         }
         System.out.println("antall resepter på narkotiske legemidler: "+ teller);
       }

       if (i4==3) {
         System.out.println("tast 1 for å vise leger som har skrevet ut resept på narkotiske legemidler ");
         System.out.println("tast 2 for å vise pasienter som har resept på narkotiske legemidler");
         int i41=in.nextInt();
         if (i41==1) {

/*
           for (Resept r : reseptliste) {
             if (r.hentLegemiddel() instanceof Narkotisk) {
               System.out.println(r.hentLege().hentNavn() +" har skrevet ut: "+ r.hentLege().hentantNarkRes() + " narkotiske legemidler" );
             }
           }*/
          /* for (Lege l : legeliste ) {
             if (l instanceof Spesialist) {
               for (Resept r : l.hentReseptListe() ) {
                 if (r.hentLegemiddel() instanceof Narkotisk) {
                   System.out.println(r.hentLege().hentNavn()+ " har skrevet ut: " + r.hentLege().hentantNarkRes()+ " narkotiske legemidler");
                 }
               }
             }
           }*/
           for (Lege l :legeliste ) {
             if (l instanceof Spesialist && l.hentantNarkRes()>0) {
               System.out.println(l.hentNavn()+ " har skrevet ut: "+ l.hentantNarkRes()+ " narkotiske resepter");
             }
           }

         }
         if (i41==2) {
          /* for (Resept r : reseptliste ) {
             if (r.hentLegemiddel() instanceof Narkotisk) {
               System.out.println(r.hentPasient().hentNavn() + " har "+ r.hentPasient().hentantNarkResP()+ " narkotiske resepter");
             }
           }*/

           for (Pasient p :pasientliste ) {
             if (p.hentantNarkResP()>0) {
               System.out.println(p.hentNavn()+ " har "+ p.hentantNarkResP()+ " narkotiske resepter");
             }
           }


         }
       }




     }


   }




 }







}
