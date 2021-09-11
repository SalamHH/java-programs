class PResept extends HvitResept{

  public PResept(Legemiddel lm, Lege l, Pasient p){
    super(lm, l, p, 3);
  }

  @Override
  public double prisAaBetale(){
    if (hentLegemiddel().hentPris()>=108) {
      return (hentLegemiddel().hentPris()-108);
    }
    else {
      return (0);
    }

  }

  @Override
  public String toString(){
    return "id: " + ID + " legemiddel: " + legemiddel.hentNavn() + " lege: " + lege + " pasient: "+ pasient + " reit: " + reit + " farge: " + farge() +" Resept-Pris: " + prisAaBetale();
  }





}
