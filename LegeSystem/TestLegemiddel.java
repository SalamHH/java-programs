class TestLegemiddel{
  public static void main(String[] args) {
    Narkotisk n=new Narkotisk("morfin", 500, 1.1, 40);
    Vanedannende vd=new Vanedannende("benzo", 200, 0.5,10);
    Vanlig vanlig=new Vanlig("paracet", 40, 0.1);




    //noen tester failer med vilje :)



    System.out.println("tester narkotisk Legemiddel: ");
    boolean nTestID=testLegemiddelID(n, 1);
    System.out.println(nTestID);
    boolean nTestNavn=testLegemiddelNavn(n, "Kokain");
    System.out.println(nTestNavn);
    boolean nTestPris=testLegemiddelpris(n, 500);
    System.out.println(nTestPris);
    boolean nTestVirkestoff=testLegemiddelvirkestoff(n, 1.1);
    System.out.println(nTestVirkestoff);
    boolean nTestNyPris=testLegemiddelNyPris(n, 30,30);
    System.out.println(nTestNyPris);
    boolean nTestStyrke=testLegemiddelStyrke(n, 420);
    System.out.println(nTestStyrke);


    System.out.println(n.toString());


    System.out.println("\ntester Vanedannende Legemiddel: ");
    boolean vdTestID=testLegemiddelID(vd, 2);
    System.out.println(vdTestID);
    boolean vdTestNavn=testLegemiddelNavn(vd, "benzo");
    System.out.println(vdTestNavn);
    boolean vdTestPris=testLegemiddelpris(vd, 500);
    System.out.println(vdTestPris);
    boolean vdTestVirkestoff=testLegemiddelvirkestoff(vd, 1.1);
    System.out.println(vdTestVirkestoff);
    boolean vdTestNyPris=testLegemiddelNyPris(vd, 30,31);
    System.out.println(vdTestNyPris);
    boolean vdTestStyrke=testLegemiddelStyrke(vd, 420);
    System.out.println(vdTestStyrke);

    System.out.println(vd.toString());


    System.out.println("\ntester vanlig Legemiddel: ");
    boolean vanligTestID=testLegemiddelID(vanlig, 3);
    System.out.println(vanligTestID);
    boolean vanligTestNavn=testLegemiddelNavn(vanlig, "paracet");
    System.out.println(vanligTestNavn);
    boolean vanligTestPris=testLegemiddelpris(vanlig, 500);
    System.out.println(vanligTestPris);
    boolean vanligTestVirkestoff=testLegemiddelvirkestoff(vanlig, 0.1);
    System.out.println(vanligTestVirkestoff);
    boolean vanligTestNyPris=testLegemiddelNyPris(vanlig, 30,30);
    System.out.println(vanligTestNyPris);

    System.out.println(vanlig.toString());







  }


    public static boolean testLegemiddelID(Legemiddel lm, int forventetId){
      if (lm.hentID()==forventetId) {
        return true;
      }
      else{
        return false;
      }
    }

    public static boolean testLegemiddelNavn(Legemiddel lm, String navn){
      if (lm.hentNavn()==navn) {
        return true;
      }
      else{
        return false;
      }
    }

    public static boolean testLegemiddelpris(Legemiddel lm, double pris){
      if (lm.hentPris()==pris) {
        return true;
      }
      else{
        return false;
      }
    }

    public static boolean testLegemiddelvirkestoff(Legemiddel lm, double virkestoff){
      if (lm.hentVirkestoff()==virkestoff) {
        return true;
      }
      else{
        return false;
      }

    }


    public static boolean testLegemiddelNyPris(Legemiddel lm, double nypris, int forventetpris){
      lm.settNyPris(nypris);
      if (lm.hentPris()==forventetpris) {
        return true;
      }
      else{
        return false;
      }
    }

    public static boolean testLegemiddelStyrke(Narkotisk nlm, int styrke){
      if (nlm.hentNarkotiskStyrke()==styrke) {
        return true;
      }
      else{
        return false;
      }
    }

    public static boolean testLegemiddelStyrke(Vanedannende vlm, int styrke){
      if (vlm.hentVanedannendeStyrke()==styrke) {
        return true;
      }
      else{
        return false;
      }
    }

}
