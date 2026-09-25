class Solution {
    private String s;
    private int pos;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        pos = 0;

        Set<String> result = parseSeq();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parseSeq() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (pos < s.length() &&
               s.charAt(pos) != ',' &&
               s.charAt(pos) != '}') {

            Set<String> termSet = parseTerm();

            Set<String> newResult = new HashSet<>();

            for (String a : result) {
                for (String b : termSet) {
                    newResult.add(a + b);
                }
            }

            result = newResult;
        }

        return result;
    }

    private Set<String> parseTerm() {
        if (s.charAt(pos) == '{') {
            pos++; // consume '{'

            Set<String> union = new HashSet<>();

            while (true) {
                Set<String> seqSet = parseSeq();
                union.addAll(seqSet);

                if (s.charAt(pos) == ',') {
                    pos++; // consume ','
                } 
                else if (s.charAt(pos) == '}') {
                    pos++; // consume '}'
                    break;
                }
            }

            return union;

        } else {
            char c = s.charAt(pos);
            pos++;

            Set<String> singleton = new HashSet<>();
            singleton.add(String.valueOf(c));

            return singleton;
        }
    }
}