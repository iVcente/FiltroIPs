// https://blog.caelum.com.br/ordenando-colecoes-com-comparable-e-comparator/
// https://www.journaldev.com/780/comparable-and-comparator-in-java-example

public class Intervalo implements Comparable<Intervalo>{

    protected int min;
    protected int max;

    public Intervalo(int min, int max){
        this.min=min;
        this.max=max;
    }

    public String toString(){
        return min + " - " + max;

    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public int compareTo(Intervalo outroIntervalo) {
        if (this.min < outroIntervalo.min)
            return -1;
        
        if (this.min > outroIntervalo.min)
            return 1;
        
        return 0;
    }

}


