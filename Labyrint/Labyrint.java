import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Labyrint {

  private Rute[][] labyrint;
  private ArrayList<ArrayList<Tuppel>> utveier;

  public Labyrint(File fil) throws FileNotFoundException {
    labyrint = lesFraFil(fil);
    utveier = new ArrayList<>();
    Tester.print("Slik laybyrint ble laget: \n" + this + "\n");
  }

  private Rute[][] lesFraFil(File fil) throws FileNotFoundException{
    Rute[][] labyrint;
    Scanner sc;
    int antKolonner, antRader, teller;
    Rute[][][] naboer;
    char[] tegn;
        boolean harHoyre, harVenstre, harOver, harUnder;
    //åpne fil
    sc = new Scanner(fil);
    //les stoerrelse
    antRader = sc.nextInt();
    antKolonner = sc.nextInt();
    sc.nextLine(); //next-metodene tar ikke med linjeskiftet
    labyrint = new Rute[antKolonner][antRader];
    naboer = new Rute[antKolonner][antRader][4];
    //populer rutenett
    Tester.print("Labyrint sin inndata:");
    for (int y = 0; y < antRader; y++) {
      tegn = sc.nextLine().toCharArray();
      Tester.print(String.valueOf(tegn));
      for (int x = 0; x < antKolonner; x++) {
        if (tegn[x] == '#') {
          labyrint[x][y] = new SortRute(x,y,this);
        } else if ((x == 0 || x == antKolonner-1
        || y == 0 || y == antRader - 1) && tegn[x] == '.') {
          labyrint[x][y] = new Aapning(x,y,this);
        } else if (tegn[x] == '.') {
          labyrint[x][y] = new HvitRute(x,y,this);
        }
      }
    }
    Tester.print("\n");
    //finner naboer, 0-3 er hoyre, under, venstre, over (som en klokke)
    for (int x = 0; x < antKolonner; x++) {
      if (x == 0) {harVenstre = false;} else {harVenstre = true;}
      if (x == antKolonner-1) {harHoyre = false;} else {harHoyre = true;}
      for (int y = 0; y < antRader; y++) {
        if (y == 0) {harOver = false;} else {harOver = true;}
        if (y == antRader-1) {harUnder = false;} else {harUnder = true;}
        if (harHoyre) naboer[x][y][0] = labyrint[x+1][y];
        if (harUnder) naboer[x][y][1] = labyrint[x][y+1];
        if (harVenstre) naboer[x][y][2] = labyrint[x-1][y];
        if (harOver) naboer[x][y][3] = labyrint[x][y-1];
        labyrint[x][y].giNaboer(naboer[x][y]);
      }
    }
    return labyrint;
  }

  public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int kol, int rad) {
    utveier.clear();
    labyrint[kol][rad].finnUtvei();
    return utveier;
  }

  public void leggTilUtvei(ArrayList<Tuppel> utvei) {
    utveier.add(utvei);
  }

  @Override
  public String toString() {
    String str = "";
    //merk: vi må printe rad for rad, og y er radene
    //for hver rad
    for (int y = 0; y < labyrint[0].length; y++) {
      //for hver kolonne på raden, legg til
      for (int x = 0; x < labyrint.length; x++) {
        str += labyrint[x][y].tilTegn();
      }
      str += "\n";
    }
    return str;
  }

  //for testing
  public Rute[][] hentLabyrint() {
    return labyrint;
  }

}


/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint{
    private boolean utskrift = true;
    private static Rute ruter[][];
    public static int labKol, labRad;
    Liste<String> utvei;

    private Labyrint(Rute[][] ruteArr, int kolonner, int rader){
        ruter = ruteArr; labKol = kolonner; labRad = rader;
        for(Rute[] enRad: ruter){
            for(Rute rute : enRad){
                rute.settLabyrint(this);
                rute.settNabo();
            }
        }
    }

    public void utskrift(){
        utskrift = !utskrift;
    }

    public boolean hentUtskrift(){
        return utskrift;
    }

    public Rute[][] hentLabyrint(){return ruter;}

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException{
        Scanner sctmp = new Scanner(fil);
        String[] info = sctmp.nextLine().split(" ");
        int lestRader = Integer.parseInt(info[0]);
        int lestKol = Integer.parseInt(info[1]);
        Rute maze[][] = new Rute[lestRader][lestKol];
        int m = 0;
        while(sctmp.hasNextLine()){
            int n = 0;
            String s = sctmp.nextLine();
            for(char ch : s.toCharArray()){
                if(ch == '.' && ((m == 0 || n == 0) || (m == lestRader-1 || n == lestKol-1)))
                maze[m][n] = new Aapning(m,n);
                else if(ch == '#')
                maze[m][n] = new SortRute(m,n);
                else if(ch == '.')
                maze[m][n] = new HvitRute(m,n);
                n++;
            }
            m++;
        }
        sctmp.close();
        return new Labyrint(maze, lestKol, lestRader);
    }

    public String toString(){
        String retur = "";
        for(Rute[] rarr : ruter){
            retur += System.lineSeparator();
            for(Rute rute : rarr){
                retur += rute.toString() + " ";
            }
        }
        return retur;
    }

    public Liste<String> finnUtveiFra(int kolnr, int radnr){
        utvei = new Lenkeliste<String>();
        Rute start = ruter[radnr][kolnr];
        start.finnUtvei();
        return utvei;
    }
}*/
