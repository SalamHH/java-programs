import java.util.Iterator;
import java.lang.Iterable;

class Lenkeliste <T> implements Liste<T>{

  class Node{
    Node neste=null;
    T data;
    Node (T x){
      data=x;
    }
  }

  public Node start= null;


  public int stoerrelse(){
    int teller=0;
    Node peker=start;
    while (peker!=null) {
      peker=peker.neste;
      teller++;
    }
    return teller;
  }



  public void leggTil(T x){
    Node nyNode=new Node(x);

    if (start==null) {
      start=nyNode;
    }

    else {
      Node peker=start;
      while (peker.neste!=null) {
        peker=peker.neste;
      }
      peker.neste=nyNode; ///når jeg har kommet dit peker sin neste er null, så gjør jeg at den heller peker på den nye noden vi setter inn istedet for null
    }

  }


  public void leggTil(int pos, T x) throws UgyldigListeIndeks{
    Node nyNode=new Node(x);

    if( pos < 0 || (pos > stoerrelse() && start != null)|| (start == null && pos != 0) ){
           throw new UgyldigListeIndeks(pos);
       }



    else {
      Node peker=start;
      if (pos==0) {
        nyNode.neste=peker;
        start=nyNode;
      }
      else {
        for (int i=0;i<pos-1 ;i++ ) {
          peker=peker.neste;
        }
        nyNode.neste=peker.neste;
        peker.neste=nyNode;
      }

    }

  }




  public void sett(int pos, T x) throws UgyldigListeIndeks{
    Node nyNode=new Node(x);


    if (start==null || pos>this.stoerrelse()-1 || pos<0) {
      throw new UgyldigListeIndeks(pos);
    }

    else {
      Node peker=start;
      if (pos==0) {
        nyNode.neste=peker.neste;
        start=nyNode;
      }
      else {
        for (int i=0;i<pos-1 ;i++ ) {
          peker=peker.neste;
        }
        nyNode.neste=peker.neste.neste;
        peker.neste=nyNode;
      }

    }

  }





  public T hent( int pos) throws UgyldigListeIndeks{
    Node peker=start;

    if (start==null || pos>this.stoerrelse()-1 || pos<0 ) {
      throw new UgyldigListeIndeks(pos);
    }
    else {
      for (int i=0; i<pos ; i++ ) {
        peker=peker.neste;
      }
    }



    return peker.data;

  }




  public T fjern(int pos){

    Node peker=start;
    T fjern;

    if (stoerrelse()==0) {
      throw new UgyldigListeIndeks(-1);
    }
    if(pos < 0 || pos >= stoerrelse()){  //Passer på at pos fins som posisjon.
      throw new UgyldigListeIndeks(pos);
    }


    else {

      if (pos==0) {
        start=peker.neste;
        return peker.data;
      }
      else {
        for (int i=0;i<pos-1 ; i++ ) {
          peker=peker.neste;
        }
        fjern=peker.neste.data;
        peker.neste=peker.neste.neste;
        return fjern;


      }

    }




}

  public T fjern() throws UgyldigListeIndeks{
    Node peker=start;

    if (start==null) {
      throw new UgyldigListeIndeks(-1);
    }
    else {
      start=peker.neste;

    }
    return peker.data;
  }


  public Iterator<T> iterator(){
     return new LenkelisteIterator();
   }


  class LenkelisteIterator implements Iterator<T>{
    Node denne;

    public LenkelisteIterator(){
      denne=start;
    }

    @Override
    public boolean hasNext(){
      return denne!=null;

    }

    @Override
    public T next(){
      T data=denne.data;
      denne=denne.neste;
      return data;

    }


  }









  public String toString(){
    Node peker=start;
    String str=" ";
    while (peker!=null) {
      str+=peker.data + " ";
      peker=peker.neste;
    }
    return str;




  }


}
