class Solution {
    public int verificarDif(String s, int k) {
        int i = k,
            res = 0,
            I = 1,
            V = 5,
            X = 10,
            L = 50,
            C = 100,
            D = 500,
            M = 1000;
        boolean fim = false;

        while (!fim) {
            if (s.charAt(i) != null && s.charAt(i) < s.charAt(k)) {
                if (s.charAt(i) == 'I') {
                    res += I;
                } 
                else if (s.charAt(i) == 'V') {
                    res += V;
                }
                else if (s.charAt(i) == 'X') {
                    res += X;
                }
                else if (s.charAt(i) == 'L') {
                    res += L;
                }
                else if (s.charAt(i) == 'C') {
                    res += C;
                }
                else if (s.charAt(i) == 'D') {
                    res += D;
                }
                else if (s.charAt(i) == 'M') {
                    res += M;
                }
                i--;
            }
            else {
                fim = true;
            }
        }

        return res;
    }

    public int romanToInt(String s) {
        int res = 0,
            tmp = 0,
            I = 1,
            V = 5,
            X = 10,
            L = 50,
            C = 100,
            D = 500,
            M = 1000;
        if (s.length() >= 1 || s.length() <= 15) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'I') {
                    res += I;
                } 
                else if (s.charAt(i) == 'V') {
                    res += V;
                    if (s.charAt(i-1) < 'V') {
                        res -= verificarDif(s, i-1, V);
                    } 
                }
                else if (s.charAt(i) == 'X') {
                    res += 10;
                }
                else if (s.charAt(i) == 'L') {
                    res += 50;
                }
                else if (s.charAt(i) == 'C') {
                    res += 100;
                }
                else if (s.charAt(i) == 'D') {
                    res += 500;
                }
                else if (s.charAt(i) == 'M') {
                    res += 1000;
                }
            }
        }

        return res;
    }
}
