class SortertLenkeliste <T extends Comparable<T>> extends Lenkeliste<T>{





  @Override
  public void leggTil( T x){
    Node nyNode=new Node(x);
    Node peker=start;
    int i=0;
    if(stoerrelse()== 0){
      super.leggTil(x);
    }
    else {

      while (peker!=null && (x.compareTo(peker.data)>0)) {
        i++;
        peker=peker.neste;

      }

      super.leggTil(i,x);



    }

  }

  @Override
  public T fjern(){

    return super.fjern(stoerrelse()-1);
  }



  @Override
  public void sett(int pos, T x) throws UnsupportedOperationException {
      throw new UnsupportedOperationException("Du kan ikke sette noe her");
  }

  @Override
  public void leggTil(int pos, T x)  throws UnsupportedOperationException{
      throw new UnsupportedOperationException("Du kan ikke sette noe her");
  }





}
