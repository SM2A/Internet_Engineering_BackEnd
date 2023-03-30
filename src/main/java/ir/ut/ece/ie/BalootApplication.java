package ir.ut.ece.ie;

import ir.ut.ece.ie.util.DataInitializer;

import java.io.IOException;

public class BalootApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        DataInitializer.loadData("http://5.253.25.110:5000");
    }
}
