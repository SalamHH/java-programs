import java.io.File;
import java.io.FileNotFoundException;

class TestLabyrint {
  public static void main(String[] args) {
    try {
    File f = new File("3.in");
    Labyrint l = null;
    l = l.lesFraFil(f);
    System.out.println(l);


		Liste<String> utveier = l.finnUtveiFra(5, 3);
		if (utveier.stoerrelse() != 0) {
				for (String s : utveier) {
						System.out.println(s);
				}
		} else {
				System.out.println("Ingen utveier.");
		}



  }
		catch (FileNotFoundException e) {
    System.out.println("Fant ikke fil");
  }
  }
}
