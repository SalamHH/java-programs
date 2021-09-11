abstract class Resept{
  int ID;
  static int globalId=1;
  Legemiddel legemiddel;
  Lege lege;
  Pasient pasient;
  int reit;

  public Resept( Legemiddel lm, Lege l, Pasient p, int r){
    ID=globalId;
    globalId++;


    legemiddel=lm;
    lege=l;
    pasient=p;
    reit=r;
  }



  public int hentID(){
    return ID;
  }

  public Legemiddel hentLegemiddel(){
    return legemiddel;
  }

  public Lege hentLege(){
    return  lege;
  }

  public Pasient hentPasient(){
    return pasient;
  }

  public int hentReit(){
    return reit;
  }

  public boolean bruk(){
    if (reit>0) {
      reit--;
      return true;
    }
    else {
      return false;
    }
  }

  public void Bruk(){

  }

  abstract public String farge();
  abstract public double prisAaBetale();

  @Override
  public String toString(){
    return "\nid: " + ID + " legemiddel: " + legemiddel.hentNavn() + " lege: " + lege + " pasient: "+ pasient + " reit: " + reit +"\n";
  }







}
