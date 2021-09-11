class MilitaerResept extends HvitResept{

  public MilitaerResept(Legemiddel lm, Lege l, Pasient p, int r){
    super(lm, l, p, r);

  }
  @Override
  public double prisAaBetale(){
    return (hentLegemiddel().hentPris()*0);
  }

  @Override
  public String toString(){
    return "id: " + ID + " legemiddel: " + legemiddel.hentNavn() + " lege: " + lege + " pasient: "+ pasient + " reit: " + reit + " farge: hvit"  +" Resept-Pris: " + prisAaBetale();
  }



}
