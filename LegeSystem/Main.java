class Main{
  public static void main(String[] args) throws UlovligUtskrift {
    Legesystem ls=new Legesystem();

    ls.lesFraFil("data.txt");
    ls.kommandoLoekke();
  }
}
