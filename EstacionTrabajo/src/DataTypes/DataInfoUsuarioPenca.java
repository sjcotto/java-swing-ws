package DataTypes;

public class DataInfoUsuarioPenca implements Comparable{

    private String nick;
    private int puntos;

    public DataInfoUsuarioPenca(){}
    public DataInfoUsuarioPenca(String nick,int pts) {
        this.nick = nick;
        puntos=pts;
     }

    
    public int getPuntos(){
        return puntos;
    }
    public String getNick(){
        return nick;
    }

     @Override
        public int compareTo (Object i){
             DataInfoUsuarioPenca info = (DataInfoUsuarioPenca) i;
             if (this.puntos> info.getPuntos()){
                return -1;
             }
             if (this.puntos< info.getPuntos()){
                return 1;
             }
             return 0;
         }

      public void setPuntos(int pts){
          puntos=pts;
      }

      public void setNick(String n){
          nick=n;
    }
    
}
