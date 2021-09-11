class BlaaResept extends Resept{

  public BlaaResept( Legemiddel lm, Lege l, Pasient p, int r){
    super(lm,l,p,r);
  }

  @Override
  public String farge(){
    return "Blaa";
  }

  @Override
  public double prisAaBetale(){
    return (double) Math.round(hentLegemiddel().hentPris()*0.25);
  }

  @Override
  public String toString(){
    return "id: " + ID + " legemiddel: " + legemiddel.hentNavn() + " lege: " + lege + " pasient: "+ pasient + " reit: " + reit + " farge: " + farge() +" Resept-Pris: " + prisAaBetale();
  }




}
