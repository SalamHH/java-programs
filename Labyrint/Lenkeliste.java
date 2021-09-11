

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Lenkeliste<T> implements Liste<T>{
    class Node{
        public T data;
        public Node neste;

        Node(T x){
            data = x;
        }
    }

    class LenkelisteIterator implements Iterator<T>{
        private int index = 0;
        @Override public boolean hasNext(){
            return (index < iBruk);
        }
        @Override public T next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            index++;
            return hent(index-1);
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new LenkelisteIterator();
    }

    Node start = null;
    protected int iBruk = 0;

    @Override
    public int stoerrelse(){
        return iBruk;
    }

    @Override
    public void leggTil(int pos, T x) throws UgyldigListeIndeks{
        if( pos < 0 || (pos > iBruk && start != null)|| (start == null && pos != 0) ){
            throw new UgyldigListeIndeks(pos);
        }

        if(start == null && pos == 0){
            leggTil(x);
            iBruk--;
        }

        else if(pos == 0){
            Node node = new Node(x);
            node.neste = start;
            start = node;
        }
        else if(pos != 0){
            Node node = new Node(x);
            Node ref = start;
            for(int i = 0; i < pos-1; i++){
                ref = ref.neste;
            }
        node.neste = ref.neste;
        ref.neste = node;
        }
        iBruk++;
    }
    @Override
    public void leggTil(T x){
        Node node = new Node(x);
        iBruk++;

        if(start == null){
            start = node;
        }
        else{
            Node ref = start;
            while(ref.neste != null){
                ref = ref.neste;
            }
            ref.neste = node;

        }
    }
    @Override
    public void sett(int pos, T x) throws UgyldigListeIndeks{
        if(pos < 0|| pos >= iBruk){
            throw new UgyldigListeIndeks(pos);
        }
        Node node = new Node(x);

        if(pos == 0){
            node.neste = start.neste;
            start = node;
        }

        if(pos != 0){
            Node ref = start;
            for(int i = 0; i < pos-1; i++){
                ref = ref.neste;
            }
            node.neste = ref.neste.neste;
            ref.neste = node;
        }
    }
    @Override
    public T hent(int pos)throws UgyldigListeIndeks{
        if(start == null || pos < 0|| pos >= iBruk){
            throw new UgyldigListeIndeks(pos);
        }
            Node ref = start;
            for(int i = 0; i < pos; i++){
                ref = ref.neste;
            }

        return ref.data;
    }
    @Override
    public T fjern()throws UgyldigListeIndeks{
        if(start == null){
            throw new UgyldigListeIndeks(-1);
        }
        T hold = start.data;
        start = start.neste;
        iBruk--;
        return hold;
    }
    @Override
    public T fjern(int pos) throws UgyldigListeIndeks{
        if(start == null || pos < 0 || pos >= iBruk ){
            throw new UgyldigListeIndeks(pos);
        }
        T hold = hent(pos);
        if(pos == 0){
            start = start.neste;
        }
        else if(pos != 0){
            Node ref = start;
            for(int i = 0; i < pos-1; i++){
                ref = ref.neste;
            }
            if(pos == iBruk-1){
                ref.neste = null;
            }
            else{
                ref.neste = ref.neste.neste;
            }
        }
        iBruk--;
        return hold;
    }

  public static void main(String[] args) {  //testprogram
      Lenkeliste<String> ll = new Lenkeliste<>();
      ll.leggTil(". ");
      ll.leggTil("...");
      for(String str : ll){
          System.out.print(str);
      }
  }
}
