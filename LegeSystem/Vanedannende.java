class Vanedannende extends Legemiddel{
  int styrke;

  public Vanedannende(String navn, double pris, double virkestoff, int s){
    super(navn, pris, virkestoff);
    styrke=s;
  }

  public int hentVanedannendeStyrke(){
    return styrke;
  }
  @Override
  public String toString(){
    return "ID: "+ ID+ " navn: " + navn +" pris:" + pris + " virkestoff: " + virkestoff +  " styrke: " + styrke;
  }


}
