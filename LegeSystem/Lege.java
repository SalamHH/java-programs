class Lege implements Comparable<Lege>{
  String navn;
  static int globalId=1;
  int id;
  Lenkeliste<Resept> utskrevedeResepter=new Lenkeliste<>();
  int antNarkotiskeResepter;

  public Lege(String n){
    navn=n;
    id=globalId;
    globalId++;

  }

  public int hentantNarkRes(){
    return antNarkotiskeResepter;
  }

  public int hentID(){
    return id;
  }

  public String hentNavn(){
    return navn;
  }

  public Lenkeliste<Resept> hentReseptListe(){
    /*for (Resept r : utskrevedeResepter ) {
      System.out.println(r);
    }*/
    return utskrevedeResepter;
  }


  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
   // hvis legemiddel er narkotisk
   if((legemiddel instanceof Narkotisk)){
     throw new UlovligUtskrift(this, legemiddel);
   }else{ // ellers lages ny resept objekt og returneres
     HvitResept res = new HvitResept(legemiddel,this,pasient,reit);
     utskrevedeResepter.leggTil(res);
     pasient.leggTilResept(res);
     return res;
   }
 }

   public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
     // hvis legemiddel er narkotisk
     if((legemiddel instanceof Narkotisk)){
       throw new UlovligUtskrift(this, legemiddel);
     }else{ // ellers lages ny resept objekt og returneres
       MilitaerResept res = new MilitaerResept(legemiddel,this,pasient,reit);
       utskrevedeResepter.leggTil(res);
       pasient.leggTilResept(res);

       return res;
     }

  }

  public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
    // hvis legemiddel er narkotisk
    if((legemiddel instanceof Narkotisk)){
      throw new UlovligUtskrift(this, legemiddel);
    }else{ // ellers lages ny resept objekt og returneres
      PResept res = new PResept(legemiddel,this,pasient);
      utskrevedeResepter.leggTil(res);
      pasient.leggTilResept(res);

      return res;
    }

 }

 public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
   // hvis legemiddel er narkotisk
   if((legemiddel instanceof Narkotisk)){
     throw new UlovligUtskrift(this, legemiddel);
   }else{ // ellers lages ny resept objekt og returneres
    BlaaResept res = new BlaaResept(legemiddel,this,pasient,reit);
     utskrevedeResepter.leggTil(res);
     pasient.leggTilResept(res);

     return res;
   }

}





  public int compareTo(Lege l){
    int compareInt=this.navn.compareTo(l.navn);
    if (compareInt<0) return -1;
    if (compareInt>0) return 1;
    return 0;
  }






  @Override
  public String toString(){
    return "id: "+ id+ " navn: " + navn;
  }


}
