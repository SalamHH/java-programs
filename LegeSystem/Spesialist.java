class Spesialist extends Lege implements Godkjenningsfritak{
  String kontrollID;
  //static int globalId=1;
  //int id;
  int antNarkotiskeResepter;

  public Spesialist(String navn,String kID){
    super(navn);
    kontrollID=kID;
  //  id=globalId;
  //  globalId++;
  }

  public String hentKontrollID(){
    return kontrollID;
  }

  public int hentID(){
    return id;
  }

  public int hentantNarkRes(){
    return antNarkotiskeResepter;
  }



  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
    if (legemiddel instanceof Narkotisk) {
      antNarkotiskeResepter++;
    }
     HvitResept res = new HvitResept(legemiddel,this,pasient,reit);
     utskrevedeResepter.leggTil(res);
     pasient.leggTilResept(res);

     return res;

 }
 public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
   if (legemiddel instanceof Narkotisk) {
     antNarkotiskeResepter++;
   }
    MilitaerResept res = new MilitaerResept(legemiddel,this,pasient,reit);
    utskrevedeResepter.leggTil(res);
    pasient.leggTilResept(res);

    return res;

}

public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
  if (legemiddel instanceof Narkotisk) {
    antNarkotiskeResepter++;
  }
   BlaaResept res = new BlaaResept(legemiddel,this,pasient,reit);
   utskrevedeResepter.leggTil(res);
   pasient.leggTilResept(res);

   return res;

}

public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
  if (legemiddel instanceof Narkotisk) {
    antNarkotiskeResepter++;
  }
   PResept res = new PResept(legemiddel,this,pasient);
   utskrevedeResepter.leggTil(res);
   pasient.leggTilResept(res);

   return res;

}




  @Override
  public String toString(){
    return "id: "+ id+ " navn: " + navn + " kontrollID: " + kontrollID;
  }

}
