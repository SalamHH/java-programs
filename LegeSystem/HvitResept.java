class HvitResept extends Resept{

  public HvitResept( Legemiddel lm, Lege l, Pasient p, int r){
    super(lm,l,p,r);
  }

  @Override
  public String farge(){
    return "Hvit";
  }

  @Override
  public double prisAaBetale(){
    return hentLegemiddel().hentPris();
  }

  @Override
  public String toString(){
    return "id: " + ID + " legemiddel: " + legemiddel.hentNavn() + " lege: " + lege + " pasient: "+ pasient + " reit: " + reit + " farge: " + farge() +" Resept-Pris: " + prisAaBetale();
  }


}
