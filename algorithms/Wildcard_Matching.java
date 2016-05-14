/*Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/
package algorithms;
public class Wildcard_Matching {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        if (pl == 0) {
            if (sl == 0)
                return true;
            else
                return false;
        }
        int plr = p.replace("*", "").length();
        if (plr > sl) {
            return false;
        }

        boolean[][] dp = new boolean[pl + 1][sl + 1];
        dp[0][0] = true;
        for (int i = 1; i < sl + 1; i++) {
            dp[0][i] = false;
        }

        for (int j = 1; j < pl + 1; j++) {
            dp[j][0] = (p.charAt(j - 1) == '*') && dp[j - 1][0];
        }
        for (int i = 1; i < pl + 1; i++) {
            for (int j = 1; j < sl + 1; j++) {
                if ((p.charAt(i - 1) == '?' || p.charAt(i - 1) == s.charAt(j - 1)) && dp[i - 1][j - 1]) {
                    dp[i][j] = true;
                } else {

                    if (p.charAt(i - 1) == '*' &&
                            (dp[i][j - 1] /*match more than 1 char*/ ||
                            dp[i - 1][j - 1] /*match 1 char*/ ||
                            dp[i - 1][j]) /*match 0 char*/) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[pl][sl];
    }
}