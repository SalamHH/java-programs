class Vanlig extends Legemiddel{

  public Vanlig(String navn, double pris, double virkestoff){
    super(navn, pris, virkestoff);
  }


  @Override
  public String toString(){
    return "ID: "+ ID+ " navn: " + navn +" pris:" + pris + " virkestoff: "+ virkestoff;
  }



}
