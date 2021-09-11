class YoMain{
  public static void main(String[] args) {

    Lege lege1=new Lege("erik");
    Lege lege2=new Lege("jon");

    Vanlig vanlig=new Vanlig("paracet", 40,12);
    Narkotisk narkotisk=new Narkotisk("heroin", 40,12,2);

    Pasient pas=new Pasient("salam", "30040078157");

    MilitaerResept mr=new MilitaerResept(narkotisk,lege1,pas,3);



    BlaaResept blaaResept=new BlaaResept(vanlig, lege1, pas, 3);
    BlaaResept blaaResept2=new BlaaResept(vanlig, lege2, pas, 3);

    pas.leggTilResept(blaaResept);
    pas.leggTilResept(blaaResept2);


    System.out.println(mr);

  }


}
