package DataTypes;

public class DataCriterio {
	private TipoCriterio criterio1;
	private TipoCriterio criterio2;
	private TipoCriterio criterio3;

	public DataCriterio(TipoCriterio c1, TipoCriterio c2,TipoCriterio c3) {
		
                criterio1=c1;
		criterio1=c2;
		criterio1=c3;
		
	}
	public TipoCriterio getCriterio1(){
		return criterio1;
	}
	public TipoCriterio getCriterio2(){
		return criterio2;
	}
	public TipoCriterio getCriterio3(){
		return criterio3;
	}

        public void setCriterio1(TipoCriterio tc1){
        criterio1=tc1;
        }

        public void setCriterio2(TipoCriterio tc2){
        criterio2=tc2;
        }

        public void setCriterio3(TipoCriterio tc3){
        criterio3=tc3;
        }
}
