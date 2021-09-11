class Pasient{
  String navn;
  String fodselnr;
  static int globalId=1;
  int id;
  Stabel<Resept> reseptListe=new Stabel<>();

  public Pasient(String n, String f){
    navn=n;
    fodselnr=f;
    id=globalId;
    globalId++;
  }

  public int hentID(){
    return id;
  }

  public String hentNavn(){
    return navn;
  }

  public void leggTilResept(Resept r){
    reseptListe.leggPaa(r);
  }

  public int hentantNarkResP(){
    int narkTeller=0;
    for (Resept r: reseptListe ) {
      if (r.hentLegemiddel() instanceof Narkotisk) {
        narkTeller++;
      }
    }
    return narkTeller;
  }

  public Stabel<Resept> hentReseptListe(){
    return reseptListe;
  }

  public void skrivUtListe(){
    System.out.println(navn+ ": "+ reseptListe);
  }

  public String toString(){
    return "id: " + id+ " navn: " + navn + " fodselnr: " + fodselnr;
  }


}
