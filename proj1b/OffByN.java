public class OffByN implements CharacterComparator{
    private int diff;
    public OffByN(int N){
        diff = N;
    }

    @Override
    public boolean equalChars(char x,char y){
        int charDiff = x - y ;
        if (Math.abs(charDiff) == diff) {
            return true;
        }
        return false;
    }
}
