class TestResepter{
  public static void main(String[] args) {
    Vanlig vanligLegemiddel=new Vanlig("paracet", 200, 0.2);
    Lege lege=new Lege("ola");
    Pasient pasient=new Pasient("bjørn", "0340412");

    HvitResept hvit=new HvitResept(vanligLegemiddel, lege,pasient, 4);
    MilitaerResept militaer=new MilitaerResept(vanligLegemiddel, lege,pasient, 2);
    PResept p=new PResept(vanligLegemiddel, lege, pasient);
    BlaaResept blaa=new BlaaResept(vanligLegemiddel, lege,pasient, 1);


    System.out.println("TESTER HVIT RESEPT");
    boolean hvitTestId=testReseptID(hvit, 1);
    System.out.println(hvitTestId);
    boolean hvitTestLegemiddel=testReseptLegemiddel(hvit, vanligLegemiddel);
    System.out.println(hvitTestLegemiddel);
    boolean hvitTestLege=testReseptLege(hvit, lege);
    System.out.println(hvitTestLege);
    boolean hvitTestPasientid=testReseptPasient(hvit, pasient);
    System.out.println(hvitTestPasientid);
    boolean hvitTestReit=testReseptReit(hvit, 4);
    System.out.println(hvitTestReit);
    boolean hvitTestBruk=testReseptBruk(hvit);
    System.out.println(hvitTestBruk);
    System.out.println(hvit.hentReit());

    boolean hvitTestFarge=testReseptFarge(hvit, "Hvit");
    System.out.println(hvitTestFarge);
    boolean hvitTestPris=testReseptPris(hvit, 100);
    System.out.println(hvitTestPris);


    System.out.println(hvit.toString());


    System.out.println("\nTESTER MILITAER RESEPT");
    boolean militaerTestPris=testReseptPris(militaer, 0);
    System.out.println(militaerTestPris);
    boolean militaerTestFarge=testReseptFarge(militaer, "Hvit");
    System.out.println(militaerTestFarge);

    System.out.println(militaer.toString());




    System.out.println("\nTESTER PRESEPT");
    boolean pTestReit=testReseptReit(p, 3);
    System.out.println(pTestReit);
    p.bruk();
    System.out.println(p.hentReit());
    boolean pReseptPris=testReseptPris(p,92);
    System.out.println(pReseptPris);

    System.out.println(p.toString());



    System.out.println("\nTESTET BLAA RESEPT");
    boolean blaaTestpris=testReseptPris(blaa,50);
    System.out.println(blaaTestpris);
    boolean blaaTestFarge=testReseptFarge(blaa,"Blaa");
    System.out.println(blaaTestFarge);

    System.out.println(blaa.toString());







  }
  public static boolean testReseptID(Resept r, int forventetID ){
    if (r.hentID()==forventetID) {
      return true;
    }
    else{
      return false;
    }
  }

  public static boolean testReseptLegemiddel(Resept r, Legemiddel legemiddel){
    if (r.hentLegemiddel()==legemiddel) {
      return true;
    }
    else{
      return false;
    }
  }

  public static boolean testReseptLege(Resept r, Lege lege){
    if (r.hentLege()==lege) {
      return true;
    }
    else{
      return false;
    }
  }

  public static boolean testReseptPasient(Resept r, Pasient p){
    if (r.hentPasient()==p) {
      return true;
    }
    else{
      return false;
    }
  }

  public static boolean testReseptReit(Resept r, int Reit){
    if (r.hentReit()==Reit) {
      return true;
    }
    else{
      return false;
    }
  }

  public static boolean testReseptBruk(Resept r){
    if (r.bruk()) {
      return true;
    }
    else {
      return false;
    }
  }

  public static boolean testReseptFarge(Resept r, String farge){
    if (r.farge()==farge) {
      return true;
    }
    else {
      return false;
    }
  }

  public static boolean testReseptPris(Resept r, int pris){
    if (r.prisAaBetale()==pris) {
      return true;
    }
    else {
      return false;
    }
  }







}
