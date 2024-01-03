package org.zmz.sb3.newfeatures.j21.str;

public class STR1 {
    public static void main(String[] args) {
        String s = STR. """
                {
                  "status":\{ args[0] },
                  "msg":"\{ args[1] }"
                }
                """ ;
        System.out.println(s);
    }
}
